
package sig.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import sig.view.Frame;


public class InoviceHeader extends AbstractTableModel {

    private ArrayList<Header> HeaderLst;
    private String[] cols = {"Invoice Num", "Invoice Date", "Customer Name", "Invoice Total"};
    
    public InoviceHeader(ArrayList<Header> HeaderLst) {
        this.HeaderLst = HeaderLst;
    }

    @Override
    public int getRowCount() {
        return HeaderLst.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public Object getValueAt(int getRow, int getCol) {
        Header inv = HeaderLst.get(getRow);
        switch (getCol) {
        case 0: 
            return
                    inv.getNum();
        case 1: 
            return 
                    Frame.dateFormat.format(inv.getDate());
        case 2: 
            return 
                    inv.getCustomer();
        case 3:
            return
                    inv.getItemTotal();
        }
        return "";
    }

    @Override
    public String getColumnName(int col) {
        return cols[col];
    }
}
