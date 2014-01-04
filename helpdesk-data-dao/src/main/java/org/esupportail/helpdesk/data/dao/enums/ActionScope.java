package org.esupportail.helpdesk.data.dao.enums;

public enum ActionScope {

    /** HAction scope value. */
    DEFAULT("DEFAULT"),

    /** HAction scope value. */
    INVITED("INVITED"),

    /** HAction scope value. */
    OWNER("OWNER"),

    /** HAction scope value. */
    MANAGER("MANAGER");
    
    private final String value;
    
    private ActionScope(final String value) {
        this.value = value;
    }
    
    public String value() {
        return value;
    }
}
