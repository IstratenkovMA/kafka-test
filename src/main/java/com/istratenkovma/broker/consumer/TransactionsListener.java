package com.istratenkovma.broker.consumer;

import com.istratenkovma.broker.binding.TransactionsEventBinding;
import com.istratenkovma.model.dto.TransactionDto;
import com.istratenkovma.service.verification.VerifyTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@Slf4j
@EnableBinding(TransactionsEventBinding.class)
public class TransactionsListener {

  private VerifyTransactionService verifyTransactionService;

  @Autowired
  public TransactionsListener(
      VerifyTransactionService verifyTransactionService) {
    this.verifyTransactionService = verifyTransactionService;
  }

  @StreamListener(target = TransactionsEventBinding.TRANSACTIONS_INPUT)
  public void handleMessage(TransactionDto transactionDto) throws Exception {
    log.debug("Kafka message was handled: {}", transactionDto.toString());
    verifyTransactionService.verifyTransaction(transactionDto);
  }
}
