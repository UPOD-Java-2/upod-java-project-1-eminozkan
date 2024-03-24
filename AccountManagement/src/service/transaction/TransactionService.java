package service.transaction;

import transaction.Transaction;
import user.User;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    void transaction(Transaction t, User user);

    List<Transaction> getTransactions(String tckn);

    BigDecimal calculateBalance(String tckn);
}
