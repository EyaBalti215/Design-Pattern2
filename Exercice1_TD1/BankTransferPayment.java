public class BankTransferPayment implements PaymentMethod {
    @Override
    public void process(double amount) {
        System.out.println("Paiement de " + amount + " EUR par virement bancaire.");
    }
}