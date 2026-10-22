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
//- Panel where the user is prompted to enter the dimensions of the icon
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
                @Override
                public void actionPerformed(ActionEvent e){
                    if (advancedOptionButton.isSelected()){
                        JPanel fillPanel = new JPanel();
                        fillPanel.setLayout(new GridLayout(2,2));
                        JTextField rows = new JTextField();
                        JTextField cols = new JTextField();
                        fillPanel.add(new JLabel("Rows: "));
                        fillPanel.add(rows);
                        fillPanel.add(new JLabel("Cols: "));
                        fillPanel.add(cols);

                        JPanel importPanel = new JPanel();
                        JButton chooseBMP = new JButton("Choose BMP File");
                        importPanel.add(chooseBMP);
                        chooseBMP.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e){
                                JFileChooser bmpChooser = new JFileChooser();
                                int chooserResult = bmpChooser.showOpenDialog(BitmapGUI.this);
                                if(chooserResult == JFileChooser.APPROVE_OPTION){
                                    File selectedFile = bmpChooser.getSelectedFile();
                            }
                          }
                        });
                        JTabbedPane tabbedPane = new JTabbedPane();
                        tabbedPane.addTab("Fill", fillPanel);
                        tabbedPane.addTab("Import", importPanel);
                        int result = JOptionPane.showConfirmDialog(BitmapGUI.this, tabbedPane, "Advanced Options", JOptionPane.OK_CANCEL_OPTION);
                        if(result == JOptionPane.OK_OPTION){
                            int selectedtab = tabbedPane.getSelectedIndex();
                            if(selectedtab == 0){
                                try{
                                    int fillRows = Integer.parseInt(rows.getText());
                                    int fillCols = Integer.parseInt(cols.getText());
                                    for(int r = row; r < row + fillRows && r < height; r++){
                                        for(int c = col; c < col + fillCols && c < width; c++){
                                            int red = redSlider.getValue();
                                            int green = greenSlider.getValue();
                                            int blue = blueSlider.getValue();
                                            buttonGrid.get(r).get(c).setBackground(new Color(red,green,blue));
                                            icon.setPixel(r,c,red,green,blue);
                                            addToFiveColors(new Color(red,green,blue));
                                        }
                                    }
                        }catch(NumberFormatException ex){
                                    JOptionPane.showMessageDialog(BitmapGUI.this, "Please enter valid integers for rows and columns.");
                                }
                            }
                        }
                    }
                    else{
                        int r = redSlider.getValue();
                        int g = greenSlider.getValue();
                        int b = blueSlider.getValue();
                        button.setBackground(new Color(r,g,b));
                        icon.setPixel(row, col, r, g, b);
                        addToFiveColors(new Color(r,g,b));
                    }
                }
                });
                currentRow.add(button);
                buttonLayout.add(button);
            }
            buttonGrid.add(currentRow);
        }
}
//- Creats a panel where the user is allowed to choose a color using sliders.(RGB) Small preview shown as the user changes sliders
    private void colorChooser(){
//- Two panels, one for sliders, the other is for the preview color
        colorChooserPanel = new JPanel();
        colorPreviewPanel = new JPanel();
//- RGB sliders with a range of 0 - 255, sliders defualt color set to black 
        redSlider = new JSlider(0,255,0);
        greenSlider = new JSlider(0,255,0);
        blueSlider = new JSlider(0,255,0);
//- Setting the size for the color preview panel, and set to black as default
        colorPreviewPanel.setPreferredSize(new Dimension(50,50));
        colorPreviewPanel.setBackground(new Color(0,0,0));
//- Labels for the sliders, and adding the sliders and preview panel to the color choose panel  
        colorChooserPanel.setLayout(new GridLayout(0,2));
        colorChooserPanel.add(new JLabel("Red:"));
        colorChooserPanel.add(redSlider);
        colorChooserPanel.add(new JLabel("Green:"));
        colorChooserPanel.add(greenSlider);
        colorChooserPanel.add(new JLabel("Blue:"));
        colorChooserPanel.add(blueSlider);
        colorChooserPanel.add(colorPreviewPanel);
//- Update the color preview panel as the user changes the sliders
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
// - Adds a color to the last five color list
//- If the color already is in the list it will not be added again
//- If there are more than 5 colors in the list, the last color will be removed to add the new color 
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
//- Building the last five colors panel, where the user is allowed to view and select one of the last five colors used.
//- Sliders and color preview will update to the selected color.
    private void buildLastFiveColorsPanel(){
        lastFiveColorsPanel = new JPanel[5]; // array to hold the 5 colors
        lastFiveColorsLayout = new JPanel(); // panel to hold the colors
        lastFiveColorsLayout.setLayout(new GridLayout(1,5)); // one row 5 columns
        for(int i=0; i < 5; i++){
            lastFiveColorsPanel[i] = new JPanel(); // creating new panel for each color
            lastFiveColorsPanel[i].setPreferredSize(new Dimension(30,30)); // Set the size of the color panel
            lastFiveColorsPanel[i].setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK)); // ouline for each color panel to be visable
            if(i < lastFiveColors.size()){ 
                lastFiveColorsPanel[i].setBackground(lastFiveColors.get(i)); // set the background color to the color in the list
            }
            else{
                lastFiveColorsPanel[i].setBackground(Color.WHITE); // By default, there is no 5 colors in the list so set them as white
            }
            lastFiveColorsLayout.add(lastFiveColorsPanel[i]); // add the color panel to the layout
            int index = i;
            lastFiveColorsPanel[i].addMouseListener(new java.awt.event.MouseAdapter() {
                // When the user clicks on one of the last five colors, it will update the sliders and color preview to that color
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
//- Update the last five colors panel to show the current colors in the list
    private void updateLastFiveColorsPanel(){
        for(int i=0; i < 5; i++){
            if(i < lastFiveColors.size()){
                lastFiveColorsPanel[i].setBackground(lastFiveColors.get(i)); 
            }
            else{
                lastFiveColorsPanel[i].setBackground(Color.WHITE);
            }
        }
    }
//- Button called "Create Bitmap" where the user is allowed to creat a bitmap file
//- User is prompted to choose a location and name for the file.
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
//- Checkbox named "Advanced" set to the top of the panel next to the bitmap button
//- This is where the user is allowed to view advanced options once checked.
    private void addvancedButton(){
        advancedOptionButton = new JCheckBox("Advanced");
        JPanel topPanel = new JPanel();
        topPanel.add(bitMapButton);
        topPanel.add(advancedOptionButton);
        add(topPanel, BorderLayout.NORTH);
    }
}
