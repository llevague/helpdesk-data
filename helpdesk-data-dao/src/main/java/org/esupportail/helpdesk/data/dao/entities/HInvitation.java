package org.esupportail.helpdesk.data.dao.entities;

import javax.persistence.*;

/**
 * A class to store ticket invitations.
 */
@Entity
@Table(name = "h_invi")
public class HInvitation {

    @Id
    private long id;

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
    private HInvitation() {
    }

    /**
     * @param user
     * @param ticket
     */
    private HInvitation(final HUser user, final HTicket ticket) {
        this.user = user;
        this.ticket = ticket;
    }

    /**
     * @param user
     * @param ticket
     * @return
     */
    public static HInvitation invitation(final HUser user, final HTicket ticket) {
        return new HInvitation(user, ticket);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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