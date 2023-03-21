/**
 *
 */
package net.ent.etrs.commons.utils.mp3Utils;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * cette classe permet d'encapsuler les méta-données
 * d'un fichier MP3.
 *
 * @author CDT RBN
 */

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString

public class SongEntry {
    /**
     * artiste de la chanson.
     */
    @Getter
    @Setter
    String artist;

    /**
     * titre de la chanson.
     */
    @Getter
    @Setter
    String title;

    /**
     * genre musical de la chanson.
     */
    @Getter
    @Setter
    String musicGenre;

    /**
     * album de la chanson.
     */
    @Getter
    @Setter
    String album;

    /**
     * fichier analysé.
     */
    @Getter
    @Setter
    String fileName;

    /**
     * taille du fichier.
     */
    @Getter
    @Setter
    Long size;

    /**
     * année de sortie.
     */
    @Getter
    @Setter
    int year;


    protected void trim() {
        if (artist != null) artist = artist.trim();
        if (title != null) title = title.trim();
        if (musicGenre != null) musicGenre = musicGenre.trim();
        if (album != null) album = album.trim();
        if (fileName != null) fileName = fileName.trim();
    }
}
