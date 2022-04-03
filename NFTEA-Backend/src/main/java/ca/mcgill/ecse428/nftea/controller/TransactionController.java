package ca.mcgill.ecse428.nftea.controller;

import ca.mcgill.ecse428.nftea.dto.TransactionDto;
import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.Transaction;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import ca.mcgill.ecse428.nftea.service.TransactionService;
import ca.mcgill.ecse428.nftea.utils.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static ca.mcgill.ecse428.nftea.utils.DtoUtils.convertToDto;

@CrossOrigin(origins = "*")
@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping(value = {"/market/createTransaction", "/market/createTransaction/"})
    public ResponseEntity createTransaction(
            @RequestParam Long buyerId,
            @RequestParam Long listingId
    ) {
        Transaction transaction = null;
        try {
            transaction = transactionService.createTransaction(buyerId, listingId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(convertToDto(transaction), HttpStatus.OK);
    }

    @GetMapping(value = {"/market/getByBuyer", "/market/getByBuyer/"})
    public ResponseEntity getTransactionsByBuyer(
            @RequestParam Long buyerId
    ) {
        List<Transaction> transactions = new ArrayList<>();
        List<TransactionDto> transactionDtos = new ArrayList<>();
        try {
            transactions = transactionService.getTransactionsByBuyer(buyerId);
            for(Transaction t : transactions) {
                TransactionDto transactionDto = convertToDto(t);
                transactionDtos.add(transactionDto);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(transactionDtos, HttpStatus.OK);
    }

    @GetMapping(value = {"/market/getBySeller", "/market/getBySeller/"})
    public ResponseEntity getTransactionsBySeller(
            @RequestParam Long sellerId
    ) {
        List<Transaction> transactions = new ArrayList<>();
        List<TransactionDto> transactionDtos = new ArrayList<>();
        try {
            transactions = transactionService.getTransactionsBySeller(sellerId);
            for(Transaction t : transactions) {
                TransactionDto transactionDto = convertToDto(t);
                transactionDtos.add(transactionDto);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(transactionDtos, HttpStatus.OK);
    }

    @PostMapping(value = {"/market/getTransactionById", "/market/getTransactionById/"})
    public ResponseEntity getTransactionById(
            @RequestParam Long transactionId
    ) {
        Transaction transaction = null;
        try {
            transaction = transactionService.getTransactionsById(transactionId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(convertToDto(transaction), HttpStatus.OK);
    }

}
