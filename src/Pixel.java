public class Pixel {

    private int rgb;

    public String toString(){
        
        return "rgb(" + getRed() + ", " + getGreen() + ", " + getBlue() + ")";
    }

    public String toStringHex(){
        StringBuilder hex = new StringBuilder(String.format("%06X",rgb));
        hex.insert(0,'#');
        return hex.toString();
    }

    public Pixel(){
        setRed(0);
        setGreen(0);
        setBlue(0);
    }

    public Pixel(int val){
        rgb = val;
    }

    public void setRed(int r){
        if (r >= 0 && r <= 255){
            rgb = (rgb & 0xFFFF00) | r;
        }
    }
    public int getRed(){
        return (rgb & 0x0000FF);
    }

    public void setGreen(int g){
        if (g >= 0 && g <= 255){
            rgb = (rgb & 0xFF00FF) | g << 8; 
        }
    }
    public int getGreen(){
        return (rgb >> 8 & 0x0000FF);
    }

    public void setBlue(int b){
        if (b >= 0 && b <= 255){
            rgb = (rgb & 0x00FFFF) | b << 16;
        }
    }
    public int getBlue(){
        return (rgb >> 16 & 0x0000FF);
    }
}
