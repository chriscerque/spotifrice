package net.ent.etrs.spotifrice.controller;


import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import net.ent.etrs.commons.utils.mp3Utils.Mp3Analyzer;
import net.ent.etrs.commons.utils.mp3Utils.SongEntry;
import net.ent.etrs.spotifrice.model.entities.Album;
import net.ent.etrs.spotifrice.model.entities.RessourceMp3;
import net.ent.etrs.spotifrice.model.facade.FacadeMetier;
import net.ent.etrs.spotifrice.model.facade.exceptions.BusinessException;
import net.ent.etrs.spotifrice.view.converter.GenreConverter;
import net.ent.etrs.spotifrice.view.references.Screens;
import net.ent.etrs.spotifrice.view.utils.AlerteUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ListingRessourceMp3Presenter {

    @FXML
    private TableView<RessourceMp3> tvRessourceMp3;

    @FXML
    private TableColumn<RessourceMp3, String> tcTitre;

    @FXML
    private TableColumn<RessourceMp3, String> tcAlbum;

    @FXML
    private TableColumn<RessourceMp3, String> tcGenre;

    @FXML
    private MenuItem miPlay, miStop, miSupprimer;

    private MediaPlayer player;

    private FacadeMetier leMetier = new FacadeMetier();

    private ObservableList<RessourceMp3> ol = FXCollections.observableArrayList();

    private RessourceMp3 mp3Selectionne;

    @FXML
    public void initialize() {
        try {
            this.ol.addAll(leMetier.listerLesRessourceMp3());

            this.tvRessourceMp3.setItems(ol);
            miStop.setDisable(true);

            this.tcTitre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getTitre()));
            this.tcAlbum.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getSonAlbum().getNom()));
            this.tcGenre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getSonGenre().toString()));

            //Sur l'appui de la touche F11, la musique commence a lire
