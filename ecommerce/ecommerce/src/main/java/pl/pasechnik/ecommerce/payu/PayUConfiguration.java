package pl.pasechnik.ecommerce.payu;

public class PayUConfiguration {
    String clientId;
    String clientSecret;
    boolean sandbox;

    public PayUConfiguration(String clientId, String clientSecret, boolean sandbox) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.sandbox = sandbox;
    }

    public static PayUConfiguration sandbox(String clientId, String clientSecret) {
        return new PayUConfiguration(clientId, clientSecret, true);
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getBaseUrl() {
        if (sandbox) {
            return "https://secure.snd.payu.com";
        } else {
            return "https://secure.payu.com";
        }
    }
}
