package in.guesp.blockchain.dto;

import in.guesp.blockchain.model.Block;
import in.guesp.blockchain.model.Transaction;

import java.util.ArrayList;

public class BlockDTO {

    public final ArrayList<Transaction> transactions;

    public final int nonce;

    public final int id;

    public final String previousHash;

    public final int blockDifficulty;

    public final String hash;

    public final boolean isValid;


    public BlockDTO(Block block) {
        this.id = block.getId();
        this.transactions = block.getTransactions();
        this.previousHash = block.getPreviousHash();
        this.blockDifficulty = block.getBlockDifficulty();
        this.nonce = block.getNonce();
        this.hash = block.getHash();
        this.isValid = block.isValid();
    }


    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof BlockDTO blockDTO)) {
            return false;
        }
        boolean previousHashEquals;
        if(previousHash != null) {
            previousHashEquals = previousHash.equals(blockDTO.previousHash);
        } else {
            previousHashEquals = blockDTO.previousHash == null;
        }


        return id == blockDTO.id
                && transactions.equals(blockDTO.transactions)
                && blockDifficulty == blockDTO.blockDifficulty
                && nonce == blockDTO.nonce
                && hash.equals(blockDTO.hash)
                && isValid == blockDTO.isValid
                && previousHashEquals;

    }
}
