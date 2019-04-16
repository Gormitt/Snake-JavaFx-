package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Game extends Application {

    public boolean isGame = true;

    public static int width = 24, height = 24;
    public static int size = 25;
    public static int menuWidth = 250;

    public int dirHorizontal = 0, dirVertical = -1;
    public boolean dirChange = false;

    public int points = 0;

    public int seconds = 0, minutes = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, width * size + menuWidth, height * size);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake");
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.show();

        Snake snake = new Snake(root);
        Apple apple = new Apple(root);
        Menu menu = new Menu(root);

        menu.buttonRestart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                menu.menuRestart();
                snake.disappear();
                snake.restart();
                snake.show();
                apple.changeLocation(root);
                apple.show();
                points = 0;
                minutes = 0;
                seconds = 0;
                dirHorizontal = 0;
                dirVertical = -1;
                isGame = true;
            }
        });

        menu.buttonExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) menu.buttonExit.getScene().getWindow();
                stage.close();
            }
        });

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (dirChange == false) {
                    dirChange = true;
                    if (event.getCode() == KeyCode.A) {
                        if (dirHorizontal == 0) {
                            dirVertical = 0;
                            dirHorizontal = -1;
                        }
                    } else if (event.getCode() == KeyCode.D) {
                        if (dirHorizontal == 0) {
                            dirVertical = 0;
                            dirHorizontal = 1;
                        }
                    } else if (event.getCode() == KeyCode.W) {
                        if (dirVertical == 0) {
                            dirVertical = -1;
                            dirHorizontal = 0;
                        }
                    } else if (event.getCode() == KeyCode.S) {
                        if (dirVertical == 0) {
                            dirVertical = 1;
                            dirHorizontal = 0;
                        }
                    }
                }
            }
        });

        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (isGame) {
                    seconds++;
                    if (seconds == 60) {
                        seconds = 0;
                        minutes++;
                    }
                    menu.setTextTime(seconds, minutes);
                }
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (isGame) {
                    snake.moveSnake(dirHorizontal, dirVertical);
                    if (snake.isDying()) {
                        // Game over
                        isGame = false;
                        snake.disappear();
                        apple.disappear();
                        menu.gameOverInfo();
                    }
                    dirChange = false;

                    if (snake.getHeadX() == apple.getX() && snake.getHeadY() == apple.getY()) {
                        apple.changeLocation(root);
                        snake.growSnake(root);

                        points += 1;
                        menu.setTextPoints(points);

                        if(points == width * height) {
                            // win the game
                        }
                    }
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
