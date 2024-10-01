package Pedido;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<String> items;
    private Menu menu;
    private double total;

    public Pedido(Menu menu) {
        this.menu = menu;
        this.items = new ArrayList<>();
        this.total = 0.0;
    }

    public void addItem(String item) {
        if (menu.itemExists(item)) {
            items.add(item);
            total += menu.getPrice(item);
        } else {
            System.out.println("Item não encontrado no menu.");
        }
    }

    public void showOrder() {
        System.out.println(" Pedido ");
        for (String item : items) {
            System.out.println(item + " - $" + menu.getPrice(item));
        }
        System.out.println("Total: $" + total);
    }

    public double getTotal() {
        return total;
    }
}
