package Views;

import javax.swing.*;
import java.awt.*;

/**
 * Created by desk on 2/27/14.
 */
public class ConfigurationView extends JFrame{

    private int player1Type;
    private int player2Type;

    private int FirstTurn;

    private int rows;
    private int cols;

    private int placementType;


    JPanel configurationPanel;


    public ConfigurationView(){
        super("Configurer");
        setSize(300,300);
        configurationPanel = new JPanel();

        JPanel te = new JPanel();

        JLabel player1 = new JLabel("player1");
        JLabel player2 = new JLabel("player2");
        JRadioButton p1Ai = new JRadioButton("Ai");
        JRadioButton p1Human = new JRadioButton("Human");
        JRadioButton p2Ai = new JRadioButton("Ai");
        JRadioButton p2Human = new JRadioButton("Human");

        ButtonGroup choicesType = new ButtonGroup();
        choicesType.add(p1Ai);
        choicesType.add(p1Human);



        JRadioButton p1Trun = new JRadioButton("Player 1");
        JRadioButton p2Trun = new JRadioButton("Player 2");
        ButtonGroup groupTurn = new ButtonGroup();
               groupTurn.add(p1Trun);groupTurn.add(p2Trun);

       /* configurationPanel.add(player1);
        configurationPanel.add(p1Ai);
        configurationPanel.add(p1Human);
        configurationPanel.add(player2);*/

        configurationPanel.setLayout(new GridBagLayout());

        addItem(configurationPanel,player1,0,0,1,1,GridBagConstraints.EAST);
        addItem(configurationPanel,player2,0,1,1,1,GridBagConstraints.EAST);
        addItem(configurationPanel,p1Ai,1,0,1,1,GridBagConstraints.WEST);
        addItem(configurationPanel,p1Human,2,0,1,1,GridBagConstraints.WEST);
        addItem(configurationPanel,p2Ai,1,1,1,1,GridBagConstraints.WEST);
        addItem(configurationPanel,p2Human,2,1,1,1,GridBagConstraints.WEST);


        addItem(configurationPanel,new JLabel("Start first?"),0,3,1,1,GridBagConstraints.EAST);
        addItem(configurationPanel,p1Trun,1,3,1,1,GridBagConstraints.WEST);
        addItem(configurationPanel,p2Trun,2,3,1,1,GridBagConstraints.WEST);



        JLabel lrow = new JLabel("row :");
        JLabel lcol = new JLabel("column :");
        JTextField row = new JTextField(10);
        JTextField col = new JTextField(10);
        addItem(configurationPanel,lrow,0,4,1,1,GridBagConstraints.WEST);
        addItem(configurationPanel,row,1,4,2,1,GridBagConstraints.WEST);
        addItem(configurationPanel,lcol,0,5,1,1,GridBagConstraints.WEST);
        addItem(configurationPanel,col,1,5,2,1,GridBagConstraints.WEST);



        JLabel lplacementType = new JLabel("Type placement:");
        JRadioButton coulisse = new JRadioButton("Coulisse");
        JRadioButton exact = new JRadioButton("Exact");
        ButtonGroup groupPlacement = new ButtonGroup();
        groupPlacement.add(coulisse);groupPlacement.add(coulisse);
        addItem(configurationPanel,lplacementType,0,6,1,1,GridBagConstraints.EAST);
        addItem(configurationPanel,coulisse,1,6,1,1,GridBagConstraints.WEST);
        addItem(configurationPanel,exact,2,6,1,1,GridBagConstraints.WEST);


        JButton ok = new JButton("Ok");
        JButton cancel = new JButton("Cancel");
        addItem(configurationPanel,ok,0,7,1,1,GridBagConstraints.WEST);
        addItem(configurationPanel,cancel,1,7,1,1,GridBagConstraints.EAST);

       /* Box sizeBox = Box.createVerticalBox();

        JPanel psize =new JPanel();
        psize.setSize(20,20);

        psize.add(lrow);psize.add(row);psize.add(lcol);psize.add(col);
        psize.setLayout(new BorderLayout(2,2));*/
        /*addItem(psize, lrow, 0, 0, 1, 1, GridBagConstraints.NONE);
        addItem(psize, row, 1, 0, 1, 1, GridBagConstraints.NONE);
        addItem(psize,lcol,0,1,1,1,GridBagConstraints.NONE);
        addItem(psize,col,1,1,1,1,GridBagConstraints.NONE);*/
        //psize.setBorder(BorderFactory.createTitledBorder("Size"));
       /* sizeBox.add(psize);
        sizeBox.setBorder(BorderFactory.createTitledBorder("Size"));*/
       //configurationPanel.add(psize);
       // addItem(configurationPanel, psize, 0, 4, 6, 30, GridBagConstraints.NORTH);











        add(configurationPanel);


        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void addItem(JPanel p, JComponent c, int x, int y, int width, int height, int align) {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = x;
        gc.gridy = y;
        gc.gridwidth = width;
        gc.gridheight = height;
        gc.weightx = 100.0;
        gc.weighty = 100.0;
        gc.insets = new Insets(5, 5, 5, 5);
        gc.anchor = align;
        gc.fill = GridBagConstraints.NONE;
        p.add(c, gc);
    }

}
