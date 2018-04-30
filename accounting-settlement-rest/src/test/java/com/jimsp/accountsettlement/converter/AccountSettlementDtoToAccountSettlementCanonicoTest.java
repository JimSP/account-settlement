package com.jimsp.accountsettlement.converter;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.jimsp.accountsettlement.canonico.AccountSettlementCanonico;
import com.jimsp.accountsettlement.dto.AccountSettlementDto;

@RunWith(MockitoJUnitRunner.class)
public class AccountSettlementDtoToAccountSettlementCanonicoTest {

	@InjectMocks
	private AccountSettlementDtoToAccountSettlementCanonico accountSettlementCanonicoToAccountSettlementDto;
	
	@Test(expected=IllegalArgumentException.class)
	public void convertNulls() {
		final AccountSettlementDto accountDto = new AccountSettlementDto(null, null, null);
		accountSettlementCanonicoToAccountSettlementDto.convert(accountDto);
	}
	
	@Test
	public void convert() {
		final AccountSettlementDto accountDto = new AccountSettlementDto(1L, 20180101, BigDecimal.ONE);
		final AccountSettlementCanonico accountSettlementCanonico = accountSettlementCanonicoToAccountSettlementDto.convert(accountDto);
		Assert.assertNotNull(accountSettlementCanonico);
		Assert.assertEquals(accountDto.getContaContabil(), accountSettlementCanonico.getContaContabil());
		Assert.assertEquals(accountDto.getData(), Integer.valueOf(new SimpleDateFormat("yyyyMMdd").format(accountSettlementCanonico.getData())));
		Assert.assertEquals(accountDto.getValor(), accountSettlementCanonico.getValor());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void convertInvalidData() {
		final AccountSettlementDto accountDto = new AccountSettlementDto(1L, 1, BigDecimal.ONE);
		final AccountSettlementCanonico accountSettlementCanonico = accountSettlementCanonicoToAccountSettlementDto.convert(accountDto);
		Assert.assertNotNull(accountSettlementCanonico);
		Assert.assertEquals(accountDto.getContaContabil(), accountSettlementCanonico.getContaContabil());
		Assert.assertEquals(accountDto.getData(), Integer.valueOf(new SimpleDateFormat("yyyyMMdd").format(accountSettlementCanonico.getData())));
		Assert.assertEquals(accountDto.getValor(), accountSettlementCanonico.getValor());
	}
}
