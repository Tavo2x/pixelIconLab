import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

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
        // Icon I4 = new Icon(5,5); // Icon of 5 by 5

        // I4.setPixel(0, 0, 255, 0, 0); // Red top left
        // I4.setPixel(0, 4, 0, 255, 0); // Green top right
        // I4.setPixel(4, 0, 0, 0, 255); // Blue bottom left
        // I4.setPixel(4, 4, 255, 255, 255); // White bottom right

        // System.out.println(I4); // Ouput in the terminal showing hexadecimal format

        // I4.createBipmapfile("icon.bmp"); // Creation of icon

        try { UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); } catch (Exception e) { e.printStackTrace(); }

        //new BitmapGUI();

        JTextField heightTXT = new JTextField();
        JTextField widthTXT = new JTextField();
        JTextField windowsTXT = new JTextField();

        JPanel panelWindow = new JPanel();
        panelWindow.setLayout(new GridLayout(1,2));;
        panelWindow.add(new JLabel("Enter the number of windows: "));
        panelWindow.add(windowsTXT);

        JPanel panelDimensions = new JPanel();
        panelDimensions.setLayout(new GridLayout(2,2));
        panelDimensions.add(new JLabel("Height: "));
        panelDimensions.add(heightTXT);
        panelDimensions.add(new JLabel("Width: "));
        panelDimensions.add(widthTXT);

        int height = 0;
        int width = 0;
        int windows = 0;


        while (true){
            int numWind = JOptionPane.showConfirmDialog(null, panelWindow,"Number of Windoes:", JOptionPane.OK_CANCEL_OPTION);
            if(numWind != JOptionPane.OK_OPTION){
                System.exit(0);
            }
            try{
                windows = Integer.parseInt(windowsTXT.getText());
                if(windows > 0){
                    break;
                }
            }
            catch(NumberFormatException e){
                System.out.println("Enter a number greater than zero for the number of windows you wish to see.");
            }
        }
        while (true){
            int result = JOptionPane.showConfirmDialog(null, panelDimensions, "Enter Icon Dimension: ", JOptionPane.OK_CANCEL_OPTION);
            if(result != JOptionPane.OK_OPTION){
                System.exit(0);
            }
            try {
                height = Integer.parseInt(heightTXT.getText());
                width = Integer.parseInt(widthTXT.getText());
                if(height > 0 && width > 0){
                    break;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Enter a number greater than zero for both dimensions.");
            }
        }
        IconEdit iconEdit = new IconEdit();
        for(int i = 0; i < windows; i++){
            BitmapGUI window = new BitmapGUI(height, width, iconEdit);
            window.setLocation(50 + i * 40, 50 + i * 40);            
        }
    }
}