package org.esupportail.helpdesk.data.dao.entities;

import java.util.*;
import javax.persistence.*;

/**
 * The class for icons.
 */
@Entity
@Table(name = "h_icon")
public class HIcon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The content type.
     */
    @Basic
    @Column(name = "cont_type")
    private String contentType;

    /**
     * The data.
     */
    @Basic
    @Column(name = "data", columnDefinition = "BLOB")
    private byte[] data;

    /**
     * The name.
     */
    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(targetEntity = HCategory.class, mappedBy = "icon", cascade = CascadeType.MERGE)
    private Set<HCategory> categories = new HashSet<>();

    @OneToMany(targetEntity = HConfig.class, mappedBy = "defaultCategoryIcon", cascade = CascadeType.MERGE)
    private Set<HConfig> categoryIconConfigs = new HashSet<>();

    @OneToMany(targetEntity = HConfig.class, mappedBy = "defaultDepartmentIcon", cascade = CascadeType.MERGE)
    private Set<HConfig> departmentIconConfigs = new HashSet<>();

    @OneToMany(targetEntity = HDepartment.class, mappedBy = "icon", cascade = CascadeType.MERGE)
    private Set<HDepartment> departments = new HashSet<>();

    /**
     *
     */
    private HIcon() {
    }


    /**
     * @param name
     * @param contentType
     * @param data
     */
    private HIcon(final String name, final String contentType, final byte[] data) {
        this.name = name;
        this.contentType = contentType;
        this.data = data;
    }

    /**
     * @param c
     */
    private HIcon(final HIcon c) {
        this(c.name, c.contentType, c.data);
        this.id = c.id;
    }

    /**
     * @param name
     * @param contentType
     * @param data
     * @return
     */
    public static HIcon icon(final String name, final String contentType, final byte[] data) {
        return new HIcon(name, contentType, data);
    }

    /**
     * @param icon
     * @return a copy of the original {@link HIcon}
     */
    public static HIcon icon(final HIcon icon) {
        return new HIcon(icon);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<HCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<HCategory> categories) {
        this.categories = categories;
    }

    public Set<HConfig> getCategoryIconConfigs() {
        return categoryIconConfigs;
    }

    public void setCategoryIconConfigs(Set<HConfig> categoryIconConfigs) {
        this.categoryIconConfigs = categoryIconConfigs;
    }

    public Set<HConfig> getDepartmentIconConfigs() {
        return departmentIconConfigs;
    }

    public void setDepartmentIconConfigs(Set<HConfig> departmentIconConfigs) {
        this.departmentIconConfigs = departmentIconConfigs;
    }

    public Set<HDepartment> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<HDepartment> departments) {
        this.departments = departments;
    }
}