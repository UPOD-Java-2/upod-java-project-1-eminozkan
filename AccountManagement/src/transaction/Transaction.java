package transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
    private String description;

    private LocalDate transactionDate;

    private BigDecimal amount;

    public Transaction(String description, LocalDate transactionDate, BigDecimal amount) {
        this.description = description;
        this.transactionDate = transactionDate;
        this.amount = amount;
    }

    public Transaction setDescription(String description) {
        this.description = description;
        return this;
    }

    public Transaction setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
        return this;
    }

    public Transaction setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
