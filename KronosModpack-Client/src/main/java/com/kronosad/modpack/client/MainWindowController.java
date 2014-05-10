package com.kronosad.modpack.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
    private AnchorPane pageStart;
    @FXML
    private ChoiceBox<String> packList;
    @FXML
    private Button btnUpdatePack, btnRemovePack;

    // New Pack Page
    @FXML
    private AnchorPane pageNewPack;
    @FXML
    private Button btnFile, btnAddPack, btnCancel;
    @FXML
    private TextField txtPackLocation;

    // Download Page
    @FXML
    private Label lblPack, lblFile;
    @FXML
    private ProgressIndicator progressIndicator;

    // Constants
    final String NEW_PACK_TEXT = "Add a New Pack...";


    private ObservableList<String> packs = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bg.setImage(new Image("dirt_background.png"));
        bg.setFitWidth(800);
        bg.setFitHeight(600);

        pageStart.setEffect(new DropShadow(15.0, Color.BLACK));

        packs.add(NEW_PACK_TEXT);

        packList.setItems(packs);
        packList.getSelectionModel().selectFirst();

        // Main (Start) page buttons
        btnUpdatePack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (packList.getValue().equals(NEW_PACK_TEXT)) {
                    pageNewPack.setVisible(true);
                }
            }
        });

        btnRemovePack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!packList.getValue().equals(NEW_PACK_TEXT)) {
                    packs.removeAll(packList.getValue());

                    // TODO: Also remove Modpack from hard drive.
                }
            }
        });


        btnFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser chooser = new FileChooser();
                chooser.setTitle("Pick a local modpack JSON file...");
                File file = chooser.showOpenDialog(null);
                txtPackLocation.setText(file.getAbsolutePath());

            }
        });

        // Add Pack Page buttons
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pageNewPack.setVisible(false);
                pageStart.setVisible(true);
            }
        });

        btnAddPack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO: Things... Magic internet/file things.

                // Yeah yeah.. I know this is a little sketchy. Have a better way? Submit a PR.
                if (txtPackLocation.getText().startsWith("http") || txtPackLocation.getText().startsWith("www")) {
                    // Internet location
                    try {
                        InputStream in = new URL(txtPackLocation.getText()).openStream();
                        System.out.println(IOUtils.toString(in));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                pageStart.setVisible(true);
                pageNewPack.setVisible(false);

                // TODO: Add new pack to the dropdown here.
            }
        });

        // Download page logic (if any?)

    }
}
