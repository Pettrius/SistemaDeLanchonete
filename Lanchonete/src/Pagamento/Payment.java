package Lanchonete.src.Pagamento;

public class Payment {
    void processPayment();
    boolean isPaid();
    double getTotalAmount();
}

// Implementation of payment with credit card
public class CreditCardPayment implements Payment {
    private double totalAmount;
    private boolean paid;

    public CreditCardPayment(double totalAmount) {
        this.totalAmount = totalAmount;
        this.paid = false;
    }

    @Override
    public void processPayment() {
        if (!paid) {
            this.paid = true;
            System.out.println("Payment of $" + totalAmount + " processed with credit card.");
        } else {
            System.out.println("Payment has already been processed.");
        }
    }

    @Override
    public boolean isPaid() {
        return paid;
    }

    @Override
    public double getTotalAmount() {
        return totalAmount;
    }
}

// Implementation of payment with cash
public class CashPayment implements Payment {
    private double totalAmount;
    private boolean paid;

    public CashPayment(double totalAmount) {
        this.totalAmount = totalAmount;
        this.paid = false;
    }

    @Override
    public void processPayment() {
        if (!paid) {
            this.paid = true;
            System.out.println("Payment of $" + totalAmount + " processed in cash.");
        } else {
            System.out.println("Payment has already been processed.");
        }
    }

    @Override
    public boolean isPaid() {
        return paid;
    }

    @Override
    public double getTotalAmount() {
        return totalAmount;
    }
}
