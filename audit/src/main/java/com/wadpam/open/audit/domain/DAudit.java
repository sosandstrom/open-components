package com.wadpam.open.audit.domain;

import javax.persistence.Entity;
import net.sf.mardao.core.Parent;
import net.sf.mardao.core.domain.AbstractLongEntity;

/**
 *
 * @author os
 */
@Entity
public class DAudit extends AbstractLongEntity {
    
    /** 
     * Build the parent key using id=1 and the deleted entity's kind,
     * for example KEY('DProfile', 1L).
     * Parent kind will vary with deleted entity kind */
    @Parent(kind="DAudit")
    private Object kindKey;
    
    public DAudit() {
    }

    public DAudit(Object kindKey, long simpleKey) {
        this.kindKey = kindKey;
        setId(simpleKey);
    }

    public Object getKindKey() {
        return kindKey;
    }

    public void setKindKey(Object kindKey) {
        this.kindKey = kindKey;
    }
    
    
}
