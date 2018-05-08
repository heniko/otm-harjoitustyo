package ui;

import dao.HighscoreDao;
import domain.Database;
import domain.Highscore;
import java.sql.Date;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logic.GameController;

public class GameUi extends Application {

    private int[][] grid;
    private GameController gameController;
    private GridPane gameGridPane;
    private StringProperty[][] tileStyles;
    private IntegerProperty[][] tileValues;
    private Database db;
    private HighscoreDao dao;

    public static void main(String[] args) {
        launch(GameUi.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        db = new Database("jdbc:sqlite:highscores.db");
        dao = new HighscoreDao(db);
        dao.createDatabaseAndTableIfNotExists();

        grid = new int[4][4];
        gameController = new GameController(grid);
        gameController.initializeGame();
        gameGridPane = new GridPane();
        tileValues = new IntegerProperty[4][4];
        tileStyles = new StringProperty[4][4];

        Scene game;
        Scene menu;
        //Menu elements
        Button startGameButton = new Button("New game");
        Button openHighscoreButton = new Button("Highscores");
        GridPane menuPane = new GridPane();
        //Labels for each game tile
        Label[][] labels = new Label[4][4];

        //Styles for gameGridPane
        gameGridPane.setStyle("-fx-background-color: #a39284;");
        gameGridPane.setHgap(10);
        gameGridPane.setVgap(10);
        gameGridPane.setPrefSize(500, 500);
        gameGridPane.setAlignment(Pos.CENTER);

        //Menu style
        menuPane.setStyle("-fx-background-color: #a39284;");
        menuPane.setHgap(100);
        menuPane.setVgap(100);
        menuPane.setPrefSize(500, 500);
        menuPane.setAlignment(Pos.CENTER);
        menuPane.add(startGameButton, 0, 0);
        menuPane.add(openHighscoreButton, 0, 1);
        menu = new Scene(menuPane);

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

        //Game view
        game = new Scene(gameGridPane);
        //Main menu view

        //Highscore view
        //Listeners and handlers
        EventHandler<KeyEvent> moveHandler = event -> {
            gameController.move(event);
            if (gameController.gameIsRunning()) {
                updateGameGrid();
            } else {
                newHighscore(gameController.getScore());
                stage.setScene(menu);
            }
        };
        game.addEventFilter(KeyEvent.KEY_PRESSED, moveHandler);

        startGameButton.setOnAction((event) -> {
            stage.setScene(game);
            gameController.initializeGame();
            updateGameGrid();
        });

        //Main menu view
        //Highscore view
        stage.setScene(menu);
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

    //Creating new stage for adding score
    public void newHighscore(int score) {
        Date date = new Date(System.currentTimeMillis());
        Stage addScoreStage = new Stage();
        Label showScore = new Label("Score: " + score);
        TextField userName = new TextField();
        Button addButton = new Button("Add score!");
        HBox addHighscore = new HBox();
        addHighscore.getChildren().add(showScore);
        addHighscore.getChildren().add(userName);
        addHighscore.getChildren().add(addButton);
        
        addButton.setOnAction((event) -> {
            try {
                dao.addNew(new Highscore(score, userName.getText(), date));
                addScoreStage.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        });
        
        Scene addScoreScene = new Scene(addHighscore);
        addScoreStage.setScene(addScoreScene);
        addScoreStage.show();
    }
    
    public void showTop20() {
        
    }
}
