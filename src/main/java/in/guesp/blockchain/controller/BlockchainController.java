package in.guesp.blockchain.controller;

import in.guesp.blockchain.dto.BlockDTO;
import in.guesp.blockchain.dto.TransactionDTO;
import in.guesp.blockchain.exception.ImpossibleToMine;
import in.guesp.blockchain.exception.IncorrectPreviousHash;
import in.guesp.blockchain.payload.TransactionPayload;
import in.guesp.blockchain.service.BlockchainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blockchain")
public class BlockchainController {


    private final BlockchainService blockchainService;

    BlockchainController(BlockchainService blockchainService) {
        this.blockchainService = blockchainService;
    }


    @GetMapping("/")
    public ResponseEntity<List<BlockDTO>> getBlocks() {
        return ResponseEntity.ok(blockchainService.getBlocks());
    }


    @GetMapping("/current")
    public ResponseEntity<BlockDTO> getCurrentBlock() throws IncorrectPreviousHash {
        return ResponseEntity.ok(blockchainService.getCurrentBlock());
    }


    @GetMapping("/mine")
    public ResponseEntity<BlockDTO> mine() throws IncorrectPreviousHash, ImpossibleToMine {
        return ResponseEntity.ok(blockchainService.mine());
    }

    @PostMapping("/transaction")
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody TransactionPayload transactionPayload) throws IncorrectPreviousHash {
        return ResponseEntity.ok(blockchainService.addTransaction(transactionPayload));
    }





}
