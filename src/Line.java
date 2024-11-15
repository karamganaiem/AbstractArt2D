// Karam Ganaiem

/**
 * The Line class represents a line segment in a 2D plane.
 * It can be defined by two points or by four coordinates.
 * This class provides methods for calculating line properties,
 * detecting intersections, and finding intersection points.
 */
public class Line {

    /**
     * The starting point of the line segment.
     */
    private Point start;

    /**
     * The ending point of the line segment.
     */
    private Point end;

    /**
     * The x-coordinate of the starting point.
     */
    private double x1;

    /**
     * The y-coordinate of the starting point.
     */
    private double y1;

    /**
     * The x-coordinate of the ending point.
     */
    private double x2;

    /**
     * The y-coordinate of the ending point.
     */
    private double y2;

    /**
     * Constructs a Line object with two specified points.
     *
     * @param start The starting point of the line segment.
     * @param end   The ending point of the line segment.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;

        this.x1 = start.getX();
        this.y1 = start.getY();
        this.x2 = end.getX();
        this.y2 = end.getY();
    }

    /**
     * Constructs a Line object with specified coordinates.
     *
     * @param x1 The x-coordinate of the starting point.
     * @param y1 The y-coordinate of the starting point.
     * @param x2 The x-coordinate of the ending point.
     * @param y2 The y-coordinate of the ending point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point startPoint = new Point(x1, y1);
        Point endPoint = new Point(x2, y2);

        // We assign each element to its respective value
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;

        // Delegate to the other constructor using Point objects
        this.start = startPoint;
        this.end = endPoint;
    }

    /**
     * Calculates the length of the line segment.
     *
     * @return The length of the line segment.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Finds the middle point of the line segment.
     *
     * @return The middle point of the line segment.
     */
    public Point middle() {

        // We calculate each mid point of the line
        double midX = (this.x2 + this.x1) / 2;
        double midY = (this.y2 + this.y1) / 2;

        return new Point(midX, midY);
    }

    /**
     * Gets the starting point of the line segment.
     *
     * @return The starting point of the line segment.
     */
    public Point start() {
        return new Point(this.x1, this.y1);
    }

    /**
     * Gets the ending point of the line segment.
     *
     * @return The ending point of the line segment.
     */
    public Point end() {
        return new Point(this.x2, this.y2);
    }

    /**
     * Checks if this line intersects with another line.
     *
     * @param other The other line to check for intersection.
     * @return True if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        // Lines share an endpoint or the start point
        if (this.start.equals(other.end) || this.end.equals(other.start)) {
            return true;
        }

        // We check the slope of the given line of the current one
        double thisSlope = calculateSlope(this.start, this.end);
        double otherSlope = calculateSlope(other.start, other.end);

        // Lines are parallel, no intersection
        if (thisSlope == otherSlope) {
            return false;
        }

        // We find the b parameter in the y=mx+b func
        double thisBParam = findYIntercept(this.start, thisSlope);
        double otherBParam = findYIntercept(other.start, otherSlope);

        // We find the x and y of the intersection point
        double interX = (otherBParam - thisBParam) / (thisSlope - otherSlope);
        double interY = (thisSlope * interX) + thisBParam;

        // We check if the point is in range and return true or fals
        return ((isPointInRange(interX, interY, this.x1, this.y1, this.x2, this.y2))
                && (isPointInRange(interX, interY, other.x1, other.y1, other.x2, other.y2)));

    }

    /**
     * Checks if three lines intersect with each other.
     *
     * @param other1 The first other line to check for intersection.
     * @param other2 The second other line to check for intersection.
     * @return True if all three lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other1, Line other2) {

        // We check if given line connects with the other
        boolean intersectThisAnd1 = isIntersecting(other1);
        boolean intersectThisAnd2 = isIntersecting(other2);
        boolean intersectBetween1and2 = other1.isIntersecting(other2);

        // If all three booleans return True we return true, else False
        return ((intersectThisAnd1) && (intersectThisAnd2) && (intersectBetween1and2));
    }

    /**
     * Finds the intersection point if two lines intersect.
     *
     * @param other The other line to find intersection with.
     * @return The intersection point if the lines intersect, null otherwise.
     */
    public Point intersectionWith(Line other) {
        double otherSlope = 0, thisSlope = 0, bForOther = 0, bForThis = 0;

        if (other.x2 == other.x1) {
            otherSlope = 0;
            bForOther = other.y1;
        } else if (this.x2 == this.x1) {
            thisSlope = 0;
            bForThis = this.y1;
        } else {
            // Calculate slopes
            otherSlope = (other.y2 - other.y1) / (other.x2 - other.x1);
            thisSlope = (this.y2 - this.y1) / (this.x2 - this.x1);

            // Calculate intercepts
            bForOther = other.y1 - otherSlope * other.x1;
            bForThis = this.y1 - thisSlope * this.x1;
        }

        // Check if lines are parallel (won't intercect)
        if (otherSlope == thisSlope) {
            return null;
        }

        // Calculate intersection point
        double interX = (bForThis - bForOther) / (otherSlope - thisSlope);
        double interY = otherSlope * interX + bForOther;

        // Check if the intersection point is within the parameter range of both lines
        if ((isPointInRange(interX, interY, other.x1, other.y1, other.x2, other.y2))
                && (isPointInRange(interX, interY, this.x1, this.y1, this.x2, this.y2))) {
            return new Point(interX, interY);
        } else {
            // Intersection point is not within the parameter range of both lines
            return null;
        }
    }

