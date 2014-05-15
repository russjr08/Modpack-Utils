package com.kronosad.modpack.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kronosad.projects.modpack.common.objects.mclauncher.Profile;
import com.kronosad.projects.modpack.common.objects.util.LauncherUtils;
import com.kronosad.projects.modpack.common.objects.util.OperatingSystem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

/**
 * Author russjr08
 * Created at 5/8/14
 */
public class Main extends Application {
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();


    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MainWindow.fxml"));

        Scene scene = new Scene(root, 800, 450);

        stage.setTitle("Modpack Utils");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();


        Profile profile = new Profile();
        profile.setName("TMPI");
        profile.setGameDir(OperatingSystem.getCurrentOperatingSystem().getModpacksFolder().getAbsolutePath() + File.separator + "TMPI");
        profile.setLastVersionId("1.7.2-Forge10.12.1.1075");
        profile.setLauncherVisibilityOnGameClose("keep the launcher open");

        LauncherUtils.addProfileToLauncher(profile);

    }
}
