package com.wadpam.open.i18n.web;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wadpam.docrest.domain.RestCode;
import com.wadpam.docrest.domain.RestReturn;
import com.wadpam.open.exceptions.NotFoundException;
import com.wadpam.open.i18n.domain.Di18n;
import com.wadpam.open.i18n.json.Ji18n;
import com.wadpam.open.i18n.service.I18nService;
import com.wadpam.open.mvc.CrudController;
/**
 * The i18n controller implements all REST methods related to translations.
 * There methods should preferably only be user by some kind of backoffice interface
 * to manage the translations.
 * Any app side integration should be in the Controller och Service accessed by the
 * app (the app should not access the REST interface directly).
 * @author mattiaslevin
 
 * @author sophea <a href='mailto:sm@goldengekko.com'> sophea </a>
 * @version $id$ - $Revision$
 * @date 2013
 * @author sophea <a href='mailto:sm@goldengekko.com'> sophea </a>
 * @version $id$ - $Revision$
 * @date 2013
 */
@RestReturn(value = Ji18n.class)
@Controller
@RequestMapping(value="{domain}/i18n")
public class I18nController extends CrudController<Ji18n,  Di18n, String, I18nService>{
    
  private static final int ERR_NOT_FOUND = 100;
  private static final int ERR_BAD_REQUEST =  102;
  private static final int ERR_SERVER_ERROR =  103;
    
    protected I18nController() {
        super(Ji18n.class);
    }
    
    
    /**
     * Get a localized i18n for a specific locale and parent.
     * 
     * @param parent the parent key resource being translated
     * @param locale the locale
     * @return the translation
     */
    @RestReturn(value = Ji18n.class, entity = Ji18n.class, code = {
            @RestCode(code = 200, message = "OK", description = "Translation found"),
            @RestCode(code = 404, message = "NOK", description = "Translations not found")})
    @RequestMapping(value = "", method = RequestMethod.GET, params = {"parent", "locale"})
    public ResponseEntity<Ji18n> getI18n(HttpServletRequest request, @RequestParam(required = true) String parent,
            @RequestParam(required = true) String locale) {

        final Di18n body = service.get(parent, locale);

        if (null == body) {
            throw new NotFoundException(ERR_NOT_FOUND, String.format("Locale:%s for parent:%s not found", parent, locale));
        }

        return new ResponseEntity<Ji18n>(convertDomain(body), HttpStatus.OK);
    }
    
    /**
     * Get all localized i18n for a specific parent.
     * 
     * @param parent the parent key resource being translated
     * @return a list of translations
     */
    @RestReturn(value = Ji18n.class, entity = Ji18n.class, code = {@RestCode(code = 200, message = "OK", description = "Translation found")})
    @RequestMapping(value = "", method = RequestMethod.GET, params = "parent")
    public ResponseEntity<Collection<Ji18n>> getI18nsForParent(HttpServletRequest request,
            @RequestParam(required = true) String parent) {

            final Iterable<Di18n> body = service.getI18ns(service.getPrimaryKey(parent));

            return new ResponseEntity(convert(body), HttpStatus.OK);

    }
    
    @Override
    protected Ji18n populateRequestBody(HttpServletRequest request, Model model, Ji18n body) {
        //create parent key as Object
        if (body.getParent()!=null) {
            body.setParent(service.getPrimaryKey((String)body.getParent()));
        }
        return super.populateRequestBody(request, model, body);
    }
   
    @Override
    public void convertDomain(Di18n from, Ji18n to) {
        to.setParent(service.getKeyString(from.getParent()));
        to.setLocale(from.getLocale());
        to.setLocalizedString(from.getLocalizedString());
        to.setLocalizedImage(from.getLocalizedImage());
        to.setLocalizedUrl(from.getLocalizedUrl());
    }

    @Override
    public void convertJson(Ji18n from, Di18n to) {
        to.setParent(from.getParent());
        to.setLocale(from.getLocale());
        to.setLocalizedString(from.getLocalizedString());
        to.setLocalizedImage(from.getLocalizedImage());
        to.setLocalizedUrl(from.getLocalizedUrl());
        
    }
    
    public void setService(I18nService service) {
        this.service = service;
    }

}