    /**
     * Calculates the slope of a line given two points.
     *
     * @param p1 The first point.
     * @param p2 The second point.
     * @return The slope of the line defined by the two points.
     * If the line is vertical, returns Double.POSITIVE_INFINITY.
     */
    private double calculateSlope(Point p1, Point p2) {

        // We calculate delta X and delta Y
        double deltaX = (p2.getX() - p1.getX());
        double deltaY = (p2.getY() - p1.getY());

        // if the delta X is 0 then it is a vertical line
        if (deltaX == 0) {
            return Double.POSITIVE_INFINITY;
        }
        return (deltaY) / (deltaX);
    }

    /**
     * Calculates the y-intercept of a line given a point and slope.
     *
     * @param point The point on the line.
     * @param slope The slope of the line.
     * @return The y-intercept of the line.
     * If the line is vertical, returns Double.NaN.
     */
    private double findYIntercept(Point point, double slope) {

        // Vertical line has no y-intercept
        if (Double.isInfinite(slope)) {
            return Double.NaN;
        }
        return point.getY() - slope * point.getX();
    }

    /**
     * Checks if a point (x, y) is within the line segment defined by (x1, y1) and (x2, y2).
     *
     * @param interX The x-coordinate of the point to check.
     * @param interY The y-coordinate of the point to check.
     * @param x1     The x-coordinate of the first endpoint of the line segment.
     * @param y1     The y-coordinate of the first endpoint of the line segment.
     * @param x2     The x-coordinate of the second endpoint of the line segment.
     * @param y2     The y-coordinate of the second endpoint of the line segment.
     * @return True if the point is within the line segment, false otherwise.
     */
    private boolean isPointInRange(double interX, double interY, double x1, double y1, double x2, double y2) {
        return ((interX >= Math.min(x1, x2) && interX <= Math.max(x1, x2))
                && (interY >= Math.min(y1, y2) && interY <= Math.max(y1, y2)));
    }

