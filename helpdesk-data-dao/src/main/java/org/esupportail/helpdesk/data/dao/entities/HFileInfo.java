package org.esupportail.helpdesk.data.dao.entities;

import java.util.*;
import javax.persistence.*;

import static org.esupportail.helpdesk.data.dao.utils.Functions.*;

/**
 * This class handles uploaded files.
 */
@Entity
@Table(name = "h_file2")
public class HFileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The date of the action.
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dat", nullable = false)
    private Date date;

    /**
     * The ticket.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "tick_id", nullable = false)
    private HTicket ticket;

    /**
     * The user that did the action.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private HUser user;

    /**
     * The name of the file, as given by the client when uploading.
     */
    @Basic
    @Column(name = "name", nullable = false)
    private String filename;

    /**
     * The scope of the action.
     */
    @Basic
    @Column(name = "scop", nullable = false)
    private String scope;

    /**
     * The size of the file.
     */
    @Basic
    @Column(name = "size", columnDefinition = "INT")
    private int filesize;

    /**
     *
     */
    private HFileInfo() {
        this.date = new Date();
    }

    /**
     * @param filename
     * @param ticket
     * @param user
     * @param scope
     */
    private HFileInfo(final String filename, final HTicket ticket, final HUser user, final String scope) {
        this();
        this.filename = filename;
        this.ticket = ticket;
        this.user = user;
        this.scope = scope;
    }

    /**
     * @param filename
     * @param ticket
     * @param user
     * @param scope
     * @return
     */
    public static HFileInfo fileInfo(final String filename, final HTicket ticket, final HUser user, final String scope) {
        return new HFileInfo(filename, ticket, user, scope);
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public int getFilesize() {
        return filesize;
    }

    public void setFilesize(int filesize) {
        this.filesize = filesize;
    }

    public String getEffectiveScope() {
        return effectiveScope.f(this.scope, this.ticket.getEffectiveScope());
    }
}