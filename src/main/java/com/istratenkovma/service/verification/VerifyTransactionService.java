package com.istratenkovma.service.verification;

import com.istratenkovma.model.dto.TransactionDto;

public interface VerifyTransactionService {
  boolean isVerifiedTransaction(TransactionDto transactionDto);
  void publishVerificationResult(Boolean isVerified, TransactionDto transactionDto);
  void verifyTransaction(TransactionDto transactionDto);
}
