public class Pixel {

    private int rgb;

    // This was from the lab where it would return a string like rgb(x,x,x)
    public String toString(){
        
        return "rgb(" + getRed() + ", " + getGreen() + ", " + getBlue() + ")";
    }
    // Returns a string in hexdecimal from like #0000FF
    public String toStringHex(){
        StringBuilder hex = new StringBuilder(String.format("%06X",rgb));
        hex.insert(0,'#');
        return hex.toString();
    }
    // Constructor of a pixel and initializes the pixels to black by deafult 
    public Pixel(){
        setRed(0);
        setGreen(0);
        setBlue(0);
    }
    // Retunr rgb value 
    public Pixel(int val){
        rgb = val;
    }
    // Setting the red color and checking bounds between 0 - 255 
    // Keeps the blue and green value and replace the red value
    // Shifts the red value 16 bits left. 
    public void setRed(int r){
        if (r >= 0 && r <= 255){
            rgb = (rgb & 0x00FFFF) | r << 16;
        }
    }
    // Returns only red value
    // Shifts the red values 16 bits to the right
    // Keeps only last 8 bits, containing the red value
    public int getRed(){
        return (rgb >> 16 & 0x0000FF);
    }
    // Setting the green color and checking bounds between 0 - 255
    // Keeps the red and blue value and replace the green value
    // Shifts the green value 8 bits left
    public void setGreen(int g){
        if (g >= 0 && g <= 255){
            rgb = (rgb & 0xFF00FF) | g << 8; 
        }
    }
    // Returns only green value
    // Shifts the green values 8 bits to the right
    // Keeps only last 8 bits, containing the green value
    public int getGreen(){
        return (rgb >> 8 & 0x0000FF);
    }
    // Setting the blue color and checking bounds between 0 - 255
    // Keeps the red and green value and replaces the blue value
    // No shifting is needed since the blue value are alrealready the last 8 bits
    public void setBlue(int b){
        if (b >= 0 && b <= 255){
            rgb = (rgb & 0xFFFF00) | b;
        }
    }
    // Returns only red value
    // No shifting needed blue value already the last 8 bits
    public int getBlue(){
        return (rgb & 0x0000FF);
    }
}
