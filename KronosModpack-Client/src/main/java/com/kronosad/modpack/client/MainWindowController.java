package com.kronosad.modpack.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.kronosad.projects.modpack.common.objects.Modpack;
import com.kronosad.projects.modpack.common.objects.PackFile;
import com.kronosad.projects.modpack.common.objects.mclauncher.Profile;
import com.kronosad.projects.modpack.common.objects.util.LauncherUtils;
import com.kronosad.projects.modpack.common.objects.util.OperatingSystem;
import javafx.application.Platform;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

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
    private AnchorPane pageDownload;
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

        reloadPacks();

        packList.setItems(packs);
        packList.getSelectionModel().selectFirst();

        // Main (Start) page buttons
        btnUpdatePack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (packList.getValue().equals(NEW_PACK_TEXT)) {
                    pageNewPack.setVisible(true);
                } else {
                    // Verify all files and redownload any necessary ones.
                    pageStart.setVisible(false);
                    pageDownload.setVisible(true);

                    try {
                        final File packFolder = new File(OperatingSystem.getCurrentOperatingSystem().getModpacksFolder(), packList.getValue());
                        Modpack pack = new Gson().fromJson(FileUtils.readFileToString(new File
                                (OperatingSystem.getCurrentOperatingSystem().getModpacksFolder(), packList.getValue() + ".json"))
                                , Modpack.class);

                        // If the pack was obtained from the internet, update it and re add it to the launcher.
                        if (pack.getInternetURL() != null) {
                            String url = pack.getInternetURL();
                            pack = Modpack.getModpackFromInternet(url);
                            pack.setInternetURL(url);


                            FileUtils.writeStringToFile((new File
                                    (OperatingSystem.getCurrentOperatingSystem().getModpacksFolder(), packList.getValue() + ".json")), prettyGson.toJson(pack));

                            LauncherUtils.removeProfileFromLauncher(pack.getName());

                            Profile profile = new Profile();
                            profile.setName(pack.getName());
                            profile.setLastVersionId(pack.getForgeVersion());
                            profile.setGameDir(OperatingSystem.getCurrentOperatingSystem().getModpacksFolder().getAbsolutePath() + File.separator + pack.getName());

                            LauncherUtils.addProfileToLauncher(profile);
                        }

                        final List<PackFile> filesToDownload = new ArrayList<PackFile>();
                        for (PackFile file : pack.getFiles()) {
                            if (file != null) {
                                File modFile = new File(packFolder, file.getPath());
                                System.out.println(modFile.getAbsolutePath());

                                if (modFile.exists()) {
                                    if (!com.kronosad.projects.modpack.common.objects.util.FileUtils.md5Match(modFile, file.getMd5())) {
                                        filesToDownload.add(file);
                                    }
                                } else {
                                    filesToDownload.add(file);
                                }
                            }
                        }

                        lblPack.setText("Downloading: " + pack.getName());
                        final AtomicInteger filesDownloaded = new AtomicInteger();
                        filesDownloaded.set(0);

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                for (final PackFile file : filesToDownload) {
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            lblFile.setText(file.getName());
                                            //TODO: Fix progress indicator
//                                            progressIndicator.setProgress((filesDownloaded.getAndIncrement() / filesToDownload.size()) * 100);

                                        }
                                    });
                                    File destination = new File(packFolder, file.getPath());
                                    if (destination.exists()) {
                                        destination.delete();
                                    }
                                    try {
                                        FileUtils.copyURLToFile(new URL(file.getUrl()), destination);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                }
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        pageStart.setVisible(true);
                                        pageDownload.setVisible(false);
                                    }
                                });

                                try {
                                    Process myProcess = new ProcessBuilder("java", "-jar " + OperatingSystem.getCurrentOperatingSystem().getMinecraftFolder().getAbsolutePath() + File.separator + "launcher.jar").start();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                            }
                        }).start();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        btnRemovePack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!packList.getValue().equals(NEW_PACK_TEXT)) {
                    try {
                        File packJson = new File(OperatingSystem.getCurrentOperatingSystem().getModpacksFolder(), packList.getValue() + ".json");
                        if (packJson.exists()) {
                            packJson.delete();
                            LauncherUtils.removeProfileFromLauncher(packList.getValue());
                            reloadPacks();
                        } else {
                            ErrorWindowController.openError().constructError("File Error",
                                    "Couldn't delete file.. The pack may already be deleted! Try restarting the app.");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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
                Modpack pack = null;

                // Yeah yeah.. I know this is a little sketchy. Have a better way? Submit a PR.
                if (txtPackLocation.getText().startsWith("http") || txtPackLocation.getText().startsWith("www")) {
                    // Internet location
                    try {

                        pack = Modpack.getModpackFromInternet(txtPackLocation.getText());
                        try {
                            System.out.println(pack.toString());
                            if (pack.getFiles() == null) {
                                throw new IllegalArgumentException("The Mods list is empty, this Modpack file is inavlid.");
                            }
                            pack.setInternetURL(txtPackLocation.getText());
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
                        if (pack.getFiles() == null) {
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

                        Profile profile = new Profile();
                        profile.setName(pack.getName());
                        profile.setLastVersionId(pack.getForgeVersion());
                        profile.setGameDir(OperatingSystem.getCurrentOperatingSystem().getModpacksFolder().getAbsolutePath() + File.separator + pack.getName());

                        // Check to see if Forge is installed

                        if (pack.getForgeVersion() != null) {
                            File forgeFolder = new File(OperatingSystem.getCurrentOperatingSystem().getMinecraftFolder(), "versions/" + pack.getForgeVersion());
                            if (!forgeFolder.exists()) {
                                ErrorWindowController.openError().constructError("Forge Not Installed"
                                        , "Warning: You do not have the required version of Forge (" + pack.getForgeVersion() + ") installed! Without Forge, the game will launch as Vanilla. http://files.minecraftforge.net/");
                            }
                        }
                        LauncherUtils.addProfileToLauncher(profile);
                    } catch (IOException e) {
                        e.printStackTrace();
                        ErrorWindowController.openError().constructError("File Error", "Unable to save Modpack files!");
                    } catch (Exception e) {
                        e.printStackTrace();
                        ErrorWindowController.openError().constructError("Cannot detect Operating System",
                                "Sorry, but this app doesn't support your operating system!");
                    }
                }

                reloadPacks();
            }
        });

        // Download page logic (if any?)

    }

    public void reloadPacks() {
        packs.clear();
        packs.add(NEW_PACK_TEXT);

        try {
            if (OperatingSystem.getCurrentOperatingSystem().getModpacksFolder().listFiles() != null) {
                for (File file : OperatingSystem.getCurrentOperatingSystem().getModpacksFolder().listFiles()) {
                    if (file.getName().endsWith(".json")) {
                        Modpack modPack = new Gson().fromJson(FileUtils.readFileToString(file), Modpack.class);
                        packs.add(modPack.getName());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
