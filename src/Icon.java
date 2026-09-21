import java.io.FileOutputStream;
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

    public void setPixel(int row, int col, int r, int g, int b){
        setRed(row, col, r);
        setGreen(row, col, g);
        setBlue(row, col, b);
    }

    public void createBipmapfile (String filename){
        ArrayList <Byte> fileBytes = new ArrayList<>();

        int height = pixels.size();
        int width = pixels.get(0).size();
        int rowBytes = width * 3;
        int padding = rowBytes % 4;

        if (padding != 0){
            padding = 4 - padding;
        }

        int pixelDataSize = (rowBytes + padding) * height;
        int fileSize = pixelDataSize + 54;

        fileBytes.add((byte) 'B');
        fileBytes.add((byte) 'M');

        convertLittleEndian4(fileSize, fileBytes);
        convertLittleEndian2(0, fileBytes);
        convertLittleEndian2(0, fileBytes);
        convertLittleEndian4(54, fileBytes);
        convertLittleEndian4(40, fileBytes);
        convertLittleEndian4(width, fileBytes);
        convertLittleEndian4(height, fileBytes);
        convertLittleEndian2(1, fileBytes);
        convertLittleEndian2(24, fileBytes);
        convertLittleEndian4(0, fileBytes);
        convertLittleEndian4(pixelDataSize, fileBytes);
        convertLittleEndian4(0, fileBytes);
        convertLittleEndian4(0, fileBytes);
        convertLittleEndian4(0, fileBytes);
        convertLittleEndian4(0, fileBytes);


        for (int row = height-1; row >=0; row--){
            for (int col = 0; col < width; col++){

                fileBytes.add((byte)getBlue(row, col));
                fileBytes.add((byte)getGreen(row, col));
                fileBytes.add((byte)getRed(row, col));
            }
            for (int i = 0; i < padding ; i ++){
                fileBytes.add((byte)0);
            }
        }

        byte[] data = new byte[fileBytes.size()];

        for (int i = 0; i < fileBytes.size(); i ++){
            data[i] = fileBytes.get(i);
        }

        try (FileOutputStream out = new FileOutputStream(filename)){
            out.write(data);
        } catch (Exception e) {
            System.out.println("Error writing out file");
        }
        
    }

    public void convertLittleEndian2(int val, ArrayList <Byte> fileBytes ){
        fileBytes.add((byte)val);
        fileBytes.add((byte) (val >> 8));
    }

    public void convertLittleEndian4(int val, ArrayList <Byte> fileBytes){
        fileBytes.add((byte)val);
        fileBytes.add((byte) (val >> 8));
        fileBytes.add((byte) (val >> 16));
        fileBytes.add((byte) (val >> 24));
    }
}
