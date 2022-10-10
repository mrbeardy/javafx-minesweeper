package tech.hibbs.minesweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;

public class App extends Application {
    private static final int BOARD_TILE_WIDTH = 30;
    private static final int BOARD_TILE_HEIGHT = 30;
    private static final String RESOURCES_IMG_BOMB_DARK_PNG = "resources/img/BombDark.png";
    private static final String RESOURCES_IMG_TILE_EMPTY_DARK_PNG = "resources/img/TileEmptyDark.png";
    private static final String RESOURCES_IMG_TILE_DARK_PNG = "resources/img/TileDark.png";
    private final static int WIDTH = 600;
    private final static int HEIGHT = 600;


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

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        setupBacktickKeyHandler(scene);

        GridPane gridPane = (GridPane) loader.getNamespace().get("tiles");
        
        List<String> board = new ArrayList<String>();

        board.add("_XXX_");
        board.add("._X_.");
        board.add("__X_.");
        board.add("._X._");

        int boardHeight = board.size();
        int boardWidth = board.get(0).length();

        for (int y = 0; y < boardHeight; y++) {
            String row = board.get(y);

            for (int x = 0; x < boardWidth; x++) {
                StackPane stackPane = new StackPane();
                stackPane.setMaxSize(BOARD_TILE_WIDTH, BOARD_TILE_HEIGHT);

                char charAt = row.charAt(x);
                String url = RESOURCES_IMG_TILE_DARK_PNG;

                if (charAt == 'X' || charAt == '_') {
                    url = RESOURCES_IMG_TILE_EMPTY_DARK_PNG;
                }

                ImageView tileImageView = createTileImageView(url);
                StackPane.setAlignment(tileImageView, Pos.CENTER);
                stackPane.getChildren().add(tileImageView);

                if (charAt == '.') {
                    tileImageView.addEventHandler(EventType.ROOT, event -> {
                        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                            System.out.println("Get your hands off me!");
                        }
                    });
                }

                if (charAt == 'X') {
                    tileImageView = createTileImageView(RESOURCES_IMG_BOMB_DARK_PNG);
                    StackPane.setAlignment(tileImageView, Pos.CENTER);
                    stackPane.getChildren().add(tileImageView);
                }

                GridPane.setConstraints(stackPane, x, y);
                gridPane.getChildren().add(stackPane);
            }
        }


        // for(int i = 0; i < 800; i++) {
        //     tilePane.getChildren().add(createTileImageView(RESOURCES_IMG_TILE_DARK_PNG));
        // }

        stage.setMinWidth(WIDTH);
        stage.setMinHeight(HEIGHT);

        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }

    private ImageView createTileImageView(String resourceUrl) {
        return createTileImageView(resourceUrl, BOARD_TILE_WIDTH, BOARD_TILE_HEIGHT);
    }

    private ImageView createTileImageView(String resourceUrl, int width, int height) {
        Image image = new Image(getClass().getResourceAsStream(resourceUrl));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        return imageView;
    }

    // --------------------------------------------------------------------------

    private void setupBacktickKeyHandler(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.BACK_QUOTE) {
                stage.close();
            }
        });
    }
}
