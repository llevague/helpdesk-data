package org.esupportail.helpdesk.data.dao.enums;

public enum ControlPanel {

    /** A constant for the status filter. */
    STATUS_FILTER_ANY("ANY"),

    /** A constant for the status filter. */
    STATUS_FILTER_OPENED("OPENED"),

    /** A constant for the status filter. */
    STATUS_FILTER_CLOSED("CLOSED"),

    /** A constant for the involvement filter. */
    MANAGER_INVOLVEMENT_FILTER_ANY("ANY"),

    /** A constant for the involvement filter. */
    MANAGER_INVOLVEMENT_FILTER_FREE("FREE"),

    /** A constant for the involvement filter. */
    MANAGER_INVOLVEMENT_FILTER_MANAGED("MANAGED"),

    /** A constant for the involvement filter. */
    MANAGER_INVOLVEMENT_FILTER_MANAGED_OR_FREE("MANAGED_OR_FREE"),

    /** A constant for the involvement filter. */
    USER_INVOLVEMENT_FILTER_ANY("ANY"),

    /** A constant for the involvement filter. */
    USER_INVOLVEMENT_FILTER_OWNER("OWNER"),

    /** A constant for the involvement filter. */
    USER_INVOLVEMENT_FILTER_INVITED("INVITED"),

    /** A constant for the involvement filter. */
    USER_INVOLVEMENT_FILTER_OWNER_OR_INVITED("OWNER_OR_INVITED"),

    /** A constant for the involvement filter. */
    USER_INVOLVEMENT_FILTER_MONITORING("MONITORING");
    
    private final String value;
    
    private ControlPanel(final String value) {
        this.value = value;
    }
    
    public String value() {
        return value;
    }
}
