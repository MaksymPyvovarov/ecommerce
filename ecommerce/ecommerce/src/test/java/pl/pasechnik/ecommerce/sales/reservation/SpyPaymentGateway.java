package pl.pasechnik.ecommerce.sales.reservation;

import pl.pasechnik.ecommerce.sales.payment.PaymentDetails;
import pl.pasechnik.ecommerce.sales.payment.PaymentGateway;
import pl.pasechnik.ecommerce.sales.payment.RegisterPaymentRequest;

public class SpyPaymentGateway implements PaymentGateway {
    Integer requestCount = 0;
    public RegisterPaymentRequest lastRequest;

    public Integer getRequestsCount() {
        return requestCount;
    }

    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        this.requestCount++;
        lastRequest = registerPaymentRequest;
        return new PaymentDetails("http://spy-gateway");
    }
}
