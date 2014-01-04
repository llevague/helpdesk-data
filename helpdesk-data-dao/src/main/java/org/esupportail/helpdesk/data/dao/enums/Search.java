package org.esupportail.helpdesk.data.dao.enums;

public enum Search {

    /** A constant for the status filter. */
    TYPE_FILTER_ALL("ALL"),
    
    /** A constant for the status filter. */
    TYPE_FILTER_ACTIVE_TICKET_AND_FAQ("ACTIVE_TICKET_AND_FAQ"),
    
    /** A constant for the status filter. */
    TYPE_FILTER_ACTIVE_TICKET("ACTIVE_TICKET"),
    
    /** A constant for the status filter. */
    TYPE_FILTER_ARCHIVED_TICKET("ARCHIVED_TICKET"),

    /** A constant for the status filter. */
    TYPE_FILTER_TICKET("TICKET"),

    /** A constant for the status filter. */
    TYPE_FILTER_FAQ("FAQ");
    
    private final String value;
    
    private Search(final String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
