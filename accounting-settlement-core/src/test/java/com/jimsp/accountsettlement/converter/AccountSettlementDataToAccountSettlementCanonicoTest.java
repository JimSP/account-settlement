package com.jimsp.accountsettlement.converter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.jimsp.accountsettlement.canonico.AccountSettlementCanonico;
import com.jimsp.accountsettlement.model.AccountSettlementData;

@RunWith(MockitoJUnitRunner.class)
public class AccountSettlementDataToAccountSettlementCanonicoTest {

	@InjectMocks
	private AccountSettlementDataToAccountSettlementCanonico accountSettlementDataToAccountSettlementCanonico;

	@Test
	public void testNulls() {
		final AccountSettlementData accountSettlementData = new AccountSettlementData();
		final AccountSettlementCanonico accountSettlementCanonico = accountSettlementDataToAccountSettlementCanonico
				.accountSettlementCanonicoToAccountSettlementData(accountSettlementData);

		Assert.assertNotNull(accountSettlementCanonico);
		Assert.assertEquals(accountSettlementData.getContaContabil(), accountSettlementCanonico.getContaContabil());
		Assert.assertEquals(accountSettlementData.getData(), accountSettlementCanonico.getData());
		Assert.assertEquals(accountSettlementData.getValor(), accountSettlementCanonico.getValor());
	}
	
	@Test
	public void test() {
		final AccountSettlementData accountSettlementData = new AccountSettlementData();
		accountSettlementData.setContaContabil(1L);
		accountSettlementData.setData(new Date());
		accountSettlementData.setId(UUID.randomUUID());
		accountSettlementData.setValor(BigDecimal.ONE);
		
		final AccountSettlementCanonico accountSettlementCanonico = accountSettlementDataToAccountSettlementCanonico
				.accountSettlementCanonicoToAccountSettlementData(accountSettlementData);

		Assert.assertNotNull(accountSettlementCanonico);
		Assert.assertEquals(accountSettlementData.getContaContabil(), accountSettlementCanonico.getContaContabil());
		Assert.assertEquals(accountSettlementData.getData(), accountSettlementCanonico.getData());
		Assert.assertEquals(accountSettlementData.getValor(), accountSettlementCanonico.getValor());
	}

}
