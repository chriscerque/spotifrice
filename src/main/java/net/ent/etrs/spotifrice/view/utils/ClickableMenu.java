package net.ent.etrs.spotifrice.view.utils;

import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class ClickableMenu extends Menu {

    private final Label label;

    /**
     * Créer un ClickableMenu sans titre.
     */
    public ClickableMenu() {
        this("");
    }

    /**
     * Créer un ClickableMenu avec un titre.
     *
     * @param title titre initial
     */
    public ClickableMenu(String title) {
        // dummy item is needed to make JavaFX "believe", that menu item was pressed
        MenuItem dummyItem = new MenuItem();
        dummyItem.setVisible(false);
        getItems().add(dummyItem);

        this.label = new Label();
        label.setText(title);
        label.setOnMouseClicked(evt -> {
            // forced child MenuItem click (this item is hidden, so this action is not visible but triggers parent "onAction" event handler anyway)
            dummyItem.fire();
        });
        setGraphic(label);
    }

    /**
     * This method should be used instead of {@link #getText() getText()} method.
     *
     * @return title of this Menu
     */
    public String getTitle() {
        return label.getText();
    }

    /**
     * This method should be used instead of {@link #setText() setText()} method.
     *
     * @param text new title of this menu
     */
    public void setTitle(String text) {
        label.setText(text);
    }

    /**
     * This method should be used instead of {@link #textProperty() textProperty()} method.
     *
     * @return title property
     */
    public StringProperty titleProperty() {
        return label.textProperty();
    }

}
