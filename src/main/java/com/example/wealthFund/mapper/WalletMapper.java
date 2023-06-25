package com.example.wealthFund.mapper;

import com.example.wealthFund.dto.WalletDto;
import com.example.wealthFund.repository.entity.WalletEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WalletMapper {

    WalletDto walletToWalletDto(WalletEntity wallet);
    WalletEntity walletDtoToWallet(WalletDto walletDto);
}
