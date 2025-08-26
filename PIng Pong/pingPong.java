import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class pingPong extends JPanel implements KeyListener, ActionListener{
    // GAME CONSTANTS
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // Player 1 - W/S keys
        if (key == KeyEvent.VK_W) {
            if(player1Y > 0) // Prevent paddle from going out of bounds
                player1Y -= PADDLE_SPEED;
        } else if (key == KeyEvent.VK_S) {
            if(player1Y < HEIGHT - PADDLE_HEIGHT) // Prevent paddle from going out of bounds
                player1Y += PADDLE_SPEED;
        }

        // Player 2 - Up/Down arrow keys
        if (key == KeyEvent.VK_UP) {
            if(player2Y > 0) // Prevent paddle from going out of bounds
                player2Y -= PADDLE_SPEED;
        } else if (key == KeyEvent.VK_DOWN) {
            if(player2Y < HEIGHT - PADDLE_HEIGHT) // Prevent paddle from going out of bounds
                player2Y += PADDLE_SPEED;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO: Add logic if needed
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }
    public static final int PADDLE_WIDTH = 10;
    public static final int PADDLE_HEIGHT = 100;
    public static final int BALL_SIZE = 10;
    public static final int PADDLE_SPEED = 10;
    public static final int DELAY = 16; // 60 FPS (1000ms/60fps = 16mms per  frame)

    // GAME VARIABLES
    private int player1Y = HEIGHT / 2 - PADDLE_HEIGHT / 2;
    private int player2Y = HEIGHT / 2 - PADDLE_HEIGHT / 2;
    private int ballX = WIDTH / 2 - BALL_SIZE / 2;
    private int ballY = HEIGHT / 2 - BALL_SIZE / 2;
    private int ballXSpeed = 5;
    private int ballYSpeed = 5;
    private int player1Score = 0;
    private int player2Score = 0;
    Timer gameTimer = new Timer(DELAY, this);

    // INITIALIZE GAME
    pingPong() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener((KeyListener) this);   

        resetGame();
        gameTimer.start();
    }

    public void resetGame() {
        player1Y = HEIGHT / 2 - PADDLE_HEIGHT / 2;
        player2Y = HEIGHT / 2 - PADDLE_HEIGHT / 2;
        ballX = WIDTH / 2 - BALL_SIZE / 2;
        ballY = HEIGHT / 2 - BALL_SIZE / 2;
        ballXSpeed = 5;
        ballYSpeed = 5;
    }

    // GAME LOOP
    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    public void update() {
        // Update ball position
        ballX   += ballXSpeed;
        ballY += ballYSpeed;

        // 1. CHECK BALL COLLISION WITH TOP WALL
        if (ballY <= 0) {
            ballYSpeed = -ballYSpeed; // Reverse vertical direction (start moving down)
            //
        }

        // 2. CHECK BALL COLLISION WITH BOTTOM WALL
        if (ballY + BALL_SIZE >= HEIGHT) {
            ballYSpeed = -ballYSpeed; // Reverse vertical direction (start moving up)
            }



        // Check Ball Collision With Paddle 1 (LEFT)
        if(ballX <= PADDLE_WIDTH && ballY + BALL_SIZE >= player1Y && ballY <= player1Y + PADDLE_HEIGHT) {
            ballXSpeed = -ballXSpeed; // Reverse ball horizontal direction
            ballXSpeed += 1; // Increase ball speed
            // paddle hit sound
        }

        // Check Ball Collision With Paddle 2 (RIGHT)
        if(ballX + BALL_SIZE >= WIDTH - PADDLE_WIDTH && ballY + BALL_SIZE >= player2Y && ballY <= player2Y + PADDLE_HEIGHT) {
            ballXSpeed = -ballXSpeed; // Reverse ball horizontal direction
            ballXSpeed += 1; // Increase ball speed
            // paddle hit sound
        }

        // Check For Scoring
        if(ballX < 0) {
            // Player 2 scores
            player2Score++;
            // play score sound
            resetGame();
        } 
        else if(ballX > WIDTH) {
            // Player 1 scores
            player1Score++;
            // play score sound
            resetGame();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);

        // Draw Score
        g.drawString("Player 1: " + player1Score, 50, 50);
        g.drawString("Player 2: " + player2Score, WIDTH - 150, 50);

        // Draw Ball
        g.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);

        // Draw Paddles
        g.fillRect(0, player1Y, PADDLE_WIDTH, PADDLE_HEIGHT); // Left Paddle
        g.fillRect(WIDTH - PADDLE_WIDTH, player2Y, PADDLE_WIDTH, PADDLE_HEIGHT); // Right Paddle

        // Draw Net
        for (int y = 0; y < HEIGHT; y += 15) {
            g.fillRect(WIDTH / 2 , y, 2, 10);
        }

    }
    

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ping Pong Game");
        pingPong game = new pingPong();
        frame.add(game);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}

