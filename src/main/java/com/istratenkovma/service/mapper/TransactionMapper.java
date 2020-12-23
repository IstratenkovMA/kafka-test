package com.istratenkovma.service.mapper;

import com.istratenkovma.model.dto.TransactionDto;
import com.istratenkovma.model.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TransactionMapper {
  TransactionDto map(TransactionEntity from);
  TransactionEntity map(TransactionDto from);
}
