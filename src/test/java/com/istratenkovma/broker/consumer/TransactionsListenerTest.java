package com.istratenkovma.broker.consumer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import com.istratenkovma.broker.producer.TransactionsPublisher;
import com.istratenkovma.model.dto.TransactionDto;
import com.istratenkovma.service.verification.VerifyTransactionService;
import java.math.BigDecimal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092",
    "port=9092"})
@ActiveProfiles("test")
@Import(TransactionsPublisher.class)
public class TransactionsListenerTest {

  @Autowired
  private TransactionsListener transactionsListener;
  @Autowired
  private TransactionsPublisher transactionsPublisher;
  @MockBean
  private VerifyTransactionService verifyTransactionService;

  @Test
  public void testIntegrationBetweenTransactionPublisherAndListener() {
    TransactionDto transactionDto = new TransactionDto(1L, new BigDecimal("123.2"), "{data: tata}");
    transactionsPublisher.publishMessage(transactionDto);
    ArgumentCaptor<TransactionDto> argumentCaptor = ArgumentCaptor.forClass(TransactionDto.class);
    verify(verifyTransactionService).verifyTransaction(argumentCaptor.capture());
    TransactionDto value = argumentCaptor.getValue();
    assertEquals(transactionDto.getId(), value.getId());
    assertEquals(transactionDto.getAmount(), value.getAmount());
    assertEquals(transactionDto.getData(), value.getData());
  }
}