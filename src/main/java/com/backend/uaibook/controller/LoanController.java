package com.backend.uaibook.controller;

import com.backend.uaibook.dto.Response;
import com.backend.uaibook.entity.Loan;
import com.backend.uaibook.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CrossOrigin("*")
@RestController
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/loan")
    public ResponseEntity<Response<Loan>> createLoan(@RequestBody @Valid Loan loan, BindingResult result) {
        Response<Loan> response = new Response<>();
        if(result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            response.setErrors(errors);

            return ResponseEntity.badRequest().body(response);
        }
        try {
            response.setData(loanService.createLoan(loan));
            return ResponseEntity.status(201).body(response);
        } catch (NullPointerException ne) {
            response.setData(null);
            response.setErrors(Collections.singletonList(ne.getMessage()));
            return  ResponseEntity.status(404).body(response);
        }
        catch (Exception e) {
            response.setData(null);
            response.setErrors(Collections.singletonList(e.getMessage()));
            return  ResponseEntity.status(400).body(response);
        }
    }

    @GetMapping("/loan")
    public List<Loan> getLoan(@RequestParam(required = false, defaultValue = "false") Boolean open) {
        return loanService.getLoan(open);
    }

    @PutMapping("/loan/{id}")
    public Loan closeLoan(@PathVariable(name = "id") Long id) {
        return loanService.closeLoan(id);
    }

    @DeleteMapping("/loan/{id}")
    public void deleteLoan(@PathVariable(name = "id") Long id) {
        loanService.deleteLoan(id);
    }
}
