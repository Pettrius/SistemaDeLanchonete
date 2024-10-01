package Pedido;


import java.util.HashMap;
import java.util.Map;

public class Menu {
    private Map<String, Double> items;

    public Menu() {
        items = new HashMap<>();
        items.put("Hambúrguer", 15.99);
        items.put("Batata frita", 6.99);
        items.put("Refrigerante", 4.50);
    }

    public void showMenu() {
        System.out.println(" Menu ");
        for (Map.Entry<String, Double> item : items.entrySet()) {
            System.out.println(item.getKey() + ": $" + item.getValue());
        }
    }

    public Double getPrice(String item) {
        return items.getOrDefault(item, null);
    }

    public boolean itemExists(String item) {
        return items.containsKey(item);
    }
}

