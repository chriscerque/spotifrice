package net.ent.etrs.spotifrice.utils;


import net.ent.etrs.spotifrice.model.references.Genre;

public final class GenreConverter {
    private GenreConverter() {
        // TODO Auto-generated constructor stub
    }

    public static Genre convertir(String chaine) {
        if (chaine == null || chaine.equals("")) {
            return Genre.GENRE_INCONNU;
        }
        chaine = chaine.trim();
        chaine = chaine.toUpperCase();
        chaine = chaine.replace(" ", "_");
        try {
            return Genre.valueOf(chaine);
        } catch (IllegalArgumentException e) {
            return Genre.GENRE_INCONNU;
        }

    }
}