    /**
     * Checks if two lines are equal.
     *
     * @param other The other line to compare with.
     * @return True if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if (other == null) {
            return false;
        }

        // We check if the given line is equal to this line using arithmetic calculations
        boolean sameEndpoints = (((other.x1 == this.x1) && (other.y1 == this.y1))
                && ((other.x2 == this.x2) && (other.y2 == this.y2)));
        boolean swappedEndpoints = (((other.x1 == this.x2) && (other.y1 == this.y2))
                && ((other.x2 == this.x1) && (other.y2 == this.y1)));

        // return true if they are equal
        return sameEndpoints || swappedEndpoints;
    }

}

 /*
    public boolean isIntersecting(Line other) {
        final double epsilon = 1e-10; // Adjust the epsilon value based on your requirements

        // Lines share an endpoint or the start point
        if (this.start.equals(other.end) || this.end.equals(other.start)
                || this.start.equals(other.start) || this.end.equals(other.end)) {
            return true;
        }

        // We check the slope of the given line of the current one
        double thisSlope = calculateSlope(this.start, this.end);
        double otherSlope = calculateSlope(other.start, other.end);

        // Lines are parallel, no intersection
        if (Double.isNaN(thisSlope) && Double.isNaN(otherSlope)) {
            return false;
        }
        if (Double.isInfinite(thisSlope) && Double.isInfinite(otherSlope)) {
            return false;
        }
        if (Math.abs(thisSlope - otherSlope) < epsilon) {
            return false;
        }

        // We find the b parameter in the y=mx+b func
        double thisBParam = findYIntercept(this.start, thisSlope);
        double otherBParam = findYIntercept(other.start, otherSlope);

        // this is vertical
        if (Double.isNaN(otherBParam)) {
            double interY = (otherSlope * this.x1) + thisBParam;
            double interX = this.x1;

            return (isPointInRange(interX, interY, this.x1, this.y1, this.x2, this.y2, epsilon)
                    && isPointInRange(interX, interY, other.x1, other.y1, other.x2, other.y2, epsilon));
        }

        // other is vertical
        if (Double.isNaN(thisBParam)) {
            double interY = (thisSlope * other.x1) + otherBParam;
            double interX = other.x1;

            return (isPointInRange(interX, interY, this.x1, this.y1, this.x2, this.y2, epsilon)
                    && isPointInRange(interX, interY, other.x1, other.y1, other.x2, other.y2, epsilon));
        }

        // this is horizontal
        if (Double.isInfinite(thisSlope)) {
            double interX = (thisBParam - otherBParam) / otherSlope;
            double interY = thisBParam;

            return (isPointInRange(interX, interY, this.x1, this.y1, this.x2, this.y2, epsilon)
                    && isPointInRange(interX, interY, other.x1, other.y1, other.x2, other.y2, epsilon));
        }

        // other is horizontal
        if (Double.isInfinite(otherSlope)) {
            double interX = (otherBParam - thisBParam) / thisSlope;
            double interY = otherBParam;

            return (isPointInRange(interX, interY, this.x1, this.y1, this.x2, this.y2, epsilon)
                    && isPointInRange(interX, interY, other.x1, other.y1, other.x2, other.y2, epsilon));
        }

        // We find the x and y of the intersection point
        double interX = (otherBParam - thisBParam) / (thisSlope - otherSlope);
        double interY = (thisSlope * interX) + thisBParam;

        // We check if the point is in range and return true or false
        return (isPointInRange(interX, interY, this.x1, this.y1, this.x2, this.y2, epsilon)
                && isPointInRange(interX, interY, other.x1, other.y1, other.x2, other.y2, epsilon));
    }


    public Point intersectionWith(Line other) {
        final double epsilon = 1e-10; // Adjust the epsilon value based on your requirements

        // Lines share an endpoint or the start point
        if (this.start.equals(other.end) || this.end.equals(other.start)) {
            return new Point(this.x2, this.y2);
        }
        if (this.start.equals(other.start) || this.end.equals(other.end)) {
            return new Point(this.x1, this.y1);
        }

        // We check the slope of the given line of the current one
        double thisSlope = calculateSlope(this.start, this.end);
        double otherSlope = calculateSlope(other.start, other.end);

        // Lines are parallel, no intersection
        if (Double.isNaN(thisSlope) && Double.isNaN(otherSlope)) {
            return null;
        }
        if (Double.isInfinite(thisSlope) && Double.isInfinite(otherSlope)) {
            return null;
        }
        if (Math.abs(thisSlope - otherSlope) < epsilon) {
            return null;
        }

        // We find the b parameter in the y=mx+b func
        double thisBParam = findYIntercept(this.start, thisSlope);
        double otherBParam = findYIntercept(other.start, otherSlope);

        // this is vertical
        if (Double.isNaN(otherBParam)) {
            double interY = (otherSlope * this.x1) + thisBParam;
            double interX = this.x1;

            if (isPointInRange(interX, interY, this.x1, this.y1, this.x2, this.y2, epsilon)
                    && isPointInRange(interX, interY, other.x1, other.y1, other.x2, other.y2, epsilon)) {
                return new Point(interX, interY);
            }
        }

        // other is vertical
        if (Double.isNaN(thisBParam)) {
            double interY = (thisSlope * other.x1) + otherBParam;
            double interX = other.x1;

            if (isPointInRange(interX, interY, this.x1, this.y1, this.x2, this.y2, epsilon)
                    && isPointInRange(interX, interY, other.x1, other.y1, other.x2, other.y2, epsilon)) {
                return new Point(interX, interY);
            }
        }

        // this is horizontal
        if (Double.isInfinite(thisSlope)) {
            double interX = (thisBParam - otherBParam) / otherSlope;
            double interY = thisBParam;

            if (isPointInRange(interX, interY, this.x1, this.y1, this.x2, this.y2, epsilon)
                    && isPointInRange(interX, interY, other.x1, other.y1, other.x2, other.y2, epsilon)) {
                return new Point(interX, interY);
            }
        }

        // other is horizontal
        if (Double.isInfinite(otherSlope)) {
            double interX = (otherBParam - thisBParam) / thisSlope;
            double interY = otherBParam;

            if (isPointInRange(interX, interY, this.x1, this.y1, this.x2, this.y2, epsilon)
                    && isPointInRange(interX, interY, other.x1, other.y1, other.x2, other.y2, epsilon)) {
                return new Point(interX, interY);
            }
        }

        // We find the x and y of the intersection point
        double interX = (otherBParam - thisBParam) / (thisSlope - otherSlope);
        double interY = (thisSlope * interX) + thisBParam;

        // We check if the point is in range and return true or false
        if (isPointInRange(interX, interY, this.x1, this.y1, this.x2, this.y2, epsilon)
                && isPointInRange(interX, interY, other.x1, other.y1, other.x2, other.y2, epsilon)) {
            return new Point(interX, interY);
        }

        return null;
    }

    */

