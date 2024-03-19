package in.guesp.blockchain.model;

public class Transaction {

    private final Long id;
    private final Double amount;
    private final String from;
    private final String to;

    public Transaction(Long id, double amount, String from, String to) {
        this.id = id;
        this.amount = amount;
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Transaction transaction)) {
            return false;
        }
        return id.equals(transaction.id)
                && amount.equals(transaction.amount)
                && from.equals(transaction.from)
                && to.equals(transaction.to);
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public double getAmount() {
        return amount;
    }


    @Override
    public int hashCode() {
        return id.hashCode() + amount.hashCode() + from.hashCode() + to.hashCode();
    }

    public Long getId() {
        return id;
    }
}

