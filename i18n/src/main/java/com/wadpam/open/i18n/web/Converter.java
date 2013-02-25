package com.wadpam.open.i18n.web;

import com.google.appengine.api.datastore.Key;
import com.wadpam.open.i18n.dao.Di18nTranslationDao;
import com.wadpam.open.i18n.domain.Di18nTranslation;
import com.wadpam.open.i18n.json.Ji18nTranslation;
import com.wadpam.open.json.JBaseObject;
import com.wadpam.open.web.BaseConverter;

public class Converter  extends BaseConverter {
    
    private Di18nTranslationDao translationDao;
    
 // Convert translations
    public Ji18nTranslation convert(Di18nTranslation from) {

        if (null == from) {
            return null;
        }

        Ji18nTranslation to = new Ji18nTranslation();
        to.setId(toString((Key) translationDao.getPrimaryKey(from)));
        to.setParent(toString((Key)from.getParent()));
        to.setLocale(from.getLocale());
        to.setLocalizedString(from.getLocalizedString());
        if (null != from.getLocalizedImage())
            to.setLocalizedImage(from.getLocalizedImage());
        if (null != from.getLocalizedUrl())
            to.setLocalizedUrl(from.getLocalizedImage());

        return to;
    }

    @Override
    public JBaseObject convertBase(Object from) {
        return convert((Di18nTranslation)from);
    }

    public void setTranslationDao(Di18nTranslationDao translationDao) {
        this.translationDao = translationDao;
    }
}
