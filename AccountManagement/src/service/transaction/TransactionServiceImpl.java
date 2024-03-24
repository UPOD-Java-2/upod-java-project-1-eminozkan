package service.transaction;

import transaction.Transaction;
import user.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {
    String path = "transactions/";

    @Override
    public void transaction(Transaction t, User user) {
        String filePath = user.getTckn() + ".txt";
        String line = t.getDescription() + "_" + t.getTransactionDate() + "_" + t.getAmount();
        writeToFile(filePath, line);
    }

    @Override
    public List<Transaction> getTransactions(String tckn) {
        String filePath = tckn + ".txt";
        return readFromFile(filePath);
    }

    @Override
    public BigDecimal calculateBalance(String tckn) {
        String filePath = tckn + ".txt";
        BigDecimal balance = new BigDecimal(0);
        for (Transaction t : readFromFile(filePath)){
            balance = balance.add(t.getAmount());
        }
        return balance;
    }

    private void writeToFile(String filePath, String line) {
        try {
            FileWriter writer = new FileWriter(path + filePath, true);
            writer.write(line + "\n");
            writer.close();
            System.out.println("Transaction done.");
        } catch (IOException e) {
            System.err.println("An error accured. ");
            e.printStackTrace();
        }
    }

    private List<Transaction> readFromFile(String filePath) {
        List<String> lines = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(path + filePath);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Transaction> transactions = new ArrayList<>();
        for (String s : lines) {
            String[] line = s.split("_");
            Transaction t = new Transaction(line[0], LocalDate.parse(line[1]),new BigDecimal(line[2]));
            transactions.add(t);
        }
        return transactions;
    }
}
