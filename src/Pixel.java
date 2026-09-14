public class Pixel {

    private int RGB;

    public String toString(){
        return "rgb(" + getRed() + ", " + getGreen() + ", " + getBlue() + ")";
    }

    public String toStringHex(){
        return (Integer.toHexString(RGB));
    }

    public Pixel(){
        setRed(0);
        setGreen(0);
        setBlue(0);
    }

    public Pixel(int val){
        RGB = val;
    }

    public void setRed(int r){
        if (r >= 0 && r <= 255){
            RGB = (RGB & 0xFFFF00) | r;
        }
    }
    public int getRed(){
        return (RGB & 0x0000FF);
    }

    public void setGreen(int g){
        if (g >= 0 && g <= 255){
            RGB = (RGB & 0xFF00FF) | g << 8; 
        }
    }
    public int getGreen(){
        return (RGB >> 8 & 0x0000FF);
    }

    public void setBlue(int b){
        if (b >= 0 && b <= 255){
            RGB = (RGB & 0x00FFFF) | b << 16;
        }
    }
    public int getBlue(){
        return (RGB >> 16 & 0x0000FF);
    }
}
