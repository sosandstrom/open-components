package com.wadpam.open.i18n.service;
/***/

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.google.appengine.api.datastore.Key;
import com.wadpam.open.exceptions.NotFoundException;
import com.wadpam.open.i18n.dao.Di18nTranslationDao;
import com.wadpam.open.i18n.domain.Di18nTranslation;
import com.wadpam.open.transaction.Idempotent;

/**
 * @author sophea <a href='mailto:sm@goldengekko.com'> sophea </a>
 * @version $id$ - $Revision$
 * @date 2013
 */
public class TranslationService {

    private static final Logger LOG = LoggerFactory.getLogger(TranslationService.class);

    public static final int ERR_TRANSLATION_SERVICE = 2000;
    private static final int ERR_NOT_FOUND = ERR_TRANSLATION_SERVICE + 1;

    private Di18nTranslationDao i18nDao;

    // Add or update a translation
    @Transactional
    @Idempotent
    public Di18nTranslation addTranslation(Key parentKey, String locale, String localizedString,
                                           String localizedImageUrl, String localizedLinkUrl) {
        LOG.debug("Add new translation:{} for local:{}", localizedString, locale);

        Di18nTranslation di18n = new Di18nTranslation();
        di18n.setParent(parentKey);
        di18n.setLocale(locale);
        di18n.setLocalizedString(localizedString);
        di18n.setLocalizedImage(localizedImageUrl);
        di18n.setLocalizedUrl(localizedLinkUrl);
        
        // Save in datastore
        i18nDao.persist(di18n);

        return di18n;
    }

    // Get translation with id
    public Di18nTranslation getTranslation(Key key) {
        LOG.debug("Get translation with id:{}", key);
        
        return i18nDao.findByPrimaryKey(key);
    }

    // Get a translation for a specific parent and locale
    public Di18nTranslation getTranslation(Key parentKey, String locale) {
        LOG.debug("Get translation for locale:{} and parent:{}", locale, parentKey);

        return i18nDao.findByPrimaryKey(parentKey, locale);
    }

    // Get translations for a specific parent
    public Iterable<Di18nTranslation> getTranslations(Key parentKey) {
        LOG.debug("Get translations for parent:{}", parentKey);

        return i18nDao.queryAll(parentKey);
    }

    // Get translations for a list of parents
    public Map<Key, Iterable<Di18nTranslation>> getTranslations(Collection<Key> parentKeys) {
        LOG.debug("Get translations for parents:{}", parentKeys);

        Map<Key, Iterable<Di18nTranslation>> resultMap = new HashMap<Key, Iterable<Di18nTranslation>>();
        for (Key parentKey : parentKeys) {
            Iterable<Di18nTranslation> di18nIterable = this.getTranslations(parentKey);
            if (null != di18nIterable)
                resultMap.put(parentKey, di18nIterable);
        }

        return resultMap;
    }


    // Get translations for a list of parents in a specific locale
    public Map<Key, Di18nTranslation> getTranslations(Collection<Key> parentKeys, String locale) {
        LOG.debug("Get translations for parents:{}", parentKeys);

        Map<Key, Di18nTranslation> resultMap = new HashMap<Key, Di18nTranslation>();
        for (Key parentKey : parentKeys) {
            Di18nTranslation di18nIterable = this.getTranslation(parentKey, locale);
            if (null != di18nIterable)
                resultMap.put(parentKey, di18nIterable);
        }

        return resultMap;
    }

    // Delete a translation with id
    @Transactional
    @Idempotent
    public Di18nTranslation deleteTranslation(Key key) {
        LOG.debug("Delete translation with id:{}", key);

        Di18nTranslation di18nTranslation = i18nDao.findByPrimaryKey(key);

        if (null == di18nTranslation)
            throw new NotFoundException(ERR_NOT_FOUND, String.format("Localisation with key:%s not found during delete", key));

        i18nDao.delete(di18nTranslation);

        return di18nTranslation;
    }

    // Delete a translation for a specific parent and locale
    @Transactional
    @Idempotent
    public Di18nTranslation deleteTranslation(Key parentKey, String locale) {
        LOG.debug("Delete translation for locale:{} and parent:{}", locale, parentKey);

        Di18nTranslation di18nTranslation = this.getTranslation(parentKey, locale);

        if (null == di18nTranslation)
            throw new NotFoundException(ERR_NOT_FOUND, String.format("Locale:%s for parent:%s not found during delete", parentKey, locale));

        i18nDao.delete(di18nTranslation);

        return di18nTranslation;
    }


    // Delete all translations for a parent resource
    @Transactional
    @Idempotent
    public int deleteTranslationForParent(Key parentKey) {
        LOG.debug("Delete all translations for parent:{}", parentKey);

        Iterable<String> di18nIterable = i18nDao.queryKeysByParent(parentKey);

        if (null == di18nIterable)
            throw new NotFoundException(ERR_NOT_FOUND, String.format("Parent:%s not found during delete", parentKey));
        
        return i18nDao.delete(parentKey,di18nIterable);
    }


    // Setters
    public void setI18nDao(Di18nTranslationDao i18nDao) {
        this.i18nDao = i18nDao;
    }

    public Di18nTranslationDao getI18nDao() {
        return i18nDao;
    }
    
}
