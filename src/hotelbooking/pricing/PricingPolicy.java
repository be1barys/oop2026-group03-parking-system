package hotelbooking.pricing;

public class PricingPolicy {
    private static final PricingPolicy INSTANCE = new PricingPolicy();
    private PricingPolicy() {}

    public static PricingPolicy getInstance() {
        return INSTANCE;
    }

    public double total(double pricePerNight, long nights) {
        return pricePerNight * nights;
    }

    public double minDeposit(double total) {
        return total * 0.20;
    }

    public double cancelFee(double total) {
        return total * 0.20;
    }
}
