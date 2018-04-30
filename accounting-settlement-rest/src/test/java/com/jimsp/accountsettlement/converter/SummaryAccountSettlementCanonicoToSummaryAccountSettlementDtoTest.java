package com.jimsp.accountsettlement.converter;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.jimsp.accountsettlement.canonico.SummaryAccountSettlementCanonico;
import com.jimsp.accountsettlement.dto.SummaryAccountSettlementDto;

@RunWith(MockitoJUnitRunner.class)
public class SummaryAccountSettlementCanonicoToSummaryAccountSettlementDtoTest {

	@InjectMocks
	private SummaryAccountSettlementCanonicoToSummaryAccountSettlementDto summaryAccountSettlementCanonicoToSummaryAccountSettlementDto;
	
	@Test
	public void convertNulls() {
		final SummaryAccountSettlementCanonico summaryAccountSettlementCanonico = new SummaryAccountSettlementCanonico(
				null, null, null, null, null);
		final SummaryAccountSettlementDto summaryAccountSettlementDto = summaryAccountSettlementCanonicoToSummaryAccountSettlementDto
				.convert(summaryAccountSettlementCanonico);

		Assert.assertEquals(summaryAccountSettlementCanonico.getMax(), summaryAccountSettlementDto.getMax());
		Assert.assertEquals(summaryAccountSettlementCanonico.getMedia(), summaryAccountSettlementDto.getMedia());
		Assert.assertEquals(summaryAccountSettlementCanonico.getMin(), summaryAccountSettlementDto.getMin());
		Assert.assertEquals(summaryAccountSettlementCanonico.getQtde(), summaryAccountSettlementDto.getQtde());
		Assert.assertEquals(summaryAccountSettlementCanonico.getSoma(), summaryAccountSettlementDto.getSoma());
	}

	@Test
	public void convert() {
		final SummaryAccountSettlementCanonico summaryAccountSettlementCanonico = new SummaryAccountSettlementCanonico(
				BigDecimal.valueOf(1L), BigDecimal.valueOf(2L), BigDecimal.valueOf(3L), BigDecimal.valueOf(4L),
				BigInteger.valueOf(5L));
		final SummaryAccountSettlementDto summaryAccountSettlementDto = summaryAccountSettlementCanonicoToSummaryAccountSettlementDto
				.convert(summaryAccountSettlementCanonico);

		Assert.assertEquals(summaryAccountSettlementCanonico.getMax(), summaryAccountSettlementDto.getMax());
		Assert.assertEquals(summaryAccountSettlementCanonico.getMedia(), summaryAccountSettlementDto.getMedia());
		Assert.assertEquals(summaryAccountSettlementCanonico.getMin(), summaryAccountSettlementDto.getMin());
		Assert.assertEquals(summaryAccountSettlementCanonico.getQtde(), summaryAccountSettlementDto.getQtde());
		Assert.assertEquals(summaryAccountSettlementCanonico.getSoma(), summaryAccountSettlementDto.getSoma());
	}
}
