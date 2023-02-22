package com.backend.uaibook.repository;

import com.backend.uaibook.entity.Loan;
import com.backend.uaibook.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    Loan getLoanById(Long id);

    List<Loan> getLoanByIsOpenIsTrue();

    List<Loan> getLoanByUserAndDevolutionDateBeforeAndIsOpenIsTrue(Users user, LocalDate date);
}
