package net.ent.etrs.spotifrice;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.net.URISyntaxException;

public class EssaiLecture extends Application {
    public static void main(String[] args) throws URISyntaxException {
        launch(args);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //TEST de lecture

        //Chemin SGF
        //Media m = new Media(new URL( "file:///D:/mp3/funk/EarthWind&Fire-September.mp3").toString());

        Media m = new Media("file:///D:/mp3/AC-DC/HellsBells.mp3");

        //Chemin package
        //Media media = new Media(Essai.class.getResource("/music/AC-DC/HellsBells.mp3").toString());


        MediaPlayer player = new MediaPlayer(m);
        player.play();


    }
}		




