// Karam Ganaiem

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Create Scanner object for reading user input

        // Display the menu options using printf for console output
        displayMenu();

        // Menu loop to capture user input from the console
        while (true) {
            String userInput = scanner.nextLine();  // Read user input as a string

            // Check user input and perform corresponding actions
            switch (userInput) {
                case "1":
                    startAbstractArtDrawing();
                    break;
                case "2":
                    startSingleBallAnimation();
                    break;
                case "3":
                    startMultipleFramesAnimation();
                    break;
                case "4":
                    startMultipleFramesBouncingBallsAnimation();
                    break;
                case "exit":
                    System.out.println("Exiting the menu.");
                    return;  // Exit the program if user types 'exit'
                default:
                    System.out.println("Invalid option, please choose again.");
                    displayMenu();  // Display the menu again if the option is invalid
            }
        }
    }

    // Display the menu options in the console
    public static void displayMenu() {
        System.out.println("Choose Mode:");
        System.out.println("1 & Enter for AbstractArtDrawing");
        System.out.println("2 & Enter for Single Ball Animation");
        System.out.println("3 & Enter for Multiple Frames Animation");
        System.out.println("4 & Enter for MultipleFramesBouncingBallsAnimation");
        System.out.println("Type & Enter 'exit' to exit the menu");
    }

    // Start the Abstract Art Drawing
    public static void startAbstractArtDrawing() {
        AbstractArtDrawing.Option1Player();
        // Call the method for Abstract Art Drawing (replace with actual code for the art)
    }

    // Start the animation with a single ball
    public static void startSingleBallAnimation() {
        BouncingBallAnimation.Option2Player();
        // Call the method for Single Ball Animation (replace with actual code for the animation)
    }

    // Start the animation with multiple balls
    public static void startMultipleFramesAnimation() {
        MultipleBouncingBallsAnimation.Option3Player();
        // Call the method for Multiple Frames Animation (replace with actual code for the animation)
    }

    // Start the MultipleFramesBouncingBallsAnimation
    public static void startMultipleFramesBouncingBallsAnimation() {
        MultipleFramesBouncingBallsAnimation.Option4Player();
        // Call the method for Multiple Frames Bouncing Balls Animation (replace with actual code)
    }
}
