package org.goobi.beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.sub.goobi.AbstractTest;

public class UserTest extends AbstractTest {

    @Test
    public void testConstructor() {
        User user = new User();
        assertNotNull(user);
    }

    @Test
    public void testId() throws Exception {
        User user = new User();
        assertNull(user.getId());
        user.setId(1);
        assertEquals(1, user.getId().intValue());
    }

    @Test
    public void testVorname() throws Exception {
        User user = new User();
        assertNull(user.getVorname());
        user.setVorname("fixture");
        assertEquals("fixture", user.getVorname());

    }

    @Test
    public void testNachname() throws Exception {
        User user = new User();
        assertNull(user.getNachname());
        user.setNachname("fixture");
        assertEquals("fixture", user.getNachname());
    }

    @Test
    public void testNachVorname() throws Exception {
        User user = new User();
        user.setVorname("");
        user.setNachname("");
        assertEquals(", ", user.getNachVorname());

        user.setVorname("first");
        user.setNachname("last");
        assertEquals("last, first", user.getNachVorname());
    }

    @Test
    public void testLogin() throws Exception {
        User user = new User();
        assertNull(user.getLogin());
        user.setLogin("fixture");
        assertEquals("fixture", user.getLogin());
    }

    @Test
    public void testLdaplogin() throws Exception {
        User user = new User();
        assertNull(user.getLdaplogin());
        user.setLdaplogin("fixture");
        assertEquals("fixture", user.getLdaplogin());
    }

    @Test
    public void testPasswort() throws Exception {
        User user = new User();
        assertNull(user.getPasswort());
        user.setPasswort("fixture");
        assertEquals("fixture", user.getPasswort());
    }

    @Test
    public void testStandort() throws Exception {
        User user = new User();
        assertNull(user.getStandort());
        user.setStandort("fixture");
        assertEquals("fixture", user.getStandort());
    }

    @Test
    public void testTabellengroesse() throws Exception {
        User user = new User();
        assertEquals(10, user.getTabellengroesse().intValue());

        user.setTabellengroesse(null);
        assertEquals(10, user.getTabellengroesse().intValue());

        user.setTabellengroesse(20);
        assertEquals(20, user.getTabellengroesse().intValue());
    }

    @Test
    public void testSessiontimeout() throws Exception {
        User user = new User();
        assertEquals(14400, user.getSessiontimeout().intValue());

        user.setSessiontimeout(null);
        assertEquals(14400, user.getSessiontimeout().intValue());

        user.setSessiontimeout(20);
        assertEquals(20, user.getSessiontimeout().intValue());
    }

    @Test
    public void testMetadatenSprache() throws Exception {
        User user = new User();
        assertNull(user.getMetadatenSprache());
        user.setMetadatenSprache("fixture");
        assertEquals("fixture", user.getMetadatenSprache());
    }

    @Test
    public void testBenutzergruppen() throws Exception {
        User user = new User();
        List<Usergroup> ugList = new ArrayList<>();
        Usergroup grp = new Usergroup();
        ugList.add(grp);
        user.setBenutzergruppen(ugList);
        assertEquals(1, user.getBenutzergruppen().size());
        assertEquals(1, user.getBenutzergruppenSize());
    }

    @Test
    public void testSchritte() throws Exception {
        User user = new User();
        List<Step> stepList = new ArrayList<>();
        Step step = new Step();
        stepList.add(step);
        user.setSchritte(stepList);
        assertEquals(1, user.getSchritte().size());
        assertEquals(1, user.getSchritteSize());
    }

    @Test
    public void testBearbeitungsschritte() throws Exception {
        User user = new User();
        List<Step> stepList = new ArrayList<>();
        Step step = new Step();
        stepList.add(step);
        user.setBearbeitungsschritte(stepList);
        assertEquals(1, user.getBearbeitungsschritte().size());
        assertEquals(1, user.getBearbeitungsschritteSize());
    }

    @Test
    public void testProjekte() throws Exception {
        User user = new User();
        List<Project> list = new ArrayList<>();
        Project p = new Project();
        list.add(p);
        user.setProjekte(list);
        assertEquals(1, user.getProjekte().size());
        assertEquals(1, user.getProjekteSize());
    }

    @Test
    public void testEigenschaften() throws Exception {
        User user = new User();
        List<UserProperty> list = new ArrayList<>();
        UserProperty p = new UserProperty();
        list.add(p);
        user.setEigenschaften(list);
        assertEquals(1, user.getEigenschaften().size());
        assertEquals(1, user.getEigenschaftenSize());
    }

    @Test
    public void testMitMassendownload() throws Exception {
        User user = new User();
        assertFalse(user.isMitMassendownload());
        user.setMitMassendownload(true);
        assertTrue(user.isMitMassendownload());
    }

