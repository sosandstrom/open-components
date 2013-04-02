package com.wadpam.open.i18n.service;

import com.wadpam.open.i18n.domain.Di18n;
import com.wadpam.open.mvc.CrudService;

public interface I18nService extends CrudService<Di18n, String> {
    
    
    // Get a translation for a specific parent and locale
    Di18n getI18n(Object parentKey, String locale);

    // Get translations for a specific parent
    Iterable<Di18n> getI18ns(Object parentKey);
    
    Object getPrimaryKey(String keyString);

    String getKeyString(Object parent);
    
}
