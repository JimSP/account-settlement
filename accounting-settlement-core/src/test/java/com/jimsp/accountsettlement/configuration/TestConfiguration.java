package com.jimsp.accountsettlement.configuration;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.jimsp.accountsettlement.converter.AccountSettlementDataToAccountSettlementCanonico;
import com.jimsp.accountsettlement.rules.AvgAccountSettlement;
import com.jimsp.accountsettlement.rules.CountAccountSettlement;
import com.jimsp.accountsettlement.rules.CreateSummaryAccountSettlementCanonico;
import com.jimsp.accountsettlement.rules.FilterAccountSettlement;
import com.jimsp.accountsettlement.rules.MapAccountSettlementExtractValue;
import com.jimsp.accountsettlement.rules.MaxAccountSettlement;
import com.jimsp.accountsettlement.rules.MinAccountSettlement;
import com.jimsp.accountsettlement.rules.SumAccountSettlement;

@SpringBootConfiguration
@EnableAutoConfiguration(exclude = { HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class })
public class TestConfiguration {

	@Bean
	public CreateSummaryAccountSettlementCanonico createSummaryAccountSettlementCanonico() {
		return new CreateSummaryAccountSettlementCanonico();
	}

	@Bean
	public AccountSettlementDataToAccountSettlementCanonico accountSettlementDataToAccountSettlementCanonico() {
		return new AccountSettlementDataToAccountSettlementCanonico();
	}

	@Bean
	public MapAccountSettlementExtractValue mapAccountSettlementExtractValue() {
		return new MapAccountSettlementExtractValue();
	}
	
	@Bean
	public FilterAccountSettlement filterAccountSettlement() {
		return new FilterAccountSettlement();
	}

	@Bean
	public SumAccountSettlement sumAccountSettlement() {
		return new SumAccountSettlement();
	}

	@Bean
	public MinAccountSettlement minAccountSettlement() {
		return new MinAccountSettlement();
	}

	@Bean
	public MaxAccountSettlement maxAccountSettlement() {
		return new MaxAccountSettlement();
	}

	@Bean
	public AvgAccountSettlement avgAccountSettlement() {
		return new AvgAccountSettlement();
	}

	@Bean
	public CountAccountSettlement countAccountSettlement() {
		return new CountAccountSettlement();
	}

}
