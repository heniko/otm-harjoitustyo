package ui;

public class TileStyle {

    public TileStyle() {

    }

    public String getStyle(int value) {
        switch (value) {
            case 0:
                return "-fx-background-radius: 10 10 10 10; -fx-background-color: #baaa9c;-fx-text-fill: #baaa9c";
            case 2:
                return "-fx-background-radius: 10 10 10 10; -fx-background-color: #eee4da;-fx-text-fill: #776e65";
            case 4:
                return "-fx-background-radius: 10 10 10 10; -fx-background-color: #ede0c8;-fx-text-fill: #776e65";
            case 8:
                return "-fx-background-radius: 10 10 10 10; -fx-background-color: #f2b179;-fx-text-fill: #f9f6f2";
            case 16:
                return "-fx-background-radius: 10 10 10 10; -fx-background-color: #f59563;-fx-text-fill: #f9f6f2";
            case 32:
                return "-fx-background-radius: 10 10 10 10; -fx-background-color: #f67c5f;-fx-text-fill: #f9f6f2";
            case 64:
                return "-fx-background-radius: 10 10 10 10; -fx-background-color: #f65e3b;-fx-text-fill: #f9f6f2";
            case 128:
                return "-fx-background-radius: 10 10 10 10; -fx-background-color: #f5ed62;-fx-text-fill: #f9f6f2";
            case 256:
                return "-fx-background-radius: 10 10 10 10; -fx-background-color: #edcc61;-fx-text-fill: #f9f6f2";
            case 512:
                return "-fx-background-radius: 10 10 10 10; -fx-background-color: #edc850;-fx-text-fill: #f9f6f2";
            case 1024:
                return "-fx-background-radius: 10 10 10 10; -fx-background-color: #edc53f;-fx-text-fill: #f9f6f2";
            case 2048:
                return "-fx-background-radius: 10 10 10 10; -fx-background-color: #edc22e;-fx-text-fill: #f9f6f2";
            default:
                return "-fx-background-radius: 10 10 10 10; -fx-background-color: black;-fx-text-fill: white";
        }
    }
}
