package org.esupportail.helpdesk.data.dao.entities;

import javax.persistence.*;

/**
 * The representation of a deleted item, to be deleted from the index.
 */
@Entity
@Table(name = "h_dele_item")
public class HDeletedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The id used for the indexation.
     */
    @Basic
    @Column(name = "doc_id", nullable = false)
    private String indexId;

    /**
     *
     */
    private HDeletedItem() {
    }

    /**
     * Constructor.
     *
     * @param indexId
     */
    private HDeletedItem(final String indexId) {
        this.indexId = indexId;
    }

    /**
     * @param indexId
     * @return
     */
    public static HDeletedItem deletedItem(final String indexId) {
        return new HDeletedItem(indexId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }
}