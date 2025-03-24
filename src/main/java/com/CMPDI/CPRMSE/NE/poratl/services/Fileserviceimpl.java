/*
 * package com.CMPDI.CPRMSE.NE.poratl.services;
 * 
 * import java.io.BufferedReader; import java.io.IOException; import
 * java.io.InputStream; import java.io.InputStreamReader; import
 * java.util.ArrayList; import java.util.List;
 * 
 * import org.apache.commons.csv.CSVFormat; import
 * org.apache.commons.csv.CSVParser; import org.apache.commons.csv.CSVRecord;
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service; import
 * org.springframework.web.multipart.MultipartFile;
 * 
 * import com.CMPDI.CPRMSE.NE.poratl.models.Transactions; import
 * com.CMPDI.CPRMSE.NE.poratl.repositories.TransactionsRepository;
 * 
 * @Service public class Fileserviceimpl implements Fileservice {
 * 
 * @Autowired private TransactionsRepository repo;
 * 
 * @Override public boolean hasCsvFormat(MultipartFile file) { String type =
 * "text/csv"; if (!type.equals(file.getContentType())) { return false; } return
 * true; }
 * 
 * @Override public void processAndSaveData(MultipartFile file) { try {
 * List<Transactions> transactions = csvToTransactions(file.getInputStream());
 * repo.saveAll(transactions); } catch (IOException e) { e.printStackTrace(); }
 * }
 * 
 * private List<Transactions> csvToTransactions(InputStream inputStream) {
 * List<Transactions> transactions = new ArrayList<>(); try (BufferedReader
 * fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
 * CSVParser csvParser = new CSVParser(fileReader,
 * CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim()
 * )) {
 * 
 * List<CSVRecord> records = csvParser.getRecords(); for (CSVRecord csvRecord :
 * records) { Transactions transaction = new Transactions(
 * csvRecord.get("employeecode"), csvRecord.get("year"), csvRecord.get("month"),
 * csvRecord.get("claimed"), csvRecord.get("passed"), csvRecord.get("remarks")
 * ); transactions.add(transaction); } } catch (IOException e) {
 * e.printStackTrace(); } return transactions; }
 * 
 * 
 * 
 * import java.io.BufferedReader; import java.io.IOException; import
 * java.io.InputStream; import java.io.InputStreamReader; import
 * java.util.ArrayList; import java.util.List;
 * 
 * import org.apache.commons.csv.CSVFormat; import
 * org.apache.commons.csv.CSVParser; import org.apache.commons.csv.CSVRecord;
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service; import
 * org.springframework.web.multipart.MultipartFile;
 * 
 * import com.CMPDI.CPRMSE.NE.poratl.models.Transactions; import
 * com.CMPDI.CPRMSE.NE.poratl.repositories.TransactionsRepository;
 * 
 * @Service public class Fileserviceimpl implements Fileservice {
 * 
 * @Autowired private TransactionsRepository repo;
 * 
 * @Override public boolean hasCsvFormat(MultipartFile file) { String type =
 * "text/csv"; if (!type.equals(file.getContentType())) { return false; } return
 * true; }
 * 
 * @Override public void ProcessAndSaveData(MultipartFile file) { try {
 * List<Transactions> transactions = csvToTransactions(file.getInputStream());
 * List<Transactions> newTransactions = new ArrayList<>();
 * 
 * for (Transactions transaction : transactions) { // Check if the transaction
 * already exists in the database using transaction_id if
 * (!repo.existsByTransaction_id(transactions.getTransaction_id())) {
 * newTransactions.add(transaction); } }
 * 
 * // Save only new transactions to the database repo.saveAll(newTransactions);
 * } catch (IOException e) { e.printStackTrace(); } }
 * 
 * private List<Transactions> csvToTransactions(InputStream inputStream) { try
 * (BufferedReader fileReader = new BufferedReader(new
 * InputStreamReader(inputStream, "UTF-8")); CSVParser csvParser = new
 * CSVParser(fileReader,
 * CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim()
 * )) { List<Transactions> transactions = new ArrayList<>(); List<CSVRecord>
 * records = csvParser.getRecords(); for (CSVRecord csvRecord : records) {
 * Transactions transaction = new Transactions( csvRecord.get("employeecode"),
 * csvRecord.get("year"), csvRecord.get("month"), csvRecord.get("claimed"),
 * csvRecord.get("passed"), csvRecord.get("remarks"),
 * csvRecord.get("transaction_id") // Adjusted to read transaction_id );
 * transactions.add(transaction); } return transactions; } catch (IOException e)
 * { e.printStackTrace(); }
 * 
 * return null; } }
 * 
 */




package com.CMPDI.CPRMSE.NE.poratl.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.CMPDI.CPRMSE.NE.poratl.models.Transactions;
import com.CMPDI.CPRMSE.NE.poratl.repositories.TransactionsRepository;

@Service
public class Fileserviceimpl implements Fileservice {
    
    @Autowired
    private TransactionsRepository repo;

    @Override
    public boolean hasCsvFormat(MultipartFile file) {
        String type = "text/csv";
        if (!type.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    @Override
    public void ProcessAndSaveData(MultipartFile file) {
        try {
            List<Transactions> transactions = csvToTransactions(file.getInputStream());
            repo.saveAll(transactions);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private List<Transactions> csvToTransactions(InputStream inputStream) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                    CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            // processing the CSV data
            List<Transactions> transactions = new ArrayList<>();
            List<CSVRecord> records = csvParser.getRecords();
            for (CSVRecord csvRecord : records) {
                Transactions transaction = new Transactions(
                    csvRecord.get("employeecode"),
                    csvRecord.get("year"),
                    csvRecord.get("month"),
                    csvRecord.get("claimed"),
                    csvRecord.get("passed"),
                    csvRecord.get("remarks")
                );
                transactions.add(transaction);    
            }
            return transactions;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
