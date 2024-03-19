package in.guesp.blockchain.dto;

import in.guesp.blockchain.model.Transaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TransactionDTOTest {

    @Test
    public void testConvertToDto() {
        Transaction transaction = new Transaction(1L, 5, "Alice", "Bob");
        TransactionDTO transactionDTO = new TransactionDTO(transaction);

        assertEquals(transaction.getId(), transactionDTO.id);
        assertEquals(transaction.getAmount(), transactionDTO.amount);
        assertEquals(transaction.getFrom(), transactionDTO.from);
        assertEquals(transaction.getTo(), transactionDTO.to);
    }


    @Test
    public void testTransactionDTOEquals() {
        Transaction transactionA = new Transaction(1L, 5, "Alice", "Bob");
        Transaction transactionB = new Transaction(1L, 6, "Alice", "Bob");
        assertEquals(new TransactionDTO(transactionA), new TransactionDTO(transactionA));
        assertEquals(new TransactionDTO(transactionB), new TransactionDTO(transactionB));
        assertNotEquals(new TransactionDTO(transactionA), new TransactionDTO(transactionB));

        assertNotEquals(new TransactionDTO(transactionA), new Object());
    }

}
