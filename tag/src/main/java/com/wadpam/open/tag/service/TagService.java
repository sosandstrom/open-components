package com.wadpam.open.tag.service;

import com.wadpam.open.mvc.CrudService;
import java.util.Date;

import com.wadpam.open.tag.domain.DTag;

public interface TagService extends CrudService<DTag, Long> {

    Iterable<DTag> getAll();

    Iterable<DTag> getDTagByArg0(String appArg0);

    long getLastUpdate(Date since);

}
