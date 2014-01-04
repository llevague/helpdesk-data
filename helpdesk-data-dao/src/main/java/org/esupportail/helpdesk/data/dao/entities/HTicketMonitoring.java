package org.esupportail.helpdesk.data.dao.entities;

import javax.persistence.*;

/**
 * A class to store ticket monitoring by users.
 */
@Entity
@Table(name = "h_tick_moni")
public class HTicketMonitoring {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Represent the ticket in the user-ticket relation.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "tick_id", nullable = false)
    private HTicket ticket;

    /**
     * Represent the user in the user-ticket relation.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    private HUser user;

    private HTicketMonitoring() {
    }

    /**
     * @param user   the user
     * @param ticket the ticket
     */
    private HTicketMonitoring(final HUser user, final HTicket ticket) {
        this.user = user;
        this.ticket = ticket;
    }

    /**
     * @param user
     * @param ticket
     * @return
     */
    public static HTicketMonitoring ticketMonitoring(final HUser user, final HTicket ticket) {
        return new HTicketMonitoring(user, ticket);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HTicket getTicket() {
        return ticket;
    }

    public void setTicket(HTicket ticket) {
        this.ticket = ticket;
    }

    public HUser getUser() {
        return user;
    }

    public void setUser(HUser user) {
        this.user = user;
    }
}