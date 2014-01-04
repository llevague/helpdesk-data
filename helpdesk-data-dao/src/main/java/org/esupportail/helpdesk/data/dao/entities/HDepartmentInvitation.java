package org.esupportail.helpdesk.data.dao.entities;

import javax.persistence.*;

/**
 * A class to store department invitations.
 */
@Entity
@Table(name = "h_depa_invi")
public class HDepartmentInvitation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The department.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "depa_id", nullable = false)
    private HDepartment department;

    /**
     * The user.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    private HUser user;

    /**
     *
     */
    private HDepartmentInvitation() {
    }

    /**
     * @param user       the user
     * @param department the department
     */
    private HDepartmentInvitation(final HUser user, final HDepartment department) {
        this.user = user;
        this.department = department;
    }

    /**
     * @param user
     * @param department
     * @return
     */
    public static HDepartmentInvitation departmentInvitation(final HUser user, final HDepartment department) {
        return new HDepartmentInvitation(user, department);
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
}