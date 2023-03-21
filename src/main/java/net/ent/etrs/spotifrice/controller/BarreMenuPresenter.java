package net.ent.etrs.spotifrice.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import net.ent.etrs.spotifrice.view.Screens;

import java.io.IOException;

public class BarreMenuPresenter {
    @FXML
    private MenuBar laBarre;

    public void voirLesRessourceMp3() throws IOException {
        Scene sceneCourante = laBarre.getScene();
        VBox vbox = (VBox) FXMLLoader.load(this.getClass().getResource(Screens.SCREEN_LISTER_MP3));
        sceneCourante.setRoot(vbox);
    }

    public void voirLesAlbums() throws IOException {
        Scene sceneCourante = laBarre.getScene();
        VBox vbox = (VBox) FXMLLoader.load(this.getClass().getResource(Screens.SCREEN_LISTER_ALBUMS));
        sceneCourante.setRoot(vbox);
    }

    public void quitter() {
        Platform.exit();
    }
}
