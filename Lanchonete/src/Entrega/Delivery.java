package Entrega;

public interface Delivery {
    void delivery();
    String getAddress();
    Order getOrder();
}

public class StandardDelivery implements Delivery {
    private Order order;
    private String address;

    public StandardDelivery(Order order, String address) {
        this.order = order;
        this.address = address;
    }

    @Override
    public void delivery() {
        System.out.println("Entrega do pedido no endereço:" + address);
    }

    @Override
    public Order getOrder() {
        return order;
    }

    @Override
    public String getAddress() {
        return address;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

public class ExpressDelivery extends StandardDelivery {
    public ExpressDelivery(Order order, String address) {
        super(order, address);
    }

    @Override
    public void delivery() {
        System.out.println("Entrega rápida do pedido no endereço:" + getAddress());
    }
}
