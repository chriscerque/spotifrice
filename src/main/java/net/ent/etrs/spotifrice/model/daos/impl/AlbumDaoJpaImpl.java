package net.ent.etrs.spotifrice.model.daos.impl;

import net.ent.etrs.spotifrice.model.daos.AlbumDaoJpa;
import net.ent.etrs.spotifrice.model.daos.exceptions.DaoException;
import net.ent.etrs.spotifrice.model.entities.Album;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.Optional;

public class AlbumDaoJpaImpl extends AbstractJpaDao<Album, Serializable> implements AlbumDaoJpa {


    public Optional<Album> findByNom(String nom) throws DaoException {
        try {
            TypedQuery<Album> q = em.createQuery(ReqJpa.ALBUM__SEARCH_BY_NOM, Album.class);

            q.setParameter("nom", nom);
            return Optional.ofNullable(q.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

}
