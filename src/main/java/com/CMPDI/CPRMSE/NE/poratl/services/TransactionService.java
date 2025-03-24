package com.CMPDI.CPRMSE.NE.poratl.services;
/*
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.CMPDI.CPRMSE.NE.poratl.models.Transactions; import
 * com.CMPDI.CPRMSE.NE.poratl.repositories.TransactionsRepository;
 * 
 * import java.util.List;
 * 
 * @Service public class TransactionService {
 * 
 * @Autowired private TransactionsRepository transactionsRepository;
 * 
 * public List<Transactions> getTransactions(String employeeCode, String month)
 * { return transactionsRepository.findByEmployeeCodeAndMonth(employeeCode,
 * month); } }
 * 
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CMPDI.CPRMSE.NE.poratl.models.Transactions;
import com.CMPDI.CPRMSE.NE.poratl.repositories.TransactionsRepository;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    public List<Transactions> getTransactions(String employeecode, String month, String year) {
        if (month != null && !month.isEmpty() && year != null && !year.isEmpty()) {
            return transactionsRepository.findByEmployeeCodeAndOptionalMonthAndYear(employeecode, month, year);
        } else if (month != null && !month.isEmpty() && employeecode != null && !employeecode.isEmpty()) {
            return transactionsRepository.findByEmployeecodeAndMonth(employeecode, month);
        } else if (year != null && !year.isEmpty() && employeecode != null && !employeecode.isEmpty()) {
            return transactionsRepository.findByEmployeecodeAndYear(employeecode, year);
        } 
        else if ((employeecode == null || employeecode.isEmpty()) && year != null && !year.isEmpty() && month != null && !month.isEmpty()) {
        	return transactionsRepository.findByMonthAndYear(month, year);
        }
        else if ((employeecode == null || employeecode.isEmpty()) && (year == null || year.isEmpty()) && month != null && !month.isEmpty()) {
            return transactionsRepository.findByMonth(month);
        }
        else if ((employeecode == null || employeecode.isEmpty()) && (month == null || month.isEmpty()) && year != null && !year.isEmpty()) {
            return transactionsRepository.findByYear(year);
        }
        else {
            return transactionsRepository.findByEmployeecode(employeecode);
        }
    }

}
