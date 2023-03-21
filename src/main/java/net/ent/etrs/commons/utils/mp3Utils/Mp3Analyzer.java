package net.ent.etrs.commons.utils.mp3Utils;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * classe utilitaire d'analyse de fichier MP3.
 *
 * @author CDT ROBIN
 * @see SongEntry
 */
public final class Mp3Analyzer {

    private Mp3Analyzer() {
        // protection du constructeur.
    }

    /**
     * analyse un fichier MP3 et retourne une instance de SongEntry qui
     * encapsule les métadonnnées.
     *
     * @param mp3File fichier à analyser.
     * @return instance de SongEntry.
     */
    public final static SongEntry analyze(File mp3File) {
        SongEntry songEntry = new SongEntry();
//TODO
        System.out.println("mp3File : " + mp3File);
        try {
            Path path = Paths.get(mp3File.getPath());
            System.out.println("path :" + path);
            Mp3File mp3File1 = new Mp3File(path.toAbsolutePath());
            System.out.println(mp3File1);

            if (mp3File1.hasId3v1Tag()) {
                songEntry.setArtist(mp3File1.getId3v1Tag().getArtist());
                songEntry.setTitle(mp3File1.getId3v1Tag().getTitle());
                songEntry.setAlbum(mp3File1.getId3v1Tag().getAlbum());
                songEntry.setMusicGenre(mp3File1.getId3v1Tag().getGenreDescription());
                if (mp3File1.getId3v2Tag().getYear() != null) {
                    songEntry.setYear(Integer.valueOf(mp3File1.getId3v1Tag().getYear()));
                }
                songEntry.setSize(mp3File1.getLength());
                songEntry.setFileName(mp3File1.getFilename());
            } else if (mp3File1.hasId3v2Tag()) {
                songEntry.setArtist(mp3File1.getId3v2Tag().getArtist());
                songEntry.setTitle(mp3File1.getId3v2Tag().getTitle());
                songEntry.setAlbum(mp3File1.getId3v2Tag().getAlbum());
                songEntry.setMusicGenre(mp3File1.getId3v2Tag().getGenreDescription());
                //TODO
                System.out.println("mp3File1 : " + mp3File1);
                System.out.println("mp3File1.getId3v2Tag() : " + mp3File1.getId3v2Tag());
                System.out.println("mp3File1.getId3v2Tag().getYear() : " + mp3File1.getId3v2Tag().getYear());
                if (mp3File1.getId3v2Tag().getYear() != null) {
                    songEntry.setYear(Integer.valueOf(mp3File1.getId3v2Tag().getYear()));
                }
                songEntry.setSize(mp3File1.getLength());
                songEntry.setFileName(mp3File1.getFilename());
            }


            //TODO
//        Media media = new Media(mp3File.toURI().toString());
//        System.out.println("media : " + media);
//        media.getMetadata().entrySet().forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
//        System.out.println("////////////////////////////////");
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        System.out.println("mediaPlayer : " + mediaPlayer);
//        AudioFileFormat baseFileFormat;
//        try {
//            baseFileFormat = AudioSystem.getAudioFileFormat(mp3File);
////TODO
//            System.out.println("baseFileFormat : " + baseFileFormat);
//            Map<String, Object> properties = baseFileFormat.properties();
//
//            //TODO
//            properties.forEach((k, v) -> System.out.println(String.format("%s : %s", k, v)));
//
//            songEntry.setArtist((String) properties.get("author"));
//            songEntry.setTitle((String) properties.get("title"));
//            songEntry.setAlbum((String) properties.get("album"));
//            songEntry.setMusicGenre((String) properties.get("mp3.id3tag.genre"));
//            songEntry.setYear(Integer.parseInt((String) properties.get("date")));
//            songEntry.setSize(mp3File.length());
//            songEntry.setFileName(mp3File.getAbsolutePath());


//                songEntry.setArtist(mp3File1.getId3v1Tag().getArtist());
//            songEntry.setTitle(mp3File1.getId3v1Tag().getTitle());
//            songEntry.setAlbum(mp3File1.getId3v1Tag().getAlbum());
//            songEntry.setMusicGenre(mp3File1.getId3v1Tag().getGenreDescription());
//            songEntry.setYear(Integer.valueOf(mp3File1.getId3v1Tag().getYear()));
//            songEntry.setSize(mp3File1.getLength());
//            songEntry.setFileName(mp3File1.getFilename());

            songEntry.trim();

        } catch (IOException | UnsupportedTagException | InvalidDataException e) {
            e.printStackTrace();
        }

        return songEntry;
    }

}
