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

    public static final int COUT = 10;
    //JFrame frame;
    public OptionsView optionsView;
    public Grid view_grid;
    public int  row=60;
    public int col =60;


    final Timer timer_refresh = new Timer(100,null);
    public Node depart;
    public Node arrive;

    ArrayList<Node> openList = new ArrayList<Node>();
    ArrayList<Node> closedList = new ArrayList<Node>();
    ArrayList<Node> path = new ArrayList<Node>();

    public FrameGeneral(){


        super("Puissance 4");
        Dimension frame_dim = new Dimension(650,650);
        setSize(frame_dim);
        setPreferredSize(frame_dim);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        this.optionsView = new OptionsView();
        this.optionsView.setSize(20, 10);

        timer_refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view_grid.repaint();
            }
        });


        initGrid();
        optionsView.setListener(this);
        add(optionsView, BorderLayout.SOUTH);
        // frame.add(view_grid,BorderLayout.CENTER);
        pack();



        setVisible(true);
        setIgnoreRepaint(true);
    }

    private void initGrid(){

        timer_refresh.stop();

        this.view_grid = null;
        this.view_grid = new Grid(this.row,this.col);


        Random ramdom = new Random();
        int ran_row = ramdom.nextInt(row-2)+1;
        int ran_col = ramdom.nextInt(col-2)+1;
        this.depart = null;
        this.depart = new Node(ran_row,ran_col,2);
        view_grid.setNode(ran_row,ran_col,2);



        ran_row = ramdom.nextInt(row-2) +1;
        ran_col = ramdom.nextInt(col-2) +1;
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

        add(this.view_grid, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {


        System.out.println(actionEvent.getActionCommand());
        if(actionEvent.getActionCommand() == "Reset"){
          // view_grid.removeAll();
            timer_refresh.stop();
            openList.clear();
            closedList.clear();
            path.clear();

            initGrid();
            view_grid.updateUI();

        }else{
            dessiner();


        }
        System.out.println("fini");

    }




/*       L'ALGORITHME COMMENCE ICI JUSQU'EN BAS
*
*
*
* */
    public void dessiner(){

        timer_refresh.start();

        aStarAlgo();


   }
   void aStarAlgo() {
       openList.add(depart);
      /* Thread thread_astar = new Thread(new Runnable() {
           @Override
           public void run() {



           }
       });*/
       final Timer timer_aStar = new Timer(10,null);
       timer_aStar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               if (openList.size() > 0) { // Ici c'est normalement un while, mais puisque je suis dnas un
                   // Runnable c'est un if appeler plusieurs fois
                   Node current = findBestOpen();
                   closedList.add(current);
                   openList.remove(current);

                   current.setColor(Color.cyan);


                   if (current.getState() == 3) {
                       System.out.println("arrive");
                       Node unPas = view_grid.grids[arrive.getXpos()][arrive.getYpos()];
                       path = new ArrayList<Node>();
                       while (unPas.getParent() != null) {
                           path.add(unPas);
                           unPas = unPas.getParent();
                       }

                       for (Node pathNode : path) {
                           pathNode.setColor(Color.getHSBColor(120, 100, 50));
                       }
                       depart.setColor(Color.GREEN);
                       arrive.setColor(Color.RED);
                       view_grid.updateUI();
                       timer_aStar.stop();

                   }
                   calculerVoisinage(current);

               }
           }
       });
       timer_aStar.start();
     /*  final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

       executor.scheduleAtFixedRate(new Runnable() {
           @Override
           public void run() {
               if(openList.size()>0){ // Ici c'est normalement un while, mais puisque je suis dnas un
                                      // Runnable c'est un if appeler plusieurs fois
                   Node current = findBestOpen();
                   closedList.add(current);
                   openList.remove(current);

                   current.setColor(Color.pink);


                   if(current.getState() == 3){
                       System.out.println("arrive");


                       executor.shutdown();
                   }
                   calculerVoisinage(current);

               }

           }

       },0,10, TimeUnit.MILLISECONDS);
*/
   }


    public Node findBestOpen(){
        Node best = openList.get(0);
        double best_Cost = best.getF_totalCost();
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
            if(voisin.getState() != BLOCK && !closedList.contains(voisin)){
                voisin.setColor(Color.gray);


                int newG = current.getG_movementCost() + COUT;
                if(!openList.contains(voisin) || newG < voisin.getG_movementCost()){
                    voisin.setParent(current);
                    voisin.setG_movementCost(current.getG_movementCost()+ COUT );
                    voisin.setH_heuristique(calculHeuristique(voisin.getXpos(), voisin.getYpos()));


                   // current.setG_movementCost(newG);
                    //current.setParent(voisin);
                    if(!openList.contains(voisin)){
                        openList.add(voisin);
                    }
                }
            }/*else{
                if(newG < voisin.getG_movementCost()){
                 //   voisin.setG_movementCost(newG);
                  //  voisin.setParent(current);
                    current.setParent(voisin);
                    current.setG_movementCost(newG);
                }
            }*/
        }

       }


    public ArrayList<Node> findVoisin(Node current){
        ArrayList<Node> voisins = new ArrayList<Node>();
        int x_cur = current.getXpos();
        int y_cur = current.getYpos();
        if(view_grid.valideCoord(x_cur+1,y_cur))
            voisins.add(view_grid.grids[x_cur+1][y_cur]) ;  // North
        if(view_grid.valideCoord(x_cur-1,y_cur))
             voisins.add(view_grid.grids[x_cur-1][y_cur]) ; // South
        if(view_grid.valideCoord(x_cur,y_cur+1))
             voisins.add(view_grid.grids[x_cur][y_cur+1]) ; // Est
        if(view_grid.valideCoord(x_cur,y_cur-1))
             voisins.add(view_grid.grids[x_cur][y_cur-1]) ; // Ouest

        return voisins;
    }
    public double calculHeuristique(int x1, int y1){

        //return  (Math.abs((x1 -arrive.getXpos()) + Math.abs(y1 - arrive.getYpos())))* COUT;
       return Math.sqrt(Math.pow(x1 - arrive.getXpos(),2) + (Math.pow(y1 - arrive.getYpos(),2))) * COUT;


        /*heuristique trouver sur le net en c#

         case HeuristicType.Manhattan:
                    H = Math.Abs(StartX - EndX) + Math.Abs(StartY - EndY);
                    break;

                case HeuristicType.Diagonal:
                    H = Math.Max(Math.Abs(StartX - EndX), Math.Abs(StartY - EndY));
                    break;

                case HeuristicType.Euclidean:
                    H = Math.Sqrt(Math.Pow(StartX - EndX, 2) + Math.Pow(StartY - EndY, 2));
                    break;

                case HeuristicType.EuclideanSquared:
                    H = Math.Pow(StartX - EndX, 2) + Math.Pow(StartY - EndY, 2);
                    break;

                case HeuristicType.TieBreakerManhattan:
                    H = Math.Abs(StartX - EndX) + Math.Abs(StartY - EndY) * m_tieBreaker;
                    break;

                case HeuristicType.TieBreakerDiagonal:
                    H = Math.Max(Math.Abs(StartX - EndX), Math.Abs(StartY - EndY)) * m_tieBreaker;
                    break;

                case HeuristicType.TieBreakerEuclidean:
                    H = Math.Sqrt(Math.Pow(StartX - EndX, 2) + Math.Pow(StartY - EndY, 2)) * m_tieBreaker;
                    break;

                case HeuristicType.TieBreakerEuclideanSquared:
                    H = Math.Pow(StartX - EndX, 2) + Math.Pow(StartY - EndY, 2) * m_tieBreaker;
                    break;


         */


    }


}
