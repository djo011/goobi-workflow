package io.goobi.workflow.api.vocabulary.jsfwrapper;

import io.goobi.vocabulary.exchange.FieldDefinition;
import io.goobi.vocabulary.exchange.FieldInstance;
import io.goobi.vocabulary.exchange.TranslationInstance;
import io.goobi.vocabulary.exchange.VocabularyRecord;
import io.goobi.vocabulary.exchange.VocabularySchema;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JSFVocabularyRecord extends VocabularyRecord {
    private VocabularySchema schema;
    private Map<Long, FieldDefinition> fieldDefinitions;

    @Getter
    private String mainValue;
    @Getter
    private List<String> titleValues;
    @Setter
    private String language;
    @Getter
    private List<JSFFieldInstance> jsfFields;
    @Getter
    @Setter
    private boolean expanded;
    @Getter
    @Setter
    private boolean shown;
    @Getter
    @Setter
    private int level;
    @Getter
    private List<String> parents;

    public JSFVocabularyRecord() {
    }

    public JSFVocabularyRecord(VocabularyRecord legacy) {
        setId(legacy.getId());
        setVocabularyId(legacy.getVocabularyId());
        setFields(legacy.getFields());
        setParentId(legacy.getParentId());
        setChildren(legacy.getChildren());
    }

    public void load(VocabularySchema schema) {
        this.schema = schema;
        this.fieldDefinitions = new HashMap<>();
        this.schema.getDefinitions().forEach(d -> {
            this.fieldDefinitions.put(d.getId(), d);
        });
//        this.jsfFields = getFields().stream()
//                .map(this::transform)
//                .flatMap(Collection::stream)
//                .collect(Collectors.toList());
        this.parents = new LinkedList<>();
        this.titleValues = schema.getDefinitions().stream()
                .sorted(Comparator.comparingLong(FieldDefinition::getId))
                .filter(d -> Boolean.TRUE.equals(d.getTitleField()))
                .map(this::getFieldValue)
                .collect(Collectors.toList());
        this.mainValue = schema.getDefinitions().stream()
                .filter(d -> Boolean.TRUE.equals(d.getMainEntry()))
                .map(this::getFieldValue)
                .findAny().orElseThrow(() -> new RuntimeException("Record has no main value defined"));

        // Sort languages to show same order for in record editor
        this.getFields().forEach(f -> f.getValues().forEach(v -> v.getTranslations().sort((t1, t2) -> t1.getLanguage().compareToIgnoreCase(t2.getLanguage()))));
    }

    public void addParent(String parent) {
        parents.add(0, parent);
    }

//    private List<JSFFieldInstance> transform(FieldInstance field) {
//        // TODO: Check if this works, transform back when saving
//        Map<String, List<String>> languageValues = new HashMap<>();
//        field.getValues().forEach(v -> {
//            v.getTranslations().forEach((lang, value) -> {
//                if (!languageValues.containsKey(lang)) {
//                    languageValues.put(lang, new ArrayList<>());
//                }
//                languageValues.get(lang).add(value);
//            });
//        });
//        List<JSFFieldInstance> results = new LinkedList<>();
//        languageValues.forEach((lang, values) -> {
//            JSFFieldInstance result = new JSFFieldInstance();
//            result.setId(field.getId());
//            result.setRecordId(field.getRecordId());
//            result.setDefinitionId(field.getDefinitionId());
//            result.setLanguage(lang);
//            result.setValue(String.join("|", values));
//            results.add(result);
//        });
//        return results;
//    }

    private String getFieldValue(FieldDefinition definition) {
        // TODO: Decide which language to show
        return getFields().stream()
                .filter(f -> f.getDefinitionId().equals(definition.getId()))
                .map(this::extractValue)
                .findFirst()
                .orElse("");
    }

    private String extractValue(FieldInstance field) {
        return field.getValues().stream()
                .map(
                        v -> v.getTranslations().stream()
                                .map(TranslationInstance::getValue)
                                .findFirst()
                                .orElse("")
                ).collect(Collectors.joining("|"));
    }

    public List<FieldInstance> sortedFields() {
        return getFields().stream()
                .sorted(Comparator.comparingLong(FieldInstance::getDefinitionId))
                .collect(Collectors.toList());
    }
}
