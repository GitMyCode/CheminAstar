
import Views.OptionsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * Created by desk on 2/9/14.
 */
public class FrameGeneral extends JFrame implements ActionListener{


    public static final int LIBRE  = 0;
    public static final int BLOCK  = 1;
    public static final int START  = 2;
    public static final int ARRIVE = 3;

    public static final int COUT = 15;
    JFrame frame;
    public OptionsView optionsView;
    public Grid view_grid;
    public int  row=30;
    public int col = 30;


    public Node depart;
    public Node arrive;

    ArrayList<Node> openList = new ArrayList<Node>();
    ArrayList<Node> closedList = new ArrayList<Node>();

    public FrameGeneral(){


        frame = new JFrame("Puissance 4");
        frame.setSize(650,650);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);


        this.optionsView = new OptionsView();
        this.optionsView.setSize(20,10);

        initGrid();
        optionsView.setListener(this);
        frame.add(optionsView, BorderLayout.SOUTH);
       // frame.add(view_grid,BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void initGrid(){

        this.view_grid = null;
        this.view_grid = new Grid(this.row,this.col);


        Random ramdom = new Random();
        int ran_row = ramdom.nextInt(row);
        int ran_col = ramdom.nextInt(col);
        this.depart = null;
        this.depart = new Node(ran_row,ran_col,2);
        view_grid.setNode(ran_row,ran_col,2);



        ran_row = ramdom.nextInt(row);
        ran_col = ramdom.nextInt(col);
        this.arrive= null;
        this.arrive = new Node(ran_row,ran_col,3);
        view_grid.setNode(ran_row,ran_col,3);
        for (int i=0;i< view_grid.getNbSquare()/2; i++){
            ran_row = ramdom.nextInt(row);
            ran_col = ramdom.nextInt(col);
            if(depart.getXpos()!=ran_row && depart.getYpos()!=ran_col
                    && arrive.getXpos()!=ran_row && arrive.getYpos()!=ran_col){
                 view_grid.setNode(ran_row,ran_col,1);
            }

        }

        this.frame.add(this.view_grid,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        System.out.println(actionEvent.getActionCommand());
        if(actionEvent.getActionCommand() == "Reset"){
          // view_grid.removeAll();

            openList.clear();
            closedList.clear();


            this.frame.remove(this.view_grid);
            initGrid();
            view_grid.updateUI();
        }else{
            dessiner();
            view_grid.updateUI();
        }

}

    public void dessiner(){
        openList.add(depart);
        for(;openList.size()>0;){
            Node current = findBestOpen();
            closedList.add(current);
            current.setColor(Color.pink);
            openList.remove(current);
            if(current.getState() == 3){
                System.out.println("arrive");
                break;
            }
            calculerVoisinage(current);
        }

        Node unPas = view_grid.grids[arrive.getXpos()][arrive.getYpos()];
        ArrayList<Node> path = new ArrayList<Node>();
        while (unPas.getParent() !=null){
            System.out.println("ici");
            path.add(unPas);
            unPas = unPas.getParent();
        }


        for(Node pathNode : path){
            System.out.println("un pas");
            pathNode.setColor(Color.orange);

        }
        depart.setColor(Color.GREEN);
        arrive.setColor(Color.RED);
    }

    public Node findBestOpen(){
        Node best = openList.get(0);
        int best_Cost = best.getF_totalCost();
        for(Node n : openList){
            if(n.getF_totalCost() < best_Cost){
                best_Cost = n.getF_totalCost();
                best = n;
            }
        }
        return best ;
    }

    public void calculerVoisinage(Node current){

        ArrayList<Node> voisins = findVoisin(current);
        for(Node voisin : voisins){
            if(voisin.getState() != 1 && !closedList.contains(voisin)){
                openList.add(voisin);
                voisin.setColor(Color.gray);
                voisin.setParent(current);
                voisin.setG_movementCost(current.getG_movementCost()+ COUT );
                voisin.setH_heuristique(distance(voisin.getXpos(),voisin.getYpos()));

            }else{
                 int newG = current.getG_movementCost() + COUT;
                if(newG < voisin.getG_movementCost()){
                    voisin.setG_movementCost(newG);
                    voisin.setParent(current);
                }
            }
        }

       }


    public ArrayList<Node> findVoisin(Node current){
        ArrayList<Node> voisins = new ArrayList<Node>();
        int x_cur = current.getXpos();
        int y_cur = current.getYpos();
        if(view_grid.valideCoord(x_cur+1,y_cur))
            voisins.add(view_grid.grids[x_cur+1][y_cur]) ;
        if(view_grid.valideCoord(x_cur-1,y_cur))
             voisins.add(view_grid.grids[x_cur-1][y_cur]) ;
        if(view_grid.valideCoord(x_cur,y_cur+1))
             voisins.add(view_grid.grids[x_cur][y_cur+1]) ;
        if(view_grid.valideCoord(x_cur,y_cur-1))
             voisins.add(view_grid.grids[x_cur][y_cur-1]) ;

        return voisins;
    }
    public int distance(int x1, int y1){

       return (int) (Math.abs((x1 -arrive.getXpos()) + Math.abs(y1 - arrive.getYpos())))*COUT;
    //return (int) Math.sqrt(Math.pow((x1 -arrive.getXpos()),2) + Math.pow((y1 - arrive.getYpos()),2));  Ne marche pas

    }


}
