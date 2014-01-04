package org.esupportail.helpdesk.data.dao.enums;

public enum FaqScope {

    /** FAQ scope. */
    DEFAULT("DEFAULT"),
    /** FAQ scope. */
    ALL("ALL"),
    /** FAQ scope. */
    AUTHENTICATED("AUTHENTICATED"),
    /** FAQ scope. */
    DEPARTMENT("DEPARTMENT"),
    /** FAQ scope. */
    MANAGER("MANAGER"),
    /** FAQ scope. */
    ADMIN("ADMIN");
    
    private final String value;
    
    private FaqScope(final String value) {
        this.value = value;
    }
    
    public String value(){
        return value;
    }
}
