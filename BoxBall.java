import java.awt.*;
import java.awt.geom.*;

/**
 * Class BouncingBall - a graphical ball that observes the effect of gravity. The ball
 * has the ability to move. Details of movement are determined by the ball itself. It
 * will fall downwards, accelerating with time due to the effect of gravity, and bounce
 * upward again when hitting the ground.
 *
 * This movement can be initiated by repeated calls to the "move" method.
 * 
 * @author Michael Kölling (mik)
 * @author David J. Barnes
 * @author Bruce Quig
 *
 * @version 2011.07.31
 */

public class BoxBall
{

    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private Canvas canvas;
    private int ySpeed = 1;                // initial downward speed
    private final int groundPos;
    private final int dcha;
    private final int izq;
    private final int techo;
    private boolean derecha;    // Direccion por defecto derecha
    private boolean abajo;      // Sentido por defecto
    /**
     * Constructor for objects of class BouncingBall
     *
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball
     * @param ballColor  the color of the ball
     * @param groundPos  the position of the ground (where the wall will bounce)
     * @param drawingCanvas  the canvas to draw this ball on
     */
    public BoxBall(int xPos, int yPos, int ballDiameter, Color ballColor,
    int groundPos, int dcha, int izq, int techo, boolean derecha, boolean abajo,  Canvas drawingCanvas)
    {
        xPosition = xPos;
        yPosition = yPos;
        color = ballColor;
        diameter = ballDiameter;
        canvas = drawingCanvas;
        this.groundPos = groundPos;
        this.dcha = dcha;
        this.izq = izq;
        this.techo = techo;
        this.derecha = derecha;
        this.abajo = abajo;
    }

    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }

    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }    

    /**
     * Move this ball according to its position and speed and redraw.
     **/
    public void move()
    {
        // remove from canvas at the current position
        erase();
            
        // compute new position
        int desplaza = ySpeed;
        if (!derecha) {
            desplaza *= -1;
        }
        xPosition += desplaza;
        desplaza = 1;
        if (!abajo) {
            desplaza *= -1;
        }
        yPosition += desplaza;
        // check if it has hit the ground
        if(yPosition == (groundPos - diameter)) {
            yPosition = (int)(groundPos - diameter); 
            changeSen();
        }
        else if(yPosition == (techo)) {
            yPosition = techo; 
            changeSen();
        }
        if(xPosition == (izq - diameter)) {
            xPosition = (int)(izq - diameter); 
            changeDire();
        }
        else if(xPosition == dcha) {
            xPosition = dcha; 
            changeDire();
        }


        // draw again at new position
        draw();
    }    

    /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPosition()
    {
        return yPosition;
    }

    /**
     * Permite cambiar la direccion de la bola (izquierda o derecha)
     */
    public void changeDire()
    {
        derecha = !derecha;
    }

    /**
     * Permite cambiar el sentido de la bola (arriba o abajo)
     */
    public void changeSen()
    {
        abajo = !abajo;
    }
}
