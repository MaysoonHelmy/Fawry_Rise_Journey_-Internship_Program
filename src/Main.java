import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Product cheese = new Cheese("Cheese", 100, 5, LocalDate.of(2025, 12, 1), 0.2);
        Product biscuits = new Biscuits("Biscuits", 150, 2, LocalDate.of(2025, 10, 1));
        Product scratchCard = new ScratchCard("ScratchCard", 50, 10);
        Product tv = new TV("TV", 300, 3, 5.0);
        Product expiredMilk = new Cheese("Milk", 30, 2, LocalDate.of(2022, 1, 1), 0.5);

        CheckoutService checkoutService = new CheckoutService();

        // Test Case 1
        System.out.println("----- Successful Checkout -----");
        Customer alice = new Customer("Alice", 1000);
        alice.getCart().addProduct(cheese, 2);
        alice.getCart().addProduct(biscuits, 1);
        alice.getCart().addProduct(scratchCard, 1);
        checkoutService.checkout(alice);
        System.out.println("\n");

        // Test Case 2
        System.out.println("----- Empty Cart -----");
        Customer Tom = new Customer("Tom", 500);
        try {
            checkoutService.checkout(Tom);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n");

        // Test Case 3
        System.out.println("----- Expired Product -----");
        Customer charlie = new Customer("Charlie", 500);
        try {
            charlie.getCart().addProduct(expiredMilk, 1);
            checkoutService.checkout(charlie);
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        System.out.println("\n");

        // Test Case 4
        System.out.println("----- Quantity Exceeds Stock -----");
        Customer harry = new Customer("Harry", 1000);
        try {
            harry.getCart().addProduct(tv, 10);
            checkoutService.checkout(harry);
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        System.out.println("\n");

        // Test Case 5
        System.out.println("----- Insufficient Balance -----");
        Customer karen = new Customer("Karen", 100);
        try {
            karen.getCart().addProduct(cheese, 2);
            karen.getCart().addProduct(tv, 1);
            checkoutService.checkout(karen);
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        System.out.println("\n");

        // Test Case 6
        System.out.println("----Shipping Large Items -----");
        Customer Joe = new Customer("Joe", 2000);
        Joe.getCart().addProduct(tv, 2);
        Joe.getCart().addProduct(cheese, 1);
        checkoutService.checkout(Joe);
        System.out.println("\n");
    }
}
