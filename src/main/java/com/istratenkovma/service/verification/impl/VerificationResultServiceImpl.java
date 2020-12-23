package com.istratenkovma.service.verification.impl;

import com.istratenkovma.model.dto.TransactionVerificationResult;
import com.istratenkovma.service.verification.VerificationResultService;
import org.springframework.stereotype.Service;

@Service
public class VerificationResultServiceImpl implements VerificationResultService {

  @Override
  public Boolean notEmptyResult(TransactionVerificationResult transactionVerificationResult) {
    return transactionVerificationResult.getIsVerified() != null
        && transactionVerificationResult.getTransactionPid() != null;
  }
}
