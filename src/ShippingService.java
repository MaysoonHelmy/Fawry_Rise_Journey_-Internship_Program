import java.util.List;

public class ShippingService {
    public void shipItems(List<Shippable> items) {
        System.out.println("\nShipping the following items:");
        for (Shippable item : items) {
            System.out.println("- " + item.getName() + ", Weight: " + item.getWeight());
        }
    }

    public double calculateShippingFee(List<Shippable> items) {
        return items.stream().mapToDouble(Shippable::getWeight).sum() * 2; // $2 per kg
    }
}
