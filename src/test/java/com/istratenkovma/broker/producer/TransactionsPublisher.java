package com.istratenkovma.broker.producer;

import com.istratenkovma.broker.binding.TransactionsEventBinding;
import com.istratenkovma.model.dto.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.support.MessageBuilder;

@Profile("test")
@EnableBinding(TransactionsEventBinding.class)
public class TransactionsPublisher {

  @Autowired
  private TransactionsEventBinding transactionsEventBinding;

  public void publishMessage(TransactionDto transactionDto) {
    transactionsEventBinding.output().send(MessageBuilder.withPayload(transactionDto).build());
  }
}