package in.guesp.blockchain.service;

import in.guesp.blockchain.dto.BlockDTO;
import in.guesp.blockchain.dto.TransactionDTO;
import in.guesp.blockchain.exception.ImpossibleToMine;
import in.guesp.blockchain.exception.IncorrectPreviousHash;
import in.guesp.blockchain.model.Block;
import in.guesp.blockchain.model.Blockchain;
import in.guesp.blockchain.payload.TransactionPayload;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BlockchainServiceTest {

    @Autowired
    private BlockchainService blockchainService;


    @Test
    public void testGetCurrentBlock() throws IncorrectPreviousHash {
        BlockDTO blockDTO = blockchainService.getCurrentBlock();

        Blockchain blockchain = blockchainService.getBlockchain();

        Block block = blockchain.getCurrentBlock();

        assertEquals(blockDTO, new BlockDTO(block));
    }

    @Test
    public void testGetBlocks() {
        List<BlockDTO> blocks = blockchainService.getBlocks();

        Blockchain blockchain = blockchainService.getBlockchain();

        assertEquals(blocks, blockchain.getBlocks().stream().map(BlockDTO::new).toList());
    }

    @Test
    public void testAddTransaction() throws IncorrectPreviousHash {
        TransactionPayload transactionPayload = new TransactionPayload();
        transactionPayload.amount = 5.0;
        transactionPayload.from = "Alice";
        transactionPayload.to = "Bob";

        TransactionDTO transactionDTO = blockchainService.addTransaction(transactionPayload);

        Block block = blockchainService.getBlockchain().getCurrentBlock();

        assertFalse(block.getTransactions().isEmpty());
        assertEquals(new TransactionDTO(block.getTransactions().get(0)), transactionDTO);

    }

    @Test
    public void testMine() throws IncorrectPreviousHash, ImpossibleToMine {

        assertThrows(ImpossibleToMine.class, () -> {
            blockchainService.mine();
        });

        TransactionPayload transactionPayload = new TransactionPayload();
        transactionPayload.amount = 5.0;
        transactionPayload.from = "Alice";
        transactionPayload.to = "Bob";

        blockchainService.addTransaction(transactionPayload);

        Block block = blockchainService.getBlockchain().getCurrentBlock();

        assertEquals(blockchainService.mine(), new BlockDTO(block));
    }



}
