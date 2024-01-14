package com.example.loans.controller;

import com.example.loans.dto.LoanDto;
import com.example.loans.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('SCOPE_user.read')")
    public List<LoanDto> listAllLoans() {
        return loanService.listAllLoans();
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('SCOPE_user.write')")
    public String applyLoan(@RequestBody LoanDto loanDto) {
        return loanService.applyLoan(loanDto);
    }
}
