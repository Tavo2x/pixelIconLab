import java.io.FileOutputStream;
import java.util.ArrayList;

public class Icon {
    // 2D collection of pixels used in an arraylist
    private ArrayList <ArrayList<Pixel>> pixels;

    // Default constructor that is a 40x40 icon
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

    // Creats a unique icon where the user can initalize its size 
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

    // Allows you to select a specific pixel and set its color to red
    // Check the row then the column to verify its location is a valid
    // If valid, it gets the pixel at that row and column and sets it red value to valR
    public void setRed(int row, int col, int valR){
        if (row >= 0 && row < pixels.size() && col >=0 && col < pixels.get(row).size()){
            pixels.get(row).get(col).setRed(valR);
        }
    }

    // Returns the red value from a specific location
    // Checks to see if the row and column passes its indeed a valid location
    // If so it gets the pixel at said row and col and returns the red value
    // If not returns -1 which is out of bounds of 0 - 255 letting you know something went wrong
    public int getRed(int row, int col){
        if (row >=0 && row < pixels.size() && col >= 0 && col < pixels.get(row).size()){
            return pixels.get(row).get(col).getRed();
        }
        else{
            return -1;
        }
    }

    // Allows you to access a specific pixel and set its color to green
    // Checks the row and column passes is a valid location
    // If valid it gets the pixel at said row and column setting its green value to valG
    public void setGreen(int row, int col, int valG){
        if(row >=0 && row < pixels.size() && col >=0 && col < pixels.get(row).size()){
             pixels.get(row).get(col).setGreen(valG);
        }
    }

    // Returns the value from a specific location
    // Does some checking first to validate the row and column entered is a location accessable
    // If so it will then return the pixel at that row and col and return the green value
    // If not returns -1 showing error due to number not being between 0 - 255
    public int getGreen(int row, int col){
        if (row >=0 && row < pixels.size() && col >= 0 && col < pixels.get(row).size()){
            return pixels.get(row).get(col).getGreen();
        }
        else{
            return -1;
        }
    }
    
    // Able to get a specific pixel to set its color blue
    // Does some checking first to verify if the row and column entered is within the pixels size
    // If so then get the pixel at said row and column and set its value to valB
    public void setBlue(int row, int col, int valB){
        if(row >=0 && row < pixels.size() && col >=0 && col < pixels.get(row).size()){
            pixels.get(row).get(col).setBlue(valB);
        }
    }

    // Returns the blue value from a specific location
    // Does checking to see if it is in bounds in the first place
    // If so returns blue value from said row and column
    // -1 retunrs if either row or column was out of bounds
    public int getBlue (int row, int col){
        if (row >=0 && row < pixels.size() && col >= 0 && col < pixels.get(row).size()){
            return pixels.get(row).get(col).getBlue();
        }
        else{
            return -1;
        }
    }
    
    // Calling this method allows you to see in the termianl how the icon should look like
    //  Showing the amount of rows, columnns, and what each location hexadecimal/color value
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

    // Method called by user to set the color in a specific location of the icon
    public void setPixel(int row, int col, int r, int g, int b){
        setRed(row, col, r);
        setGreen(row, col, g);
        setBlue(row, col, b);
    }

    // Method that creats bitmap. 
    // Below is the process of converting certain values into either little endian 2 or 4
    // https://en.wikipedia.org/wiki/BMP_file_format#Example
    public void createBipmapfile (String filename){
        // List that holds the bytes of the file
        ArrayList <Byte> fileBytes = new ArrayList<>();

        // Height was got by identifying the pixel size
        int height = pixels.size();

        // Width was got by getting the length of a column
        int width = pixels.get(0).size();

    
        int rowBytes = width * 3; // Value of one rows side 3 standing for (r,g,b)

        // How many padding is needed to make rowBytes multple of 4
        int padding = rowBytes % 4;

        if (padding != 0){
            padding = 4 - padding; // if 0 stays 0 padding not needed
        }

        int pixelDataSize = (rowBytes + padding) * height; // size of the pixel data
        int fileSize = pixelDataSize + 54; // Size of the file, to then convert into littl endian 4, then adding to the list

        // At the start the bmp file must start with b and m 
        fileBytes.add((byte) 'B');
        fileBytes.add((byte) 'M');

        convertLittleEndian4(fileSize, fileBytes); // Size of the entire file
        convertLittleEndian2(0, fileBytes); // bfReserved1 must be 0
        convertLittleEndian2(0, fileBytes); // bfReserved2 must be 0
        convertLittleEndian4(54, fileBytes); // Where the pixel data starts
        convertLittleEndian4(40, fileBytes); // Number of bytes in the DIB header
        convertLittleEndian4(width, fileBytes); // Width of bitmap in the pixels
        convertLittleEndian4(height, fileBytes); // Height of bitmap in pixels
        convertLittleEndian2(1, fileBytes); // Number of color planes
        convertLittleEndian2(24, fileBytes); // Number of bits per pixel
        convertLittleEndian4(0, fileBytes); // No pixel array compresion used
        convertLittleEndian4(pixelDataSize, fileBytes); // Size of the raw bitmap data
        convertLittleEndian4(0, fileBytes); // horizontal resolution
        convertLittleEndian4(0, fileBytes); // vertical resolution
        convertLittleEndian4(0, fileBytes); // number of colors in the palette
        convertLittleEndian4(0, fileBytes); // Important colors which were 0

        // Building the bmp file from bottom to top. Left to right. Each being stored as blue, green, red
        for (int row = height-1; row >=0; row--){
            for (int col = 0; col < width; col++){

                fileBytes.add((byte)getBlue(row, col));
                fileBytes.add((byte)getGreen(row, col));
                fileBytes.add((byte)getRed(row, col));
            }
            for (int i = 0; i < padding ; i ++){
                fileBytes.add((byte)0); // Padding for row 
            }
        }

        // Out put stream needs a byte array, list is then copied into one
        byte[] data = new byte[fileBytes.size()];

        for (int i = 0; i < fileBytes.size(); i ++){
            data[i] = fileBytes.get(i);
        }

        // writing out the bytes
        try (FileOutputStream out = new FileOutputStream(filename)){
            out.write(data);
        } catch (Exception e) {
            System.out.println("Error writing out file");
        }
        
    }

    // Adding 2 byte value to list in little endian order
    // Val being the the number to store
    // Filebytes being the bytes that'll creat the bmp file
    // First line in method keeps only the lowest 8 bits
    // Second line shifts 8 bits to the right making it now the least significant then stores those bits
    public void convertLittleEndian2(int val, ArrayList <Byte> fileBytes ){
        fileBytes.add((byte)val);
        fileBytes.add((byte) (val >> 8));
    }

    // Adding 4 byte values to the list. Val meaning number to store. Filebytes being the list that'll then creat bmp file
    // First line - no shifting needed because it's the lowest already. Added to list.
    // Second line - Shifting 8 to the right making it the newer lowest. Added to list.
    // Third line - Shifting 16 to the right making it the newer lowest. Added to the list.
    // Fourth line - Shifting 24 to the right making it the newer lowest. Added to the list.
    public void convertLittleEndian4(int val, ArrayList <Byte> fileBytes){
        fileBytes.add((byte)val);
        fileBytes.add((byte) (val >> 8));
        fileBytes.add((byte) (val >> 16));
        fileBytes.add((byte) (val >> 24));
    }
}
