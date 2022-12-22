package sig.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import sig.view.Frame;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.Date;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import sig.view.InvoiceHeaderView;
import sig.view.InvoiceLineView;





 public class FileOperations implements ActionListener{
         private Frame TheFrame;
         private InvoiceHeaderView InvoiceHeaderForm;
         private InvoiceLineView InvoiceLineForm;

    public FileOperations(Frame frame) {
         
        this.TheFrame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch(e.getActionCommand()){
            
            case "Create New Invoice" : 
            
                CreateNewInvoice();
            break;
    
          case "Delete Invoice" : 
         
            DeleteInvoice();
              break;
    
              case "Save Change" : 
         
                 SaveChanges();
              break;
    
                 case "Cancel" : 
           
                    cancel();
                  break;
             case "newInvoiceSave":
                newInvoiceDialogOK();
                break;

            case "newInvoiceCancel":
                newInvoiceDialogCancel();
                break;

            case "newLineCancel":
                newLineDialogCancel();
                break;

            case "newLineSave":
                newLineDialogOK();
                break;
    
              case "load file" : 
          
                     loadfile();
                  break;
    
                 case "save file" : 
                  System.out.println("Done");
                     savefile();
                    break;

       
}
    }
 
 
    
      private void loadfile() {
        JFileChooser selectedFile = new JFileChooser();
        try {
            int _res = selectedFile.showOpenDialog(TheFrame);
            if (_res == JFileChooser.APPROVE_OPTION) {
                File selectedHeader = selectedFile.getSelectedFile();
                Path selectedHeaderPath = Paths.get(selectedHeader.getAbsolutePath());
                if(!selectedHeader.getAbsolutePath().endsWith(".csv")){
                     JOptionPane.showMessageDialog(TheFrame, "Wrong file format", "Error", JOptionPane.ERROR_MESSAGE);
                     return;
                }
                   
                List<String> headerLines = Files.readAllLines(selectedHeaderPath);
                ArrayList<Header> Headers = new ArrayList<>();
                for (String headerLine : headerLines) {
                    String[] Strings = headerLine.split(",");
                    String String1 = Strings[0];
                    String String2 = Strings[1];
                    String String3 = Strings[2];
                    int code = Integer.parseInt(String1);
                   try{
                       SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                
                         Date invoiceDate = dateFormat.parse(String2);     
                         
                         Header header = new Header(code, String3, invoiceDate);
                         Headers.add(header);
                   }
                   catch(Exception e ){
                       System.out.println(e);
                            
                        JOptionPane.showMessageDialog(TheFrame, "Wrong date format", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                   }
                   
                  
                }
                TheFrame.setInvoicesArray(Headers);

                _res = selectedFile.showOpenDialog(TheFrame);
                if (_res == JFileChooser.APPROVE_OPTION) {
                    File lineFile = selectedFile.getSelectedFile();
                    Path linePath = Paths.get(lineFile.getAbsolutePath());
                    
                    if(!selectedHeader.getAbsolutePath().endsWith(".csv")){
                     JOptionPane.showMessageDialog(TheFrame, "Wrong file format", "Error", JOptionPane.ERROR_MESSAGE);
                     return;
                }
                    
                    List<String> lineLines = Files.readAllLines(linePath);
                    ArrayList<Line> invoiceLines = new ArrayList<>();
                    for (String line : lineLines) {
                        String[] Strings = line.split(",");
                        String String1 = Strings[0];    // invoice num (int)
                        String String2 = Strings[1];    // item name   (String)
                        String String3 = Strings[2];    // price       (double)
                        String String4 = Strings[3];    // count       (int)
                        int invCode = Integer.parseInt(String1);
                        double price = Double.parseDouble(String3);
                        int count = Integer.parseInt(String4);
                        Header inv = TheFrame.getInvObject(invCode);
                        Line invoiceLine = new Line(String2, price, count, inv);
                        inv.getLines().add(invoiceLine);
                    }
                }
                InoviceHeader headerTable = new InoviceHeader(Headers);
                TheFrame.setInvoiceheaderTable(headerTable);
                TheFrame.getheaderTable().setModel(headerTable);
                System.out.println("files read");
            }

        } 
        
      
      
        catch (Exception ex) {
            JOptionPane.showMessageDialog(TheFrame, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
        } 
    }

    private void CreateNewInvoice() {
        InvoiceHeaderForm = new InvoiceHeaderView(TheFrame);
        InvoiceHeaderForm.setVisible(true);
    }

    private void DeleteInvoice() {
        int selectedInvoiceIndex = TheFrame.getheaderTable().getSelectedRow();
        if (selectedInvoiceIndex != -1) {
            TheFrame.getInvoicesArray().remove(selectedInvoiceIndex);
            TheFrame.getInvoiceheaderTable().fireTableDataChanged();

            TheFrame.getlineTable().setModel(new InvoiceLine(null));
            TheFrame.setLinesArray(null);
            TheFrame.getCustNameLbl().setText("");
            TheFrame.getInvNumLbl().setText("");
            TheFrame.getInvTotalIbl().setText("");
            TheFrame.getDateLbl().setText("");
        }
    }

    private void SaveChanges() {
        InvoiceLineForm = new InvoiceLineView(TheFrame);
        InvoiceLineForm.setVisible(true);
    }

    private void cancel() {
        int selectedLineIndex = TheFrame.getlineTable().getSelectedRow();
        int selectedInvoiceIndex = TheFrame.getheaderTable().getSelectedRow();
        if (selectedLineIndex != -1) {
            TheFrame.getLinesArray().remove(selectedLineIndex);
            InvoiceLine lineTableModel = (InvoiceLine) TheFrame.getlineTable().getModel();
            lineTableModel.fireTableDataChanged();
            TheFrame.getInvTotalIbl().setText("" + TheFrame.getInvoicesArray().get(selectedInvoiceIndex).getItemTotal());
            TheFrame.getInvoiceheaderTable().fireTableDataChanged();
            TheFrame.getheaderTable().setRowSelectionInterval(selectedInvoiceIndex, selectedInvoiceIndex);
        }
    }

    private void savefile() {
        ArrayList<Header> invoicesArray = TheFrame.getInvoicesArray();
        JFileChooser fc = new JFileChooser();
        try {
            int _res = fc.showSaveDialog(TheFrame);
            if (_res == JFileChooser.APPROVE_OPTION) {
                File selectedHeader = fc.getSelectedFile();
                if(!selectedHeader.getAbsolutePath().endsWith(".csv")){
                     JOptionPane.showMessageDialog(TheFrame, "Wrong file format", "Error", JOptionPane.ERROR_MESSAGE);
                     return;
                }
                   
                FileWriter hfw = new FileWriter(selectedHeader);
                String headers = "";
                String lines = "";
                for (Header invoice : invoicesArray) {
                    headers += invoice.toString();
                    headers += "\n";
                    for (Line line : invoice.getLines()) {
                        lines += line.toString();
                        lines += "\n";
                    }
                }
                
                headers = headers.substring(0, headers.length()-1);
                lines = lines.substring(0, lines.length()-1);
                _res = fc.showSaveDialog(TheFrame);
                File lineFile = fc.getSelectedFile();
                 if(!lineFile.getAbsolutePath().endsWith(".csv")){
                     JOptionPane.showMessageDialog(TheFrame, "Wrong file format", "Error", JOptionPane.ERROR_MESSAGE);
                     return;
                }
                FileWriter lfw = new FileWriter(lineFile);
                hfw.write(headers);
                lfw.write(lines);
                hfw.close();
                lfw.close();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(TheFrame, "Folder/File path is not found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void  newLineDialogCancel() {
        InvoiceLineForm.setVisible(false);
        InvoiceLineForm.dispose();
        InvoiceLineForm = null;
    }
  

    private void newInvoiceDialogOK() {
        InvoiceHeaderForm.setVisible(false);

        String custName = InvoiceHeaderForm.getCustomerNameTextField().getText();
        String str = InvoiceHeaderForm.getDateTextField().getText();
        Date d = new Date();
        
 
        try {
             SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
             d = dateFormat.parse(str);
             int invNum = 0;
        for (Header inv : TheFrame.getInvoicesArray()) {
            if (inv.getNum() > invNum) {
                invNum = inv.getNum();
            }
        }
        invNum++;
        Header newInv = new Header(invNum, custName, d);
        TheFrame.getInvoicesArray().add(newInv);
       TheFrame.getInvoiceheaderTable().fireTableDataChanged();
       InvoiceHeaderForm.dispose();
        InvoiceHeaderForm = null;
        } 
        catch (ParseException ex) {
            JOptionPane.showMessageDialog(TheFrame, "Wrong date format", "Invalid date format", JOptionPane.ERROR_MESSAGE);
        }

        
    }

    private void newInvoiceDialogCancel() {
        InvoiceHeaderForm.setVisible(false);
        InvoiceHeaderForm.dispose();
        InvoiceHeaderForm = null;
    }

    private void newLineDialogOK() {
        InvoiceLineForm.setVisible(false);

        String name = InvoiceLineForm.getItemNameOfTheField().getText();
        String str1 = InvoiceLineForm.getItemCountOfTheField().getText();
        String StringNum2 = InvoiceLineForm.getItemPriceOfTheField().getText();
        int count = 1;
        double price = 1;
        try {
            count = Integer.parseInt(str1);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(TheFrame, "Cannot convert number", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }

        try {
            price = Double.parseDouble(StringNum2);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(TheFrame, "Cannot convert price", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }
        int selectedInvHeader = TheFrame.getheaderTable().getSelectedRow();
                 if (selectedInvHeader != -1) {
            Header invHeader = TheFrame.getInvoicesArray().get(selectedInvHeader);
            Line line = new Line(name, price, count, invHeader);
            //invHeader.getLines().add(line);
            TheFrame.getLinesArray().add(line);
            InvoiceLine lineTable = (InvoiceLine) TheFrame.getlineTable().getModel();
            lineTable.fireTableDataChanged();
            TheFrame.getInvoiceheaderTable().fireTableDataChanged();
        }
        TheFrame.getheaderTable().setRowSelectionInterval(selectedInvHeader, selectedInvHeader);
        InvoiceLineForm.dispose();
        InvoiceLineForm = null;
    }

    

  

}