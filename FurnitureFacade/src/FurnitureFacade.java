public class FurnitureFacade {
    private final InventoryService inventory;
    private final PricingService pricing;
    private final PaymentService payment;
    private final DeliveryService delivery;

    public FurnitureFacade(int customerBalance) {
        inventory = new InventoryService();
        pricing = new PricingService();
        payment = new PaymentService(customerBalance);
        delivery = new DeliveryService();
    }

    public void orderFurniture(String item, int quantity, String city) {
        if (item == null || quantity <= 0 || !delivery.supportsCity(city)) {
            System.out.println("Order rejected: invalid item, quantity, or delivery city.");
            return;
        }

        if (!inventory.reserve(item, quantity)) {
            System.out.println("Order rejected: furniture is unavailable.");
            return;
        }

        // If payment fails, return the reserved furniture to stock.
        int furnitureTotal = pricing.calculateFurnitureTotal(item, quantity);
        int deliveryFee = delivery.calculateFee(city);
        int total = pricing.calculateGrandTotal(furnitureTotal, deliveryFee);

        if (!payment.charge(total)) {
            inventory.release(item, quantity);
            System.out.println("Order rejected: insufficient balance. Stock restored.");
            return;
        }

        String deliveryDetails = delivery.arrangeDelivery(city, item, quantity);
        System.out.println("Order confirmed: " + deliveryDetails);
        System.out.println("Furniture: " + furnitureTotal + " KZT");
        System.out.println("Delivery: " + deliveryFee + " KZT");
        System.out.println("Total paid: " + total + " KZT");
        System.out.println("Remaining balance: " + payment.remainingBalance() + " KZT");
    }
}
