package sig.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class InvoiceLine extends AbstractTableModel {

    private ArrayList<Line> Linelst;
    private String[] Cols = {"Item Name", "Unit Price", "Count", "Line Total"};

    public InvoiceLine(ArrayList<Line> linesArray) {
        this.Linelst = linesArray;
    }

    @Override
    public int getRowCount() {
        return Linelst == null ? 0 : Linelst.size();
    }

    @Override
    public int getColumnCount() {
        return Cols.length;
    }

    @Override
    public Object getValueAt(int getRow, int getCol) {
        if (Linelst == null) {
            return "";
        } else {
            Line line = Linelst.get(getRow);
            switch (getCol) {
                case 0:
                    return line.getItem();
                case 1:
                    return line.getPrice();
                case 2:
                    return line.getCount();
                case 3:
                    return line.getLineTotal();
                default:
                    return "";
            }
        }
    }

    @Override
    public String getColumnName(int col) {
        return Cols[col];
    }

}


