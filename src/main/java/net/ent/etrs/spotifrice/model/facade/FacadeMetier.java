package net.ent.etrs.spotifrice.model.facade;

//import spotifrice.model.dao.AlbumDaoJpa;
//import spotifrice.model.dao.DaoFactory;
//import spotifrice.model.dao.RessourceMp3DaoJpa;
//import spotifrice.model.dao.exceptions.DaoException;
//import spotifrice.model.entities.Album;
//import spotifrice.model.entities.RessourceMp3;

import net.ent.etrs.spotifrice.model.daos.exceptions.DaoException;
import net.ent.etrs.spotifrice.model.daos.impl.AlbumDaoJpaImpl;
import net.ent.etrs.spotifrice.model.daos.impl.DaosFactory;
import net.ent.etrs.spotifrice.model.daos.impl.RessourceMp3DaoJpaImpl;
import net.ent.etrs.spotifrice.model.entities.Album;
import net.ent.etrs.spotifrice.model.entities.RessourceMp3;
import net.ent.etrs.spotifrice.model.facade.exceptions.BusinessException;
import net.ent.etrs.spotifrice.model.references.ConstantesModel;
import org.apache.commons.collections4.IterableUtils;

import java.util.List;
import java.util.Optional;

/**
 * Facade metier de Spotifrice: rends les fonctionnalités de l'applicatif
 *
 * @author nicolas.magniez
 */
public final class FacadeMetier {

    RessourceMp3DaoJpaImpl daoRessourceMp3 = DaosFactory.getRessourceMp3DaoJpa();
    AlbumDaoJpaImpl daoAlbum = DaosFactory.getAlbumDaoJpa();

    /**
     * "Référence" un mp3 dans la base de données ( persiste les méta-données du fichier mp3).
     * [et l'ajoute dans la "playlist" de l'appli].
     *
     * @param mp3 : la ressource mp3 à ajouter à la playlist
     */
    public void ajouter(RessourceMp3 mp3) throws BusinessException {
        try {
            daoRessourceMp3.save(mp3);
        } catch (DaoException e) {
            throw new BusinessException(ConstantesModel.CLIENT_BY_COURRIEL_NON_TROUVE);
        }
    }

    /**
     * "Déreférence" un mp3 de la base de données ( suppression de ses méta-données).
     * [et l'enlève de la "playlist" de l'appli].
     *
     * @param mp3 : la ressource mp3 à enlever de la playlist
     */
    public void supprimer(RessourceMp3 mp3) throws BusinessException {
        try {
            daoRessourceMp3.delete(mp3);
        } catch (DaoException e) {
            throw new BusinessException(ConstantesModel.CLIENT_BY_COURRIEL_NON_TROUVE);
        }
    }

    /**
     * Liste les mp3 référencés dans la bdd.
     *
     * @return les mp3 référencés.
     */
    public List<RessourceMp3> listerLesRessourceMp3() throws BusinessException {
        try {
            return List.copyOf(IterableUtils.toList(daoRessourceMp3.findAll()));
        } catch (DaoException e) {
            throw new BusinessException(ConstantesModel.CLIENT_BY_COURRIEL_NON_TROUVE);
        }
    }

    /**
     * Liste les albums référencés dans la bdd.
     *
     * @return les albums référencés.
     */
    public List<Album> listerLesAlbums() throws BusinessException {
        try {
            return List.copyOf(IterableUtils.toList(daoAlbum.findAll()));
        } catch (DaoException e) {
            throw new BusinessException(ConstantesModel.CLIENT_BY_COURRIEL_NON_TROUVE);
        }
    }

    /**
     * Verifie si un mp3 est déjà référencé en base de données.
     *
     * @param chemin : le chemin "physique" du mp3.
     * @return vrai/faux.
     */
    public boolean estDejaReference(String chemin) throws BusinessException {
        try {
            return daoRessourceMp3.existByChemin(chemin);
        } catch (DaoException e) {
            throw new BusinessException(ConstantesModel.CLIENT_BY_COURRIEL_NON_TROUVE);
        }
    }

    /**
     * Vérifie si un album est déja référencé en base de données.
     *
     * @param nom     : le nom de l'album.
     * @param artiste : l'artiste/groupe auteur de l'album.
     * @return vrai/faux.
     */
    public boolean estDejaReference(String nom, String artiste) throws BusinessException {
        try {
            return daoRessourceMp3.countByArtisteAndNom(nom, artiste) != 0;
        } catch (DaoException e) {
            throw new BusinessException(ConstantesModel.CLIENT_BY_COURRIEL_NON_TROUVE);
        }
    }

    /**
     * Récupère un album  depuis la base de données connaissant son nom.	 *
     *
     * @param nom
     * @return Album : un album "attaché" au contexte de persistence.
     */
    public Optional<Album> recupererAlbumParSonNom(String nom) throws BusinessException {
        try {
            return daoAlbum.findByNom(nom);
        } catch (DaoException e) {
            throw new BusinessException(ConstantesModel.CLIENT_BY_COURRIEL_NON_TROUVE);
        }
    }

}
