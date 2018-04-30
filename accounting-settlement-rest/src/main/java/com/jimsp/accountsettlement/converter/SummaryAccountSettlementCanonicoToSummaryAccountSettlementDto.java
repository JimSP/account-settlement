package com.jimsp.accountsettlement.converter;

import org.springframework.stereotype.Component;

import com.jimsp.accountsettlement.canonico.SummaryAccountSettlementCanonico;
import com.jimsp.accountsettlement.dto.SummaryAccountSettlementDto;

@Component
public class SummaryAccountSettlementCanonicoToSummaryAccountSettlementDto {

	public SummaryAccountSettlementDto convert(
			final SummaryAccountSettlementCanonico summaryAccountSettlementCanonico) {

		return new SummaryAccountSettlementDto(summaryAccountSettlementCanonico.getSoma(),
				summaryAccountSettlementCanonico.getMin(), summaryAccountSettlementCanonico.getMax(),
				summaryAccountSettlementCanonico.getMedia(), summaryAccountSettlementCanonico.getQtde());
	}
}
