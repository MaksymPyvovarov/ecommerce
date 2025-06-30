package pl.pasechnik.ecommerce.sales.reservation;

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
    public String getFname() {
        return fname;
    }
    public String getLname() {
        return lname;
    }
    public String getEmail() {
        return email;
    }
}
