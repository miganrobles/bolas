import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class BallDemo   
{
    private Canvas myCanvas;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce(int numeroBolas)
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);
        Random aleatorio = new Random();
        // draw the ground
        myCanvas.drawLine(50, ground, 550, ground);
        ArrayList<BouncingBall> bolas = new ArrayList<>();
        // crate and show the balls
        for (int i = 0; i < numeroBolas; i++) {
            float r = aleatorio.nextFloat();
            float g = aleatorio.nextFloat();
            float b = aleatorio.nextFloat();
            Color color = new Color(r, g, b);// Se crea el color de forma aleatoria

            int posicion =  aleatorio.nextInt(150);
            int radio = aleatorio.nextInt(30);
            bolas.add(new BouncingBall(posicion, posicion, radio, color, ground, myCanvas));
            bolas.get(i).draw();
        }

        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            for (BouncingBall bola : bolas) {
                bola.move();
                if(bola.getXPosition() >= 550) {
                    finished = true;
                }
            }
            // stop once ball has travelled a certain distance on x axis

        }
    }
}
