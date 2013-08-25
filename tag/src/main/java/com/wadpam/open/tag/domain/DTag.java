package com.wadpam.open.tag.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;

import net.sf.mardao.core.domain.AbstractLongEntity;

/**
 * DTag domain object
 * 
 * @author huy
 */
@Entity
public class DTag extends AbstractLongEntity {

    /** The tag name */
    @Basic
    private String name;

    /** The tag description */
    @Basic
    private String description;

    /** The tag icon url */
    @Basic
    private String iconUrl;

    /** The bigger number of priority the higher priority it is */
    @Basic
    private Long   priority;

    /** The key to the ref asset, it could be Country */
    @Basic
    private String appArg0;
    
    /**Telenor will use this setting up collection will be part of around-me section: 1*/
    @Basic
    private Long feature;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public String getAppArg0() {
        return appArg0;
    }

    public void setAppArg0(String appArg0) {
        this.appArg0 = appArg0;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getFeature() {
        return feature;
    }

    public void setFeature(Long feature) {
        this.feature = feature;
    }
}