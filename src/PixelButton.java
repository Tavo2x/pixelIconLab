import javax.swing.JButton;

public class PixelButton extends JButton{
//- variables used throughout
    private int xCoord;
    private int yCoord;

    PixelButton(int x, int y){
        xCoord = x;
        yCoord = y;
    }

    public int getXcord(){
        return xCoord;
    }
    public void setXCords(int x){
        if(x >= 0 ){
            xCoord = x;
        }
        else{xCoord = 0;}
    }

    public int getYcord(){
        return yCoord;
    }

    public void setYCords(int y){
        if(y >=0 ){
            yCoord = y;
        }
        else{yCoord = 0;}
    }
}
