package org.esupportail.helpdesk.data.dao.entities;

import java.util.*;
import javax.persistence.*;

/**
 * This class is used to store the configuration of department selection.
 */
@Entity
@Table(name = "h_depa_sele_conf")
public class HDepartmentSelectionConfig {

    @Id
    private Long id;

    /**
     * The config itself.
     */
    @Basic
    @Column(name = "data")
    private String data;

    /**
     * The date.
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false)
    private Date date;

    /**
     * The user.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private HUser user;

    /**
     *
     */
    private HDepartmentSelectionConfig() {
    }

    /**
     * @param user
     * @param data
     * @param date
     */
    private HDepartmentSelectionConfig(final HUser user, final String data, final Date date) {
        this.user = user;
        this.data = data;
        this.date = date;
    }

    /**
     * @param user
     * @param data
     * @param date
     * @return
     */
    public static HDepartmentSelectionConfig departmentSelectionConfig(final HUser user, final String data, final Date date) {
        return new HDepartmentSelectionConfig(user, data, date);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public HUser getUser() {
        return user;
    }

    public void setUser(HUser user) {
        this.user = user;
    }
}