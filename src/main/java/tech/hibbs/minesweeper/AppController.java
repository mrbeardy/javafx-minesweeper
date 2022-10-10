package tech.hibbs.minesweeper;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AppController {
    private Stage stage;

    public void init(Stage stage) {
        this.stage = stage;
    }

    public void handleButtonAction(ActionEvent event) {
        System.out.println("Clicked!");

        this.stage.close();
    }

    public void fileQuitAction(ActionEvent event) {
        Alert quitAlert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to quit?", ButtonType.NO, ButtonType.YES);

        quitAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                this.stage.close();
            }
        });
    }

    public void fileHelpAboutAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/About.fxml"));
        Parent root = (Parent) loader.load();

        Scene scene = new Scene(root, 240, 120);
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }
}
