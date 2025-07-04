import java.util.ArrayList;
import java.util.List;

public class Cart {
    List<CartItem> items = new ArrayList<>();

    public void addProduct(Product product, int quantity)
    {
        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("Sold out");
        }
        items.add(new CartItem(product, quantity));
    }

    public boolean isEmpty()
    {
        return items.isEmpty();
    }

    public List<CartItem> getItems()
    {
        return items;
    }

    public double getSubtotal()
    {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }
}