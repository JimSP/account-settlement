package com.jimsp.accountsettlement.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jimsp.accountsettlement.canonico.AccountSettlementCanonico;
import com.jimsp.accountsettlement.canonico.AccountSettlementMarker;
import com.jimsp.accountsettlement.canonico.SummaryAccountSettlementCanonico;
import com.jimsp.accountsettlement.converter.AccountSettlementCanonicoToAccountSettlementDto;
import com.jimsp.accountsettlement.converter.AccountSettlementDtoToAccountSettlementCanonico;
import com.jimsp.accountsettlement.converter.SummaryAccountSettlementCanonicoToSummaryAccountSettlementDto;
import com.jimsp.accountsettlement.dto.AccountIdDto;
import com.jimsp.accountsettlement.dto.AccountSettlementDto;
import com.jimsp.accountsettlement.dto.SummaryAccountSettlementDto;
import com.jimsp.accountsettlement.remote.AccountSettlementRemote;

@RestController
public class AccountSettlementController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountSettlementController.class);

	@Autowired
	private AccountSettlementRemote accountSettlementRemote;

	@Autowired
	private AccountSettlementDtoToAccountSettlementCanonico accountDtoToAccountCanonico;

	@Autowired
	private AccountSettlementCanonicoToAccountSettlementDto accountCanonicoToAccountDto;
	
	@Autowired
	private SummaryAccountSettlementCanonicoToSummaryAccountSettlementDto summaryAccountSettlementCanonicoToSummaryAccountSettlementDto;

	@PostMapping("/lancamentos-contabeis")
	@ResponseBody
	public ResponseEntity<AccountIdDto> createAccount(@Valid @RequestBody final AccountSettlementDto accountSettlementDto) {
		
		LOGGER.debug(AccountSettlementMarker.SERVICE_REST, "m=createAccount, endpoint=\"/lancamentos-contabeis\" accountSettlementDto={}", accountSettlementDto);
		
		final AccountSettlementCanonico accountCanonico = accountDtoToAccountCanonico.convert(accountSettlementDto);
		final UUID accountId = accountSettlementRemote.createAccount(accountCanonico);
		return ResponseEntity.status(201).body(new AccountIdDto(accountId));
	}

	@GetMapping("/lancamentos-contabeis/{id}")
	@ResponseBody
	public AccountSettlementDto getById(@PathVariable(name = "id", required = true) final String id) {
		
		LOGGER.debug(AccountSettlementMarker.SERVICE_REST, "m=getById, endpoint=\"/lancamentos-contabeis/{id}\" id={}", id);
		
		final AccountSettlementCanonico accountCanonico = accountSettlementRemote.getAccountById(UUID.fromString(id));
		return accountCanonicoToAccountDto.convert(accountCanonico);
	}

	@GetMapping("/lancamentos-contabeis")
	@ResponseBody
	public List<AccountSettlementDto> getByContaContabil(
			@RequestParam(name = "contaContabil", required = true) final Long contaContabil) {
		
		LOGGER.debug(AccountSettlementMarker.SERVICE_REST, "m=getByContaContabil, endpoint=\"/lancamentos-contabeis\" contaContabil={}", contaContabil);
		
		Assert.notNull(contaContabil, "expected contaContabil not null.");
		return accountSettlementRemote.getAccountByContaContabil(contaContabil) //
				.stream() //
				.map(accountCanonico -> accountCanonicoToAccountDto.convert(accountCanonico)) //
				.collect(Collectors.toList());
	}
	
	@GetMapping("/lancamentos-contabeis/_stats")
	@ResponseBody
	public SummaryAccountSettlementDto summary(
			@RequestParam(name = "contaContabil", required = false) final Long contaContabil) {
		
		LOGGER.debug(AccountSettlementMarker.SERVICE_REST, "m=getByContaContabil, endpoint=\"/lancamentos-contabeis/_stats\" contaContabil={}", contaContabil);
		
		final SummaryAccountSettlementCanonico summaryAccountSettlementCanonico = accountSettlementRemote.summary(contaContabil);
		return summaryAccountSettlementCanonicoToSummaryAccountSettlementDto.convert(summaryAccountSettlementCanonico);
	}
}
