package com.wadpam.open.tag.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    public TagController() {
        super(JTag.class);
    }

    /**
     * @return Iterable<JTag>
     */
    @RequestMapping(value = "v10/all", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<JTag> getAll() {
        return convert(service.getAll());
    }

    @RestReturn(value = JTag.class, code = {@RestCode(code = 200, description = "get Entity successfully", message = "ok")})
    @ResponseBody
    @RequestMapping(value = "v10", params = "appArg0", method = RequestMethod.GET)
    public Iterable<JTag> getByAppArg0(@RequestParam String appArg0) {

        return convert(service.getDTagByArg0(appArg0));

    }

    public void setService(TagService tagService) {
        this.service = tagService;
    }

    @Override
    public void convertDomain(DTag from, JTag to) {

        convertLongEntity(from, to);
        to.setAppArg0(from.getAppArg0());
        to.setIconUrl(from.getIconUrl());
        to.setName(from.getName());
        to.setPriority(from.getPriority());

    }

    @Override
    public void convertJson(JTag from, DTag to) {

        convertJLong(from, to);
        to.setAppArg0(from.getAppArg0());
        to.setIconUrl(from.getIconUrl());
        to.setName(from.getName());
        to.setPriority(from.getPriority());

    }
}
