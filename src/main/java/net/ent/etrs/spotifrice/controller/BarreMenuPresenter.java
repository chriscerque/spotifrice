package net.ent.etrs.spotifrice.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import net.ent.etrs.spotifrice.view.references.Screens;
import net.ent.etrs.spotifrice.view.utils.FxmlUtils;

import java.io.IOException;

public class BarreMenuPresenter {
    @FXML
    private MenuBar laBarre;

    public void voirLesRessourceMp3() throws IOException {
//        Scene sceneCourante = laBarre.getScene();
//        VBox vbox = (VBox) FXMLLoader.load(this.getClass().getResource(Screens.SCREEN_LISTER_MP3));
//        sceneCourante.setRoot(vbox);

        FxmlUtils.chargerScene(Screens.SCREEN_LISTER_MP3);
    }

    public void voirLesAlbums() throws IOException {
//        Scene sceneCourante = laBarre.getScene();
//        VBox vbox = (VBox) FXMLLoader.load(this.getClass().getResource(Screens.SCREEN_LISTER_ALBUMS));
//        sceneCourante.setRoot(vbox);
        FxmlUtils.chargerScene(Screens.SCREEN_LISTER_ALBUMS);
    }

    public void quitter() {
        Platform.exit();
    }
}
