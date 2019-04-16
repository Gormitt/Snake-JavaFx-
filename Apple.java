package game;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Apple extends Game {

    public int x, y;
    Rectangle r;

    public Apple(Group root) {
        x = (int) (Math.random() * (width - 2) + 1);
        y = (int) (Math.random() * (height - 2) + 1);
        System.out.println("Apple log: " + x + " " + y);
        x *= size;
        y *= size;

        this.r = new Rectangle(x, y, size - 1, size - 1);
        this.r.setFill(Color.RED);
        root.getChildren().add(this.r);
    }

    public void changeLocation(Group root) {
        x = (int) (Math.random() * width + 0) * size;
        y = (int) (Math.random() * height + 0) * size;
        this.r.setX(x);
        this.r.setY(y);
    }

    public void disappear() {
        r.setFill(Color.WHITE);
    }

    public void show() {
        r.setFill(Color.RED);
    }

    // getter
    public int getX() {
        return x;
    }
    // getter
    public int getY() {
        return y;
    }
}
