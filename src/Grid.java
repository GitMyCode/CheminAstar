/**
 * Created by desk on 2/9/14.
 */


import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;


import javax.swing.*;


public class Grid extends JPanel implements MouseListener{


    Node[] squares;

    Node[][] grids;
    //JPanel panel;
    int nb_square;
    int row;
    int col;
    public Grid(){
        setSize(200, 100);
         }
    public Grid(int row, int col){
       super();
        this.row = row;
        this.col = col;
        this.nb_square = row * col;
        grids = new Node[row][col];
        setLayout(new GridLayout(row,col));
        for(int i =0; i< row;i++){
            for(int j =0; j<col;j++){
                grids[i][j]= new Node(i,j);
              //  grids[i][j].addMouseListener(this);
                add(grids[i][j]);
            }
        }
 }

    public void addController(MouseListener c){
        for(int i=0;i< row ; i++){
            for(int j=0; j<col ;j++){
                grids[i][j].addMouseListener(c);
            }
        }
    }

    public JPanel getSquareIndex(int index){
        return this.grids[index][0];
    }
    public int getNbSquare(){
        return nb_square;
    }

   public void setNode(int i, int j, int state){
        grids[i][j].setState(state);

    }
    public boolean valideCoord(int x,int y){
        return ( x < row && y < col
                 && x > 0   && y > 0);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object obj = e.getSource();
        for(int i =0; i<row; i++){
            for(int j=0; j<col; j++){
                if( obj == grids[i][j]){
                    System.out.println(i + " "+ j );
                    grids[i][j].setColor(Color.BLACK);
                }
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
