package bsu.rfe.java.group8.lab3.Chernysh.varC3;

import java.lang.StringBuilder;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CGornerTableCellRenderer implements TableCellRenderer {
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();
    private String sNeedle = null;
    private DecimalFormat formatter = (DecimalFormat)NumberFormat.getInstance();
    
    public CGornerTableCellRenderer() {
        formatter.setMaximumFractionDigits(5);
        formatter.setGroupingUsed(false);
        DecimalFormatSymbols dottedDouble = formatter.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(dottedDouble);
        panel.add(label);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
    }
    
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        String formattedDouble = formatter.format(value);
        StringBuilder reversDouble = new StringBuilder();
        reversDouble.append(formatter.format(value)).reverse();
        label.setText(formattedDouble);
        
        if (col == 1 && sNeedle != null && sNeedle.equals(formattedDouble)) {
            panel.setBackground(Color.RED);
        }
        else if (sNeedle != null && formattedDouble.equals(reversDouble.toString())) {
            panel.setBackground(Color.PINK);
        }
        else {
            panel.setBackground(Color.WHITE);
        }
        return panel;
    }
    
    public void setNeedle(String needle) {
        this.sNeedle = needle;
    }
}
