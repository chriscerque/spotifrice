package net.ent.etrs.commons.utils.serializationUtils;


import net.ent.etrs.commons.utils.serializationUtils.exceptions.Messages;
import net.ent.etrs.commons.utils.serializationUtils.exceptions.SerializationException;

import java.io.*;

/**
 * Classe utilitaire offrant les méthodes de serialisation et deserialisation d'objets
 *
 * @author nicolas.magniez
 */
public class SerializationUtils {


    private SerializationUtils() {

    }

    /**
     * Sauvegarde des Objets Serializable de type T (objs) dans un fichier dont le chemin est donn� en param�tre (chemin)
     *
     * @param chemin String
     * @param objs   T
     * @throws SerializationException
     */
    public static <T extends Serializable> void save(final String chemin, final T objs) throws SerializationException {
        try (FileOutputStream fos = new FileOutputStream(chemin);
             ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(objs);

        } catch (IOException e) {
            throw new SerializationException(Messages.SERIALISATION_IMPOSSIBLE, e);
        }

    }

    /**
     * charge des Objets Serializable de type T  d'un fichier  dont le chemin est donn� en param�tre (chemin) dans une List<T>
     *
     * @param chemin String
     * @return
     * @throws SerializationException
     */
    public static <T extends Serializable> T load(final String chemin) throws SerializationException {
        try (FileInputStream fis = new FileInputStream(chemin);
             ObjectInputStream ois = new ObjectInputStream(fis);) {

            return (T) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new SerializationException(Messages.DESERIALISATION_IMPOSSIBLE, e);
        }
    }

}
