package game;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Iterator;

public class Snake extends Game {

    public ArrayList <Rectangle> s = new ArrayList<>(1);
    public int lastSeenTailX, lastSeenTailY;

    public Snake(Group root) {

        int headX = (int) (Math.random() * (width - 1)) + 2;
        int headY = (int) (Math.random() * (height - 4)) + 2;

        s.add(new Rectangle(headX * size, headY * size, size - 1, size - 1));
        s.add(new Rectangle(headX * size, (headY + 1) * size, size - 1, size - 1));
        s.add(new Rectangle(headX * size, (headY + 2) * size, size - 1, size - 1));

        for (Rectangle rect : s) {
            root.getChildren().add(rect);
        }
    }

    public void moveSnake(int dirHorizontal, int dirVertical) {
        lastSeenTailX = (int) s.get(s.size() - 1).getX();
        lastSeenTailY = (int) s.get(s.size() - 1).getY();
        for (int i = s.size() - 1; i >= 1; i--) {
            s.get(i).setX(s.get(i - 1).getX());
            s.get(i).setY(s.get(i - 1).getY());
        }

        s.get(0).setX(s.get(0).getX() + size * dirHorizontal);
        s.get(0).setY(s.get(0).getY() + size * dirVertical);

        if (s.get(0).getX() < 0) s.get(0).setX(s.get(0).getX() + width * size);
        if (s.get(0).getX() >= width * size) s.get(0).setX(0);
        if (s.get(0).getY() < 0) s.get(0).setY(s.get(0).getY() + height * size);
        if (s.get(0).getY() >= height * size) s.get(0).setY(0);
    }

    public void growSnake(Group root) {
        s.add(new Rectangle(lastSeenTailX, lastSeenTailY, size - 1, size - 1));
        root.getChildren().add(s.get(s.size() - 1));
    }

    public boolean isDying() {
        for (int i = 1; i < s.size(); i++) {
            if (s.get(0).getX() == s.get(i).getX() && s.get(0).getY() == s.get(i).getY())
                return true;
        }
        return  false;
    }

    public void disappear() {
        for (Rectangle rect : s) {
            rect.setFill(Color.WHITE);
            rect.setX(rect.getX() + 5 * width * size);
        }
    }

    public void show() {
        for (Rectangle rect : s) {
            rect.setFill(Color.BLACK);
        }
    }

    public void restart() {
        int index = 0;
        for (Iterator<Rectangle> iterator = s.iterator(); iterator.hasNext(); ){
            Rectangle block = iterator.next();
            index++;
            if (index > 3) {
                iterator.remove();
            }
        }

        int headX = (int) (Math.random() * (width - 1)) + 2;
        int headY = (int) (Math.random() * (height - 4)) + 2;
        s.get(0).setX(headX * size);
        s.get(0).setY(headY * size);
        s.get(1).setX(headX * size);
        s.get(1).setY((headY + 1) * size);
        s.get(2).setX(headX * size);
        s.get(2).setY((headY + 2) * size);
    }

    // getter
    public int getHeadX() {
        return (int) s.get(0).getX();
    }
    // getter
    public int getHeadY() {
        return (int) s.get(0).getY();
    }
}
