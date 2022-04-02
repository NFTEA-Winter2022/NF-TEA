package ca.mcgill.ecse428.nftea.service;

import ca.mcgill.ecse428.nftea.dao.ListingRepository;
import ca.mcgill.ecse428.nftea.dao.TransactionRepository;
import ca.mcgill.ecse428.nftea.dao.UserAccountRepository;
import ca.mcgill.ecse428.nftea.exception.WrongInputException;
import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.Transaction;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserAccountRepository userAccountRepository;
    @Autowired
    ListingRepository listingRepository;
    @Autowired
    ListingService listingService;

    @Transactional
    public Transaction createTransaction(Long buyerId, Long listingId) throws IllegalArgumentException{
        String error = "";
        if(buyerId == null) {
            error += ("Buyer id can't be null");
        } else if(listingId == null) {
            error += ("Listing id can't be null");
        } else if(userAccountRepository.findUserAccountById(buyerId) == null) {
            error += ("User account not found");
        } else if(listingRepository.findListingByListingID(listingId) == null) {
            error += ("Listing not found");
        }
        error.trim();
        if(error.length() > 0) {
            throw new WrongInputException(HttpStatus.BAD_REQUEST, error);
        }

        UserAccount buyer = userAccountRepository.findUserAccountById(buyerId);
        Listing listing = listingRepository.findListingByListingID(listingId);
        Transaction transaction = new Transaction(buyer, listing.getOwner(), listing);
        transactionRepository.save(transaction);
        listingService.deleteListing(listingId);

        return transaction;
    }

    @Transactional
    public List<Transaction> getTransactionsByBuyer(Long buyerId) throws IllegalArgumentException{
        String error = "";
        if(buyerId == null) {
            error += ("Buyer id can't be null");
        } else if(userAccountRepository.findUserAccountById(buyerId) == null) {
            error += ("User account not found");
        }
        error.trim();
        if(error.length() > 0) {
            throw new WrongInputException(HttpStatus.BAD_REQUEST, error);
        }
        UserAccount buyer = userAccountRepository.findUserAccountById(buyerId);
        List<Transaction> transactions = transactionRepository.findTransactionsByBuyer(buyer);
        return  transactions;
    }

    @Transactional
    public List<Transaction> getTransactionsBySeller(Long sellerId) throws IllegalArgumentException{
        String error = "";
        if(sellerId == null) {
            error += ("Seller id can't be null");
        } else if(userAccountRepository.findUserAccountById(sellerId) == null) {
            error += ("User account not found");
        }
        error.trim();
        if(error.length() > 0) {
            throw new WrongInputException(HttpStatus.BAD_REQUEST, error);
        }
        UserAccount seller = userAccountRepository.findUserAccountById(sellerId);
        List<Transaction> transactions = transactionRepository.findTransactionsBySeller(seller);
        return  transactions;
    }

    @Transactional
    public Transaction getTransactionsById(Long id) throws IllegalArgumentException{
        String error = "";
        if(id == null) {
            error += ("Transaction id can't be null");
        } else if(transactionRepository.findTransactionById(id) == null) {
            error += ("Transaction not found");
        }
        error.trim();
        if(error.length() > 0) {
            throw new WrongInputException(HttpStatus.BAD_REQUEST, error);
        }
        Transaction transaction = transactionRepository.findTransactionById(id);
        return  transaction;
    }

    @Transactional
    public List<Transaction> getTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        for(Transaction t : transactionRepository.findAll()) {
            transactions.add(t);
        }
        return transactions;
    }

}
