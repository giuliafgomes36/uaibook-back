package com.backend.uaibook.service;

import com.backend.uaibook.entity.Book;
import com.backend.uaibook.entity.Employee;
import com.backend.uaibook.entity.Loan;
import com.backend.uaibook.entity.Users;
import com.backend.uaibook.repository.EmployeeRepository;
import com.backend.uaibook.repository.LoanRepository;
import com.backend.uaibook.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookService bookService;
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    public LoanService(LoanRepository loanRepository,
                       BookService bookService,
                       EmployeeRepository employeeRepository,
                       UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.bookService = bookService;
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
    }

    public Loan createLoan(Loan loan) throws Exception {
        LocalDate now = LocalDate.now();

        List<Loan> overdues = loanRepository.getLoanByUserAndDevolutionDateBeforeAndIsOpenIsTrue(loan.getUser(), now);
        if(overdues.size() > 0)
            throw new Exception("Overdue loans: " + overdues.size());

        Book book = bookService.getBookById(loan.getBook().getId());

        Users user = userRepository.findById(loan.getUser().getId())
                .orElseThrow(() -> new NullPointerException("User not found"));

        Employee employee = employeeRepository.findById(loan.getEmployee().getId())
                .orElseThrow(() -> new NullPointerException("Employee not found"));

        loan.setIsOpen(true);
        loan.setLoanDate(LocalDate.now());
        loan.setBook(book);
        loan.setUser(user);
        loan.setEmployee(employee);

        boolean devolutionDateIsNull = loan.getDevolutionDate() == null;
        if(devolutionDateIsNull || loan.getDevolutionDate().compareTo(LocalDate.now().plusMonths(1)) > 0)
            loan.setDevolutionDate(LocalDate.now().plusWeeks(2));

        return loanRepository.save(loan);
    }

    public List<Loan> getLoan(Boolean open) {
        if(open)
            return loanRepository.getLoanByIsOpenIsTrue();

        return loanRepository.findAll();
    }

    public Loan closeLoan(Long loanId) {
        Loan loan = loanRepository.getLoanById(loanId);

        if(loan == null)
            throw new NullPointerException("Empréstimo não encontrado");

        loan.setIsOpen(false);

        return loanRepository.save(loan);
    }

    public void deleteLoan(Long loanId) {
        Loan loan = loanRepository.getLoanById(loanId);

        if(loan == null)
            throw new NullPointerException("Empréstimo não encontrado");

        loanRepository.delete(loan);
    }
}
