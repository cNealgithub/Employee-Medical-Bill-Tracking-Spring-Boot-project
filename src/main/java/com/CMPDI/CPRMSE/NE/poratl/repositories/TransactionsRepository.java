package com.CMPDI.CPRMSE.NE.poratl.repositories;
/*
 * import java.util.List;
 * 
 * import org.springframework.data.jpa.repository.JpaRepository; import
 * org.springframework.data.jpa.repository.Query; import
 * org.springframework.data.repository.query.Param; import
 * org.springframework.stereotype.Repository;
 * 
 * import com.CMPDI.CPRMSE.NE.poratl.models.Transactions;
 * 
 * @Repository public interface TransactionsRepository extends
 * JpaRepository<Transactions, Long> {
 * 
 * public List<Transactions> findTransactionsByEmployeecode(String
 * employeecode);
 * 
 * 
 * 
 * @Query("SELECT t FROM Transactions t WHERE t.employeecode = :employeeCode AND t.month = :month AND t.year = :year"
 * ) public List<Transactions>
 * findByEmployeeCodeAndMonthAndYear(@Param("employeeCode") String
 * employeeCode, @Param("month") String month, @Param("year") String year);
 * 
 * 
 * @Query("SELECT t FROM Transactions t WHERE t.employeecode = :employeeCode" +
 * " AND (:month IS NULL OR t.month = :month)" +
 * " AND (:year IS NULL OR t.year = :year)") List<Transactions>
 * findByEmployeeCodeAndOptionalMonthAndYear(@Param("employeeCode") String
 * employeeCode,
 * 
 * @Param("month") String month, @Param("year") String year);
 * 
 * List<Transactions> findByEmployeecodeAndMonth(@Param("employeecode") String
 * employeecode,
 * 
 * @Param("month") String month);
 * 
 * List<Transactions> findByEmployeecodeAndYear(@Param("employeecode") String
 * employeecode,
 * 
 * @Param("year") String year);
 * 
 * List<Transactions> findByMonthAndYear(@Param("month") String month,
 * 
 * @Param("year") String year);
 * 
 * List<Transactions> findByEmployeecode(@Param("employeecode") String
 * employeecode);
 * 
 * List<Transactions> findByMonth(@Param("month") String month);
 * 
 * List<Transactions> findByYear(@Param("year") String year);
 * 
 * }
 */



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.CMPDI.CPRMSE.NE.poratl.models.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

	@Query("SELECT DISTINCT t FROM Transactions t WHERE t.employeecode = :employeecode")
    public List<Transactions> findTransactionsByEmployeecode(String employeecode);
    
    @Query("SELECT DISTINCT t FROM Transactions t WHERE t.employeecode = :employeeCode"
            + " AND t.month = :month" + " AND t.year = :year")
    List<Transactions> findByEmployeeCodeAndOptionalMonthAndYear(@Param("employeeCode") String employeeCode,
            @Param("month") String month, @Param("year") String year);
    @Query("SELECT DISTINCT t FROM Transactions t WHERE t.employeecode = :employeecode AND t.month = :month")
    List<Transactions> findByEmployeecodeAndMonth(@Param("employeecode") String employeecode,
            @Param("month") String month);

    @Query("SELECT DISTINCT t FROM Transactions t WHERE t.employeecode = :employeecode AND t.year = :year")
    List<Transactions> findByEmployeecodeAndYear(@Param("employeecode") String employeecode,
            @Param("year") String year);

    @Query("SELECT DISTINCT t FROM Transactions t WHERE t.month = :month AND t.year = :year")
    List<Transactions> findByMonthAndYear(@Param("month") String month,
            @Param("year") String year);

    @Query("SELECT DISTINCT t FROM Transactions t WHERE t.employeecode = :employeecode")
    List<Transactions> findByEmployeecode(@Param("employeecode") String employeecode);

    @Query("SELECT DISTINCT t FROM Transactions t WHERE t.month = :month")
    List<Transactions> findByMonth(@Param("month") String month);

    @Query("SELECT DISTINCT t FROM Transactions t WHERE t.year = :year")
    List<Transactions> findByYear(@Param("year") String year);

    
    
	/*
	 * List<Transactions> findByEmployeecodeAndMonth(@Param("employeecode") String
	 * employeecode,
	 * 
	 * @Param("month") String month);
	 * 
	 * List<Transactions> findByEmployeecodeAndYear(@Param("employeecode") String
	 * employeecode,
	 * 
	 * @Param("year") String year);
	 * 
	 * List<Transactions> findByMonthAndYear(@Param("month") String month,
	 * 
	 * @Param("year") String year);
	 * 
	 * List<Transactions> findByEmployeecode(@Param("employeecode") String
	 * employeecode);
	 * 
	 * List<Transactions> findByMonth(@Param("month") String month);
	 * 
	 * List<Transactions> findByYear(@Param("year") String year);
	 */
    
}

