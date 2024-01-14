package com.example.loans.repository;

import com.example.loans.model.Loan;
import org.springframework.data.repository.ListCrudRepository;

public interface LoanRepository extends ListCrudRepository<Loan,Long> {
}