    @Test
    public void testLdapGruppe() throws Exception {
        User user = new User();
        assertNull(user.getLdapGruppe());
        Ldap ldap = new Ldap();
        user.setLdapGruppe(ldap);
        assertEquals(ldap, user.getLdapGruppe());
    }

    @Test
    public void testCss() throws Exception {
        User user = new User();
        assertEquals("/css/default.css", user.getCss());
        user.setCss("fixture");
        assertEquals("fixture", user.getCss());
    }

    @Test
    public void testEmail() throws Exception {
        User user = new User();
        assertNull(user.getEmail());
        user.setEmail("fixture@example.com");
        assertEquals("fixture@example.com", user.getEmail());
    }

    @Test
    public void testShortcutPrefix() throws Exception {
        User user = new User();
        assertEquals("ctrl+shift", user.getShortcutPrefix());
        user.setShortcutPrefix("fixture");
        assertEquals("fixture", user.getShortcutPrefix());
    }

    @Test
    public void testEncryptedPassword() throws Exception {
        User user = new User();
        assertNull(user.getEncryptedPassword());
        user.setEncryptedPassword("fixture");
        assertEquals("fixture", user.getEncryptedPassword());
    }

    @Test
    public void testPasswordSalt() throws Exception {
        User user = new User();
        assertNull(user.getPasswordSalt());
        user.setPasswordSalt("fixture");
        assertEquals("fixture", user.getPasswordSalt());
    }

    @Test
    public void testDisplayDeactivatedProjects() throws Exception {
        User user = new User();
        assertFalse(user.isDisplayDeactivatedProjects());
        user.setDisplayDeactivatedProjects(true);
        assertTrue(user.isDisplayDeactivatedProjects());
    }

    @Test
    public void testDisplayFinishedProcesses() throws Exception {
        User user = new User();
        assertFalse(user.isDisplayFinishedProcesses());
        user.setDisplayFinishedProcesses(true);
        assertTrue(user.isDisplayFinishedProcesses());
    }

    @Test
    public void testDisplaySelectBoxes() throws Exception {
        User user = new User();
        assertFalse(user.isDisplaySelectBoxes());
        user.setDisplaySelectBoxes(true);
        assertTrue(user.isDisplaySelectBoxes());
    }

    @Test
    public void testDisplayIdColumn() throws Exception {
        User user = new User();
        assertFalse(user.isDisplayIdColumn());
        user.setDisplayIdColumn(true);
        assertTrue(user.isDisplayIdColumn());
    }

    @Test
    public void testDisplayBatchColumn() throws Exception {
        User user = new User();
        assertFalse(user.isDisplayBatchColumn());
        user.setDisplayBatchColumn(true);
        assertTrue(user.isDisplayBatchColumn());
    }

    @Test
    public void testDisplayProcessDateColumn() throws Exception {
        User user = new User();
        assertFalse(user.isDisplayProcessDateColumn());
        user.setDisplayProcessDateColumn(true);
        assertTrue(user.isDisplayProcessDateColumn());
    }

    @Test
    public void testDisplayLocksColumn() throws Exception {
        User user = new User();
        assertFalse(user.isDisplayLocksColumn());
        user.setDisplayLocksColumn(true);
        assertTrue(user.isDisplayLocksColumn());
    }

    @Test
    public void testDisplaySwappingColumn() throws Exception {
        User user = new User();
        assertFalse(user.isDisplaySwappingColumn());
        user.setDisplaySwappingColumn(true);
        assertTrue(user.isDisplaySwappingColumn());
    }

    @Test
    public void testDisplayModulesColumn() throws Exception {
        User user = new User();
        assertFalse(user.isDisplayModulesColumn());
        user.setDisplayModulesColumn(true);
        assertTrue(user.isDisplayModulesColumn());
    }

    @Test
    public void testDisplayMetadataColumn() throws Exception {
        User user = new User();
        assertFalse(user.isDisplayMetadataColumn());
        user.setDisplayMetadataColumn(true);
        assertTrue(user.isDisplayMetadataColumn());
    }

    @Test
    public void testDisplayThumbColumn() throws Exception {
        User user = new User();
        assertFalse(user.isDisplayThumbColumn());
        user.setDisplayThumbColumn(true);
        assertTrue(user.isDisplayThumbColumn());
    }

    @Test
    public void testDisplayGridView() throws Exception {
        User user = new User();
        assertFalse(user.isDisplayGridView());
        user.setDisplayGridView(true);
        assertTrue(user.isDisplayGridView());
    }

    @Test
    public void testDisplayRulesetColumn() throws Exception {
        User user = new User();
        assertFalse(user.isDisplayRulesetColumn());
        user.setDisplayRulesetColumn(true);
        assertTrue(user.isDisplayRulesetColumn());
    }

    @Test
    public void testDisplayAutomaticTasks() throws Exception {
        User user = new User();
        assertFalse(user.isDisplayAutomaticTasks());
        user.setDisplayAutomaticTasks(true);
        assertTrue(user.isDisplayAutomaticTasks());
    }

