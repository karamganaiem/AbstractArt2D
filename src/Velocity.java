// Karam Ganaiem

/**
 * The Velocity class represents a 2D vector describing the change in position (dx, dy).
 * It provides methods for creating velocities, applying them to points, and accessing
 * the components of the vector.
 */
public class Velocity {
    /**
     * The change in the x-coordinate (dx).
     */
    private double dx;

    /**
     * The change in the y-coordinate (dy).
     */
    private double dy;

    /**
     * Constructs a Velocity object with specified changes in x and y coordinates.
     *
     * @param dx The change in the x-coordinate.
     * @param dy The change in the y-coordinate.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Takes a point with position (x, y) and returns a new point
     * with position (x + dx, y + dy).
     *
     * @param givenPoint The point to which the velocity is applied.
     * @return A new point after applying the velocity.
     */
    public Point applyToPoint(Point givenPoint) {
        // Create a new point with the same coordinates
        Point newballPoint = new Point(givenPoint.getX(), givenPoint.getY());

        // Update the coordinates based on the velocity
        newballPoint.setX(givenPoint.getX() + dx);
        newballPoint.setY(givenPoint.getY() + dy);
        return newballPoint;
    }

    /**
     * Creates a new Velocity instance from an angle and speed.
     *
     * @param angle The angle in degrees.
     * @param speed The speed of the velocity.
     * @return A new Velocity instance based on the given angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // Convert angle to radians
        double angleInRadians = Math.toRadians(angle);

        // Calculate dx and dy components based on angle and speed
        double dx = speed * Math.cos(angleInRadians);
        double dy = speed * Math.sin(angleInRadians);

        // Return a new Velocity instance
        return new Velocity(dx, dy);
    }


    /**
     * Sets the change in the x-coordinate (dx) of the velocity.
     *
     * @param newDx The new value for dx.
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**
     * Sets the change in the y-coordinate (dy) of the velocity.
     *
     * @param newDy The new value for dy.
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    /**
     * Gets the change in the x-coordinate (dx) of the velocity.
     *
     * @return The change in the x-coordinate.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets the change in the y-coordinate (dy) of the velocity.
     *
     * @return The change in the y-coordinate.
     */
    public double getDy() {
        return this.dy;
    }
}