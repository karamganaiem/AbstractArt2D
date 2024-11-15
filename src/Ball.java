// Karam Ganaiem

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The Ball class represents a ball in a 2D space.
 */
public class Ball {
    // Attributes
    private Point center;  // The center point of the ball
    private int r;         // The radius of the ball
    private Color color;   // The color of the ball
    private Velocity v;    // The velocity of the ball

    /**
     * Constructor for creating a new Ball.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    // Accessors

    /**
     * Get the x-coordinate of the ball's center.
     *
     * @return the x-coordinate of the ball's center
     */
    public double getX() {
        return this.center.getX();
    }

    /**
     * Get the y-coordinate of the ball's center.
     *
     * @return the y-coordinate of the ball's center
     */
    public double getY() {
        return this.center.getY();
    }

    /**
     * Get the size (radius) of the ball.
     *
     * @return the size (radius) of the ball
     */
    public double getSize() {
        return this.r;
    }

    /**
     * Get the color of the ball.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draw the ball on the given DrawSurface.
     *
     * @param surface the DrawSurface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        double ballXCord = center.getX();
        double ballYCord = center.getY();
        double ballRad = getSize();
        surface.fillCircle((int) ballXCord, (int) ballYCord, (int) ballRad);
    }

    /**
     * Set the velocity of the ball.
     *
     * @param v the velocity to set
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Set the velocity of the ball using components (dx, dy).
     *
     * @param dx the change in x-coordinate per step
     * @param dy the change in y-coordinate per step
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Get the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        if (v == null) {
            this.setVelocity(1, 1);
        }
        return v;
    }

    /**
     * Reverse the vertical direction of the ball's velocity.
     */
    public void reverseVerticalDirection() {
        this.getVelocity().setDy(-this.getVelocity().getDy());
    }

    /**
     * Get the center point of the ball.
     *
     * @return the center point of the ball
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Reverse the horizontal direction of the ball's velocity.
     */
    public void reverseHorizontalDirection() {
        this.getVelocity().setDx(-this.getVelocity().getDx());
    }

    /**
     * Move the ball one step, handling collisions with screen borders.
     */
    public void moveOneStep() {
        Point newCenter = this.getVelocity().applyToPoint(this.center);

        // Check if the ball is about to go outside the screen borders
        double x = newCenter.getX();
        double y = newCenter.getY();

        if (x - r <= 0 || x + r >= 400) {
            this.getVelocity().setDx(-this.getVelocity().getDx()); // Reverse horizontal direction
        }

        if (y - r <= 0 || y + r >= 400) {
            this.getVelocity().setDy(-this.getVelocity().getDy()); // Reverse vertical direction
        }

        // Update the ball's center with the new position
        this.center = newCenter;
    }

    /**
     * Move the ball one step, handling collisions with screen borders.
     * Updated for a larger surface (800x600).
     */
    public void moveOneStep2() {
        Point newCenter = this.getVelocity().applyToPoint(this.center);

        // Check if the ball is about to go outside the screen borders
        double x = newCenter.getX();
        double y = newCenter.getY();

        if (x - r <= 0 || x + r >= 800) {
            this.getVelocity().setDx(-this.getVelocity().getDx()); // Reverse horizontal direction
        }

        if (y - r <= 0 || y + r >= 600) {
            this.getVelocity().setDy(-this.getVelocity().getDy()); // Reverse vertical direction
        }

        // Update the ball's center with the new position
        this.center = newCenter;
    }
}

