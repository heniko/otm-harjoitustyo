package ui;

import domain.TileStyle;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import logic.GameLogic;

public class GameUi extends Application {

    private GameLogic gl;
    private GridPane gridPane;
    private Scene s;

    @Override
    public void start(Stage primaryStage) throws Exception {
        gl = new GameLogic();
        gridPane = renderGame();
        s = new Scene(gridPane);
        printGame();

        s.setOnKeyPressed((KeyEvent event) -> {
            try {
                gl.move(event);
                printGame();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        primaryStage.setScene(s);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(GameUi.class);
    }
    
    public void printGame() {
        int[][] grid = gl.getGrid();
        for(int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                System.out.print(grid[y][x] + ", ");
            }
            System.out.println("");
        }
        System.out.println("***********");
    }

    public GridPane renderGame() {
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #a39284;");
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPrefSize(500, 500);
        gridPane.setAlignment(Pos.CENTER);

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                Label l = new Label();
                l.setMinSize(100, 100);
                l.setMaxSize(100, 100);
                l.setAlignment(Pos.CENTER);
                l.setText("" + gl.getGrid()[y][x]);
                l.setStyle("-fx-background-radius: 10 10 10 10; " + new TileStyle().getStyle(gl.getGrid()[y][x]));
                gridPane.add(l, x, y);
            }
        }
        return gridPane;
    }
}
