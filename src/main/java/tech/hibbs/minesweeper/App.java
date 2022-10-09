package tech.hibbs.minesweeper;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
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

        // ---

        FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));
        Parent root = (Parent) loader.load();

        // GridPane gridPane = new GridPane();
        // gridPane.setAlignment(Pos.CENTER);
        // gridPane.setHgap(10);
        // gridPane.setVgap(10);
        // gridPane.setPadding(new Insets(25));

        // Text titleText = new Text("Welcome!");
        // titleText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        // titleText.setFill(Color.gray(.85));
        // gridPane.add(titleText, 0, 0, 2, 1);

        // Label usernameLabel = new Label("Username");
        // gridPane.add(usernameLabel, 0, 1);

        // TextField usernameTextField = new TextField();
        // gridPane.add(usernameTextField, 1, 1);

        // Label passwordLabel = new Label("Password");
        // gridPane.add(passwordLabel, 0, 2);

        // PasswordField passwordField = new PasswordField();
        // gridPane.add(passwordField, 1, 2);

        // ---

        Scene scene = new Scene(root, 300, 275);
        // System.out.println(App.class.getResource("App.css").toExternalForm());
        // scene.getStylesheets().add(
        //     App.class.getResource("App.css").toExternalForm()
        // );
        scene.setFill(Color.BLACK);
        setupBacktickKeyHandler(scene);
        stage.setScene(scene);
        stage.show();
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

}
