// Karam Ganaiem

import biuoop.GUI;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Random;
import java.util.Scanner;

/**
 * The MultipleBouncingBallsAnimation class creates a GUI window and animates bouncing balls within it.
 */
public class MultipleBouncingBallsAnimation {

    /**
     * Draws the bouncing balls animation on the given GUI window.
     *
     * @param ballsScreen the GUI window
     * @param balls       an array of balls to be animated
     */
    public static void drawAnimation(GUI ballsScreen, Ball[] balls) {
        biuoop.Sleeper sleeper = new biuoop.Sleeper();

        while (true) {
            DrawSurface userScreen = ballsScreen.getDrawSurface();

            // Move and draw each ball
            for (int i = 0; i < balls.length; i++) {
                balls[i].moveOneStep();
                balls[i].drawOn(userScreen);
            }

            // Show the updated GUI window
            ballsScreen.show(userScreen);
            sleeper.sleepFor(50);  // Pause for 50 milliseconds
        }
    }

    /**
     * The main method initializes and runs the bouncing balls animation.
     *
     * -line arguments (not used)
     */
    public static void Option3Player() {
        // Ask the user for the number of balls
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of balls you want: ");
        int numBalls = scanner.nextInt();  // Read the number of balls from user input

        // Ask the user for the average size and velocity of the balls
        System.out.print("Enter the average size of the balls: ");
        int avgBallSize = scanner.nextInt();  // Average size of the balls

        System.out.print("Enter the average velocity of the balls: ");
        int avgBallVelocity = scanner.nextInt();  // Average velocity of the balls

        // Define the range for the random values (±5 from the average)
        int sizeRange = 5;
        int velocityRange = 5;

        Ball[] ourBalls = new Ball[numBalls];
        Random rand = new Random();

        // Create balls with random sizes and velocities around the average
        for (int i = 0; i < numBalls; i++) {
            // Randomly vary the size and velocity around the given average
            int randomSize = avgBallSize + rand.nextInt(sizeRange * 2 + 1) - sizeRange;  // Random size within range
            int randomVelocity = avgBallVelocity + rand.nextInt(velocityRange * 2 + 1) - velocityRange;  // Random velocity within range

            // Ensure the size and velocity are positive
            randomSize = Math.max(randomSize, 1);  // Ball size cannot be less than 1
            randomVelocity = Math.max(randomVelocity, 1);  // Velocity cannot be less than 1

            // Create the ball with a random starting position
            Point startPoint = new Point(rand.nextInt(350), rand.nextInt(350));  // Random starting position within the screen
            Ball curBall = new Ball(startPoint, randomSize, Color.BLACK);
            curBall.setVelocity(randomVelocity, randomVelocity);

            // Store the ball in the array
            ourBalls[i] = curBall;
        }

        // Create and display the GUI window for the bouncing balls animation
        GUI ballsScreen = new GUI("Bouncing Ball Animation", 400, 400);
        drawAnimation(ballsScreen, ourBalls);
    }
}
