package com.istratenkovma.broker.binding;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface TransactionValidationResultEventBinding {

  String TRANSACTION_VALIDATION_RESULT_OUTPUT = "transactionValidationResultOutput";

  @Output(TRANSACTION_VALIDATION_RESULT_OUTPUT)
  MessageChannel output();
}
