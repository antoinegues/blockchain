package in.guesp.blockchain.model;

import in.guesp.blockchain.exception.IncorrectPreviousHash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HexFormat;

public class Block {

    private final ArrayList<Transaction> transactions = new ArrayList<>();

    private int nonce;

    private final int id;

    private final String previousHash;

    private final int blockDifficulty;

    private MessageDigest md;

    public Block(int blockDifficulty ,int id, String previousHash) throws IncorrectPreviousHash {
        this.id = id;
        this.blockDifficulty = blockDifficulty;
        if(previousHash != null && previousHash.length() != 64) {
            throw new IncorrectPreviousHash();
        }

        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (Exception ignored) {

        }

        this.previousHash = previousHash;
    }

    public String getHash() {
        String data = String.valueOf(id) + nonce + previousHash + transactions;
        byte[] hash = md.digest(data.getBytes(StandardCharsets.UTF_8));
        return HexFormat.of().formatHex(hash);
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public Transaction newTransaction(double amount, String from, String to) {
        Transaction transaction = new Transaction(0L,amount, from, to);
        transactions.add(transaction);
        return transaction;
    }

    public void setNonce(int newNonce) {
        this.nonce = newNonce;
    }

    public int getId() {
        return id;
    }

    public boolean isValid() {
        if(transactions.size() == 0) {
            return false;
        }

        return getHash().substring(0, blockDifficulty).equals("0".repeat(blockDifficulty));
    }

    public String getPreviousHash() {
        return this.previousHash;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Block block)) {
            return false;
        }

        return getHash().equals(block.getHash()) && blockDifficulty == block.blockDifficulty;
    }

    public int getBlockDifficulty() {
        return this.blockDifficulty;
    }

    public int getNonce() {
        return nonce;
    }
}
