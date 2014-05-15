package com.kronosad.modpack.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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


    }
}
