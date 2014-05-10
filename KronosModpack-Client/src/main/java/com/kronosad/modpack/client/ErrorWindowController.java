package com.kronosad.modpack.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Author russjr08
 * Created at 5/10/14
 */
public class ErrorWindowController implements Initializable {

    @FXML
    private Label messageLabel, detailsLabel;
    @FXML
    private Button okButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ((Stage) okButton.getScene().getWindow()).close();
            }
        });
        okButton.setDefaultButton(true);
    }

    public void constructError(final String title, final String message) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                messageLabel.setText(title);
                detailsLabel.setText(message);
            }
        });
    }

    public static ErrorWindowController openError() {
        FXMLLoader loader = new FXMLLoader();
        Parent page = null;
        loader.setLocation(ErrorWindowController.class.getClassLoader().getResource("fxml/ErrorDialog.fxml"));
        try {
            page = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Error Detected!");
        stage.setScene(new Scene(page));
        stage.setResizable(false);
        stage.show();

        return loader.getController();
    }
}
