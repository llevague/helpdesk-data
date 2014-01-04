package org.esupportail.helpdesk.data.dao.enums;

public enum Monitoring {

    /** A constant for the monitoring level. */
    MONITORING_NEVER(0),

    /** A constant for the monitoring level. */
    MONITORING_CREATION(1),

    /** A constant for the monitoring level. */
    MONITORING_CREATION_OR_RELEASE(2),

    /** A constant for the monitoring level. */
    MONITORING_ALWAYS(3),

    /** A constant for the monitoring level. */
    MONITORING_CREATION_OR_FREE(4);
    
    private final Integer value;
    
    private Monitoring(final Integer value) {
        this.value = value;
    }
    
    public Integer value() {
        return value;
    }
}
