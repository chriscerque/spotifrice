package net.ent.etrs.spotifrice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Lanceur extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox vbox = (VBox) FXMLLoader.load(this.getClass().getResource("/view/ListingRessourceMp3.fxml"));
        Scene scene = new Scene(vbox, 493, 272);
        primaryStage.setTitle("Spotifrice");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
