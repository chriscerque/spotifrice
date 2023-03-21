package net.ent.etrs.spotifrice.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public final class AlerteUtils {

    private AlerteUtils() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Affiche le messages le message de l'exceptione passée en paramètre avec le niveau d'alerte aussi passé en paramètre
     *
     * @param e             : l'exception.
     * @param niveauAlerte: INFO,ERROR,WARNING
     */
    public static void afficherExceptionDansAlerte(Exception e, AlertType niveauAlerte) {
        Alert a = new Alert(niveauAlerte);
        a.setContentText(e.getMessage());
        a.showAndWait();
    }
}
