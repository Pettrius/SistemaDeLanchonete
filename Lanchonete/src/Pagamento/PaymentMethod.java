package Pagamento;

    public interface PaymentMethod {
        void processPayment(double amount);
}

    public class PixPayment implements PaymentMethod {
        @Override
        public void processPayment(double amount) {
            System.out.println("Payment of $" + amount + " processed via PIX.");
    }
}

    public class CreditCardPayment implements PaymentMethod {
        @Override
        public void processPayment(double amount) {
            System.out.println("Payment of $" + amount + " processed via Credit Card.");
    }
}

    public class DebitCardPayment implements PaymentMethod {
        @Override
        public void processPayment(double amount) {
            System.out.println("Payment of $" + amount + " processed via Debit Card.");
    }
}

    public class CashPayment implements PaymentMethod {
        @Override
        public void processPayment(double amount) {
            System.out.println("Payment of $" + amount + " processed in Cash.");
    }
}