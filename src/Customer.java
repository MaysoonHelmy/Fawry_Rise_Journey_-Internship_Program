public class Customer {
    private String name;
    private double balance;
    private Cart cart;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.cart = new Cart();
    }

    public Cart getCart() {
        return cart;
    }

    public double getBalance() {
        return balance;
    }

    public void deductBalance(double amount) {
        if (balance < amount) throw new IllegalArgumentException("Insufficient balance.");
        balance -= amount;
    }

    public void printBalance() {
        System.out.println("Customer Balance After Payment: $" + balance);
    }
}
