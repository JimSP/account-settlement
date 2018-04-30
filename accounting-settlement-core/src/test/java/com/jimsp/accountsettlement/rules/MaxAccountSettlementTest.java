package com.jimsp.accountsettlement.rules;

import static com.jimsp.accountsettlement.rules.TestCommuns.VALOR1;
import static com.jimsp.accountsettlement.rules.TestCommuns.VALOR2;
import static com.jimsp.accountsettlement.rules.TestCommuns.createSupplierStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MaxAccountSettlementTest {

	@InjectMocks
	private MaxAccountSettlement maxAccountSettlement;
	
	@Mock
	private MapAccountSettlementExtractValue mapAccountSettlement;
	
	@Test
	public void test() {
		Assert.assertEquals(VALOR1, maxAccountSettlement.max(createSupplierStream(VALOR1, VALOR2)));
	}
}
