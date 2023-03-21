package net.ent.etrs.spotifrice.model.daos.impl;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaosFactory {

    private static final AlbumDaoJpaImpl ALBUM_DAO_JPA;
    private static final RessourceMp3DaoJpaImpl RESSOURCE_MP_3_DAO_JPA;

    static {
        ALBUM_DAO_JPA = new AlbumDaoJpaImpl();
        RESSOURCE_MP_3_DAO_JPA = new RessourceMp3DaoJpaImpl();
    }

    public static AlbumDaoJpaImpl getAlbumDaoJpa() {
        return ALBUM_DAO_JPA;
    }

    public static RessourceMp3DaoJpaImpl getRessourceMp3DaoJpa() {
        return RESSOURCE_MP_3_DAO_JPA;
    }

}
