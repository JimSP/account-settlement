package com.jimsp.accountsettlement.rules;

import static com.jimsp.accountsettlement.rules.TestCommuns.createAccountSettlementDataList;
import static com.jimsp.accountsettlement.rules.TestCommuns.createSummaryAccountSettlementCanonico;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jimsp.accountsettlement.canonico.SummaryAccountSettlementCanonico;
import com.jimsp.accountsettlement.configuration.TestConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {TestConfiguration.class})
public class CreateSummaryAccountSettlementCanonicoTest {

	@Autowired
	private CreateSummaryAccountSettlementCanonico createSummaryAccountSettlementCanonico;
	
	@Test
	public void test() {
		final SummaryAccountSettlementCanonico summaryAccountSettlementCanonicoExpected = createSummaryAccountSettlementCanonico();
		
		final SummaryAccountSettlementCanonico summaryAccountSettlementCanonico = createSummaryAccountSettlementCanonico.create(createAccountSettlementDataList());
		Assert.assertNotNull(summaryAccountSettlementCanonico);
		Assert.assertEquals(summaryAccountSettlementCanonicoExpected, summaryAccountSettlementCanonico);
	}
}
