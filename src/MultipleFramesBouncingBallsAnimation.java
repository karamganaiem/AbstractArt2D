// Karam Ganaiem

import biuoop.GUI;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Random;
import java.util.Scanner;

/**
 * This class represents a multiple frames bouncing balls animation using the biuoop library.
 */
public class MultipleFramesBouncingBallsAnimation {

    /**
     * Draws the bouncing balls animation on the specified GUI with the given array of balls.
     *
     * @param userScreen The GUI on which the animation is drawn.
     * @param ourballs   An array of Ball objects representing the bouncing balls in the animation.
     */
    public static void drawAnimation(GUI userScreen, Ball[] ourballs) {
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        int middle = ourballs.length / 2;

        while (true) {
            DrawSurface ballsAnimation = userScreen.getDrawSurface(); // Get a new DrawSurface in each iteration

            // Draw the grey rectangle (for first half of balls)
            ballsAnimation.setColor(Color.GRAY);
            ballsAnimation.fillRectangle(50, 50, 450, 450);

            // Move and draw the first half of the balls (inside gray rectangle)
            for (int i = 0; i < middle; i++) {
                ourballs[i].moveOneStep2();
                handleBounceInsideGray(ourballs[i]);
                ourballs[i].drawOn(ballsAnimation);
            }

            // Move and draw the second half of the balls (outside gray rectangle)
            for (int i = middle; i < ourballs.length; i++) {
                ourballs[i].moveOneStep2();
                ourballs[i].drawOn(ballsAnimation);
                handleBounceOutsideYellow(ourballs[i]);
            }

            // Draw the yellow rectangle (for second half of balls)
            ballsAnimation.setColor(Color.YELLOW);
            ballsAnimation.fillRectangle(450, 450, 150, 150);

            userScreen.show(ballsAnimation);
            sleeper.sleepFor(50);
        }
    }

    /**
     * Handles the bouncing of a ball inside the gray rectangle. If the ball reaches the boundaries of the gray
     * rectangle, its direction is reversed accordingly to simulate a bounce.
     *
     * @param givenBall The ball whose bounce inside the gray rectangle is handled.
     */
    private static void handleBounceInsideGray(Ball givenBall) {
        Point ballCenter = givenBall.getCenter();

        // Check if the ball hits the left or right boundaries of the gray rectangle
        if ((ballCenter.getX() - givenBall.getSize() <= 50 || ballCenter.getX() + givenBall.getSize() >= 500)) {
            givenBall.reverseHorizontalDirection();
        }

        // Check if the ball hits the top or bottom boundaries of the gray rectangle
        if (ballCenter.getY() - givenBall.getSize() <= 50 || ballCenter.getY() + givenBall.getSize() >= 500) {
            givenBall.reverseVerticalDirection();
        }
    }

    /**
     * Handles the bouncing of a ball outside the yellow rectangle. If the ball goes beyond the boundaries of the yellow
     * rectangle, its direction is reversed accordingly to simulate a bounce.
     *
     * @param givenBall The ball whose bounce outside the yellow rectangle is handled.
     */
    private static void handleBounceOutsideYellow(Ball givenBall) {
        Point ballCenter = givenBall.getCenter();

        // Check if the ball hits the left or right boundaries of the yellow rectangle
        if (ballCenter.getX() - givenBall.getSize() <= 450 || ballCenter.getX() + givenBall.getSize() >= 600) {
            givenBall.reverseHorizontalDirection();
        }

        // Check if the ball hits the top or bottom boundaries of the yellow rectangle
        if (ballCenter.getY() - givenBall.getSize() <= 50 || ballCenter.getY() + givenBall.getSize() >= 200) {
            givenBall.reverseVerticalDirection();
        }
    }

    /**
     * Main method for the Bouncing Ball Animation program. Parses the command line arguments as ball sizes,
     * creates an array of balls, initializes their positions and velocities, and starts the animation.
     */
    public static void Option4Player() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of balls you want: ");
        int numBalls = scanner.nextInt();  // Read the number of balls from the user input

        // Parse command line arguments as ball sizes
        int[] ballSizes = new int[numBalls]; // Start with an empty array

        // Get random sizes for the balls
        Random rand = new Random();
        for (int i = 0; i < ballSizes.length; i++) {
            ballSizes[i] = rand.nextInt(20) + 10;  // Random ball size between 10 and 30
        }

        // Create an array of balls
        Ball[] ourballs = new Ball[ballSizes.length];
        int middle = ballSizes.length / 2;

        // Initialize positions and velocities for the first half of the balls
        for (int i = 0; i < middle; i++) {
            Point startPoint = new Point(rand.nextInt(300 - ballSizes[i] + 1) + 50, rand.nextInt(300 - ballSizes[i] + 1) + 50);
            int speed = calculateSpeed(ballSizes[i]);
            Ball ball = new Ball(startPoint, ballSizes[i], Color.BLACK);
            ball.setVelocity(speed, speed);
            ourballs[i] = ball;
        }

        // Initialize positions and velocities for the second half of the balls
        for (int i = middle; i < ballSizes.length; i++) {
            Point startPoint = new Point(rand.nextInt(150 - ballSizes[i]) + 650, rand.nextInt(150 - ballSizes[i]) + 50);
            int speed = calculateSpeed(ballSizes[i]);
            Ball ball = new Ball(startPoint, ballSizes[i], Color.RED);
            ball.setVelocity(speed, speed);
            ourballs[i] = ball;
        }

        // Create GUI and start the animation
        GUI userScreen = new GUI("Bouncing Ball Animation", 800, 600);
        drawAnimation(userScreen, ourballs);
    }

    /**
     * Calculates the initial speed of a ball based on its size.
     *
     * @param ballSize The size of the ball.
     * @return The initial speed of the ball.
     */
    private static int calculateSpeed(int ballSize) {
        if (ballSize >= 50) {
            return 2;
        } else {
            return 10 - ballSize / 5;
        }
    }
}
