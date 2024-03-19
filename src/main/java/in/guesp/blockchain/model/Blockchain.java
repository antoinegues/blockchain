package in.guesp.blockchain.model;

import in.guesp.blockchain.exception.ImpossibleToMine;
import in.guesp.blockchain.exception.IncorrectPreviousHash;

import java.util.ArrayList;

public class Blockchain {


    private static final int BLOCKCHAIN_DIFFICULTY = 2;

    private Block currentBlock;

    private int currentIndex = 0;

    private final ArrayList<Block> blocks = new ArrayList<>();


    public Block getCurrentBlock() throws IncorrectPreviousHash {
        if (this.currentBlock == null) {
            this.currentBlock = new Block(BLOCKCHAIN_DIFFICULTY, currentIndex, null);
        }

        return currentBlock;
    }

    public Block mine() throws IncorrectPreviousHash, ImpossibleToMine {
        Block block = getCurrentBlock();
        if (block.getTransactions().isEmpty()) {
            throw new ImpossibleToMine();
        }

        int i = 0;
        do {
            block.setNonce(i);
            i++;
        } while (!block.isValid());
        currentIndex++;
        currentBlock = new Block(BLOCKCHAIN_DIFFICULTY, currentIndex, block.getHash());

        blocks.add(block);

        return block;
    }

    public Transaction addTransaction(double amount, String from, String to) throws IncorrectPreviousHash {
        return getCurrentBlock().newTransaction(amount, from, to);
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }
}
