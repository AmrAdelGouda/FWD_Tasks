package sig.model;

public class Line {

    private String TheNameOfTheItem;
    private double ThePriceOfTheItem;
    private int count;
    private Header HeaderFile;

    public Line() {
    }

    public Line(String item, double price, int count, Header HeaderFile) {
        this.TheNameOfTheItem = item;
        this.ThePriceOfTheItem = price;
        this.count = count;
        this.HeaderFile = HeaderFile;
    }

    public Header getHeader() {
        return HeaderFile;
    }

    public String getItem() {
        return TheNameOfTheItem;
    }

    public double getPrice() {
        return ThePriceOfTheItem;
    }

    public int getCount() {
        return count;
    }

    public double getLineTotal() {
        return ThePriceOfTheItem * count;
    }

}
