package tech.hibbs.minesweeper;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
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

        // ---

        showAbout();
    }

    // --------------------------------------------------------------------------

    private void setupBacktickKeyHandler(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.BACK_QUOTE) {
                    stage.close();
                }
            }
        });
    }

    private MenuBar getMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");

        menuBar.getMenus().add(fileMenu);

        return menuBar;
    }

    private void showAbout() throws IOException {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/About.fxml"));
        Parent root = (Parent) loader.load();

        Scene scene = new Scene(root, 240, 120);
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }

}
