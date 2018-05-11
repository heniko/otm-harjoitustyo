package ui;

import dao.HighscoreDao;
import domain.Database;
import domain.Highscore;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.layout.VBox;
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
                stage.setScene(menu);
                newHighscore(gameController.getScore());
            }
        };
        game.addEventFilter(KeyEvent.KEY_PRESSED, moveHandler);

        startGameButton.setOnAction((event) -> {
            stage.setScene(game);
            gameController.initializeGame();
            updateGameGrid();
        });
        openHighscoreButton.setOnAction((event) -> {
            showTop20();
        });

        //Main menu view
        //Highscore view
        stage.setScene(menu);
        stage.setTitle("2048");
        stage.show();
    }

    /**
     * Updates label styles and values
     */
    public void updateGameGrid() {
        TileStyle ts = new TileStyle();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                tileValues[y][x].set(grid[y][x]);
                tileStyles[y][x].set(ts.getStyle(grid[y][x]));
            }
        }
    }

    /**
     * Creates new stage for adding score
     */
    public void newHighscore(int score) {
        Date date = new Date(System.currentTimeMillis());
        Stage addScoreStage = new Stage();
        Label showScore = new Label("Score: " + score);
        Label showDate = new Label("Date: " + date.toString());
        Label usernameError = new Label("");
        TextField userName = new TextField();
        Button addButton = new Button("Add score!");
        HBox userNameHbox = new HBox();
        userNameHbox.getChildren().addAll(userName, usernameError);
        VBox addHighscore = new VBox();
        //Styles
        showScore.setPrefSize(200, 50);
        showDate.setPrefSize(200, 50);
        userNameHbox.setPrefSize(400, 50);
        addHighscore.setPrefSize(400, 200);
        addHighscore.setStyle("-fx-background-color: #a39284;");
        addScoreStage.setTitle("Add highscore");

        addHighscore.getChildren().addAll(showDate, showScore, userNameHbox, addButton);

        addButton.setOnAction((event) -> {
            if (userName.getText().length() == 0 || userName.getText().length() > 30) {
                usernameError.setText("Username must be 1-30 characters long!");
            } else {
                try {
                    dao.addNew(new Highscore(score, userName.getText(), date));
                    addScoreStage.close();
                } catch (SQLException e) {
                    usernameError.setText("Tietokannan käytössä virhe");
                }
            }
        });

        Scene addScoreScene = new Scene(addHighscore);
        addScoreStage.setScene(addScoreScene);
        addScoreStage.show();
    }

    /*
    *Creates new stage for showing top20 scores
     */
    public void showTop20() {
        //Style for lines is used to make highscores easier to read
        String[] styles = {
            "-fx-background-color: #282219;",
            "-fx-background-color: #473c2c;"};
        VBox highscoreLines = new VBox();
        //highscoreLines styles
        highscoreLines.setStyle("-fx-background-color: #a39284;");
        highscoreLines.setPrefSize(400, 630);
        //Titles for highscoreLines
        HBox titles = new HBox();
        Label nameTitle = new Label("Name");
        Label dateTitle = new Label("Date");
        Label scoreTitle = new Label("Score");
        Label posTitle = new Label("");
        posTitle.setPrefSize(100, 30);
        nameTitle.setPrefSize(100, 30);
        dateTitle.setPrefSize(100, 30);
        scoreTitle.setPrefSize(100, 30);
        titles.getChildren().addAll(posTitle, nameTitle, scoreTitle, dateTitle);
        highscoreLines.getChildren().add(titles);
        //Getting top 20 scores from database
        List<Highscore> lines = new ArrayList<>();
        try {
            lines = dao.getTop20();
        } catch (SQLException e) {

        }
        //Adding each highscore
        int position = 1;
        for (Highscore line : lines) {
            HBox newLine = new HBox();
            Label name = new Label(line.getName());
            Label date = new Label(line.getDate().toString());
            Label score = new Label(line.getScore() + "");
            Label pos = new Label(position + "");
            //Styles for labels and hbox. Alteration is based on position of the score
            newLine.setStyle(styles[position % 2]);
            name.setStyle(styles[position % 2]);
            date.setStyle(styles[position % 2]);
            score.setStyle(styles[position % 2]);
            pos.setStyle(styles[position % 2]);
            name.setPrefSize(100, 30);
            date.setPrefSize(100, 30);
            score.setPrefSize(100, 30);
            pos.setPrefSize(100, 30);
            //Adding line to highscore
            newLine.getChildren().addAll(pos, name, score, date);
            highscoreLines.getChildren().add(newLine);
            position++;
        }
        Scene s = new Scene(highscoreLines);
        Stage showHighscore = new Stage();
        showHighscore.setScene(s);
        showHighscore.setTitle("Highscores");
        showHighscore.show();
    }
}
