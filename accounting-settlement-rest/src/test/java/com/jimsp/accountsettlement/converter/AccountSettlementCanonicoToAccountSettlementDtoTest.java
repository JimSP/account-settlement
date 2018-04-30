package com.jimsp.accountsettlement.converter;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.jimsp.accountsettlement.canonico.AccountSettlementCanonico;
import com.jimsp.accountsettlement.dto.AccountSettlementDto;

@RunWith(MockitoJUnitRunner.class)
public class AccountSettlementCanonicoToAccountSettlementDtoTest {

	@InjectMocks
	private AccountSettlementCanonicoToAccountSettlementDto accountSettlementCanonicoToAccountSettlementDto;

	@Mock
	private DateToInteger dateToInteger;

	@Test
	public void convertNulls() {

		Mockito.when(dateToInteger.convert(null)).thenReturn(null);

		final AccountSettlementCanonico accountSettlementCanonico = new AccountSettlementCanonico(null, null, null);
		final AccountSettlementDto accountSettlementDto = accountSettlementCanonicoToAccountSettlementDto
				.convert(accountSettlementCanonico);
		Assert.assertNotNull(accountSettlementDto);
		Assert.assertEquals(accountSettlementCanonico.getContaContabil(), accountSettlementDto.getContaContabil());
		Assert.assertEquals(accountSettlementCanonico.getData(), accountSettlementDto.getData());
		Assert.assertEquals(accountSettlementCanonico.getValor(), accountSettlementDto.getValor());
	}

	@Test
	public void convert() {

		final Date data = new Date();
		final Integer dataInt = Integer.valueOf(new SimpleDateFormat("yyyyMMdd").format(data));
		Mockito.when(dateToInteger.convert(data))
				.thenReturn(dataInt);

		final AccountSettlementCanonico accountSettlementCanonico = new AccountSettlementCanonico(1L, data,
				BigDecimal.ONE);
		final AccountSettlementDto accountSettlementDto = accountSettlementCanonicoToAccountSettlementDto
				.convert(accountSettlementCanonico);
		Assert.assertNotNull(accountSettlementDto);
		Assert.assertEquals(accountSettlementCanonico.getContaContabil(), accountSettlementDto.getContaContabil());
		Assert.assertEquals(dataInt, accountSettlementDto.getData());
		Assert.assertEquals(accountSettlementCanonico.getValor(), accountSettlementDto.getValor());
	}
}
