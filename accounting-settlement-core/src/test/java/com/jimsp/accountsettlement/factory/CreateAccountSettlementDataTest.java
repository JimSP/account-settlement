package com.jimsp.accountsettlement.factory;

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
public class CreateAccountSettlementDataTest {

	@InjectMocks
	private CreateAccountSettlementData createAccountSettlementData;
	
	@Test
	public void testNulls() {
		final AccountSettlementCanonico accountSettlementCanonico = new AccountSettlementCanonico(null, null, null);
		final UUID id = null;
		final AccountSettlementData accountSettlementData = createAccountSettlementData.create(id, accountSettlementCanonico);
		
		Assert.assertNotNull(accountSettlementData);
		Assert.assertEquals(accountSettlementData.getId(), id);
		Assert.assertEquals(accountSettlementData.getContaContabil(), accountSettlementCanonico.getContaContabil());
		Assert.assertEquals(accountSettlementData.getData(), accountSettlementCanonico.getData());
		Assert.assertEquals(accountSettlementData.getValor(), accountSettlementCanonico.getValor());
	}
	
	@Test
	public void test() {
		final AccountSettlementCanonico accountSettlementCanonico = new AccountSettlementCanonico(1L, new Date(), BigDecimal.ONE);
		final UUID id = UUID.randomUUID();
		final AccountSettlementData accountSettlementData = createAccountSettlementData.create(id, accountSettlementCanonico);
		
		Assert.assertNotNull(accountSettlementData);
		Assert.assertEquals(accountSettlementData.getId(), id);
		Assert.assertEquals(accountSettlementData.getContaContabil(), accountSettlementCanonico.getContaContabil());
		Assert.assertEquals(accountSettlementData.getData(), accountSettlementCanonico.getData());
		Assert.assertEquals(accountSettlementData.getValor(), accountSettlementCanonico.getValor());
	}
}
