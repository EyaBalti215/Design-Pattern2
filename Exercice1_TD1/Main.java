public class Main {

    public static void main(String[] args) {

        PaymentProcessor processor = new PaymentProcessor();

        PaymentMethod payment1 = new CreditCardPayment();
        processor.processPayment(payment1, 100);

        PaymentMethod payment2 = new PaypalPayment();
        processor.processPayment(payment2, 200);
    }
} 
    

