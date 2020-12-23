package com.istratenkovma.service.verification.impl;

import com.istratenkovma.broker.producer.TransactionVerificationResultPublisher;
import com.istratenkovma.model.dto.TransactionDto;
import com.istratenkovma.model.dto.TransactionVerificationResult;
import com.istratenkovma.model.entity.TransactionEntity;
import com.istratenkovma.service.repository.TransactionRepository;
import com.istratenkovma.service.verification.VerifyTransactionService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class VerifyTransactionServiceImpl implements VerifyTransactionService {

  private TransactionRepository transactionRepository;
  private TransactionVerificationResultPublisher transactionVerificationResultPublisher;

  @Autowired
  public VerifyTransactionServiceImpl(
      TransactionRepository transactionRepository,
      TransactionVerificationResultPublisher transactionVerificationResultPublisher) {
    this.transactionRepository = transactionRepository;
    this.transactionVerificationResultPublisher = transactionVerificationResultPublisher;
  }

  @Override
  public void verifyTransaction(TransactionDto transactionDto) {
    log.debug("transaction verification started {}", transactionDto);
    boolean isVerified = isVerifiedTransaction(transactionDto);
    publishVerificationResult(isVerified, transactionDto);
  }

  @Override
  public boolean isVerifiedTransaction(TransactionDto transactionDto) {
    Optional<TransactionEntity> transactionEntity = transactionRepository.findById(transactionDto.getId());
    log.debug("try verify transaction broker {} ?= database entity", transactionDto);
    transactionEntity.ifPresent(entity -> log.debug("database entity {}", entity));
    return transactionEntity.isPresent() && transactionDto.getAmount().equals(transactionEntity.get().getAmount());
  }

  @Override
  public void publishVerificationResult(Boolean isVerified, TransactionDto transactionDto) {
    TransactionVerificationResult verificationResult = new TransactionVerificationResult(isVerified,
        transactionDto.getId());
    log.info("Transaction {} verification result {}", transactionDto, verificationResult);
    transactionVerificationResultPublisher.sendVerificationResult(
        verificationResult);
  }
}
