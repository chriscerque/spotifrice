package net.ent.etrs.spotifrice.view.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;

import java.io.IOException;

public class FxmlUtils {


    @Getter
    private static Scene scene = new Scene(new AnchorPane(), 600, 400);

    public static Scene initialiserScene(String name) {
        try {
            scene = new Scene(getFxmlLoader(name));
        } catch (IOException e) {
            e.printStackTrace();
            AlerteUtils.afficherMessageDansAlerte("Une erreure s'est produite lors de l''initialisation de la scene.", Alert.AlertType.ERROR);
        }
        return scene;
    }

    /**
     * Permet de charger une page fxml dans une scene en spécifiant le controller.
     *
     * @param sceneCourante
     * @param screen        page fxml
     * @param controller
     * @throws IOException
     */
    public static void chargerScene(Scene sceneCourante, String screen, Object controller) {
        try {
            scene.setRoot(getFxmlLoader(sceneCourante, screen, controller));
        } catch (IOException e) {
            e.printStackTrace();
            AlerteUtils.afficherMessageDansAlerte("Une erreure s'est produite lors du chargement de la page." + screen, Alert.AlertType.ERROR);
        }
    }

    /**
     * Permet de charger une page fxml dans une scene en spécifiant le controller.
     *
     * @param screen     page fxml
     * @param controller
     * @throws IOException
     */
    public static void chargerScene(String screen, Object controller) {
        try {
            scene.setRoot(getFxmlLoader(null, screen, controller));
        } catch (IOException e) {
            e.printStackTrace();
            AlerteUtils.afficherMessageDansAlerte("Une erreure s'est produite lors du chargement de la page." + screen, Alert.AlertType.ERROR);
        }
    }

    private static Parent getFxmlLoader(Scene sceneCourante, String screen, Object controller) throws IOException {
        if (sceneCourante != null) {
            scene = sceneCourante;
        }
        FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(screen));
        if (controller != null) {
            loader.setController(controller);
        }
        return loader.load();
    }

    private static Parent getFxmlLoader(Scene sceneCourante, String screen) throws IOException {
        if (sceneCourante != null) {
            scene = sceneCourante;
        }
        FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(screen));

        return loader.load();
    }

    private static Parent getFxmlLoader(String screen) throws IOException {
        FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(screen));

        return loader.load();
    }

    /**
     * Permet de charger une page fxml dans une scene.
     *
     * @param sceneCourante
     * @param screen        page fxml
     * @throws IOException
     */
    public static void chargerScene(Scene sceneCourante, String screen) {
        FxmlUtils.chargerScene(sceneCourante, screen, null);
    }

    /**
     * Permet de charger une page fxml dans une scene courante.
     *
     * @param screen page fxml
     * @throws IOException
     */
    public static void chargerScene(String screen) {
        FxmlUtils.chargerScene(null, screen, null);
    }
}
