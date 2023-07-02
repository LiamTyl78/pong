import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class App {
    public static void main(String[] args) throws Exception {
                JFrame frame = new JFrame("Pong");
                JPanel game = new JPanel();
                Pong pong = new Pong();
                // game.setBackground(Color.black);

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(650, 510);
                frame.add(pong);
                frame.setResizable(false);
                frame.setVisible(true);

                Timer timer = new Timer(15, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pong.repaint();
                        try {
                            pong.GameLogic();
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        
                    }
                });
                
                timer.start();
                        
                    }
    }


