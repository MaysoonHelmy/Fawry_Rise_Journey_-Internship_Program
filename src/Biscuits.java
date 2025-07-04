import java.time.LocalDate;
public class Biscuits extends ExpirableProduct {
    public Biscuits(String name, double price, int quantity, LocalDate expiryDate) {
        super(name, price, quantity, expiryDate);
    }
}