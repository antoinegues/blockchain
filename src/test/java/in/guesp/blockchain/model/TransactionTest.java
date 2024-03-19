package in.guesp.blockchain.model;

import in.guesp.blockchain.exception.IncorrectPreviousHash;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    @Test
    void testTransactionCreate() throws IncorrectPreviousHash {
        Block block = new Block(0, 0, null);
        Transaction transaction = block.newTransaction(5.0, "Alice", "Bob");

        assertNotNull(transaction);
        assertEquals(transaction.getFrom(), "Alice");
        assertEquals(transaction.getTo(), "Bob");
        assertEquals(transaction.getAmount(), 5.0);
    }

    @Test
    void testTransactionEqual() {
        Transaction transactionA = new Transaction(1L, 5.0, "Alice", "Bob");
        Transaction transactionB = new Transaction(1L, 5.0, "Alice", "Bob");
        Transaction transactionC = new Transaction(2L, 5.0, "Alice", "Bob");
        Transaction transactionD = new Transaction(1L, 5.0, "Charly", "Bob");
        Transaction transactionE = new Transaction(1L, 5.0, "Alice", "Charly");
        Transaction transactionF = new Transaction(1L, 6.0, "Alice", "Charly");

        assertEquals(transactionA, transactionA);
        assertEquals(transactionA, transactionB);
        assertNotEquals(transactionA, transactionC);
        assertNotEquals(transactionA, transactionD);
        assertNotEquals(transactionA, transactionE);
        assertNotEquals(transactionA, transactionF);

        assertNotEquals(transactionA, new Object());
    }


    @Test
    void testTransactionHashCode() {
        Transaction transactionA = new Transaction(1L, 5.5, "Alice", "Bob");
        Transaction transactionB = new Transaction(1L, 5.5, "Alice", "Bob");

        assertEquals(transactionA.hashCode(), transactionB.hashCode());
    }
}
