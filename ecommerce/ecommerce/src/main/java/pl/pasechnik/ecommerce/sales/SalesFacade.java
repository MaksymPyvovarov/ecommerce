package pl.pasechnik.ecommerce.sales;

import pl.pasechnik.ecommerce.sales.cart.Cart;
import pl.pasechnik.ecommerce.sales.cart.HashMapCartStorage;
import pl.pasechnik.ecommerce.sales.payment.PaymentDetails;
import pl.pasechnik.ecommerce.sales.payment.PaymentGateway;
import pl.pasechnik.ecommerce.sales.payment.RegisterPaymentRequest;
import pl.pasechnik.ecommerce.sales.reservation.AcceptOfferCommand;
import pl.pasechnik.ecommerce.sales.reservation.Reservation;
import pl.pasechnik.ecommerce.sales.reservation.ReservationDetails;
import pl.pasechnik.ecommerce.sales.reservation.ReservationRepository;

import java.math.BigDecimal;
import java.util.UUID;

public class SalesFacade {
    private HashMapCartStorage cartStorage;
    private OfferCalculator offerCalculator;
    private PaymentGateway paymentGateway;
    private ReservationRepository reservationRepository;

    public SalesFacade(HashMapCartStorage cartStorage, OfferCalculator offerCalculator, PaymentGateway paymentGateway, ReservationRepository reservationRespository) {
        this.cartStorage = cartStorage;
        this.offerCalculator = offerCalculator;
        this.paymentGateway = paymentGateway;
        this.reservationRepository = reservationRespository;
    }

    public Offer getCurrentOffer(String customerId) {
        Cart cart = getCartForCustomer(customerId);
        return new Offer(
                BigDecimal.valueOf(110).multiply(new BigDecimal(cart.getItemsCount())),
                cart.getCartItems().size()
                );
    }

    public void addProduct(String customerId, String productId) {
        Cart cart = getCartForCustomer(customerId);

        cart.add(productId);
        cartStorage.save(customerId,cart);
    }

    private Cart getCartForCustomer(String customerId) {
        return cartStorage.getForCustomer(customerId)
                .orElse(Cart.empty());
    }

    public ReservationDetails acceptOffer(String customerId, AcceptOfferCommand acceptOfferRequest) {
        String reservationId = UUID.randomUUID().toString();
        Offer offer = this.getCurrentOffer(customerId);

        PaymentDetails paymentDetails = paymentGateway.registerPayment(
                RegisterPaymentRequest.of(reservationId, acceptOfferRequest, offer.getTotal())
        );

        Reservation reservation = Reservation.of(
                reservationId,
                customerId,
                acceptOfferRequest,
                offer,
                paymentDetails);

        reservationRepository.add(reservation);

        return new ReservationDetails(reservationId, paymentDetails.getPaymentUrl());
    }
}
