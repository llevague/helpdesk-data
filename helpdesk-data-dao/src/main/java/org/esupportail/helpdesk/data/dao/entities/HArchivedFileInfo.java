package org.esupportail.helpdesk.data.dao.entities;

import java.util.*;
import javax.persistence.*;

/**
 * This class handles archived uploaded files.
 */
@Entity
@Table(name = "h_arch_file")
public class HArchivedFileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The date of the action.
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false)
    private Date date;

    @Basic
    @Column(name = "effe_scop", nullable = false)
    private String effectiveScope;

    /**
     * The id of the original HFileInfo.
     */
    @Basic
    @Column(name = "file_info_id")
    private Long fileInfoId;

    /**
     * The name of the file, as given by the client when uploading.
     */
    @Basic
    @Column(name = "file_name")
    private String filename;

    /**
     * The size of the file.
     */
    @Basic
    @Column(name = "file_size", columnDefinition = "INT")
    private Integer filesize;

    /**
     * The archived ticket.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "arch_tick_id", nullable = false)
    private HArchivedTicket archivedTicket;

    /**
     * The user that did the action.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private HUser user;

    /**
     *
     */
    private HArchivedFileInfo() {
        this.date = new Date();
    }

    /**
     * @param fileInfo
     * @param archivedTicket
     */
    private HArchivedFileInfo(final HFileInfo fileInfo, final HArchivedTicket archivedTicket) {
        this();
        this.user = fileInfo.getUser();
        this.archivedTicket = archivedTicket;
        this.effectiveScope = fileInfo.getEffectiveScope();
        this.fileInfoId = fileInfo.getId();
        this.date = fileInfo.getDate();
        this.filename = fileInfo.getFilename();
        this.filesize = fileInfo.getFilesize();
    }

    /**
     * @param fileInfo
     * @param archivedTicket
     */
    public static HArchivedFileInfo archivedFileInfo(final HFileInfo fileInfo, final HArchivedTicket archivedTicket) {
        return new HArchivedFileInfo(fileInfo, archivedTicket);
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

    public String getEffectiveScope() {
        return effectiveScope;
    }

    public void setEffectiveScope(String effectiveScope) {
        this.effectiveScope = effectiveScope;
    }

    public Long getFileInfoId() {
        return fileInfoId;
    }

    public void setFileInfoId(Long fileInfoId) {
        this.fileInfoId = fileInfoId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getFilesize() {
        return filesize;
    }

    public void setFilesize(Integer filesize) {
        this.filesize = filesize;
    }

    public HArchivedTicket getArchivedTicket() {
        return archivedTicket;
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