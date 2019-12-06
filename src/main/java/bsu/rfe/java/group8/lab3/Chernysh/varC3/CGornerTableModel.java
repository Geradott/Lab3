package bsu.rfe.java.group8.lab3.Chernysh.varC3;

import javax.swing.table.AbstractTableModel;
        
public class CGornerTableModel extends AbstractTableModel {
    private Double[] dArrCoefficients;
    private Double dFrom;
    private Double dTo;
    private Double dStep;
    private Double dGorner;
    private Float fGorner;
    
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
        return 4;
    }
    public int getRowCount() {
        return new Double(Math.ceil((dTo - dFrom) / dStep)).intValue() + 1;
    }
    public Object getValueAt(int row, int col) {
        Double x = dFrom + dStep * row;
        if (col == 0) {
            return x;
        } else if (col == 1) {
          dGorner = dArrCoefficients[0];
          for (int i = 1; i < dArrCoefficients.length; i++) {
              dGorner = dGorner * x + dArrCoefficients[i];
          }
          return dGorner;
        }
        else if (col == 2) {
            fGorner = dArrCoefficients[0].floatValue();
            for (int i = 1; i < dArrCoefficients.length; i++) {
                fGorner = x.floatValue() * fGorner + dArrCoefficients[i].floatValue();
            }
            return fGorner;
        }
        else {
            return dGorner - fGorner;
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
