package com.jimsp.accountsettlement.rules;

import static com.jimsp.accountsettlement.rules.TestCommuns.CONTA_CONTABIL;
import static com.jimsp.accountsettlement.rules.TestCommuns.DATA;
import static com.jimsp.accountsettlement.rules.TestCommuns.VALOR1;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.jimsp.accountsettlement.canonico.AccountSettlementCanonico;

@RunWith(MockitoJUnitRunner.class)
public class CreateAccountIDTest {
	
	@InjectMocks
	private CreateAccountID createAccountID;
	
	@Mock
	private ValidateAccount validateAccount;
	
	@Test
	public void testOK() {

		final AccountSettlementCanonico accountSettlementCanonico = new AccountSettlementCanonico(CONTA_CONTABIL, DATA, VALOR1);
		
		Mockito.spy(validateAccount);

		final UUID uuid = createAccountID.create(accountSettlementCanonico);
		
		Assert.assertNotNull(uuid);
	}
	
	@Test
	public void testGenerateUUIDFromEqualsAccountSettlementCanonico() {

		final AccountSettlementCanonico accountSettlementCanonico = new AccountSettlementCanonico(CONTA_CONTABIL, DATA, VALOR1);
		
		Mockito.spy(validateAccount);

		final UUID uuid1 = createAccountID.create(accountSettlementCanonico);
		final UUID uuid2 = createAccountID.create(accountSettlementCanonico);
		
		Assert.assertNotEquals(uuid1, uuid2);
	}
}
