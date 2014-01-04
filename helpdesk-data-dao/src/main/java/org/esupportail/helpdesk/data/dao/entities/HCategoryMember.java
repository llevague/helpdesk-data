package org.esupportail.helpdesk.data.dao.entities;

import javax.persistence.*;

/**
 * The class representing the membership of a user in a category.
 */
@Entity
@Table(name = "h_cate_memb")
public class HCategoryMember {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Represent the category in the user-category relation.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "cate_id", nullable = false)
    private HCategory category;

    /**
     * Represent the user in the user-category relation.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    private HUser user;

    /**
     * The order of the member in the category.
     */
    @Basic
    @Column(name = "orde", columnDefinition = "INT")
    private Integer order;

    /**
     *
     */
    private HCategoryMember() {
    }

    /**
     * @param user     the user to set
     * @param category the category to set
     */
    private HCategoryMember(final HUser user, final HCategory category) {
        this.user = user;
        this.category = category;
    }

    public static HCategoryMember categoryMember(final HUser user, final HCategory category) {
        return new HCategoryMember(user, category);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HCategory getCategory() {
        return category;
    }

    public void setCategory(HCategory category) {
        this.category = category;
    }

    public HUser getUser() {
        return user;
    }

    public void setUser(HUser user) {
        this.user = user;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}