package org.esupportail.helpdesk.data.dao.enums;

public enum TicketScope {

    /** ticket scope. */
    DEFAULT("DEFAULT"),

    /** ticket scope. */
    PUBLIC("PUBLIC"),

    /** ticket scope. */
    PRIVATE("PRIVATE"),

    /** ticket scope. */
    SUBJECT_ONLY("SUBJECT_ONLY"),

    /** ticket scope. */
    UNDEF("?");

    private final String value;

    private TicketScope(final String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
