package sig.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InvoiceHeaderView extends JDialog {
    private final JTextField NameOfTheCustomerField;
    private final JTextField DateOfTheField;
    private final JLabel _NameOfTheCustomerLabel;
    private final JLabel DateOfTheLabel;
    private final JButton SaveBtn;
    private final JButton CancelBtn;

    public InvoiceHeaderView(Frame frame) {
        _NameOfTheCustomerLabel = new JLabel("Customer Name");
        NameOfTheCustomerField = new JTextField(20);
        DateOfTheLabel = new JLabel("Date ");
        DateOfTheField = new JTextField(20);
        SaveBtn = new JButton("Save ");
        CancelBtn = new JButton("Cancel " );
        
        SaveBtn.setActionCommand("newInvoiceSave");
        CancelBtn.setActionCommand("newInvoiceCancel");
        
        SaveBtn.addActionListener(frame.getActionHandler());
        CancelBtn.addActionListener(frame.getActionHandler());
        setLayout(new GridLayout(3, 2));
        
        add(DateOfTheLabel);
        add(DateOfTheField);
        add(_NameOfTheCustomerLabel);
        add(NameOfTheCustomerField);
        add(SaveBtn);
        add(CancelBtn);
        
        pack();
        
    }

    public JTextField getCustomerNameTextField() {
        return NameOfTheCustomerField;
    }

    public JTextField getDateTextField() {
        return DateOfTheField;
    }
    
}
