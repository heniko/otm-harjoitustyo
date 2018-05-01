package ui;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import logic.GameLogic;

public class GameUi extends Application {

    private int[][] grid;
    private GameLogic gameLogic;
    private GridPane gameGridPane;
    private StringProperty[][] tileStyles;
    private IntegerProperty[][] tileValues;

    public static void main(String[] args) {
        launch(GameUi.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        grid = new int[4][4];
        gameLogic = new GameLogic(grid);
        gameLogic.initializeGame();
        gameGridPane = new GridPane();
        tileValues = new IntegerProperty[4][4];
        tileStyles = new StringProperty[4][4];
        
        Scene game;
        Label[][] labels = new Label[4][4];
        
        //Styles for gameGridPane
        gameGridPane.setStyle("-fx-background-color: #a39284;");
        gameGridPane.setHgap(10);
        gameGridPane.setVgap(10);
        gameGridPane.setPrefSize(500, 500);
        gameGridPane.setAlignment(Pos.CENTER);

        //Initializing tile values and styles
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                tileValues[y][x] = new SimpleIntegerProperty();
                tileStyles[y][x] = new SimpleStringProperty();
            }
        }
        updateGameGrid();

        //Initializing labels and binding values+styles
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                labels[y][x] = new Label();
                labels[y][x].setMinSize(100, 100);
                labels[y][x].setMaxSize(100, 100);
                labels[y][x].setAlignment(Pos.CENTER);
                labels[y][x].textProperty().bind(tileValues[y][x].asString());
                labels[y][x].styleProperty().bind(tileStyles[y][x]);
                gameGridPane.add(labels[y][x], x, y);
            }
        }

        //Listeners and handlers
        EventHandler<KeyEvent> moveHandler = event -> {
            gameLogic.move(event);
            updateGameGrid();
        };
        
        //Game view
        game = new Scene(gameGridPane);
        game.addEventFilter(KeyEvent.KEY_PRESSED, moveHandler);
        //Main menu view
        
        //Highscore view
        
        stage.setScene(game);
        stage.show();
    }

    //Changing tile values and styles
    public void updateGameGrid() {
        TileStyle ts = new TileStyle();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                tileValues[y][x].set(grid[y][x]);
                tileStyles[y][x].set(ts.getStyle(grid[y][x]));
            }
        }
    }
}
