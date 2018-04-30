package com.jimsp.accountsettlement.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jimsp.accountsettlement.canonico.AccountSettlementCanonico;
import com.jimsp.accountsettlement.canonico.AccountSettlementMarker;
import com.jimsp.accountsettlement.canonico.SummaryAccountSettlementCanonico;
import com.jimsp.accountsettlement.converter.AccountSettlementDataToAccountSettlementCanonico;
import com.jimsp.accountsettlement.exceptions.AccountSettlementNotFoundException;
import com.jimsp.accountsettlement.factory.CreateAccountSettlementData;
import com.jimsp.accountsettlement.model.AccountSettlementData;
import com.jimsp.accountsettlement.remote.AccountSettlementRemote;
import com.jimsp.accountsettlement.repository.AccountSettlementJpaRepository;
import com.jimsp.accountsettlement.rules.CreateAccountID;
import com.jimsp.accountsettlement.rules.CreateSummaryAccountSettlementCanonico;

@Service
public class AccountSettlementService implements AccountSettlementRemote{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountSettlementService.class);

	@Autowired
	private CreateAccountID createAccountID;

	@Autowired
	private CreateAccountSettlementData createAccountSettlementData;

	@Autowired
	private AccountSettlementDataToAccountSettlementCanonico accountSettlementDataToAccountSettlementCanonico;

	@Autowired
	private AccountSettlementJpaRepository accountSettlementJpaRepository;
	
	@Autowired
	private CreateSummaryAccountSettlementCanonico createSummaryAccountSettlementCanonico;

	@Transactional(propagation = Propagation.REQUIRED)
	public UUID createAccount(final AccountSettlementCanonico accountSettlementCanonico) {
		
		LOGGER.debug(AccountSettlementMarker.SERVICE_CORE, "m=createAccount, accountSettlementCanonico={}", accountSettlementCanonico);
		
		final UUID id = createAccountID.create(accountSettlementCanonico);
		final AccountSettlementData accountSettlementData = createAccountSettlementData.create(id,
				accountSettlementCanonico);
		accountSettlementJpaRepository.save(accountSettlementData);
		return id;
	}

	public AccountSettlementCanonico getAccountById(final UUID id) {
		
		LOGGER.debug(AccountSettlementMarker.SERVICE_CORE, "m=getAccountById, id={}", id);
		
		final AccountSettlementData accountSettlementData = accountSettlementJpaRepository.findById(id)
				.orElseThrow(() -> new AccountSettlementNotFoundException(id));
		return convertDataToCanonico(accountSettlementData);
	}

	public List<AccountSettlementCanonico> getAccountByContaContabil(final Long contaContabil) {
		
		LOGGER.debug(AccountSettlementMarker.SERVICE_CORE, "m=getAccountByContaContabil, contaContabil={}", contaContabil);
		
		return accountSettlementJpaRepository //
				.findByContaContabilOrderByIdDesc(contaContabil) //
				.stream() //
				.map(accountSettlementData -> convertDataToCanonico(accountSettlementData)) //
				.collect(Collectors.toList());
	}

	public SummaryAccountSettlementCanonico summary(final Long contaContabil) {
		
		LOGGER.debug(AccountSettlementMarker.SERVICE_CORE, "m=summary, contaContabil={}", contaContabil);
		
		if (contaContabil != null) {
			final List<AccountSettlementData> list = accountSettlementJpaRepository
					.findByContaContabilOrderByIdDesc(contaContabil);
			return createSummaryAccountSettlementCanonico.create(list);
		} else {
			final List<AccountSettlementData> list = accountSettlementJpaRepository.findAll();
			return createSummaryAccountSettlementCanonico.create(list);
		}
	}

	private AccountSettlementCanonico convertDataToCanonico(final AccountSettlementData accountSettlementData) {
		
		LOGGER.debug(AccountSettlementMarker.SERVICE_CORE, "m=summary, convertDataToCanonico={}", accountSettlementData);
		
		return accountSettlementDataToAccountSettlementCanonico
				.accountSettlementCanonicoToAccountSettlementData(accountSettlementData);
	}
}
