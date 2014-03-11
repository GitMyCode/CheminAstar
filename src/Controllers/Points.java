package Controllers;

import java.awt.*;

/**
 * Created by MB on 3/6/14.
 */
public class Points {

   private int x;
   private int y;
   private Color color;
    public Points(int X, int Y , Color color){

        this.x = X;
        this.y = Y;
        this.color = color;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor(){
        return this.color;
    }
    public void setColor(Color newColor){
        this.color = newColor;
    }
}
