package pl.pasechnik.ecommerce.sales;

public class AcceptOfferCommand {
    String fname;
    String lname;
    String email;

    public AcceptOfferCommand(){
    }

    public AcceptOfferCommand setFname(String fname) {
        this.fname=fname;
        return this;
    }
    public AcceptOfferCommand setLname(String lname) {
        this.lname=lname;
        return this;
    }
    public AcceptOfferCommand setEmail(String email) {
        this.email=email;
        return this;
    }
    public String getFname(String fname) {
        return fname;
    }
    public String getLname(String lname) {
        return lname;
    }
    public String getEmail(String email) {
        return email;
    }
}
