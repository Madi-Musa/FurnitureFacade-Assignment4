import java.util.HashMap;
import java.util.Map;

public class InventoryService {
    private final Map<String, Integer> stock = new HashMap<>();

    public InventoryService() {
        stock.put("Chair", 10);
        stock.put("Table", 5);
        stock.put("Wardrobe", 3);
    }

    public boolean reserve(String item, int quantity) {
        if (item == null || quantity <= 0 || !stock.containsKey(item)) {
            return false;
        }
        int available = stock.get(item);
        if (available < quantity) {
            return false;
        }
        stock.put(item, available - quantity);
        return true;
    }

    public void release(String item, int quantity) {
        if (!stock.containsKey(item) || quantity <= 0) {
            throw new IllegalArgumentException("Cannot release invalid stock.");
        }
        stock.put(item, stock.get(item) + quantity);
    }

    public int available(String item) {
        return stock.getOrDefault(item, 0);
    }
}