    @Test
    public void testHideCorrectionTasks() throws Exception {
        User user = new User();
        assertFalse(user.isHideCorrectionTasks());
        user.setHideCorrectionTasks(true);
        assertTrue(user.isHideCorrectionTasks());
    }

    @Test
    public void testDisplayOnlySelectedTasks() throws Exception {
        User user = new User();
        assertFalse(user.isDisplayOnlySelectedTasks());
        user.setDisplayOnlySelectedTasks(true);
        assertTrue(user.isDisplayOnlySelectedTasks());
    }

    @Test
    public void testDisplayOnlyOpenTasks() throws Exception {
        User user = new User();
        assertFalse(user.isDisplayOnlyOpenTasks());
        user.setDisplayOnlyOpenTasks(true);
        assertTrue(user.isDisplayOnlyOpenTasks());
    }

    @Test
    public void testDisplayOtherTasks() throws Exception {
        User user = new User();
        assertFalse(user.isDisplayOtherTasks());
        user.setDisplayOtherTasks(true);
        assertTrue(user.isDisplayOtherTasks());
    }

    @Test
    public void testMetsDisplayTitle() throws Exception {
        User user = new User();
        assertFalse(user.isMetsDisplayTitle());
        user.setMetsDisplayTitle(true);
        assertTrue(user.isMetsDisplayTitle());
    }

    @Test
    public void testMetsLinkImage() throws Exception {
        User user = new User();
        assertFalse(user.isMetsLinkImage());
        user.setMetsLinkImage(true);
        assertTrue(user.isMetsLinkImage());
    }

    @Test
    public void testMetsDisplayPageAssignments() throws Exception {
        User user = new User();
        assertFalse(user.isMetsDisplayPageAssignments());
        user.setMetsDisplayPageAssignments(true);
        assertTrue(user.isMetsDisplayPageAssignments());
    }

    @Test
    public void testMetsDisplayHierarchy() throws Exception {
        User user = new User();
        assertFalse(user.isMetsDisplayHierarchy());
        user.setMetsDisplayHierarchy(true);
        assertTrue(user.isMetsDisplayHierarchy());
    }

    @Test
    public void testMetsDisplayProcessID() throws Exception {
        User user = new User();
        assertFalse(user.isMetsDisplayProcessID());
        user.setMetsDisplayProcessID(true);
        assertTrue(user.isMetsDisplayProcessID());
    }

    @Test
    public void testMetsEditorTime() throws Exception {
        User user = new User();
        assertNull(user.getMetsEditorTime());
        user.setMetsEditorTime(10);
        assertEquals(10, user.getMetsEditorTime().intValue());
    }

    @Test
    public void testIMAGE_SIZE() throws Exception {
        assertEquals(27, User.getIMAGE_SIZE());
    }

    @Test
    public void testCustomColumns() throws Exception {
        User user = new User();
        assertNull(user.getCustomColumns());
        user.setCustomColumns("fixture");
        assertEquals("fixture", user.getCustomColumns());
    }

    @Test
    public void testCustomCss() throws Exception {
        User user = new User();
        assertNull(user.getCustomColumns());
        user.setCustomColumns("fixture");
        assertEquals("fixture", user.getCustomColumns());
    }

    @Test
    public void testMailNotificationLanguage() throws Exception {
        User user = new User();
        assertNull(user.getMailNotificationLanguage());
        user.setMailNotificationLanguage("fixture");
        assertEquals("fixture", user.getMailNotificationLanguage());
    }

    @Test
    public void testEmailConfiguration() throws Exception {

    }

    @Test
    public void testInstitution() throws Exception {

    }

    @Test
    public void testInstitutionId() throws Exception {

    }

    @Test
    public void testSuperAdmin() throws Exception {

    }

    @Test
    public void testDisplayInstitutionColumn() throws Exception {

    }

    @Test
    public void testDashboardPlugin() throws Exception {

    }

    @Test
    public void testSsoId() throws Exception {

    }

    @Test
    public void testProcessListDefaultSortField() throws Exception {

    }

    @Test
    public void testProcessListDefaultSortOrder() throws Exception {

    }

    @Test
    public void testTaskListDefaultSortingField() throws Exception {

    }

    public void testTaskListDefaultSortOrder() throws Exception {

    }

    @Test
    public void testDisplayLastEditionDate() throws Exception {

    }

    @Test
    public void testDisplayLastEditionUser() throws Exception {

    }

    @Test
    public void testDisplayLastEditionTask() throws Exception {

    }

    @Test
    public void testDashboardConfiguration() throws Exception {

    }

    @Test
    public void testUiMode() throws Exception {

    }

    @Test
    public void testStatus() throws Exception {

    }

    @Test
    public void testAvailableUiModes() throws Exception {

    }

    @Test
    public void testAdditionalData() throws Exception {

    }

}
