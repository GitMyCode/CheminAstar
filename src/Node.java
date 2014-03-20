



import sun.font.TextLabel;

import javax.swing.*;
import javax.xml.soap.Text;
import java.awt.*;
import java.util.Random;

/**
 * Created by MB on 3/6/14.
 */
public class Node extends JPanel{

    public final Color C_LIBRE = Color.white;
    public final Color C_BLOCK = Color.BLACK;
    public final Color C_START = Color.BLUE ;
    public final Color C_ARRIVE = Color.RED;

    public final int LIBRE  = 0;
    public final int BLOCK  = 1;
    public final int START  = 2;
    public final int ARRIVE = 3;

    private double h_heuristique =0;
    private int g_movementCost =0;
    private int f_totalCost =0;
    private TextArea text_heuristique;




    private Node parent;
    private int x;
    private int y;

    private int state= LIBRE ; //0- libre: 1- block: 2-start 3-arrive
    private Color state_color = Color.white;

     public Node(){


        super();
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setPreferredSize(new Dimension(100, 100));
        this.setBackground(getColor());
         text_heuristique.setText("1");

        add(text_heuristique, BorderLayout.NORTH);
    }

    public Node( int x, int y){
        super();
        this.x = x;
        this.y = y;


        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setPreferredSize(new Dimension(140,140));
        this.setBackground(getColor());
    }
    public Node( int x, int y, int state){
        super();
        this.x = x;
        this.y = y;
        this.state = state;
        setColorFromState(state);

        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setPreferredSize(new Dimension(100,100));
        this.setBackground(getColor());
    }



    public void paint(Graphics g)
    {
        super.paint(g);

        /*Wow cette affaire la donne antialiasing
        * donc beaucoup plus beau*/
        //((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(getColor());
        Dimension dim = getSize();
        g.fillRect(0,0,dim.width,dim.height);
       // g.fillOval(6, 6, dim.width -10, dim.height - 10);
    }

    public Color getColor(){
        return this.state_color;
    }
    public void setColor(Color newColor){
        this.state_color = newColor;
        repaint();
    }


    public void setState(int state) {
        this.state = state;
        setColorFromState(state);
    }
    public void setColorFromState(int state){
        switch (state){
            case 0: setColor(C_LIBRE); break;
            case 1: setColor(Color.black); break;
            case 2: setColor(C_START); break;
            case 3: setColor(C_ARRIVE); break;
        }
    }


    public double getH_heuristique() {
        return this.h_heuristique;
    }

    public int getG_movementCost() {
        return g_movementCost;
    }

    public double getF_totalCost(){
        return g_movementCost + h_heuristique;
    }

    public int getXpos() {
        return this.x;
    }

    public int getYpos() {
        return this.y;
    }


    public int getState() {
        return state;
    }

    public void setH_heuristique(double h_heuristique) {
        this.h_heuristique = h_heuristique;
    }

    public void setG_movementCost(int g_movementCost) {
        this.g_movementCost = g_movementCost;
    }

    public void setF_totalCost(int f_totalCost) {
        this.f_totalCost = f_totalCost;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setParent(Node p){
        this.parent = p;
    }
    public Node getParent(){
        return this.parent;
    }




}


