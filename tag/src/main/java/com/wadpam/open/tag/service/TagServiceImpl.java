package com.wadpam.open.tag.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wadpam.open.audit.dao.DAuditDaoBean;
import com.wadpam.open.mvc.MardaoCrudService;
import com.wadpam.open.tag.dao.DTagDao;
import com.wadpam.open.tag.domain.DTag;

public class TagServiceImpl extends MardaoCrudService<DTag, Long, DTagDao> 
        implements TagService {

    private final Logger  LOG = LoggerFactory.getLogger(TagServiceImpl.class);

    private DAuditDaoBean dAuditDao;

    @Override
    public void setDao(DTagDao dao) {
        this.dao = dao;
    }

    @Override
    public void delete(String parentKeyString, Long id) {
        // TODO Auto-generated method stub
        dAuditDao.deleteWithAudit(dao, id);
    }

    public Iterable<DTag> getAll() {
        
        Iterable<DTag> dTagIt = dao.queryAll();
      //  List<DTag> dTagList = Lists.newArrayList(dTagIt);
      //sort by Name
       // Collections.sort(dTagList, new ObjectComparator(AbstractSortType.ASC, "getName"));
        return dTagIt;

    }

    public Iterable<DTag> getDTagByArg0(String appArg0) {

        Iterable<DTag> dTagIt = getAll();
        Collection<DTag> fiter = new ArrayList<DTag>();
        for (DTag dTag : dTagIt) {
            if (appArg0 != null && appArg0.equals(dTag.getAppArg0())){
                fiter.add(dTag);
            }
        }
        return fiter;
    }

    public long getLastUpdate(Date since) {
        final long currentMillis = System.currentTimeMillis();
        // check record updated
        boolean isModified = dao.isRecordedModified(since);
        if (isModified == false) {
            // Lookup-delete audith
            Object parentKey = DAuditDaoBean.getAuditKey(dao);
            Collection<Long> deleted = dAuditDao.whatsDeleted(since, parentKey);
            return deleted.isEmpty() ? 0L : currentMillis;
        }
        return currentMillis;

    }

    public void setdAuditDao(DAuditDaoBean dAuditDao) {
        this.dAuditDao = dAuditDao;
    }
}