//        tvRessourceMp3.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
//            public void handle(KeyEvent keyevent) {
//                if (keyevent.getCode().equals(KeyCode.F11)) {
//                    play();
//                }
//            }
//        });
            tvRessourceMp3.addEventHandler(KeyEvent.KEY_PRESSED, this::handlePlay);
        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
        }
    }

    public void importer() {
        try {
            FileChooser fileChooser = new FileChooser();

            fileChooser.setTitle("Selectionner les fichiers mp3");

            fileChooser.setInitialDirectory(new File("D:\\"));

            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("mp3 files", "*.mp3"));

            List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);


            if (selectedFiles != null) {
                int cpt = 0;
                for (File file : selectedFiles) {
                    //Tester en 1 er lieu si la ressourcemp3 n'est pas deja referencée en base
                    //requete select r from RessourceMp3 r  where r.chemminComplet=:param ( param=file.getAbsolutePath())

                    if (!leMetier.estDejaReference(file.getAbsolutePath())) {
                        //1-Besoin de PRSIAnalyzer
                        SongEntry entree = Mp3Analyzer.analyze(new File(file.getAbsolutePath()));
                        //TODO
                        System.out.println("entree : " + entree);
                        //2-A partir de ce SongEntry : je reinstancie une ressourceMp3
                        RessourceMp3 mp3 = new RessourceMp3();
                        mp3.setTitre(entree.getTitle());
                        mp3.setCheminComplet(file.getAbsolutePath());
                        mp3.setTaille(entree.getSize());
                        mp3.setSonGenre(GenreConverter.convertir(entree.getMusicGenre()));
                        //Test (GENRE_INCONNU)
                        //mp3.setSonGenre(GenreConverter.convertir("Groove"));

                        Optional<Album> optAlbum = null;

                        optAlbum = leMetier.recupererAlbumParSonNom(entree.getAlbum());

                        Album album;
                        //Si il existe deja en base j'ai un album attaché sinon j'en crée un
                        if (optAlbum.isEmpty()) {
                            //Attention l'album est detaché
                            album = new Album();
                            album.setNom(entree.getAlbum());
                            album.setArtiste(entree.getArtist());

                        } else {
                            album = optAlbum.get();
                        }

                        mp3.setSonAlbum(album);
                        //A titre de coherene "memoire"
                        album.ajouterRessourceMp3(mp3);

                        //Persistence bdd
                        leMetier.ajouter(mp3);
                        //Visuel
                        ol.add(mp3);
                        cpt++;
                    }

                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(cpt + " mp3 référencés");
                alert.showAndWait();
            } else {
                System.out.println("Selection mp3 annulée");
            }
        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
        }
    }

    public void supprimer() {
        try {
            RessourceMp3 mp3Selectionne = this.tvRessourceMp3.getSelectionModel().getSelectedItem();
            leMetier.supprimer(mp3Selectionne);
            this.ol.remove(mp3Selectionne);
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }
    }

    public void detailler() {
        RessourceMp3 mp3Selectionne = this.tvRessourceMp3.getSelectionModel().getSelectedItem();
        //On crée juste une boite de dialogue d'information
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Détails pour: " + mp3Selectionne.getTitre());
        alert.setHeaderText("Détails pour: " + mp3Selectionne.getTitre());

        //Je recupere le layout de la boite de dialogue (DialogPane)
        DialogPane dialogPane = alert.getDialogPane();
        //Je lui applique le css ( en programmatique)
        //dialogPane.getStylesheets().add(getClass().getResource("/view/css/style.css").toExternalForm());
        dialogPane.getStyleClass().add("detailsDialog");


        //Je mets en contenu le ToString de la voiture
        StringBuilder sb = new StringBuilder();
        sb.append("Artiste:" + mp3Selectionne.getSonAlbum().getArtiste() + "\n");
        sb.append("Album  :" + mp3Selectionne.getSonAlbum().getNom() + "\n");
        sb.append("Genre  :" + mp3Selectionne.getSonGenre() + "\n");
        sb.append("Chemin :" + mp3Selectionne.getCheminComplet() + "\n");
        sb.append("taille :" + mp3Selectionne.getTaille() + " oct.\n");
        alert.setResizable(true);
        alert.setContentText(sb.toString());

        alert.showAndWait();
    }

    public void play() {
        this.mp3Selectionne = this.tvRessourceMp3.getSelectionModel().getSelectedItem();
        if (this.mp3Selectionne != null) {
            String chemin = mp3Selectionne.getCheminComplet();
            String cheminRetail = String.format("file:///%s", chemin).replace("\\", "/");
            Media media = new Media(cheminRetail);

            MediaPlayer player = new MediaPlayer(media);

            this.player = player;
            //Je grise les menuItem play et supprimer
            //Il faudrait aussi griser le menuItem "les albums"
            miPlay.setDisable(true);
            miSupprimer.setDisable(true);
            miStop.setDisable(false);
            player.play();
        }

    }

    public void stop() {
        miPlay.setDisable(false);
        miStop.setDisable(true);
        this.player.stop();

    }

    public void voirLesAlbums() {
        Scene sceneCourante = tvRessourceMp3.getScene();
        try {
            VBox sceneListingAlbum = (VBox) FXMLLoader.load(getClass().getResource(Screens.SCREEN_LISTER_ALBUMS));
            sceneCourante.setRoot(sceneListingAlbum);
        } catch (IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
        }

    }

    public void quitter() {
        Platform.exit();
    }

    private void handlePlay(KeyEvent keyEvent) {
        //TODO
        System.out.println(keyEvent.getCode());
        switch (keyEvent.getCode()) {
            case F11:
                play();
                break;
            case F12:
                stop();
                break;
            case PLAY:
                play();
                break;
            case RIGHT:
                play();
                break;
            case LEFT:
                stop();
                break;
            case DELETE:
                supprimer();
                break;

        }
//        if (keyEvent.getCode().equals(KeyCode.F11)) {
//            play();
//        } else {
//            stop();
//        }
    }
}
