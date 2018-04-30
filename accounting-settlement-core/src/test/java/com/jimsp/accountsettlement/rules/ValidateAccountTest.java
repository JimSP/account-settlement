package com.jimsp.accountsettlement.rules;

import static com.jimsp.accountsettlement.rules.TestCommuns.CONTA_CONTABIL;
import static com.jimsp.accountsettlement.rules.TestCommuns.DATA;
import static com.jimsp.accountsettlement.rules.TestCommuns.VALOR1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.jimsp.accountsettlement.canonico.AccountSettlementCanonico;

@RunWith(MockitoJUnitRunner.class)
public class ValidateAccountTest {

	@InjectMocks
	private ValidateAccount validateAccount;

	@Test
	public void testOK() {
		final AccountSettlementCanonico accountSettlementCanonico = new AccountSettlementCanonico(CONTA_CONTABIL, DATA, VALOR1);
		validateAccount.validate(accountSettlementCanonico);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNull() {
		final AccountSettlementCanonico accountSettlementCanonico = null;
		validateAccount.validate(accountSettlementCanonico);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAllNulls() {
		final AccountSettlementCanonico accountSettlementCanonico = new AccountSettlementCanonico(null, null, null);
		validateAccount.validate(accountSettlementCanonico);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testContaContabilNull() {
		final AccountSettlementCanonico accountSettlementCanonico = new AccountSettlementCanonico(null, DATA, VALOR1);
		validateAccount.validate(accountSettlementCanonico);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegativeContaContabil() {
		final AccountSettlementCanonico accountSettlementCanonico = new AccountSettlementCanonico(-1L, DATA, VALOR1);
		validateAccount.validate(accountSettlementCanonico);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testBigLongContaContabil() {
		final AccountSettlementCanonico accountSettlementCanonico = new AccountSettlementCanonico(10000000000000L, DATA, VALOR1);
		validateAccount.validate(accountSettlementCanonico);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDataNull() {
		final AccountSettlementCanonico accountSettlementCanonico = new AccountSettlementCanonico(CONTA_CONTABIL, null, VALOR1);
		validateAccount.validate(accountSettlementCanonico);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testValorNull() {
		final AccountSettlementCanonico accountSettlementCanonico = new AccountSettlementCanonico(CONTA_CONTABIL, DATA, null);
		validateAccount.validate(accountSettlementCanonico);
	}
}
