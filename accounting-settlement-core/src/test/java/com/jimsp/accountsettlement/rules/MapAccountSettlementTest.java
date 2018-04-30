package com.jimsp.accountsettlement.rules;

import static com.jimsp.accountsettlement.rules.TestCommuns.CONTA_CONTABIL;
import static com.jimsp.accountsettlement.rules.TestCommuns.DATA;
import static com.jimsp.accountsettlement.rules.TestCommuns.VALOR1;
import static com.jimsp.accountsettlement.rules.TestCommuns.VALOR2;
import static com.jimsp.accountsettlement.rules.TestCommuns.createSupplierStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.jimsp.accountsettlement.canonico.AccountSettlementCanonico;

@RunWith(MockitoJUnitRunner.class)
public class MapAccountSettlementTest {

	@InjectMocks
	private MapAccountSettlementExtractValue mapAccountSettlement;

	@Test
	public void test() {

		final AccountSettlementCanonico accountSettlementCanonico1 = new AccountSettlementCanonico(CONTA_CONTABIL, DATA,
				VALOR1);
		final AccountSettlementCanonico accountSettlementCanonico2 = new AccountSettlementCanonico(CONTA_CONTABIL, DATA,
				VALOR2);

		Assert.assertEquals(VALOR1.add(VALOR2),
				mapAccountSettlement.map(createSupplierStream(accountSettlementCanonico1, accountSettlementCanonico2))
						.get().reduce((a, b) -> a.add(b)).get());
	}
}
