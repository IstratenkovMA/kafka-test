package com.istratenkovma.service.verification;

import com.istratenkovma.model.dto.TransactionVerificationResult;
import org.springframework.stereotype.Service;

public interface VerificationResultService {
  Boolean notEmptyResult(TransactionVerificationResult transactionVerificationResult);
}
