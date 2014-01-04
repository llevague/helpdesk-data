package org.esupportail.helpdesk.data.dao.entities;

import javax.persistence.*;

/**
 * The representation of an alert, attached to an action.
 */
@Entity
@Table(name = "h_aler")
public class HAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The email address the alert was sent to (null if sent to a user).
     */
    @Basic
    @Column(name = "emai")
    private String email;

    /**
     * The action the alert was sent for.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "acti_id", nullable = false)
    private HAction action;

    /**
     * The user the alert was sent to (null if sent to an email address).
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private HUser user;

    /**
     *
     */
    private HAlert() {
    }

    /**
     * @param action the action
     * @param user   the user
     */
    private HAlert(final HAction action, final HUser user) {
        this.action = action;
        this.user = user;
    }

    /**
     * @param action the action
     * @param email  the email
     */
    private HAlert(final HAction action, final String email) {
        this.action = action;
        this.email = email;
    }

    /**
     * @param action the action
     * @param user   the user
     */
    public static HAlert alert(final HAction action, final HUser user) {
        return new HAlert(action, user);
    }

    /**
     * @param action the action
     * @param email  the email
     */
    public static HAlert alert(final HAction action, final String email) {
        return new HAlert(action, email);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HAction getAction() {
        return action;
    }

    public HUser getUser() {
        return user;
    }
}