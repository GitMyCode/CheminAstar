package Views;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by desk on 2/23/14.
 */
public class OptionsView extends JPanel{

    private JButton reset;
    private JButton dessiner;
    private JButton annuler;
    private JButton ouvrir;
    private JButton configurer;
    public OptionsView(){
        super();

        reset = new JButton();
        dessiner= new JButton();
        annuler= new JButton();
        ouvrir= new JButton();
        configurer= new JButton();

        reset.setText("Reset");
        dessiner.setText("Dessiner");

        OptionsView.this.add(reset);
        OptionsView.this.add(dessiner);
    }

    public void setListener(ActionListener a){
        reset.addActionListener(a);
        dessiner.addActionListener(a);
    }
}
