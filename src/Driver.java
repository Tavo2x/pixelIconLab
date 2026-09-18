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

        //Icon I2 = new Icon(5,5);
        //I2.setRed(0, 0, 225);
        //I2.setGreen(0, 4, 225);
        //I2.setBlue(4, 0, 225);
        //System.out.println(I2.toString());

        Icon I3 = new Icon(7,8);

        I3.setPixel(0, 0, 225, 0, 0);
        I3.setPixel(0, 7, 0, 255, 0);
        I3.setPixel(6, 0, 0, 0, 255);
        I3.setPixel(6, 7, 255, 255, 255);

        System.out.println(I3.toString());

    }
}