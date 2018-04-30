package com.jimsp.accountsettlement.repository;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.jimsp.accountsettlement.model.AccountSettlementData;
	
@RunWith(SpringRunner.class)
@DataJpaTest(showSql=true)
@AutoConfigureTestDatabase(connection=EmbeddedDatabaseConnection.HSQL)
public class AccountSettlementJpaRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private AccountSettlementJpaRepository accountSettlementJpaRepository;
	
	@Test
	public void test() {
		final AccountSettlementData accountSettlementData1 = new AccountSettlementData();
		
		final AccountSettlementData accountSettlementData2 = new AccountSettlementData();
		accountSettlementData2.setId(UUID.randomUUID());
		accountSettlementData2.setContaContabil(1L);
		accountSettlementData2.setData(new Date());
		accountSettlementData2.setValor(BigDecimal.TEN);
		entityManager.persist(accountSettlementData2);
		
		accountSettlementData1.setId(UUID.randomUUID());
		accountSettlementData1.setContaContabil(1L);
		accountSettlementData1.setData(new Date());
		accountSettlementData1.setValor(BigDecimal.ONE);
		entityManager.persist(accountSettlementData1);
		
		final AccountSettlementData accountSettlementData3 = new AccountSettlementData();
		accountSettlementData3.setId(UUID.randomUUID());
		accountSettlementData3.setContaContabil(3L);
		accountSettlementData3.setData(new Date());
		accountSettlementData3.setValor(BigDecimal.TEN);
		entityManager.persist(accountSettlementData3);
		
		final List<AccountSettlementData> accountSettlementDatas = accountSettlementJpaRepository.findByContaContabilOrderByIdDesc(1L);
		
		Assert.assertNotNull(accountSettlementDatas);
		Assert.assertEquals(2, accountSettlementDatas.size());
		
		assertEquals(accountSettlementData1, accountSettlementDatas.get(0));
		assertEquals(accountSettlementData2, accountSettlementDatas.get(1));
	}
}
