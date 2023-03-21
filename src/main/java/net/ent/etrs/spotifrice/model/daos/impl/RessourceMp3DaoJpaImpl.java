package net.ent.etrs.spotifrice.model.daos.impl;


import net.ent.etrs.spotifrice.model.daos.RessourceMp3DaoJpa;
import net.ent.etrs.spotifrice.model.daos.exceptions.DaoException;
import net.ent.etrs.spotifrice.model.entities.RessourceMp3;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.io.Serializable;

public class RessourceMp3DaoJpaImpl extends AbstractJpaDao<RessourceMp3, Serializable> implements RessourceMp3DaoJpa {


    public long countByArtisteAndNom(String nom, String artiste) throws DaoException {
        try {
            TypedQuery<Long> query = em.createNamedQuery(ReqJpa.ALBUM__COMPTER_ALBUMS_BY_ARTISTE_AND_NOM, Long.class);

            query.setParameter("nom", nom);
            query.setParameter("artiste", artiste);
            Long result = query.getSingleResult();
            return result;
        } catch (NoResultException e) {
            return 0;
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }


    public boolean existByChemin(String chemin) throws DaoException {
        try {
            TypedQuery<RessourceMp3> q = em.createQuery(ReqJpa.RESSOURCE_MP3__FIND_BY_CHEMIN, RessourceMp3.class);

            q.setParameter("chemin", chemin);
            return q.getResultList().size() != 0;
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}
