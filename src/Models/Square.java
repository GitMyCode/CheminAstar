package Models;

/**
 * Created by desk on 2/9/14.
 */
public class Square  {

    /**
    status = 0 = rouge
    status = 1 = jaune
    status = 2 = libre
    * */
    int status = 2;
    public Square(){

    }

    public Square(int status){
        // faire un check si entre 0 et 2
        this.status = status;
    }

    public void incrementStatus(int color){
        this.status = color;
    }
    public void setStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return this.status;
    }

}
