package net.ent.etrs.spotifrice;

import net.ent.etrs.commons.utils.mp3Utils.Mp3Analyzer;
import net.ent.etrs.commons.utils.mp3Utils.SongEntry;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class EssaiSongEntry {
    public static void main(String[] args) throws URISyntaxException, UnsupportedAudioFileException, IOException {
        //	SongEntry entree = Mp3Analyzer.analyze(new File(EssaiSongEntry.class.getResource("/music/AC-DC/HellsBells.mp3").toURI()));

        File file = new File("D:\\mp3\\Alpha Blondy\\Brigadier Sabari.mp3");
        SongEntry entree = Mp3Analyzer.analyze(file);
        System.out.println(entree.getTitle());
        System.out.println(entree.getArtist());
        System.out.println(entree.getYear());
        System.out.println(entree.getSize());
        System.out.println(entree.getFileName());
        System.out.println(entree.getMusicGenre());
        System.out.println(entree.getAlbum());
//		File audiofile = new File("D:/mp3/AC-DC/HellsBells.mp3");


//        AudioFileFormat baseFileFormat = null;
//        AudioFormat baseFormat = null;
//        baseFileFormat = AudioSystem.getAudioFileFormat(file);
//        baseFormat = baseFileFormat.getFormat();
//        AudioFileFormat.Type type = baseFileFormat.getType();
//        float frequency = baseFormat.getSampleRate();
//        System.out.println("Sample Rate: " + baseFormat.getSampleRate());
//        System.out.println("Frame Size: " + baseFormat.getFrameSize());
//        System.out.println("Frame Rate: " + baseFormat.getFrameRate());
//        System.out.println("Sample Size In Bits: " + baseFormat.getSampleSizeInBits());
//        AudioInputStream in = AudioSystem.getAudioInputStream(file);       

    }
}
