package com.kronosad.modpack.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kronosad.projects.modpack.common.objects.Mod;
import com.kronosad.projects.modpack.common.objects.Modpack;
import com.kronosad.projects.modpack.common.objects.enums.DistributionType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Arrays;

/**
 * Author russjr08
 * Created at 5/8/14
 */
public class Main extends Application {

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

        Modpack pack = new Modpack();
        pack.setType(DistributionType.INDIVIDUAL_FILES);

        pack.setForgeVersion("1.7.2");
        pack.setMd5("IM AN MD5");
        pack.setName("Testing pack pls ignore");
        pack.setVersion(1.0);

        Mod testMod = new Mod();
        testMod.setVersion(1.0);
        testMod.setMd5("IM AN MD5 AGAIN");
        testMod.setName("Test mod pls ignore");
        testMod.setAuthor("Roosall Rikardsun");
        testMod.setPath("mods/TestModPlsIgnore.jar");
        testMod.setUrl("http://gofuckyourself.tristen.io/TMPI.jar");

        pack.setMods(Arrays.asList(testMod));

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(pack));

    }
}
