package net.ent.etrs.spotifrice.model.daos.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ReqJpa {

    public static final String ALBUM__SEARCH_BY_NOM = "SELECT a FROM Album a WHERE a.nom=:nom ";
    public static final String ALBUM__COMPTER_ALBUMS_BY_ARTISTE_AND_NOM = "SELECT COUNT(a) FROM Album a WHERE a.nom =:nom AND a.artiste=:artiste";
    public static final String RESSOURCE_MP3__FIND_BY_CHEMIN = "SELECT r FROM RessourceMp3 r WHERE r.cheminComplet= :chemin";
}
