package pl.pasechnik.ecommerce.sales;

public class SalesFacade {
    public void addToCart(String customerId,String productId){

    }
    public Offer getCurrentOffer(String customerId){
        return new Offer();
    }
    public void makeReservationPaid(String reservationId){

    }
    public void addProduct(String customerId, String productId) {

    }

    public ReservationDetails acceptOffer(String customerId, AcceptOfferCommand acceptOfferRequest) {
        return new ReservationDetails();
    }
}
