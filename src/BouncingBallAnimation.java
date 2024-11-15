// Karam Ganaiem

import biuoop.GUI;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Scanner;

/**
 * A class for a simple bouncing ball animation.
 */
public class BouncingBallAnimation {

    /**
     * Draws a bouncing ball animation on a GUI.
     *
     * @param x The initial x-coordinate of the ball.
     * @param y The initial y-coordinate of the ball.
     * @param r The radius of the ball.
     * @param v The initial velocity of the ball.
     */
    public static void drawAnimation(int x, int y, int r, int v) {

        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        GUI gui = new GUI("Bouncing Ball Animation", 400, 400);

        // We create new ball with all the given input
        Point ballPoint = new Point(x, y);
        Ball oneBall = new Ball(ballPoint, r, Color.blue);
        oneBall.setVelocity(v, v);

        while (true) {
            DrawSurface d = gui.getDrawSurface();

            // Clear the previous drawings on the surface
            d.setColor(Color.WHITE);
            d.fillRectangle(0, 0, gui.getDrawSurface().getWidth(), gui.getDrawSurface().getHeight());

            // we move the ball each iritation
            oneBall.moveOneStep();
            oneBall.drawOn(d);

            // We show the ball in the GUI
            gui.show(d);
            sleeper.sleepFor(50);
        }
    }

    public static void Option2Player(){

        // Ask the user for the number of lines
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the ball you want to generate: ");
        int ballSize = scanner.nextInt();  // Read the number of lines from the user

        // Call the method to generate and display lines with user input
        // We store the input Values in indiviusal variables
        int value1 = 50;
        int value2 = 150;
        int value3 = ballSize;
        int value4 = 10;

        // Invoke drawAnimation with the array of balls
        drawAnimation(value1, value2, value3, value4);
    }
}
