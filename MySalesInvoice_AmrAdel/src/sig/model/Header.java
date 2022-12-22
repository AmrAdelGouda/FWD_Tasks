
package sig.model;

import java.util.ArrayList;
import java.util.Date;

        public class Header {
                private int invoicenum;
                private String custNum;
                private Date Date;
                private ArrayList<Line> ListOfLines;
               

public  Header() {

}

    public Header(int num, String customer, Date date) {
        this.invoicenum = num;
        this.custNum = customer;
        this.Date = date;
        }
    public int getNum() {
        return invoicenum;
    }

    public void setNum(int num) {
        this.invoicenum = num;
    }

    public String getCustomer() {
        return custNum;
    }

    public void setCustomer(String customer) {
        this.custNum = customer;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date date) {
        this.Date = date;
    }
    
  public ArrayList<Line> getLines() {
        if (ListOfLines != null) {
            return ListOfLines;
            
        }
        return ListOfLines = new ArrayList<>();
    }

    public void setLines(ArrayList<Line> lines) {
        this.ListOfLines = lines;
    }
    
    
    
    public double getItemTotal(){
        double sum = 0.0;
        int i =0;
        while(i < getLines().size() ){
            sum += getLines().get(i).getLineTotal();
            i++;
       }
        
       
        return sum;
    }
   
        }

  


    
