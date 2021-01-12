package org.goobi.goobiScript;

import java.util.HashMap;
import java.util.List;

import org.goobi.managedbeans.LoginBean;

import de.sub.goobi.forms.SessionForm;
import de.sub.goobi.helper.Helper;

public abstract class AbstractIGoobiScript implements IGoobiScript {
    protected List<Integer> processes;
    protected HashMap<String, String> parameters;
    protected String command;
    protected String username;
    protected GoobiScriptManager gsm;
    protected long starttime;

    public AbstractIGoobiScript() {
        super();
    }

    @Override
    public boolean prepare(List<Integer> processes, String command, HashMap<String, String> parameters) {
        this.processes = processes;
        this.parameters = parameters;
        this.command = command;
        SessionForm sf = (SessionForm) Helper.getManagedBeanValue("#{SessionForm}");
        gsm = sf.getGsm();
        LoginBean login = (LoginBean) Helper.getManagedBeanValue("#{LoginForm}");
        username = login.getMyBenutzer().getNachVorname();
        starttime = System.currentTimeMillis();
        return true;
    }

    /**
     * return a parameter als boolean value. If the parameter is missing or null then false is returned.
     * 
     * @param name the name of the parameter to check
     * @return boolean of the value
     */
    public boolean getParameterAsBoolean(String name) {
        boolean value = false;
        if (parameters.get(name) != null && parameters.get(name).equals("true")){
            value = true;
        }
        return value;
    }
    
    @Override
    public void execute() {
    }

}
