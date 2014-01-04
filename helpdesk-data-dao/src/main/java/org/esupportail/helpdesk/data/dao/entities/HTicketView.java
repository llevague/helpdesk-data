package org.esupportail.helpdesk.data.dao.entities;

import java.util.*;
import javax.persistence.*;

/**
 * This class is used to store the dates when users view tickets (the last time).
 */
@Entity
@Table(name = "h_tick_view")
public class HTicketView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The date.
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false)
    private Date date;

    /**
     * The ticket.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "tick_id", nullable = false)
    private HTicket ticket;

    /**
     * The user.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    private HUser user;

    /**
     *
     */
    private HTicketView() {
    }

    /**
     * @param user   the user
     * @param ticket the ticket
     * @param date   the date
     */
    private HTicketView(final HUser user, final HTicket ticket, final Date date) {
        this.user = user;
        this.ticket = ticket;
        this.date = date;
    }

    /**
     * @param user
     * @param ticket
     * @param date
     * @return
     */
    public static HTicketView ticketView(final HUser user, final HTicket ticket, final Date date) {
        return new HTicketView(user, ticket, date);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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