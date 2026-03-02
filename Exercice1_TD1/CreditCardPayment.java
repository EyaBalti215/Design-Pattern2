public class CreditCardPayment implements PaymentMethod {
    @Override
    public void process(double amount) {
      System.out.println("Paiement de " + amount + " EUR par carte bancaire.");
    }
}
