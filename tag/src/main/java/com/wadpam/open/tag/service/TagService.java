package com.wadpam.open.tag.service;

import com.wadpam.open.mvc.MardaoCrudService;
import com.wadpam.open.tag.dao.DTagDao;
import com.wadpam.open.tag.domain.DTag;

public class TagService extends MardaoCrudService<DTag, Long, DTagDao> {

    @Override
    public void setDao(DTagDao dao) {
        this.dao = dao;
    }

    public Iterable<DTag> getAll() {
        return dao.queryAll();

    }

    public Iterable<DTag> getDTagByArg0(String appArg0) {
        return dao.queryByAppArg0(appArg0);
    }
}
