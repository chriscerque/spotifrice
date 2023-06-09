package net.ent.etrs.spotifrice.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import net.ent.etrs.spotifrice.model.entities.Album;
import net.ent.etrs.spotifrice.model.entities.RessourceMp3;
import net.ent.etrs.spotifrice.model.facade.FacadeMetier;
import net.ent.etrs.spotifrice.model.facade.exceptions.BusinessException;
import net.ent.etrs.spotifrice.view.utils.AlerteUtils;

import java.util.Collection;

public class ListingAlbumPresenter {

    @FXML
    private ComboBox<Album> cbAlbums;

    @FXML
    private Label lblRessourceMp3;

    private ObservableList<Album> ol = FXCollections.observableArrayList();

    private FacadeMetier leMetier = new FacadeMetier();

    public void initialize() {
        try {
            this.ol.addAll(leMetier.listerLesAlbums());
            cbAlbums.setItems(ol);
        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
        }

    }

    public void listerContenuAlbum() {
        Collection<RessourceMp3> tracks = cbAlbums.getSelectionModel().getSelectedItem().getLstRessources();
        StringBuilder sb = new StringBuilder();
        for (RessourceMp3 track : tracks) {
            sb.append(String.format("%s%n", track.getTitre()));
        }
        lblRessourceMp3.setText(sb.toString());
    }
}
