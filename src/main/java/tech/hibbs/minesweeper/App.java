package tech.hibbs.minesweeper;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.fxml.FXMLLoader;

public class App extends Application {
    private Stage stage;

    public static void main(String[] args) {
        launch();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("Minesweeper");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/App.fxml"));
        Parent root = (Parent) loader.load();
        AppController controller = (AppController) loader.getController();
        controller.init(stage);

        Scene scene = new Scene(root, 300, 275);
        setupBacktickKeyHandler(scene);

        stage.setScene(scene);
        stage.show();
    }

    // --------------------------------------------------------------------------

    private void setupBacktickKeyHandler(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (EventHandler<KeyEvent>) event -> {
            if (event.getCode() == KeyCode.BACK_QUOTE) {
                stage.close();
            }
        });
    }
}
