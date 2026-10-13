public class Main {
    public static void main(String[] args) {
        FurnitureFacade shop = new FurnitureFacade(100000);

        System.out.println("=== Successful order ===");
        shop.orderFurniture("Chair", 2, "Astana");

        System.out.println("\n=== Unsupported delivery city ===");
        shop.orderFurniture("Table", 1, "Shymkent");

        System.out.println("\n=== Not enough stock ===");
        shop.orderFurniture("Wardrobe", 5, "Astana");

        System.out.println("\n=== Payment failure and stock rollback ===");
        shop.orderFurniture("Wardrobe", 1, "Almaty");
    }
}
