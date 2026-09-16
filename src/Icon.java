import java.util.ArrayList;

public class Icon {

    private ArrayList <ArrayList<Pixel>> pixels;

    public Icon(){
        pixels = new ArrayList <ArrayList<Pixel>>();
        for (int row = 0; row <= 39; row++){
            ArrayList <Pixel> currentRow = new ArrayList <Pixel>(); 
            for (int col =0; col <= 39; col++){
                currentRow.add(new Pixel(0));
            }
            pixels.add(currentRow);
        }
    }

    public Icon(int rows, int cols){
        pixels = new ArrayList <ArrayList<Pixel>>();
        for (int row = 0; row < rows; row++){
            ArrayList <Pixel> currentRow = new ArrayList<Pixel>(); 
            for (int col =0; col < cols; col++){
                currentRow.add(new Pixel(0));
            }
            pixels.add(currentRow);
        }
    }

    public void setRed(int row, int col, int valR){
        if (row >= 0 && row < pixels.size() && col >=0 && col < pixels.get(row).size()){
            pixels.get(row).get(col).setRed(valR);
        }
    }

    public int getRed(int row, int col){
        if (row >=0 && row < pixels.size() && col >= 0 && col < pixels.get(row).size()){
            return pixels.get(row).get(col).getRed();
        }
        else{
            return -1;
        }
    }

    public void setGreen(int row, int col, int valG){
        if(row >=0 && row < pixels.size() && col >=0 && col < pixels.get(row).size()){
             pixels.get(row).get(col).setGreen(valG);
        }
    }

    public int getGreen(int row, int col){
        if (row >=0 && row < pixels.size() && col >= 0 && col < pixels.get(row).size()){
            return pixels.get(row).get(col).getGreen();
        }
        else{
            return -1;
        }
    }

    public void setBlue(int row, int col, int valB){
        if(row >=0 && row < pixels.size() && col >=0 && col < pixels.get(row).size()){
            pixels.get(row).get(col).setBlue(valB);
        }
    }
    public int getBlue (int row, int col){
        if (row >=0 && row < pixels.size() && col >= 0 && col < pixels.get(row).size()){
            return pixels.get(row).get(col).getBlue();
        }
        else{
            return -1;
        }
    }
    
    public String toString(){
        String output = "";
        
        for (int rows = 0; rows < pixels.size(); rows++){
            for (int col =0; col < pixels.get(rows).size(); col++){
                output += pixels.get(rows).get(col).toStringHex();
                output += "\t";
            }
            output += "\n";
        }
        return output;
    }
}
