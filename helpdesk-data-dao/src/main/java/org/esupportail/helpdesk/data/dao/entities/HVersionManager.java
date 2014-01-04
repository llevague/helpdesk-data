package org.esupportail.helpdesk.data.dao.entities;

import javax.persistence.*;

/**
 * A class to store the version number in the database.
 */
@Entity
@Table(name = "h_vers_mana")
public class HVersionManager {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The version.
     */
    @Basic
    @Column(name = "vers")
    private String version;

    /**
     *
     */
    private HVersionManager() {
    }

    /**
     * @param version
     */
    private HVersionManager(final String version) {
        this.version = version;
    }

    /**
     * @param version
     * @return
     */
    public static HVersionManager versionManager(final String version) {
        return new HVersionManager(version);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}