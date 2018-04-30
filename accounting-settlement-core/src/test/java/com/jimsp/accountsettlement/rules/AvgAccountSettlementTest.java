package com.jimsp.accountsettlement.rules;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AvgAccountSettlementTest {

	@InjectMocks
	private AvgAccountSettlement avgAccountSettlement;
	
	@Test
	public void test() {
		Assert.assertEquals(BigDecimal.ZERO, avgAccountSettlement.media(BigDecimal.TEN, BigInteger.ZERO));
		Assert.assertEquals(BigDecimal.ZERO, avgAccountSettlement.media(BigDecimal.ZERO, BigInteger.TEN));
		Assert.assertEquals(BigDecimal.ONE, avgAccountSettlement.media(BigDecimal.TEN, BigInteger.TEN));
		Assert.assertEquals(BigDecimal.TEN, avgAccountSettlement.media(BigDecimal.TEN, BigInteger.ONE));
	}
}
