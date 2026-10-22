import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame; 
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener; 
import javax.swing.JFileChooser;
import java.io.File;

public class BitmapGUI extends JFrame{

//- Varriables used throughout
    int height = 0;
    int width = 0;

    private JButton bitMapButton;
    private JCheckBox advancedOptionButton;

    private JTextField heightTXT;
    private JTextField widthTXT;

    private JPanel panelDimensions;
    private JPanel buttonLayout;
    private JPanel colorChooserPanel;
    private JPanel colorPreviewPanel;
    private JPanel[] lastFiveColorsPanel;
    private JPanel lastFiveColorsLayout;

    private JSlider redSlider;
    private JSlider greenSlider;
    private JSlider blueSlider;

    private Icon icon;
    private ArrayList <ArrayList<JButton>> buttonGrid;
    private ArrayList <Color> lastFiveColors;

    public BitmapGUI(){
//- Text file where the user is prompted to enter the height and width of the icon they want to create
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
//- Creat a new icon with the dimensions entered by the user
//- Build the grid, color chooser, last five colors panel, bitmap button, and advanced button
//- Add the panels to the frane, at desired locations
//- Close the app when the 'X' button is clicked
//- Size the window to fit all the components
//- Show the window on the screen
        icon = new Icon(height, width);
        buildGrid();
        colorChooser();
        buildLastFiveColorsPanel();
        buildBitmap();
        addvancedButton();
        add(buttonLayout, BorderLayout.WEST);
        add(colorChooserPanel, BorderLayout.EAST);
        add(lastFiveColorsLayout, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();		 
        setVisible(true);
    }

    private void buildGrid(){
        buttonGrid = new ArrayList<ArrayList<JButton>>();
        lastFiveColors = new ArrayList<Color>();
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
                    }
                });
                //JButton button1 = new JButton();
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
        colorPreviewPanel.setBackground(new Color(0,0,0));

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

    private void addToFiveColors(Color c){
        if(lastFiveColors.contains(c)){
            return; // color already exists in the list,
        }
        lastFiveColors.add(0,c);
        if(lastFiveColors.size() > 5){
            lastFiveColors.remove(lastFiveColors.size()-1); // remove the last color
        }
        updateLastFiveColorsPanel();
    }

    private void buildLastFiveColorsPanel(){
        lastFiveColorsPanel = new JPanel[5]; // array to hold the 5 colors
        lastFiveColorsLayout = new JPanel(); // panel to hold the colors
        lastFiveColorsLayout.setLayout(new GridLayout(1,5)); // one row 5 columns
        for(int i=0; i < 5; i++){
            lastFiveColorsPanel[i] = new JPanel(); // creating new panel for each color
            lastFiveColorsPanel[i].setPreferredSize(new Dimension(30,30)); 
            lastFiveColorsPanel[i].setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK)); // ouline for each color panel to be visable
            if(i < lastFiveColors.size()){ // if the 
                lastFiveColorsPanel[i].setBackground(lastFiveColors.get(i)); // set the background color to the color in the list
            }
            else{
                lastFiveColorsPanel[i].setBackground(Color.WHITE); // By default, there is no 5 colors in the list so set them as white
            }
            lastFiveColorsLayout.add(lastFiveColorsPanel[i]); // add the color panel to the layout
            int index = i; 
            lastFiveColorsPanel[i].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if(index >= lastFiveColors.size()) {
                        return; // No color to select
                    }
                    Color selectedColor = lastFiveColors.get(index);
                    redSlider.setValue(selectedColor.getRed());
                    greenSlider.setValue(selectedColor.getGreen());
                    blueSlider.setValue(selectedColor.getBlue());
                }
            });
        }
        updateLastFiveColorsPanel();
    }

    private void updateLastFiveColorsPanel(){ // Update the last five colors panel to show the current colors in the list
        for(int i=0; i < 5; i++){
            if(i < lastFiveColors.size()){
                lastFiveColorsPanel[i].setBackground(lastFiveColors.get(i)); 
            }
            else{
                lastFiveColorsPanel[i].setBackground(Color.WHITE);
            }
        }
    }

    private void buildBitmap(){
        bitMapButton = new JButton("Create Bitmap");

        bitMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(BitmapGUI.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String filename = selectedFile.getAbsolutePath();
                    icon.createBipmapfile(filename);
                    JOptionPane.showMessageDialog(BitmapGUI.this, "Bitmap file created: " + filename);
                }
            }
        });
    }

    private void addvancedButton(){
        advancedOptionButton = new JCheckBox("Advanced");
        JPanel topPanel = new JPanel();
        topPanel.add(bitMapButton);
        topPanel.add(advancedOptionButton);
        add(topPanel, BorderLayout.NORTH);
    }
}
