package com.istratenkovma;

import com.istratenkovma.broker.binding.TransactionValidationResultEventBinding;
import com.istratenkovma.broker.binding.TransactionsEventBinding;
import com.istratenkovma.model.dto.TransactionDto;
import com.istratenkovma.model.dto.TransactionVerificationResult;
import com.istratenkovma.service.verification.VerifyTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.support.MessageBuilder;

//@Configuration
//@Profile("test")
public class MockConfiguration {
//
//  @Bean
//  public TransactionsPublisher transactionsPublisher() {
//    return new TransactionsPublisher();
//  }
//
//  @Bean
//  public TransactionVerificationResultListener transactionVerificationResultListener() {
//    return new TransactionVerificationResultListener();
//  }
}

//
//@Profile("test")
//@EnableBinding(TransactionsEventBinding.class)
//class TransactionsPublisher {
//
//  @Autowired
//  private TransactionsEventBinding transactionsEventBinding;
//
//  public void publishMessage(TransactionDto transactionDto) {
//    transactionsEventBinding.output().send(MessageBuilder.withPayload(transactionDto).build());
//  }
//}
//
//@Slf4j
//@Profile("test")
//@EnableBinding(TransactionValidationResultEventBinding.class)
//class TransactionVerificationResultListener {
//
//  @StreamListener(target = TransactionValidationResultEventBinding.TRANSACTION_VALIDATION_RESULT_OUTPUT)
//  public void handleResult(TransactionVerificationResult result) {
//    log.debug("Verification result handled {}", result);
////    verifyTransactionService.verifyTransaction(new TransactionDto());
//  }
//}