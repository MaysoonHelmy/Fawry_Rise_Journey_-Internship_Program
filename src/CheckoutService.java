import java.util.*;
import java.text.DecimalFormat;

public class CheckoutService {
    private ShippingService shippingService = new ShippingService();

    public void checkout(Customer customer) {
        Cart cart = customer.getCart();
        if (cart.isEmpty()) throw new IllegalStateException("Cart is empty.");

        double subtotal = cart.getSubtotal();
        Map<String, ShippableItemSummary> shippingSummaryMap = new LinkedHashMap<>();
        Map<String, Double> itemPriceMap = new LinkedHashMap<>();

        // Validate items and prepare summaries
        for (CartItem item : cart.getItems()) {
            Product product = item.product;

            if (product.isExpired()) {
                throw new IllegalStateException("Product " + product.getName() + " is expired.");
            }

            if (product.getQuantity() < item.quantity) {
                throw new IllegalStateException("Stock is not enough for : " + product.getName());
            }

            double itemTotalPrice = product.getPrice() * item.quantity;
            itemPriceMap.put(product.getName(), itemTotalPrice);

            // Group shippable items
            if (product instanceof Shippable) {
                Shippable shippable = (Shippable) product;
                ShippableItemSummary summary = shippingSummaryMap.getOrDefault(product.getName(),
                        new ShippableItemSummary(product.getName(), 0, 0));
                summary.quantity += item.quantity;
                summary.totalWeight += shippable.getWeight() * item.quantity;
                shippingSummaryMap.put(product.getName(), summary);
            }
        }

        // Shipping cost
        List<Shippable> shippableItems = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            if (item.product instanceof Shippable) {
                for (int i = 0; i < item.quantity; i++) {
                    shippableItems.add((Shippable) item.product);
                }
            }
        }

        double shippingFee = shippingService.calculateShippingFee(shippableItems);
        double totalAmount = subtotal + shippingFee;

        if (customer.getBalance() < totalAmount) {
            throw new IllegalStateException("Insufficient balance.");
        }

        for (CartItem item : cart.getItems()) {
            item.product.reduceQuantity(item.quantity);
        }
        customer.deductBalance(totalAmount);


        DecimalFormat df = new DecimalFormat("#.0");
        if (!shippingSummaryMap.isEmpty()) {
            System.out.println("Shipment notice");
            double totalGrams = 0;
            for (ShippableItemSummary summary : shippingSummaryMap.values()) {
                int weightInGrams = (int) (summary.totalWeight * 1000);
                totalGrams += weightInGrams;
                System.out.println(summary.quantity + "x " + summary.name + "\t" + weightInGrams + "g");
            }
            System.out.println("Total package weight " + df.format(totalGrams / 1000.0) + "kg\n");
        }

        System.out.println(" Checkout receipt");
        for (Map.Entry<String, Double> entry : itemPriceMap.entrySet()) {
            System.out.printf("%-12s %.0f\n", entry.getKey(), entry.getValue());
        }
        System.out.println("----------------------------");
        System.out.printf("%-12s %.0f\n", "Subtotal", subtotal);
        System.out.printf("%-12s %.0f\n", "Shipping", shippingFee);
        System.out.printf("%-12s %.0f\n", "Amount", totalAmount);
    }

    static class ShippableItemSummary {
        String name;
        int quantity;
        double totalWeight;

        public ShippableItemSummary(String name, int quantity, double totalWeight) {
            this.name = name;
            this.quantity = quantity;
            this.totalWeight = totalWeight;
        }
    }
}