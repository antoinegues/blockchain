package in.guesp.blockchain.dto;

import in.guesp.blockchain.model.Transaction;

public class TransactionDTO {

    public Long id;
    public Double amount;

    public String from;

    public String to;

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.amount = transaction.getAmount();
        this.from = transaction.getFrom();
        this.to = transaction.getTo();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof TransactionDTO transactionDTO)) {
            return false;
        }
        return id.equals(transactionDTO.id)
                && amount.equals(transactionDTO.amount)
                && from.equals(transactionDTO.from)
                && to.equals(transactionDTO.to);
    }

}
