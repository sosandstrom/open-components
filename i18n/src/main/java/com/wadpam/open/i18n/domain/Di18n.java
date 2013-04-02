package com.wadpam.open.i18n.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

import net.sf.mardao.core.Parent;
import net.sf.mardao.core.domain.AbstractCreatedUpdatedEntity;

/**
 * Handle localization of strings and other resources.
 * @mattiaslevin + @sopheamak
 */
@Entity
public class Di18n extends AbstractCreatedUpdatedEntity {

    /** The locale of the localized entity */
    @Id
    private String          locale;

    /** The parent key this localization belongs to. E.g. a tag or category */
    @Parent(kind = "Any")
    private Object          parent;

    /** Localized string value */
    @Basic
    private String          localizedString;

    /** Localized image */
    @Basic
    private String            localizedImage;

    /** Localized url */
    @Basic
    private String           localizedUrl;


    // Setters and getters
    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    

    public String getLocalizedString() {
        return localizedString;
    }

    public void setLocalizedString(String localizedString) {
        this.localizedString = localizedString;
    }

    

    public Object getParent() {
        return parent;
    }

    public void setParent(Object parent) {
        this.parent = parent;
    }

    public String getLocalizedImage() {
        return localizedImage;
    }

    public void setLocalizedImage(String localizedImage) {
        this.localizedImage = localizedImage;
    }

    public String getLocalizedUrl() {
        return localizedUrl;
    }

    public void setLocalizedUrl(String localizedUrl) {
        this.localizedUrl = localizedUrl;
    }
}
