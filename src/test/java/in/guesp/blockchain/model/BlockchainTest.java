package in.guesp.blockchain.model;

import in.guesp.blockchain.exception.ImpossibleToMine;
import in.guesp.blockchain.exception.IncorrectPreviousHash;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlockchainTest {


    @Test
    void testGetCurrentBlock() throws IncorrectPreviousHash {
        Blockchain blockChain = new Blockchain();
        Block block = blockChain.getCurrentBlock();
        assertNotNull(block);
        assertEquals(block.getId(), 0);
    }

    @Test
    void testAddBlockchainTransaction() throws IncorrectPreviousHash {
        Blockchain blockchain = new Blockchain();
        Block block = blockchain.getCurrentBlock();

        blockchain.addTransaction(5, "Alice", "Bob");

        assertFalse(block.getTransactions().isEmpty());
    }

    @Test
    void testMining() throws IncorrectPreviousHash, ImpossibleToMine {
        Blockchain blockchain = new Blockchain();
        assertThrows(ImpossibleToMine.class, blockchain::mine);

        blockchain.addTransaction(5.0, "Alice", "Bob");

        Block block = blockchain.mine();
        assertTrue(block.isValid());

        Block nextBlock = blockchain.getCurrentBlock();
        assertNotEquals(block, nextBlock);
        assertEquals(1, nextBlock.getId());
        assertEquals(nextBlock.getPreviousHash(), block.getHash());
    }

    @Test
    public void testDifferentBlockchainSameHash() throws IncorrectPreviousHash, ImpossibleToMine {
        Blockchain blockchainA = new Blockchain();
        Blockchain blockchainB = new Blockchain();
        Blockchain blockchainC = new Blockchain();

        blockchainA.addTransaction(5.0, "Alice", "Bob");
        blockchainB.addTransaction(5.0, "Alice", "Bob");
        blockchainC.addTransaction(10.0, "Alice", "Bob");

        Block blockA = blockchainA.mine();
        Block blockB = blockchainB.mine();
        Block blockC = blockchainC.mine();

        assertEquals(blockA, blockB);
        assertNotEquals(blockA, blockC);
    }

    @Test
    public void testGetBlockchainBlock() throws IncorrectPreviousHash, ImpossibleToMine {
        Blockchain blockchain = new Blockchain();
        assertTrue(blockchain.getBlocks().isEmpty());

        blockchain.addTransaction(5,"Alice", "Bob");

        assertTrue(blockchain.getBlocks().isEmpty());

        ArrayList<Block> blocks = new ArrayList<>();
        blocks.add(blockchain.mine());
        assertEquals(blockchain.getBlocks(), blocks);


        blockchain.addTransaction(10,"Bob", "Alice");
        blocks.add(blockchain.mine());

        assertEquals(blockchain.getBlocks(), blocks);
    }


}
