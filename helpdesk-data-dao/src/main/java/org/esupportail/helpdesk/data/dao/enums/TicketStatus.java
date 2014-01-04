package org.esupportail.helpdesk.data.dao.enums;

public enum TicketStatus {

    /** HTicket status. */
    FREE("FREE"),

    /** HTicket status. */
    INPROGRESS("INPROGRESS"),

    /** HTicket status. */
    CANCELLED("CANCELLED"),

    /** HTicket status. */
    INCOMPLETE("INCOMPLETE"),

    /** HTicket status. */
    POSTPONED("POSTPONED"),

    /** HTicket status. */
    CLOSED("CLOSED"),

    /** HTicket status. */
    APPROVED("APPROVED"),

    /** HTicket status. */
    EXPIRED("EXPIRED"),

    /** HTicket status. */
    CONNECTED_TO_TICKET("CONNECTED_TO_TICKET"),

    /** HTicket status. */
    CONNECTED_TO_FAQ("CONNECTED_TO_FAQ"),

    /** HTicket status. */
    REFUSED("REFUSED"),

    /** HTicket status. */
    ARCHIVED("ARCHIVED"),

    /** HTicket status. */
    UNDEF("?");
    
    private final String value;
    
    private TicketStatus(final String value) {
        this.value = value;
    }
    
    public String value() {
        return value;
    }
}
