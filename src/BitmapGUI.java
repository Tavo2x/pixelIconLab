import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame; 
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener; 

public class BitmapGUI extends JFrame{

//- Varriables used throughout
    int height = 0;
    int width = 0;

    private JTextField heightTXT;
    private JTextField widthTXT;

    private JPanel panelDimensions;
    private JPanel buttonLayout;
    private JPanel colorChooserPanel;
    private JPanel colorPreviewPanel;

    private JSlider redSlider;
    private JSlider greenSlider;
    private JSlider blueSlider;


    private Icon icon;
    private ArrayList <ArrayList<JButton>> buttonGrid;



    public BitmapGUI(){

        heightTXT = new JTextField();
        widthTXT = new JTextField();

        panelDimensions = new JPanel();
        panelDimensions.setLayout(new GridLayout(2,2));
        panelDimensions.add(new JLabel("Height: "));
        panelDimensions.add(heightTXT);
        panelDimensions.add(new JLabel("Width: "));
        panelDimensions.add(widthTXT);

        while (true){
        int result = JOptionPane.showConfirmDialog(null, panelDimensions, "Enter Icon Dimension: ", JOptionPane.OK_CANCEL_OPTION);
//- close the screen if the user clicks cancel
        if(result != JOptionPane.OK_OPTION){
            System.exit(0); 
        } 
//- Retrieve the users input, parse, store in variables
        try {
            height = Integer.parseInt(heightTXT.getText()); 
            width = Integer.parseInt(widthTXT.getText());

//- Bounds checking if the dimensions entered are valid
            if(height > 0 && width> 0){
                break;
            }
        }
        catch (NumberFormatException e) {System.out.println("Enter a number greater than zero for both dimensions.");};

        }

        icon = new Icon(height, width);
        buildGrid();
        colorChooser();
        add(buttonLayout, BorderLayout.WEST);
        add(colorChooserPanel, BorderLayout.EAST);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();		 
        setVisible(true);
    }

    private void buildGrid(){
        buttonGrid = new ArrayList<ArrayList<JButton>>();
        buttonLayout = new JPanel();
        buttonLayout.setLayout(new GridLayout(height,width));

        for(int i = 0; i < height; i++){
            ArrayList <JButton> currentRow = new ArrayList<JButton>();
            for(int j = 0 ; j < width; j++){
                final int row = i;
                final int col = j;
                PixelButton button = new PixelButton(row,col);
                button.setOpaque(true);
                button.setContentAreaFilled(true);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        int r = redSlider.getValue();
                        int g = greenSlider.getValue();
                        int b = blueSlider.getValue();
                        button.setBackground(new Color(r,g,b));
                        icon.setPixel(row, col, r, g, b);
                    }
                });
                //JButton button = new JButton();
                //currentRow.add(button);
                //buttonLayout.add(button);
                currentRow.add(button);
                buttonLayout.add(button);
            }
            buttonGrid.add(currentRow);
        }
}

    private void colorChooser(){
        colorChooserPanel = new JPanel();
        colorPreviewPanel = new JPanel();

        redSlider = new JSlider(0,255,0);
        greenSlider = new JSlider(0,255,0);
        blueSlider = new JSlider(0,255,0);

        colorPreviewPanel.setPreferredSize(new Dimension(50,50));

        colorChooserPanel.setLayout(new GridLayout(0,2));
        colorChooserPanel.add(new JLabel("Red:"));
        colorChooserPanel.add(redSlider);
        colorChooserPanel.add(new JLabel("Green:"));
        colorChooserPanel.add(greenSlider);
        colorChooserPanel.add(new JLabel("Blue:"));
        colorChooserPanel.add(blueSlider);
        colorChooserPanel.add(colorPreviewPanel);

        ChangeListener colorListener = new ChangeListener() {
            public void stateChanged (ChangeEvent e){
                int r = redSlider.getValue();
                int g = greenSlider.getValue();
                int b = blueSlider.getValue();
                colorPreviewPanel.setBackground(new Color(r,g,b));
            }
        };

        redSlider.addChangeListener(colorListener);
        greenSlider.addChangeListener(colorListener);
        blueSlider.addChangeListener(colorListener);
    }

}
