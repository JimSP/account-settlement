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
public class CountAccountSettlementTest {

	@InjectMocks
	private CountAccountSettlement countAccountSettlement;

	@Test
	public void test() {
		Assert.assertEquals(2L,
				countAccountSettlement
						.qtde(createSupplierStream(new AccountSettlementCanonico(CONTA_CONTABIL, DATA, VALOR1),
								new AccountSettlementCanonico(CONTA_CONTABIL, DATA, VALOR2)))
						.longValue());
		
		Assert.assertEquals(0L,
				countAccountSettlement
						.qtde(createSupplierStream())
						.longValue());
	}
}
