package net.ent.etrs.spotifrice.model.daos.impl;


import net.ent.etrs.spotifrice.model.entities.AbstractEntity;

import java.io.Serializable;

public abstract class AbstractJpaDao<T extends AbstractEntity, ID extends Serializable> extends JpaBaseDao<T, ID> {

    protected AbstractJpaDao() {
        super.setEm(JpaUtil.getEm());
    }
}
