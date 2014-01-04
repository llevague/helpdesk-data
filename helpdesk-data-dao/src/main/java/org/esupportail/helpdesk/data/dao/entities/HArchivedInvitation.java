package org.esupportail.helpdesk.data.dao.entities;

import javax.persistence.*;

/**
 * A class to store archived ticket invitations.
 */
@Entity
@Table(name = "h_arch_invi")
public class HArchivedInvitation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The ticket.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "arch_tick_id", nullable = false)
    private HArchivedTicket archivedTicket;

    /**
     * The user.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    private HUser user;

    /**
     *
     */
    private HArchivedInvitation() {
    }

    /**
     * @param user
     * @param archivedTicket
     */
    private HArchivedInvitation(final HUser user, final HArchivedTicket archivedTicket) {
        this.user = user;
        this.archivedTicket = archivedTicket;
    }

    /**
     * @param user
     * @param archivedTicket
     * @return an {@link HArchivedInvitation}
     */
    public static HArchivedInvitation archivedInvitation(final HUser user, final HArchivedTicket archivedTicket) {
        return new HArchivedInvitation(user, archivedTicket);
    }

    public HArchivedTicket getArchivedTicket() {
        return archivedTicket;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setArchivedTicket(HArchivedTicket archivedTicket) {
        this.archivedTicket = archivedTicket;
    }

    public HUser getUser() {
        return user;
    }

    public void setUser(HUser user) {
        this.user = user;
    }
}