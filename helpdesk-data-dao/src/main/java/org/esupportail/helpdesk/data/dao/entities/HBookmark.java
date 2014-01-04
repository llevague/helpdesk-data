package org.esupportail.helpdesk.data.dao.entities;

import javax.persistence.*;

/**
 * This class is used to store the bookmarks.
 */
@Entity
@Table(name = "h_book")
public class HBookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The archived ticket.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "arch_tick_id")
    private HArchivedTicket archivedTicket;

    /**
     * The ticket.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "tick_id")
    private HTicket ticket;

    /**
     * The user.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    private HUser user;


    private HBookmark() {
    }

    /**
     * @param user
     */
    private HBookmark(final HUser user) {
        this.user = user;
    }

    /**
     * @param user
     * @param ticket
     */
    private HBookmark(final HUser user, final HTicket ticket) {
        this(user);
        this.ticket = ticket;
    }

    /**
     * @param user
     * @param archivedTicket
     */
    private HBookmark(final HUser user, final HArchivedTicket archivedTicket) {
        this(user);
        this.archivedTicket = archivedTicket;
    }

    /**
     * @param user
     * @param ticket
     * @return
     */
    public static HBookmark bookmark(final HUser user, final HTicket ticket) {
        return new HBookmark(user, ticket);
    }

    /**
     * @param user
     * @param archivedTicket
     * @return
     */
    public static HBookmark bookmark(final HUser user, final HArchivedTicket archivedTicket) {
        return new HBookmark(user, archivedTicket);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HArchivedTicket getArchivedTicket() {
        return archivedTicket;
    }

    public void setArchivedTicket(HArchivedTicket archivedTicket) {
        this.archivedTicket = archivedTicket;
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