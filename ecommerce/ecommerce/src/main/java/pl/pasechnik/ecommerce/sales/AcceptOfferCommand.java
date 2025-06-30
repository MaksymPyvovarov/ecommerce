package pl.pasechnik.ecommerce.sales;

public class AcceptOfferCommand {
    String fname;
    String lname;
    String email;

    public AcceptOfferCommand(){
    }

    public void setFname(String fname) {
        this.fname=fname;
    }
    public void setLname(String lname) {
        this.lname=lname;
    }
    public void setEmail(String email) {
        this.email=email;
    }
}
