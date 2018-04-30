package com.jimsp.accountsettlement.rules;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jimsp.accountsettlement.canonico.AccountSettlementCanonico;
import com.jimsp.accountsettlement.canonico.SummaryAccountSettlementCanonico;
import com.jimsp.accountsettlement.converter.AccountSettlementDataToAccountSettlementCanonico;
import com.jimsp.accountsettlement.model.AccountSettlementData;

@Component
public class CreateSummaryAccountSettlementCanonico {
	
	@Autowired
	private AccountSettlementDataToAccountSettlementCanonico accountSettlementDataToAccountSettlementCanonico;
	
	@Autowired
	private MapAccountSettlementExtractValue mapAccountSettlementExtractValue;
	
	@Autowired
	private FilterAccountSettlement filterAccountSettlement;
	
	@Autowired
	private SumAccountSettlement sumAccountSettlement;

	@Autowired
	private MinAccountSettlement minAccountSettlement;

	@Autowired
	private MaxAccountSettlement maxAccountSettlement;

	@Autowired
	private AvgAccountSettlement avgAccountSettlement;

	@Autowired
	private CountAccountSettlement countAccountSettlement;
	
	public SummaryAccountSettlementCanonico create(
			final List<AccountSettlementData> accountSettlementDataList) {

		final Supplier<Stream<AccountSettlementCanonico>> supplierStream = () -> accountSettlementDataList.stream()
				.map(data -> convertDataToCanonico(data));
		
		final Supplier<Stream<AccountSettlementCanonico>> supplierStreamFiltred = filterAccountSettlement.filter(supplierStream);
		
		final Supplier<Stream<BigDecimal>> supplierStreamExtractedValue = mapAccountSettlementExtractValue.map(supplierStreamFiltred);
		
		final BigDecimal soma = sumAccountSettlement.soma(supplierStreamExtractedValue);
		final BigDecimal min = minAccountSettlement.min(supplierStreamExtractedValue);
		final BigDecimal max = maxAccountSettlement.max(supplierStreamExtractedValue);
		final BigInteger qtde = countAccountSettlement.qtde(supplierStreamFiltred);
		final BigDecimal avg = avgAccountSettlement.media(soma, qtde);
		return new SummaryAccountSettlementCanonico(soma, min, max, avg, qtde);
	}

	private AccountSettlementCanonico convertDataToCanonico(final AccountSettlementData accountSettlementData) {
		return accountSettlementDataToAccountSettlementCanonico
				.accountSettlementCanonicoToAccountSettlementData(accountSettlementData);
	}
}
