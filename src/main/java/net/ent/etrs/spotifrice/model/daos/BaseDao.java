package net.ent.etrs.spotifrice.model.daos;


import net.ent.etrs.spotifrice.model.daos.exceptions.DaoException;
import net.ent.etrs.spotifrice.model.entities.AbstractEntity;

import java.io.Serializable;
import java.util.Optional;

public interface BaseDao<T extends AbstractEntity, ID extends Serializable> {

    T save(T entity) throws DaoException;

    Optional<T> find(ID id) throws DaoException;

    Iterable<T> findAll() throws DaoException;

    void delete(ID id) throws DaoException;

    boolean exists(ID id) throws DaoException;

    long count() throws DaoException;

}
