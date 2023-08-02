import java.awt.*;

public class Ball {
    private int x, y, cx, cy, size, speed;
    private Color color;
    private RandomInteger random = new RandomInteger(5, 8);
    private static final int MAX_SPEED = 7;
    
    public Ball(int x, int y, int cx, int cy, int size, int speed, Color color) {
        this.x = x;
        this.y = y;
        this.cx = cx;
        this.cy = cy;
        this.size = size;
        this.speed = speed;
        this.color = color;
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, size, size);
    }

    public void MoveBall() {
        x += cx;
        y += cy;
    }

    public void BounceOffEdges(int top, int bottom, int left, int right) {
        if (y > bottom - size || y < top) {
            reverseY();
            // debug();
        }
    }

    public int IncreaseSpeed(){
        if (speed < MAX_SPEED) {
            speed ++;
            cx = (cx / Math.abs(cx)*speed);
            //cy = (cy / Math.abs(cy)*speed);
        }
        if(Math.abs(cy) < MAX_SPEED){
            if(cy > 0){
                cy++;
            }
            else{
                cy--;
            }
        }
        System.out.println("ball speed increased to: " + speed + "cX: " + cx + "cY: " +cy);
        return random.Generate();
    }

    public void reverseY() {
        cy = cy * -1;
    }

    public void reverseX() {
        cx = cx * -1;
    }

    private void debug(){
        System.out.println("x: " + x + " y: " + y);
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

    public int getCx() {
        return cx;
    }

    public void setCx(int cx) {
        this.cx = cx;
    }

    public int getCy() {
        return cy;
    }

    public void setCy(int cy) {
        this.cy = cy;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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
    
    
    
}
