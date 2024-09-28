package Lanchonete.src.Entrega;

public class Delivery {
    void deliverOrder();
    boolean isDelivered();
    String getDeliveryAddress();
}

// Implementation for home delivery
public class HomeDelivery implements Delivery {
    private String deliveryAddress;
    private boolean delivered;

    public HomeDelivery(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        this.delivered = false;
    }

    @Override
    public void deliverOrder() {
        if (!delivered) {
            this.delivered = true;
            System.out.println("Order delivered to the address: " + deliveryAddress);
        } else {
            System.out.println("The order has already been delivered.");
        }
    }

    @Override
    public boolean isDelivered() {
        return delivered;
    }

    @Override
    public String getDeliveryAddress() {
        return deliveryAddress;
    }
}

// Implementation for pick-up at the counter
public class CounterPickup implements Delivery {
    private boolean delivered;

    public CounterPickup() {
        this.delivered = false;
    }

    @Override
    public void deliverOrder() {
        if (!delivered) {
            this.delivered = true;
            System.out.println("Pedido disponível para retirada no balcão.");
        } else {
            System.out.println("O pedido já foi retirado.");
        }
    }

    @Override
    public boolean isDelivered() {
        return delivered;
    }

    @Override
    public String getDeliveryAddress() {
        return "Counter at the snack bar";
    }
}
