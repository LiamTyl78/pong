import java.awt.Color;
import java.awt.Graphics;

public class Paddle{
    private int height, x, y, speed;
    private Color color;
    private boolean moving;

    static final int PADDLE_WIDTH = 15;

    /**
     * A Paddle that the player uses to hit the ball
     * @param height
     * @param x
     * @param y
     * @param speed
     * @param color
     */
    public Paddle(int height, int x, int y, int speed, Color color) {
        this.height = height;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.color = color;
    }

    public void MoveTowards(int MovToY) {
        int centerY = y + height / 2;
        if(Math.abs(centerY - MovToY) > speed){
            if(centerY > MovToY){
                y -= speed;
            }
    
            if(centerY < MovToY){
                y += speed;
            }
        }
    }

    public boolean CheckCollision(Ball b) {
        int rightX = x + PADDLE_WIDTH;
        int bottomY = y + height;

        if(b.getX() > (x - b.getSize()) && b.getX() < rightX){
            if(b.getY() > y && b.getY() < bottomY){
                return true;
            }
        }
        return false;
    }

    public void paint(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, PADDLE_WIDTH, height);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public static int getPaddleWidth() {
        return PADDLE_WIDTH;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
    
}
