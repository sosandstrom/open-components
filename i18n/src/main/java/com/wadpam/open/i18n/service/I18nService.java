package com.wadpam.open.i18n.service;

import com.wadpam.open.i18n.dao.Di18nDao;
import com.wadpam.open.i18n.domain.Di18n;
import com.wadpam.open.mvc.MardaoCrudService;

public class I18nService extends MardaoCrudService<Di18n, String, Di18nDao> {
    
    
    // Get a translation for a specific parent and locale
    public Di18n getI18n(Object parentKey, String locale) {
        LOG.debug("Get i18n for locale:{} and parent:{}", locale, parentKey);
        
        return dao.findByPrimaryKey(parentKey, locale);
    }

    // Get translations for a specific parent
    public Iterable<Di18n> getI18ns(Object parentKey) {
        LOG.debug("Get i18n for parent:{}", parentKey);
        
        return dao.queryAll(parentKey);
    }
    
    public Object getPrimaryKey(String keyString) {
        return dao.getPrimaryKey(keyString);
    }
    
}
