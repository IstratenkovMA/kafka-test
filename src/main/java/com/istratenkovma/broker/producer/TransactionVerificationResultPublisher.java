package com.istratenkovma.broker.producer;

import com.istratenkovma.broker.binding.TransactionValidationResultEventBinding;
import com.istratenkovma.broker.binding.TransactionsEventBinding;
import com.istratenkovma.model.dto.TransactionDto;
import com.istratenkovma.model.dto.TransactionVerificationResult;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@EnableBinding(TransactionValidationResultEventBinding.class)
public class TransactionVerificationResultPublisher {

  @Autowired
  private TransactionValidationResultEventBinding transactionValidationResultEventBinding;

  public void sendVerificationResult(TransactionVerificationResult verificationResult) {
    transactionValidationResultEventBinding.output()
        .send(MessageBuilder.withPayload(verificationResult).build());
    log.info("transaction verification result was published {}", verificationResult);
  }
}