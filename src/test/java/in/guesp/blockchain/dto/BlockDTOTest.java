package in.guesp.blockchain.dto;

import in.guesp.blockchain.exception.IncorrectPreviousHash;
import in.guesp.blockchain.model.Block;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BlockDTOTest {

    @Test
    public void testConvertToDto() throws IncorrectPreviousHash {
        Block block = new Block(1, 2, "ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad");
        BlockDTO blockDTO = new BlockDTO(block);

        assertEquals(block.getId(), blockDTO.id);
        assertEquals(block.getHash(), blockDTO.hash);
        assertEquals(block.getTransactions(), blockDTO.transactions);
        assertEquals(block.getNonce(), blockDTO.nonce);
        assertEquals(block.getPreviousHash(), blockDTO.previousHash);
        assertEquals(block.getBlockDifficulty(), blockDTO.blockDifficulty);
        assertEquals(block.isValid(), blockDTO.isValid);
    }

    @Test
    public void testBlockDTOEquals() throws IncorrectPreviousHash {
        Block blockA = new Block(1, 2, "ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad");
        Block blockB = new Block(1, 2, null);
        assertEquals(new BlockDTO(blockA), new BlockDTO(blockA));
        assertEquals(new BlockDTO(blockB), new BlockDTO(blockB));
        assertNotEquals(new BlockDTO(blockA), new BlockDTO(blockB));

        assertNotEquals(new BlockDTO(blockA), new Object());
    }

}
