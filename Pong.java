import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Pong extends JPanel implements MouseMotionListener {
    static final int WINDOW_WIDTH = 640, WINDOW_HEIGHT = 475;
    private Paddle playerPaddle, aiPaddle;
    private Ball ball;
    private int userMouseY, playerScore, aiScore, bounces, score;
    private AudioInputStream audioInputStream;
    private Clip clip;
    RandomInteger random = new RandomInteger(-2,2);
    RandomInteger yPos = new RandomInteger(150, 300);

    
    public Pong() {
        ball = new Ball(300, 200, 2, 2, 10, 2, Color.YELLOW);
        playerPaddle = new Paddle(75, 10, 200, 5, Color.BLUE);
        aiPaddle = new Paddle(75, 610, 200, 5, Color.RED);
        
        userMouseY = 0;
        
        addMouseMotionListener(this);
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        Toolkit.getDefaultToolkit().sync();
        ball.paint(g);
        playerPaddle.paint(g);
        aiPaddle.paint(g);
        g.setColor(Color.WHITE);
        g.drawString("POINTS - PLAYER [ " + playerScore + " ]   COMP [ " + aiScore + " ]", 250, 20);
        g.drawString("Score: " + score, 10, 20);
    }
    
    public void GameLogic() throws UnsupportedAudioFileException,IOException, LineUnavailableException{
        int waitBounces = 5;
        ball.MoveBall();
        ball.BounceOffEdges(0, WINDOW_HEIGHT, 0, WINDOW_WIDTH);
        playerPaddle.MoveTowards(userMouseY);
        if (ball.getX() > 410 && aiPaddle.CheckCollision(ball) == false) {
            aiPaddle.MoveTowards(ball.getY());
        }

        if (playerPaddle.CheckCollision(ball)) {
            audioInputStream = AudioSystem.getAudioInputStream(new File("src/paddlehit.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

            ball.reverseX();
            ball.setX(ball.getX() + 1);
            bounces++;
            // System.out.println(bounces + " ball speed: " + ball.getSpeed() + " cx: " + ball.getCx() + " cY: " + ball.getCy());
            score += 10 * ball.getSpeed();
        } else if (aiPaddle.CheckCollision(ball)) {
            audioInputStream = AudioSystem.getAudioInputStream(new File("src/paddlehit.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            ball.reverseX();
            ball.setX(ball.getX() - 1);
            bounces++;
            // System.out.println(bounces + " ball speed: " + ball.getSpeed() + " cx: " + ball.getCx() + " cY: " + ball.getCy());
        }
        if (ball.getX() < 0) {
            audioInputStream = AudioSystem.getAudioInputStream(new File("src/lose.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            aiScore++;
            reset(0);
        } else if (ball.getX() > WINDOW_WIDTH - 5) {
            audioInputStream = AudioSystem.getAudioInputStream(new File("src\\win.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            playerScore++;
            score += 5000;
            reset(1);
        }

        if (bounces == waitBounces) {
            waitBounces = ball.IncreaseSpeed();
            bounces = 0;
        }
    }
    /**
     * resets the game
     * @param loser 0 if player loses 1 in ai loses
     */
    public void reset(int loser) {
        // pause for a second
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(loser == 0){
            ball.setCx(-2);
        }
        else{
            ball.setCx(2);
        }
        ball.setX(300);
        ball.setY(yPos.Generate());
        ball.setCy(random.Generate());
        ball.setSpeed(2);
        bounces = 0;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        userMouseY = e.getY();
    }
}
