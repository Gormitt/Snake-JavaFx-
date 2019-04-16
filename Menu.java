package game;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Menu extends Game {

    public Line lineScreenDivider;
    public Text textPoints, textTime, textGameOver;
    public Button buttonRestart, buttonExit;

    public Menu(Group root) {
        lineScreenDivider = new Line(width * size, 0, width * size, height * size);

        textPoints = new Text();
        textPoints.setTextAlignment(TextAlignment.CENTER);
        textPoints.setFont(Font.font("Lato", FontWeight.BOLD, 40));
        textPoints.setFill(Color.BLUE);
        textPoints.setStroke(Color.BLACK);
        textPoints.setX(width * size + 50);
        textPoints.setY(height * size / 6);
        textPoints.setText("Points: 0");

        textTime = new Text();
        textTime.setTextAlignment(TextAlignment.CENTER);
        textTime.setFont(Font.font("Lato", FontWeight.BOLD, 40));
        textTime.setFill(Color.BLUE);
        textTime.setStroke(Color.BLACK);
        textTime.setX(width * size + 75);
        textTime.setY(height * size / 6 + 50);
        textTime.setText("00:00");

        textGameOver = new Text("GAME OVER");
        textGameOver.setFont(Font.font("Lato", FontWeight.BOLD, 50));
        textGameOver.setTextAlignment(TextAlignment.CENTER);
        textGameOver.setX(width * size / 2 - 150);
        textGameOver.setY(height * size / 2 - 60);
        textGameOver.setVisible(false);

        buttonRestart = new Button("RESTART");
        buttonRestart.setPrefWidth(125);
        buttonRestart.setPrefHeight(75);
        buttonRestart.setLayoutX(width * size + 65);
        buttonRestart.setLayoutY(height * size / 6 + 175);
        buttonRestart.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00); -fx-background-radius: 30; -fx-background-insets: 0; -fx-text-fill: white;");

        buttonExit = new Button("EXIT");
        buttonExit.setPrefWidth(125);
        buttonExit.setPrefHeight(75);
        buttonExit.setLayoutX(width * size + 65);
        buttonExit.setLayoutY(height * size / 6 + 275);
        buttonExit.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00); -fx-background-radius: 30; -fx-background-insets: 0; -fx-text-fill: white;");

        root.getChildren().add(lineScreenDivider);
        root.getChildren().add(textPoints);
        root.getChildren().add(textTime);
        root.getChildren().add(textGameOver);
        root.getChildren().add(buttonRestart);
        root.getChildren().add(buttonExit);
    }

    public void setTextPoints(int n) {
        textPoints.setText("Points: " + n);
    }

    public void setTextTime(int sec, int min) {
        if (min < 10) {
            if (sec < 10) {
                textTime.setText("0" + min + ":0" + sec);
            } else {
                textTime.setText("0" + min + ":" + sec);
            }
        } else {
            if (sec < 10) {
                textTime.setText(min + ":0" + sec);
            } else {
                textTime.setText(min + ":" + sec);
            }
        }
    }

    public void gameOverInfo() {
        String time = textTime.getText();
        String points = textPoints.getText();
        textGameOver.setText("GAME OVER\n" + time + "\n" + points);
        textGameOver.setVisible(true);
    }

    public void menuRestart() {
        textGameOver.setVisible(false);
        textPoints.setText("Points: 0");
        textTime.setText("00:00");
    }
}
