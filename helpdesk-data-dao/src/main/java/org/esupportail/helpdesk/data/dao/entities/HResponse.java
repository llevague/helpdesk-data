package org.esupportail.helpdesk.data.dao.entities;

import javax.persistence.*;

import static fj.data.Option.fromString;

/**
 * The representation of a canned response.
 */
@Entity
@Table(name = "h_resp")
public class HResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The department.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "depa_id")
    private HDepartment department;

    /**
     * The user.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private HUser user;

    /**
     * The label.
     */
    @Basic
    @Column(name = "labe", nullable = false)
    private String label;

    /**
     * The message.
     */
    @Basic
    @Column(name = "mess", nullable = false)
    private String message;

    /**
     *
     */
    private HResponse() {
    }

    /**
     * @param response
     */
    private HResponse(final HResponse response) {
        this.id = response.id;
        this.user = response.user;
        this.department = response.department;
        this.label = response.label;
        this.message = response.message;
    }

    /**
     * @return
     */
    public static HResponse response() {
        return new HResponse();
    }

    /**
     * @param response
     * @return a copy of the original {@link HResponse}
     */
    public static HResponse response(final HResponse response) {
        return new HResponse(response);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HDepartment getDepartment() {
        return department;
    }

    public void setDepartment(HDepartment department) {
        this.department = department;
    }

    public HUser getUser() {
        return user;
    }

    public void setUser(HUser user) {
        this.user = user;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = fromString(message).toNull();
    }
}