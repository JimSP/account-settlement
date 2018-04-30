package com.jimsp.accountsettlement.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jimsp.accountsettlement.dto.AccountSettlementDto;
import com.jimsp.accountsettlement.exceptions.AccountSettlementNotFoundException;

@RestControllerAdvice
public class AccountSettlementAdvice {

	@ExceptionHandler(AccountSettlementNotFoundException.class)
	public ResponseEntity<AccountSettlementDto> accountSettlementNotFoundException(
			final AccountSettlementNotFoundException accountSettlementNotFoundException) {
		return ResponseEntity.badRequest().body(new AccountSettlementDto(null, null, null));
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> accountSettlementAccountSettlementBlankOrNull(
			final IllegalArgumentException illegalArgumentException) {
		return ResponseEntity.badRequest().build();
	}
}
