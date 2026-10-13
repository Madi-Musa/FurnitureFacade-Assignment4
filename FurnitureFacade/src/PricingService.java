import java.util.Map;

public class PricingService {
    private final Map<String, Integer> prices = Map.of(
            "Chair", 15000,
            "Table", 45000,
            "Wardrobe", 90000
    );

    public int calculateFurnitureTotal(String item, int quantity) {
        Integer unitPrice = prices.get(item);
        if (unitPrice == null || quantity <= 0) {
            throw new IllegalArgumentException("Unknown furniture or invalid quantity.");
        }
        return Math.multiplyExact(unitPrice, quantity);
    }

    public int calculateGrandTotal(int furnitureTotal, int deliveryFee) {
        return Math.addExact(furnitureTotal, deliveryFee);
    }
}
