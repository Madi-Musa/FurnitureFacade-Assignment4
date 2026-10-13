public class PaymentService {
    private int customerBalance;

    public PaymentService(int startingBalance) {
        if (startingBalance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        customerBalance = startingBalance;
    }

    public boolean charge(int amount) {
        if (amount <= 0 || amount > customerBalance) {
            return false;
        }
        customerBalance -= amount;
        return true;
    }

    public int remainingBalance() {
        return customerBalance;
    }
}
