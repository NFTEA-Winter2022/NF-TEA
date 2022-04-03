package ca.mcgill.ecse428.nftea.dao;

import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.Transaction;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    Transaction findTransactionById(Long id);
    List<Transaction> findTransactionsByBuyer(UserAccount buyer);
    List<Transaction> findTransactionsBySeller(UserAccount seller);
}
