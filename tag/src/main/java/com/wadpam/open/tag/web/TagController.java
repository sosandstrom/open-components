package com.wadpam.open.tag.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.wadpam.docrest.domain.RestCode;
import com.wadpam.docrest.domain.RestReturn;
import com.wadpam.open.mvc.CrudController;
import com.wadpam.open.tag.domain.DTag;
import com.wadpam.open.tag.json.JTag;
import com.wadpam.open.tag.service.TagService;

/**
 * TagController
 * 
 * @author huy
 * 
 */

@RestReturn(value = JTag.class)
@Controller
@RequestMapping(value = "{domain}/tag")
public class TagController extends CrudController<JTag, DTag, Long, TagService> {

    private final Logger LOG = LoggerFactory.getLogger(TagController.class);

    public TagController() {
        super(JTag.class);
    }

    /**
     * Retrieves all tags with support Header If-Modified-Since is optional, timestamp of last update; use
     * "Sat, 29 Oct 1994 19:43:31 GMT"
     * 
     * @return Collection of JTag objects
     */
    @RequestMapping(value = "v10/all", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<JTag> getAll(WebRequest webRequest, @RequestHeader(required = false, value = "If-Modified-Since") Date since,
            HttpServletRequest request, HttpServletResponse response) {

        Iterable<JTag> jTagIterable = convert(service.getAll());
        if (since != null) {
            final long lastUpdate = service.getLastUpdate(since);
            if (webRequest.checkNotModified(lastUpdate)) {
                return null;
            }
        }
        return jTagIterable;
    }

    /**
     * Retrieves all tags which have reference as appArg0 with support Header If-Modified-Since is optional, timestamp of last
     * update; use "Sat, 29 Oct 1994 19:43:31 GMT"
     * 
     * @param appArg0
     *            - find all tags which have references as appArg0
     * @return Collection of JTag objects
     */
    @RestReturn(value = JTag.class, code = {@RestCode(code = 200, description = "get Entity successfully", message = "ok")})
    @ResponseBody
    @RequestMapping(value = "v10", params = "appArg0", method = RequestMethod.GET)
    public Iterable<JTag> getByAppArg0(WebRequest webRequest,
            @RequestHeader(required = false, value = "If-Modified-Since") Date since, @RequestParam String appArg0,
            HttpServletRequest request, HttpServletResponse response) {

        Iterable<JTag> jTagIterable = convert(service.getDTagByArg0(appArg0));

        if (since != null) {
            if (webRequest.checkNotModified(service.getLastUpdate(since)))
                return null;
        }
        return jTagIterable;
    }

    public void setService(TagService tagService) {
        this.service = tagService;
    }

    public TagService getService() {
        return this.service;
    }

    @Override
    public void convertDomain(DTag from, JTag to) {

        convertLongEntity(from, to);
        to.setAppArg0(from.getAppArg0());
        to.setIconUrl(from.getIconUrl());
        to.setName(from.getName());
        to.setPriority(from.getPriority());
        to.setDescription(from.getDescription());

    }

    @Override
    public void convertJson(JTag from, DTag to) {

        convertJLong(from, to);
        to.setAppArg0(from.getAppArg0());
        to.setIconUrl(from.getIconUrl());
        to.setName(from.getName());
        to.setPriority(from.getPriority());
        to.setDescription(from.getDescription());

    }
}
