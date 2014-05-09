package com.kronosad.modpack.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Author russjr08
 * Created at 5/8/14
 */
public class MainWindowController implements Initializable {
    @FXML
    private ImageView bg;

    // Start Page
    @FXML
    AnchorPane pageStart;
    @FXML
    ChoiceBox<String> packList;
    @FXML
    Button btnUpdatePack, btnRemovePack;

    // New Pack Page
    @FXML
    AnchorPane pageNewPack;


    final String PANEL_BG_COLOR = "-fx-background-color: #d4d4d4; ";
    final String NEW_PACK_TEXT = "Add a New Pack...";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bg.setImage(new Image("dirt_background.png"));
        bg.setFitWidth(800);
        bg.setFitHeight(600);

        pageStart.setEffect(new DropShadow(15.0, Color.BLACK));

        ObservableList<String> packs = FXCollections.observableArrayList();
        packs.add(NEW_PACK_TEXT);

        packList.setItems(packs);
        packList.getSelectionModel().selectFirst();

        btnUpdatePack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (packList.getValue().equalsIgnoreCase(NEW_PACK_TEXT)) {
                    pageNewPack.setVisible(true);
                }
            }
        });

    }
}
