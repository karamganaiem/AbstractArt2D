// Karam Ganaiem

import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;
import java.util.Scanner;

/**
 * This class generates random lines and displays intersections on a GUI.
 */
public class AbstractArtDrawing {

    /**
     * Generates random lines, detects and displays intersections on a GUI.
     */
    public static void lineGenerator(int numLines) {
        // Create an array to store random lines based on user input
        Line[] randomLines = new Line[numLines];

        // Create a GUI for drawing
        GUI screenGUI = new GUI("Line Generator Example", 800, 600);
        DrawSurface linesIntersectScreen = screenGUI.getDrawSurface();

        // Create a random number generator
        Random rand = new Random();

        // Draw random lines on the GUI
        linesIntersectScreen.setColor(Color.BLACK);
        for (int i = 0; i < numLines; i++) {
            int curLineX1 = rand.nextInt(800);
            int curLineY1 = rand.nextInt(600);

            int curLineX2 = rand.nextInt(800);
            int curLineY2 = rand.nextInt(600);

            // Assign the generated line to the randomLines array
            randomLines[i] = new Line(curLineX1, curLineY1, curLineX2, curLineY2);

            // Draw each line in black
            linesIntersectScreen.setColor(Color.BLACK);
            linesIntersectScreen.drawLine(curLineX1, curLineY1, curLineX2, curLineY2);
        }

        // Detect and draw intersections between lines to find the green triangles
        for (int i = 0; i < numLines; i++) {
            for (int j = 0; j < numLines; j++) {
                for (int k = 0; k < numLines; k++) {
                    // With each k index of the line we check if it intersects with j and i at the same time
                    if (randomLines[k].isIntersecting(randomLines[j], randomLines[i])) {
                        // If that is true we find the three points of the triangle
                        Point greenPoint1 = randomLines[k].intersectionWith(randomLines[j]);
                        Point greenPoint2 = randomLines[i].intersectionWith(randomLines[j]);
                        Point greenPoint3 = randomLines[i].intersectionWith(randomLines[k]);

                        // We draw the three lines over the black ones with the given green points
                        linesIntersectScreen.setColor(Color.GREEN);
                        linesIntersectScreen.drawLine(
                                (int) greenPoint1.getX(), (int) greenPoint1.getY(),
                                (int) greenPoint2.getX(), (int) greenPoint2.getY());
                        linesIntersectScreen.drawLine(
                                (int) greenPoint3.getX(), (int) greenPoint3.getY(),
                                (int) greenPoint2.getX(), (int) greenPoint2.getY());
                        linesIntersectScreen.drawLine(
                                (int) greenPoint1.getX(), (int) greenPoint1.getY(),
                                (int) greenPoint3.getX(), (int) greenPoint3.getY());
                    }
                }
            }
        }

        // Detect and draw intersections within the same line
        for (int i = 0; i < numLines; i++) {
            for (int j = 0; j < numLines; j++) {
                if (randomLines[j].isIntersecting(randomLines[i])) {
                    // If there is intersection between two lines, draw the red ball
                    Point interPointRed = randomLines[j].intersectionWith(randomLines[i]);
                    linesIntersectScreen.setColor(Color.RED);
                    linesIntersectScreen.fillCircle((int) interPointRed.getX(), (int) interPointRed.getY(), 3);
                }
            }

            // Draw the middle point of each line
            Point midPoint = randomLines[i].middle();
            linesIntersectScreen.setColor(Color.BLUE);
            linesIntersectScreen.fillCircle((int) midPoint.getX(), (int) midPoint.getY(), 3);
        }

        // Display the GUI with the drawn lines and intersections
        screenGUI.show(linesIntersectScreen);
    }

    /**
     * This method starts the Abstract Art Drawing.
     */
    public static void Option1Player() {

        // Ask the user for the number of lines
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of lines you want to generate: ");
        int numLines = scanner.nextInt();  // Read the number of lines from the user

        // Call the method to generate and display lines with user input
        lineGenerator(numLines);
    }
}
