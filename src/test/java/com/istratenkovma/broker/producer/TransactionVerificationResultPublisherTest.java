package com.istratenkovma.broker.producer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

import com.istratenkovma.MockConfiguration;
import com.istratenkovma.broker.binding.TransactionValidationResultEventBinding;
import com.istratenkovma.broker.consumer.TransactionVerificationResultListener;
import com.istratenkovma.model.dto.TransactionDto;
import com.istratenkovma.model.dto.TransactionVerificationResult;
import com.istratenkovma.service.verification.VerificationResultService;
import com.istratenkovma.service.verification.VerifyTransactionService;
import java.util.Set;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092",
    "port=9092"})
@Import(TransactionVerificationResultListener.class)
@ActiveProfiles("test")
public class TransactionVerificationResultPublisherTest {

  @Autowired
  private TransactionVerificationResultPublisher transactionVerificationResultPublisher;
  @Autowired
  private TransactionVerificationResultListener transactionVerificationResultListener;
  @MockBean
  private VerificationResultService verificationResultService;

  @Test
  @SneakyThrows
  public void sendVerificationResult() {
    TransactionVerificationResult verificationResult = new TransactionVerificationResult(true, 1L);
    transactionVerificationResultPublisher.sendVerificationResult(verificationResult);
    ArgumentCaptor<TransactionVerificationResult> argumentCaptor = ArgumentCaptor.forClass(TransactionVerificationResult.class);
    verify(verificationResultService).notEmptyResult(argumentCaptor.capture());
    TransactionVerificationResult value = argumentCaptor.getValue();
    assertNotNull(value);
  }
}