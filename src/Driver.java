public class Driver {
    public static void main(String[] args) {
        System.out.println("Pixel Icon");

        //Pixel p1;
        //p1 = new Pixel(5647382);
        //System.out.println(p1.toString());
        //System.out.println(p1.toStringHex());

        //Icon I1;
        //I1 = new Icon();
        //System.out.println(I1.toString());

        Icon I2 = new Icon(5,5);
        I2.setRed(0, 0, 225);
        I2.setGreen(0, 4, 225);
        I2.setBlue(4, 0, 225);
        System.out.println(I2.toString());

        
    }
}