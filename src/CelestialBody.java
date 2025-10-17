/**
 * @author Ty Van Heerden
 * @since Oct. 15, 2025
 * @version 1.0
 */

import java.awt.Color;

/**
 * This class represents a single "celestial" used in this project.
 * Stores position x, y (in pixels), the velocity (pixels per update), the size, as well as the color of the star.
 * 
 *      
 */
public class CelestialBody {
    public int x;
    public int y; 
    public double velocityX;
    public double velocityY;
    public int size;
    public Color color;

    /**
     * Constructor of te star objects
     * 
     * @param x star's initial x position on the canvas
     * @param y star's initial y position on the canvas
     * @param velocityX star's horizontal velocity in pixels per update
     * @param velocityY star's vertical velocity in pixels per update
     * @param size renders radius in pixels
     * @param color colors of the stars
     */
    public CelestialBody(int x, int y, double velocityX, double velocityY, int size, Color color){
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.size = size;
        this.color = color;
    }

    /** @return current postion of x in pixels */
    public int getX(){
        return x;
    }
        
    /** @return current position of y in pixels */
    public int getY(){
        return y;
    }

    /** @return current horizontal velocity */
    public double getVelocityX(){
        return velocityX;
    }

    /** @return current vertical velocity */
    public double getVelocityY(){
        return velocityY;
    }

    /** @return radius in pixels*/
    public int getSize(){
        return size;
    }

    /** @return color of stars */
    public Color getColor(){
        return color;
    }
}
