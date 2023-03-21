package net.ent.etrs.commons.utils.fileutils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileUtils {
    private FileUtils() {
    }

    public static Stream<String> extraireLignes(String chemin) throws LectureImpossibleException {
        Path p = Paths.get(chemin);

        try {
            return Files.lines(p);
        } catch (IOException var3) {
            throw new LectureImpossibleException("Impossible de lire le chemin SGF", var3);
        }
    }

    public static Stream<String> extraireLignesResources(String cheminPackage) throws LectureImpossibleException {
        try {
            Path p = Paths.get(FileUtils.class.getResource(cheminPackage).toURI());
            return Files.lines(p);
        } catch (IOException | URISyntaxException var2) {
            throw new LectureImpossibleException("Impossible de lire le chemin resource package", var2);
        }
    }
}

