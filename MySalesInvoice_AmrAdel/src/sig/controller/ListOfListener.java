
package sig.controller;
    
import sig.model.Header;
import sig.model.Line;
import sig.model.InvoiceLine;
import sig.view.Frame;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListOfListener implements ListSelectionListener {

    private  Frame TheFrame;

    public ListOfListener(Frame frame) {
        this.TheFrame = frame;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedInvIndex = TheFrame.getheaderTable().getSelectedRow();
        System.out.println("Invoice selected: " + selectedInvIndex);
        if (selectedInvIndex != -1) {
            Header selectedInv = TheFrame.getInvoicesArray().get(selectedInvIndex);
            ArrayList<Line> lines = selectedInv.getLines();
            InvoiceLine lineTable = new InvoiceLine(lines);
            TheFrame.setLinesArray(lines);
            TheFrame.getlineTable().setModel(lineTable);
            TheFrame.getCustNameLbl().setText(selectedInv.getCustomer());
            TheFrame.getInvNumLbl().setText("" + selectedInv.getNum());
            TheFrame.getInvTotalIbl().setText("" + selectedInv.getItemTotal());
            TheFrame.getDateLbl().setText(Frame.dateFormat.format(selectedInv.getDate()));
        }
    }

}

    

