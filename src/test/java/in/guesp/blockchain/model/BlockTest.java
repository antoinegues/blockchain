package in.guesp.blockchain.model;

import in.guesp.blockchain.exception.IncorrectPreviousHash;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class BlockTest {

    @Test
    void testBlockHash() throws IncorrectPreviousHash {
        Block block = new Block(0, 0, null);
        String hash = block.getHash();
        assertNotNull(hash);
        assertEquals(hash.length(), 64);
    }

    @Test
    void testTransactionAddedToTransactionsBlock() throws IncorrectPreviousHash {
        Block block = new Block(0, 1, null);
        Transaction transaction = block.newTransaction(5.0, "Alice", "Bob");

        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        assertEquals(block.getTransactions(), transactions);
    }

    @Test
    void testHashChangeOnNewTransaction() throws IncorrectPreviousHash {
        Block block = new Block(0, 1, null);
        String hashBefore = block.getHash();

        block.newTransaction(5.0, "Alice", "Bob");
        assertNotEquals(block.getHash(), hashBefore);
    }


    @Test
    void testHashChangeOnNonceChange() throws IncorrectPreviousHash {
        Block block = new Block(0, 1, null);

        String hashBefore = block.getHash();

        block.setNonce(5000);
        assertNotEquals(block.getHash(), hashBefore);
    }

    @Test
    void testHashChangeOnPreviousHashChange() throws IncorrectPreviousHash {
        assertNotEquals(
                new Block(0, 1, "ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad").getHash(),
                new Block(0, 1, "baea414140db00361a396177a9cb417816bf8f01cfe5dae22230ff61f20015ad").getHash());
    }

    @Test
    void testHashChangeOnIdDifferent() throws IncorrectPreviousHash {
        assertEquals(new Block(0, 1, null).getHash(), new Block(0, 1, null).getHash());
        assertNotEquals(new Block(0, 1, null).getHash(), new Block(0, 2, null).getHash());
    }

    @Test
    void testPreviousHashIsValid() {
        assertThrows(IncorrectPreviousHash.class, () -> {
            new Block(0, 1, "0");
        });
        assertDoesNotThrow(() -> {
            new Block(0, 1, "ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad");
        });
    }

    @Test
    void testBlockValidity() throws IncorrectPreviousHash {
        int blockDifficulty = 3;

        Block block = new Block(blockDifficulty, 2, null);
        assertFalse(block.isValid());
        Random random = new Random();
        block.newTransaction(random.nextDouble(), "Alice", "Bob");

        int i = 0;
        do {
            block.setNonce(i);
            i++;
        } while (!block.isValid());

        assertEquals(block.getHash().substring(0, blockDifficulty), "0".repeat(blockDifficulty));
    }

    @Test
    void testBlockSameHash() throws IncorrectPreviousHash {
        Block blockA = new Block(0, 0,"ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad");
        Block blockB = new Block(0, 0,"ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad");

        blockA.newTransaction(5, "Alice", "Bob");
        blockB.newTransaction(5, "Alice", "Bob");

        blockA.setNonce(3);
        blockB.setNonce(3);

        assertEquals(blockA.getHash(), blockB.getHash());
    }

    @Test
    void testBlockEquality() throws  IncorrectPreviousHash {
        Block blockA = new Block(0, 0,"ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad");
        Block blockB = new Block(0, 0,"ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad");
        Block blockC = new Block(1, 0,"ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad");
        Block blockD = new Block(0, 1,"ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad");
        Block blockE = new Block(0, 0,"ba7816bf8fm1cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad");
        Block blockF = new Block(0, 0,"ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad");
        Block blockG = new Block(0, 0,"ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad");
        Block blockH = new Block(0, 0,null);


        blockA.setNonce(3);
        blockB.setNonce(3);
        blockC.setNonce(3);
        blockD.setNonce(3);
        blockE.setNonce(3);
        blockF.setNonce(4);
        blockG.setNonce(3);
        blockH.setNonce(3);


        blockA.newTransaction(5, "Alice", "Bob");
        blockB.newTransaction(5, "Alice", "Bob");
        blockC.newTransaction(5, "Alice", "Bob");
        blockD.newTransaction(5, "Alice", "Bob");
        blockE.newTransaction(5, "Alice", "Bob");
        blockF.newTransaction(5, "Alice", "Bob");
        blockH.newTransaction(5, "Alice", "Bob");


        assertEquals(blockA, blockB);
        assertNotEquals(blockA, blockC);
        assertNotEquals(blockA, blockD);
        assertNotEquals(blockA, blockE);
        assertNotEquals(blockA, blockF);
        assertNotEquals(blockA, blockG);
        assertNotEquals(blockA, blockH);

        assertNotEquals(blockA, new Object());
    }
}
