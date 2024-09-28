package Pedido;

import java.util.List;

public class Item {
    private User user;
    private List<Item> selectedItems;
    private double payment;

    public Order(User user, List<Item> selectedItems, double payment) {
        this.user = user;
        this.selectedItems = selectedItems;
        this.payment = payment;
    }

    public void displayDetails() {
        System.out.println("Order Details:");
        user.displayInformation();
        System.out.println("Ordered Items:");
        double total = calculateTotal();
        displayItems();
        System.out.println("Total to pay: $ " + total);
        System.out.println("Amount paid: $ " + payment);
        displayChange(total);
    }

    private double calculateTotal() {
        double total = 0;
        for (Item item : selectedItems) {
            total += item.getPrice();
        }
        return total;
    }

    private void displayItems() {
        for (Item item : selectedItems) {
            System.out.println(item.getName() + " - $ " + item.getPrice());
        }
    }

    private void displayChange(double total) {
        if (payment >= total) {
            System.out.println("Change: $ " + (payment - total));
        } else {
            System.out.println("Insufficient payment.");
        }
    }
}
