package com.kronosad.modpack.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.kronosad.projects.modpack.common.objects.Modpack;
import com.kronosad.projects.modpack.common.objects.util.OperatingSystem;
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
import org.apache.commons.io.FileUtils;
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

    public static final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();


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
                chooser.setTitle("Pick a local Modpack JSON file...");
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
                Modpack pack = null;

                // Yeah yeah.. I know this is a little sketchy. Have a better way? Submit a PR.
                if (txtPackLocation.getText().startsWith("http") || txtPackLocation.getText().startsWith("www")) {
                    // Internet location
                    try {
                        InputStream in = new URL(txtPackLocation.getText()).openStream();
                        String textFromInternet = IOUtils.toString(in);
                        System.out.println(textFromInternet);

                        try {
                            pack = new Gson().fromJson(textFromInternet, Modpack.class);
                            System.out.println(pack.toString());
                            if (pack.getMods() == null) {
                                throw new IllegalArgumentException("The Mods list is empty, this Modpack file is inavlid.");
                            }
                        } catch (Exception e) {
                            ErrorWindowController.openError().constructError("Invalid Modpack Found!",
                                    "The downloaded Modpack file is malformed / is not constructed properly!");
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        ErrorWindowController.openError().constructError("Invalid Location", "There was an error accessing this URL, double check it's validity!");
                    }

                } else {
                    try {
                        pack = new Gson().fromJson(FileUtils.readFileToString(new File(txtPackLocation.getText())), Modpack.class);
                        System.out.println(pack.toString());
                        if (pack.getMods() == null) {
                            ErrorWindowController.openError().constructError("No Mods found!",
                                    "The Mods list is empty, this Modpack file is inavlid.");

                            throw new IllegalArgumentException("The Mods list is empty, this Modpack file is inavlid.");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        ErrorWindowController.openError().constructError("Error!", "There was an error opening the file...");
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                        ErrorWindowController.openError().constructError("Invalid Modpack Found!"
                                , "The downloaded Modpack file is malformed / is not constructed properly!");
                    }
                }

                pageStart.setVisible(true);
                pageNewPack.setVisible(false);

                if (pack != null) {
                    try {
                        File packFile = new File(OperatingSystem.getCurrentOperatingSystem().getModpacksFolder(), pack.getName() + ".json");
                        System.out.println("Operating System: " + OperatingSystem.getCurrentOperatingSystem().toString());
                        System.out.println("Using Modpack Location: " + packFile);
                        FileUtils.writeStringToFile(packFile, prettyGson.toJson(pack));
                    } catch (Exception e) {
                        e.printStackTrace();
                        ErrorWindowController.openError().constructError("Cannot detect Operating System",
                                "Sorry, but this app doesn't support your operating system!");
                    }
                }

                // TODO: Add new pack to the dropdown here.
            }
        });

        // Download page logic (if any?)

    }

    public void reloadPacks() {

    }
}
