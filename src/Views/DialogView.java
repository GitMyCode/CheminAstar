package Views;

import javax.swing.*;

/**
 * Created by desk on 2/27/14.
 */
public class DialogView extends JOptionPane {

    private String message;

    public DialogView(){
        super();
    }

    public void setMessage(String mess){
        this.message = mess;
    }

    public void showWin(String winner){
        JOptionPane.showMessageDialog(null,"Le gagnant est :"+winner);
    }
}
