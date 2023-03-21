package net.ent.etrs.spotifrice.view.utils;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.ent.etrs.spotifrice.view.references.Screens;

import java.util.Optional;

public final class AlerteUtils {

    private AlerteUtils() {
    }

    /**
     * Affiche le messages de l'exceptione passée en paramètre avec le niveau
     * d'alerte aussi passé en paramètre.
     *
     * @param e             : l'exception.
     * @param niveauAlerte: INFO,ERROR,WARNING
     */
    public static void afficherExceptionDansAlerte(Exception e, AlertType niveauAlerte) {
        Alert a = new Alert(niveauAlerte);
        a.setContentText(e.getMessage());
        a.showAndWait();
    }

    /**
     * Affiche le messages passé en paramètre avec le niveau d'alerte aussi passé en
     * paramètre.
     *
     * @param msg           : le message.
     * @param niveauAlerte: INFO,ERROR,WARNING
     * @return renvoie true si un bouton "ok" a été cliqué
     */
    public static Boolean afficherMessageDansAlerte(String msg, AlertType niveauAlerte) {
        Boolean retour = false;
        Alert a = new Alert(niveauAlerte);
        a.setContentText(msg);
        Optional<ButtonType> oBtnType = a.showAndWait();

        if (oBtnType.get().getButtonData().equals(ButtonType.OK.getButtonData())) {
            retour = true;
        }

        return retour;
    }

    /**
     * Affiche le messages passé en paramètre avec le niveau d'alerte aussi passé en
     * paramètre.
     *
     * @param titre         : le titre de la fenêtre.
     * @param msg           : le message.
     * @param niveauAlerte: INFO,ERROR,WARNING
     * @return renvoie true si un bouton "ok" a été cliqué
     */
    public static Boolean afficherMessageDansAlerte(String titre, String msg, AlertType niveauAlerte) {
        Boolean retour = false;
        Alert a = new Alert(niveauAlerte);
        a.setTitle(titre);
        a.setContentText(msg);
        Optional<ButtonType> oBtnType = a.showAndWait();

        if (oBtnType.get().getButtonData().equals(ButtonType.OK.getButtonData())) {
            retour = true;
        }

        return retour;
    }

    /**
     * Affiche le messages passé en paramètre avec le niveau d'alerte aussi passé en
     * paramètre.
     *
     * @param titre         : le titre de la fenêtre.
     * @param header        : zone haute de la pop-up
     * @param msg           : le message.
     * @param niveauAlerte: INFO,ERROR,WARNING
     * @return renvoie true si un bouton "ok" a été cliqué
     */
    public static Boolean afficherMessageDansAlerte(String titre, String header, String msg, AlertType niveauAlerte) {
        Boolean retour = false;
        Alert a = new Alert(niveauAlerte);
        a.setTitle(titre);
        a.setHeaderText(header);
        a.setContentText(msg);
        // Je recupere le panneau du dialog pour appliquer le style a la dialogbox
        DialogPane dialogPane = a.getDialogPane();
        dialogPane.getStylesheets().add(AlerteUtils.class.getResource(Screens.SCREEN_APPLICATION_CSS).toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        Optional<ButtonType> oBtnType = a.showAndWait();

        if (oBtnType.get().getButtonData().equals(ButtonType.OK.getButtonData())) {
            retour = true;
        }

        return retour;
    }

    /**
     * Affiche le messages passé en paramètre avec le niveau d'alerte aussi passé en
     * paramètre.
     *
     * @param titre         : le titre de la fenêtre.
     * @param header        : zone haute de la pop-up
     * @param msg           : le message.
     * @param niveauAlerte: INFO,ERROR,WARNING
     * @param cheminImg     : url vers l'image à afficher dans le header
     * @return renvoie true si un bouton "ok" a été cliqué
     */
    public static Boolean afficherMessageDansAlerte(String titre, String header, String msg, AlertType niveauAlerte, String cheminImg) {
        Boolean retour = false;
        Alert a = new Alert(niveauAlerte);
        a.setTitle(titre);
        a.setHeaderText(header);
        a.setContentText(msg);
        // Je recupere le panneau du dialog pour appliquer le style a la dialogbox
        DialogPane dialogPane = a.getDialogPane();
        dialogPane.getStylesheets().add(AlerteUtils.class.getResource(Screens.SCREEN_APPLICATION_CSS).toExternalForm());
        dialogPane.getStyleClass().add("myDialog");


        //TODO sout
        System.out.println("cheminImg : " + cheminImg);

        Image img;
        try {
            img = new Image(cheminImg, 100.0, 40.0, true, true);
        } catch (NullPointerException | IllegalArgumentException e) {

            img = new Image("/garage/view/resources/NULL.png");
        }

        // Creation d'un ImageView pour y mettre l'image
        ImageView iv = new ImageView();
        iv.setImage(img);

        // Insertion de l'imageView dans le header du dialogPane
        a.setGraphic(iv);

        Optional<ButtonType> oBtnType = a.showAndWait();

        if (oBtnType.get().getButtonData().equals(ButtonType.OK.getButtonData())) {
            retour = true;
        }

        return retour;
    }

}
