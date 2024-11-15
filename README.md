
# Interactive Animation Program

This Java program provides an interactive console-based menu for selecting and running various animation modes. Using the **biuoop** library, the program demonstrates different types of animated graphics, including abstract art and bouncing balls, with each mode offering a unique visual experience. The program is designed to be modular, allowing users to easily explore and experiment with animations.

## Features

- **Modular Menu**: The program offers four distinct animation modes, each triggered by user input:
  - **Abstract Art Drawing**: A creative, abstract visual experience.
  - **Single Ball Animation**: A simple animation with a single bouncing ball.
  - **Multiple Frames Animation**: An animation involving multiple balls with varying behaviors.
  - **Multiple Frames Bouncing Balls Animation**: A more complex animation with balls bouncing inside and outside of different regions.
  
- **User Interaction**: Users can select the mode they want to run via a simple text-based menu. The program keeps running until the user decides to exit by typing `exit`.

- **Dynamic Animations**: Each mode offers a dynamic graphical experience with random ball movements, sizes, and colors.

## How to Run

### Prerequisites
- Java 8 or later
- **biuoop** library (for GUI and animation)

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/yourrepository.git
   ```

2. Compile the program:
   ```bash
   javac Main.java
   ```

3. Run the program:
   ```bash
   java Main
   ```

4. Follow the prompts in the console to choose an animation mode:
   - **1**: Start Abstract Art Drawing.
   - **2**: Start Single Ball Animation.
   - **3**: Start Multiple Frames Animation.
   - **4**: Start Multiple Frames Bouncing Balls Animation.
   - **Type `exit`** to close the program.

## Code Overview

The `Main` class provides the user interface and handles the menu logic. The program waits for user input, then calls the appropriate method for each animation mode. Each animation is handled in its respective class:
- **`AbstractArtDrawing`**: Handles the abstract art animation.
- **`BouncingBallAnimation`**: Handles the single ball animation.
- **`MultipleBouncingBallsAnimation`**: Handles the multiple frames animation.
- **`MultipleFramesBouncingBallsAnimation`**: Handles the bouncing balls animation with multiple frames.

## Learning Goals

This program serves as a good introduction to:
- **Java graphics programming** using the **biuoop** library.
- **Modular programming** and structuring code into separate classes for clarity and reuse.
- Basic **animation techniques** such as ball movement and collision detection.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
