
package sig.view;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

    public class InvoiceLineView extends JDialog{
    private final JTextField NameOfItemField;
    private final JTextField CountOfItemField;
    private final JTextField PriceOfItemField;
    private final JLabel NameOfItemLabel;
    private final JLabel CountOfItemLabel;
    private final JLabel PriceOfItemLabel;
    private JButton CreateItembtn;
    private JButton DeleteItembtn;
    
    public InvoiceLineView(Frame frame) {
        NameOfItemField = new JTextField(20);
        NameOfItemLabel = new JLabel("Item Name");
        
        CountOfItemField = new JTextField(20);
        CountOfItemLabel = new JLabel("Item Count");
        
        PriceOfItemField = new JTextField(20);
        PriceOfItemLabel = new JLabel("Item Price");
        
        CreateItembtn = new JButton("Create Item");
         DeleteItembtn = new JButton("Delete Item");
        
        CreateItembtn.setActionCommand("newLineSave");
        DeleteItembtn.setActionCommand("newLineCancel");
        
        CreateItembtn.addActionListener(frame.getActionHandler());
        DeleteItembtn.addActionListener(frame.getActionHandler());
        setLayout(new GridLayout(4, 2));
        
        add(NameOfItemLabel);
        add(NameOfItemField);
        add(CountOfItemLabel);
        add(CountOfItemField);
        add(PriceOfItemLabel);
        add(PriceOfItemField);
        add(CreateItembtn);
        add(DeleteItembtn);
        
        pack();
    }

    public JTextField getItemNameOfTheField() {
        return NameOfItemField;
    }

    public JTextField getItemCountOfTheField() {
        return CountOfItemField;
    }

    public JTextField getItemPriceOfTheField() {
        return PriceOfItemField;
    }
}

    

