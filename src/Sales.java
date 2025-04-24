
public class Sales {
    private String receiptid;
    private String bookingid;
    private String date;
    private double sales;

    public Sales(String receiptid, String bookingid, String date, double sales) {
        this.receiptid = receiptid;
        this.bookingid = bookingid;
        this.date = date;
        this.sales = sales;
    }

    public String getReceiptid() {
        return receiptid;
    }

    public void setReceiptid(String receiptid) {
        this.receiptid = receiptid;
    }

    public String getBookingid() {
        return bookingid;
    }

    public void setBookingid(String bookingid) {
        this.bookingid = bookingid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%.2f", receiptid,bookingid, date, sales);
    }

}
