package com.istratenkovma.broker.binding;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TransactionsEventBinding {

  String TRANSACTIONS_INPUT = "transactionsInput";

  @Output(TRANSACTIONS_INPUT)
  MessageChannel output();
}
