package in.guesp.blockchain.service;

import in.guesp.blockchain.dto.BlockDTO;
import in.guesp.blockchain.dto.TransactionDTO;
import in.guesp.blockchain.exception.ImpossibleToMine;
import in.guesp.blockchain.exception.IncorrectPreviousHash;
import in.guesp.blockchain.model.Blockchain;
import in.guesp.blockchain.payload.TransactionPayload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockchainService {


    private final Blockchain blockchain;

    BlockchainService() {
        this.blockchain = new Blockchain();
    }


    public BlockDTO getCurrentBlock() throws IncorrectPreviousHash {
        return new BlockDTO(blockchain.getCurrentBlock());
    }

    public Blockchain getBlockchain() {
        return blockchain;
    }

    public List<BlockDTO> getBlocks() {
        return blockchain.getBlocks().stream().map(BlockDTO::new).toList();
    }

    public TransactionDTO addTransaction(TransactionPayload transactionPayload) throws IncorrectPreviousHash {
        return new TransactionDTO(blockchain.addTransaction(transactionPayload.amount, transactionPayload.from, transactionPayload.to));
    }

    public BlockDTO mine() throws IncorrectPreviousHash, ImpossibleToMine {
        return new BlockDTO(blockchain.mine());
    }
}
