package bsu.rfe.java.group8.lab3.Chernysh.varC3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class CMainFrame extends JFrame {
    private static final int iWidth = 1080;
    private static final int iHeght = 720;
    private Double[] dArrCoefficients;
    private JFileChooser fileChooser = null;
    private JMenuItem saveToTextMenuItem;
    private JMenuItem saveToGraphicsMenuItem;
    private JMenuItem searchValueMenuItem;
    private JMenuItem infValueItem;
    private JMenuItem searchPalindromeItem;
    private JTextField textFieldFrom;
    private JTextField textFieldTo;
    private JTextField textFieldStep;
    private Box hBoxResult;
    private CGornerTableCellRenderer renderer = new CGornerTableCellRenderer();
    private CGornerTableModel data;
    
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Cannot tabulte polinomial with 0 koef");
            System.exit(-1);
        }
        Double[] dArrCoefficients = new Double[args.length];
        int i = 0;
        try {
            for(String arg: args) {
                dArrCoefficients[i++] = Double.parseDouble(arg);
            }
        }
        catch (NumberFormatException ex) {
            System.out.println("Conversion exeption '"+ args[i] + "' in Double");
            System.exit(-2);
        }
        CMainFrame frame = new CMainFrame(dArrCoefficients);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public CMainFrame(Double[] coefficients) {
        super("Gorner tabulation at segment");
        this.dArrCoefficients = coefficients;
        setSize(iWidth, iHeght);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - iWidth) / 2, (kit.getScreenSize().height - iHeght) / 2);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenu tableMenu = new JMenu("Table");
        menuBar.add(tableMenu);
        JMenu aboutMenu = new JMenu("About");
        menuBar.add(aboutMenu);
        
        Action findInformationAction = new AbstractAction ("Information") {
            public void actionPerformed(ActionEvent event) {
                ImageIcon icon = new ImageIcon("C:\\Users\\ASUS-PC\\Documents\\Proga\\Java\\Problems\\3\\123.jpg");
                JOptionPane.showMessageDialog(CMainFrame.this, "Ilya Chernysh 8 group", "Information", JOptionPane.INFORMATION_MESSAGE, icon);
                getContentPane().repaint();
            }
        };
        infValueItem = aboutMenu.add(findInformationAction);
        infValueItem.setEnabled(true);
        
        Action saveToTextAction = new AbstractAction("Save text file") {
            public void actionPerformed(ActionEvent event) {
                if (fileChooser == null) {
                    fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File("."));
                }
                if (fileChooser.showSaveDialog(CMainFrame.this) == JFileChooser.APPROVE_OPTION);
                    saveToTextFile(fileChooser.getSelectedFile());
            }
        };
        saveToTextMenuItem = fileMenu.add(saveToTextAction);
        saveToTextMenuItem.setEnabled(false);
        
        Action saveToGraphicsAction = new AbstractAction("Save data for graphics") {
            public void actionPerformed(ActionEvent event) {
                if (fileChooser == null) {
                    fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File("."));
                }
                if (fileChooser.showSaveDialog(CMainFrame.this) == JFileChooser.APPROVE_OPTION);
                    saveToGraphicsFile(fileChooser.getSelectedFile());
            }
        };
        saveToGraphicsMenuItem = fileMenu.add(saveToGraphicsAction);
        saveToGraphicsMenuItem.setEnabled(false);
        
        Action searchValueAction = new AbstractAction ("Find polinomial value") {
            public void actionPerformed(ActionEvent event) {
                String value = JOptionPane.showInputDialog(CMainFrame.this, "Enter search value", "Search value", JOptionPane.QUESTION_MESSAGE);
                renderer.setNeedle(value);
                getContentPane().repaint();
            }
        };
        searchValueMenuItem = tableMenu.add(searchValueAction);
        searchValueMenuItem.setEnabled(false);
        
        Action searchPalindromAction = new AbstractAction ("Find palindrom") {
            public void actionPerformed(ActionEvent event) {
                renderer.setNeedle("pal");
                getContentPane().repaint();
            }
        };
        searchPalindromeItem = tableMenu.add(searchPalindromAction);
        searchPalindromeItem.setEnabled(false);
        
        JLabel labelForFrom = new JLabel ("X interval is:");
        textFieldFrom = new JTextField("0.0", 10);
        textFieldFrom.setMaximumSize(textFieldFrom.getPreferredSize());
        JLabel labelForTo = new JLabel(";");
        textFieldTo = new JTextField("1.0", 10);
        textFieldTo.setMaximumSize(textFieldTo.getPreferredSize());
        JLabel labelForStep = new JLabel("with stepsize:");
        textFieldStep = new JTextField("0.1", 10);
        textFieldStep.setMaximumSize(textFieldStep.getPreferredSize());
        Box hboxRange = Box.createHorizontalBox();
        hboxRange.setBorder(BorderFactory.createBevelBorder(1));
        hboxRange.add(Box.createHorizontalGlue());
        hboxRange.add(labelForFrom);
        hboxRange.add(Box.createHorizontalStrut(10));
        hboxRange.add(textFieldFrom);
        hboxRange.add(Box.createHorizontalStrut(20));
        hboxRange.add(labelForTo);
        hboxRange.add(Box.createHorizontalStrut(10));
        hboxRange.add(textFieldTo);
        hboxRange.add(Box.createHorizontalStrut(20));
        hboxRange.add(labelForStep);
        hboxRange.add(Box.createHorizontalStrut(10));
        hboxRange.add(textFieldStep);
        hboxRange.add(Box.createHorizontalGlue());
        hboxRange.setPreferredSize(new Dimension(
                new Double(hboxRange.getMaximumSize().getWidth()).intValue(),
                new Double(hboxRange.getMinimumSize().getHeight()).intValue() * 2));
        getContentPane().add(hboxRange, BorderLayout.NORTH);
        JButton buttonCalc = new JButton ("Count");
        buttonCalc.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent ev) {
                 try {
                     Double from = Double.parseDouble(textFieldFrom.getText());
                     Double to = Double.parseDouble(textFieldTo.getText());
                     Double step = Double.parseDouble(textFieldStep.getText());
                     data = new CGornerTableModel(from, to, step, CMainFrame.this.dArrCoefficients);
                     JTable table = new JTable(data);
                     table.setDefaultRenderer(Double.class, renderer);
                     table.setRowHeight(30);
                     hBoxResult.removeAll();
                     hBoxResult.add(new JScrollPane(table));
                     getContentPane().validate(); 
                     saveToTextMenuItem.setEnabled(true);
                     saveToGraphicsMenuItem.setEnabled(true);
                     searchValueMenuItem.setEnabled(true);
                     searchPalindromeItem.setEnabled(true);
                 }
                 catch (NumberFormatException ex) {
                     JOptionPane.showMessageDialog(CMainFrame.this, "Error in floating-point mathmatics", "Wrong number format", JOptionPane.WARNING_MESSAGE);
                 }
             }
        });
        JButton buttonReset = new JButton("Clean");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldFrom.setText("0.0");
                textFieldTo.setText("1.0");
                textFieldStep.setText("0.1");
                hBoxResult.removeAll();
                hBoxResult.add(new JPanel());
                saveToTextMenuItem.setEnabled(false);
                saveToGraphicsMenuItem.setEnabled(false);
                searchValueMenuItem.setEnabled(false);
                getContentPane().validate();
            }
        });
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.setBorder(BorderFactory.createBevelBorder(1));
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setPreferredSize(new Dimension(
                new Double(hboxButtons.getMaximumSize().getWidth()).intValue(), 
                new Double(hboxButtons.getMinimumSize().getHeight()).intValue() * 2));
        getContentPane().add(hboxButtons, BorderLayout.SOUTH);
        hBoxResult = Box.createHorizontalBox();
        hBoxResult.add(new JPanel());
        getContentPane().add(hBoxResult, BorderLayout.CENTER);
    }
        protected void saveToGraphicsFile(File selectedFile) {
            try {
                DataOutputStream out = new DataOutputStream(new FileOutputStream(selectedFile));
                for (int i = 0; i < data.getRowCount(); i++) {
                    out.writeDouble((Double)data.getValueAt(i,0));
                    out.writeDouble((Double)data.getValueAt(i,1));
                    out.writeDouble((Double)data.getValueAt(i,2));
                    out.writeDouble((Double)data.getValueAt(i,3));
                }
                out.close();
            }
            catch (Exception e) {
                
            }
        }
        protected void saveToTextFile(File selectedFile) {
            try {
                PrintStream out = new PrintStream(selectedFile);
                out.println("Result of Gorner tabulation");
                out.print("Polinomial: ");
                for(int i = 0; i < dArrCoefficients.length; i++) {
                    out.print(dArrCoefficients[i] + "*X^"+ (dArrCoefficients.length - i - 1));
                    if (i != dArrCoefficients.length - 1)
                        out.print(" + ");
                }
                out.println("");
                out.println("Interval from "+ data.getFrom() + " to " + data.getTo() + " with stepsize " + data.getStep());
                out.println("====================================================");
                for(int i = 0; i < data.getRowCount(); i++) {
                  out.println("Result in point " + data.getValueAt(i,0) + " is "+ data.getValueAt(i,1));
                }
                out.close();
            }
            catch (FileNotFoundException ex) {
                
            }
        }
}