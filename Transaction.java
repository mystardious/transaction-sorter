public class Transaction {

    String day;
    String month;
    String year;

    String vendor;
    String account;
    String description;
    Double price;

    public Transaction(String date, String price, String details) {

        // Split date into three variables
        String[] tempDate = date.split("/");
        day = tempDate[0];
        month = tempDate[1];
        year = tempDate[2];

        // Convert price to double from string
        this.price = Double.parseDouble(price);

        // Save description of transaction into details cutting unnecessary stuff
        if(details.contains("VISA DEBIT PURCHASE CARD 7055")) {
            this.vendor = details.substring(30);
        } else if(details.contains("PAYMENT TO PAYPAL AUSTRALIA")) {
            this.vendor = "Please enter vendor details";
            this.account = "eCommerce";
            this.description = "Please enter description details";
        } else {
            this.vendor = details;
        }

    }

    @Override
    public String toString() {

        StringBuilder retVal = new StringBuilder();

        retVal.append(day +",");

        if(price < 0)
            retVal.append(price * -1 +",");
        else
            retVal.append(",");

        retVal.append(vendor +",");

        if(account!=null)
            retVal.append(account +",");
        else
            retVal.append("Other,");

        if(price > 0)
            retVal.append(price +",");
        else
            retVal.append(",");

        if(description!=null)
            retVal.append(description +",");
        else
            retVal.append(",");

        return retVal.toString();

    }

}
