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

        //Icon I3 = new Icon(7,8);

        //I3.setPixel(0, 0, 225, 0, 0);
        //I3.setPixel(0, 7, 0, 255, 0);
        //I3.setPixel(6, 0, 0, 0, 255);
        //I3.setPixel(6, 7, 255, 255, 255);

        //I3.createBipmapfile("icon.bmp");

        // Example of an icon
        Icon I4 = new Icon(5,5); // Icon of 5 by 5

        I4.setPixel(0, 0, 255, 0, 0); // Red top left
        I4.setPixel(0, 4, 0, 255, 0); // Green top right
        I4.setPixel(4, 0, 0, 0, 255); // Blue bottom left
        I4.setPixel(4, 4, 255, 255, 255); // White bottom right

        System.out.println(I4); // Ouput in the terminal showing hexadecimal format

        I4.createBipmapfile("icon.bmp"); // Creation of icon


    }
}