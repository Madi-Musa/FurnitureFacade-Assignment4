public class DeliveryService {
    public boolean supportsCity(String city) {
        return "Astana".equalsIgnoreCase(city)
                || "Almaty".equalsIgnoreCase(city);
    }

    public int calculateFee(String city) {
        if ("Astana".equalsIgnoreCase(city)) {
            return 2500;
        }
        if ("Almaty".equalsIgnoreCase(city)) {
            return 4000;
        }
        throw new IllegalArgumentException("Delivery city is not supported.");
    }

    public String arrangeDelivery(String city, String item, int quantity) {
        if (!supportsCity(city) || quantity <= 0) {
            throw new IllegalArgumentException("Cannot arrange delivery.");
        }
        return quantity + " x " + item + " -> " + city;
    }
}
