package com.istratenkovma.broker.consumer;

import com.istratenkovma.broker.binding.TransactionValidationResultEventBinding;
import com.istratenkovma.model.dto.TransactionDto;
import com.istratenkovma.model.dto.TransactionVerificationResult;
import com.istratenkovma.service.verification.VerificationResultService;
import com.istratenkovma.service.verification.VerifyTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Profile;

@Slf4j
@EnableBinding(TransactionValidationResultEventBinding.class)
@Profile("test")
public class TransactionVerificationResultListener {

  private VerificationResultService verificationResultService;

  @Autowired
  public TransactionVerificationResultListener(
      VerificationResultService verificationResultService) {
    this.verificationResultService = verificationResultService;
  }

  @StreamListener(target = TransactionValidationResultEventBinding.TRANSACTION_VALIDATION_RESULT_OUTPUT)
  public void handleResult(TransactionVerificationResult result) {
    log.debug("Verification result handled {}", result);
    verificationResultService.notEmptyResult(result);
  }
}