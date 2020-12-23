package com.istratenkovma.service.verification.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.istratenkovma.broker.producer.TransactionVerificationResultPublisher;
import com.istratenkovma.model.dto.TransactionDto;
import com.istratenkovma.model.entity.TransactionEntity;
import com.istratenkovma.service.repository.TransactionRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VerifyTransactionServiceImplTest {

  @Mock
  private TransactionRepository transactionRepository;
  @Mock
  private TransactionVerificationResultPublisher transactionVerificationResultPublisher;

  @Test
  public void verifyTransactionWithSameIdAndAmountInDB() {
    Optional<TransactionEntity> transactionEntity = Optional
        .of(new TransactionEntity(1L, new BigDecimal("1.1"), "{test: dataCanBeDifferent}"));
    Mockito.when(transactionRepository.findById(any())).thenReturn(transactionEntity);
    VerifyTransactionServiceImpl verifyTransactionService = new VerifyTransactionServiceImpl(
        transactionRepository, transactionVerificationResultPublisher);
    TransactionDto transactionDto =
        new TransactionDto(1L, new BigDecimal("1.1"), "{data: TestData}");
    verifyTransactionService.verifyTransaction(transactionDto);
    verify(transactionVerificationResultPublisher, times(1)).sendVerificationResult(any());
  }

  @Test
  public void isVerifiedTransaction_withSuccessResultOfVerification() {
    Optional<TransactionEntity> transactionEntity = Optional
        .of(new TransactionEntity(2L, new BigDecimal("31.1"), "{test: dataCanBeDifferent}"));
    Mockito.when(transactionRepository.findById(any())).thenReturn(transactionEntity);
    VerifyTransactionServiceImpl verifyTransactionService = new VerifyTransactionServiceImpl(
        transactionRepository, transactionVerificationResultPublisher);
    TransactionDto transactionDto = new TransactionDto(2L, new BigDecimal("31.1"), "{name: test}");
    boolean result = verifyTransactionService.isVerifiedTransaction(transactionDto);
    verify(transactionRepository, times(1)).findById(2L);
    assertTrue(result);
  }

  @Test
  public void publishVerificationResultWithTransactionDTO() {
    VerifyTransactionServiceImpl verifyTransactionService = new VerifyTransactionServiceImpl(
        transactionRepository, transactionVerificationResultPublisher);
    TransactionDto transactionDto = new TransactionDto(2L, new BigDecimal("31.1"), "{name: test}");
    verifyTransactionService.publishVerificationResult(true, transactionDto);
    verify(transactionVerificationResultPublisher, times(1))
        .sendVerificationResult(any());
  }
}