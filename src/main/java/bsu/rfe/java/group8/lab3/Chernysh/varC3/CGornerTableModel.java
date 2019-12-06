package bsu.rfe.java.group8.lab3.Chernysh.varC3;

import javax.swing.table.AbstractTableModel;
        
public class CGornerTableModel extends AbstractTableModel {
    private Double[] dArrCoefficients;
    private Double dFrom;
    private Double dTo;
    private Double dStep;
    
    public CGornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
        this.dFrom = from;
        this.dTo = to;
        this.dStep = step;
        this.dArrCoefficients = coefficients;
    }
    
    public Double getFrom() {
        return dFrom;
    }
    
    public Double getTo() {
        return dTo;
    }
    
    public Double getStep() {
        return dStep;
    }
    public int getColumnCount() {
        return 2;
    }
    public int getRowCount() {
        return new Double(Math.ceil((dTo - dFrom) / dStep)).intValue() + 1;
    }
    public Object getValueAt(int row, int col) {
        double x = dFrom + dStep * row;
        if (col == 0) {
            return x;
        } else {
          Double result = 0.0;
          return result;
        }
    }
    public String getColumnName(int col) {
        switch(col) {
            case 0:
                return "Value X";
            default:
                return "Value polinomial";
        }
    }
    public Class<?> getColumnClass(int col) {
        return Double.class;
    }
}
