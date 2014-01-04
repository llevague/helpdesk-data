package org.esupportail.helpdesk.data.dao.entities;

import java.util.*;
import javax.persistence.*;

/**
 * This class handles uploaded files.
 *
 * @deprecated
 */
@Deprecated
@Entity
@Table(name = "h_file")
public class HOldFileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The name of the file, as given by the client when uploading.
     */
    @Basic
    @Column(name = "name", nullable = false)
    private String filename;

    @OneToMany(targetEntity = HAction.class, mappedBy = "oldFileInfo", cascade = CascadeType.MERGE)
    private Set<HAction> actions = new HashSet<>();

    /**
     *
     */
    private HOldFileInfo() {
    }

    /**
     * @param filename
     */
    private HOldFileInfo(final String filename) {
        this.filename = filename;
    }

    /**
     * @param filename
     * @return
     */
    public static HOldFileInfo oldFileInfo(final String filename) {
        return new HOldFileInfo(filename);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Set<HAction> getActions() {
        return actions;
    }

    public void setActions(Set<HAction> actions) {
        this.actions = actions;
    }
}