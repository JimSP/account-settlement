package com.jimsp.accountsettlement.facade;

import static com.jimsp.accountsettlement.rules.TestCommuns.CONTA_CONTABIL;
import static com.jimsp.accountsettlement.rules.TestCommuns.DATA;
import static com.jimsp.accountsettlement.rules.TestCommuns.VALOR1;
import static com.jimsp.accountsettlement.rules.TestCommuns.createAccountSettlementDataList;
import static com.jimsp.accountsettlement.rules.TestCommuns.createSummaryAccountSettlementCanonico;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.jimsp.accountsettlement.canonico.AccountSettlementCanonico;
import com.jimsp.accountsettlement.canonico.SummaryAccountSettlementCanonico;
import com.jimsp.accountsettlement.converter.AccountSettlementDataToAccountSettlementCanonico;
import com.jimsp.accountsettlement.exceptions.AccountSettlementNotFoundException;
import com.jimsp.accountsettlement.factory.CreateAccountSettlementData;
import com.jimsp.accountsettlement.model.AccountSettlementData;
import com.jimsp.accountsettlement.repository.AccountSettlementJpaRepository;
import com.jimsp.accountsettlement.rules.CountAccountSettlement;
import com.jimsp.accountsettlement.rules.CreateAccountID;
import com.jimsp.accountsettlement.rules.CreateSummaryAccountSettlementCanonico;
import com.jimsp.accountsettlement.service.AccountSettlementService;

@RunWith(MockitoJUnitRunner.class)
public class AccountSettlementFacadeTest {

	@InjectMocks
	private AccountSettlementService accountSettlementFacade;

	@Mock
	private CreateAccountID createAccountID;

	@Mock
	private CreateAccountSettlementData createAccountSettlementData;

	@Mock
	private AccountSettlementDataToAccountSettlementCanonico accountSettlementDataToAccountSettlementCanonico;

	@Mock
	private AccountSettlementJpaRepository accountSettlementJpaRepository;

	@Mock
	private CreateSummaryAccountSettlementCanonico createSummaryAccountSettlementCanonico;

	@Mock
	private CountAccountSettlement countAccountSettlement;

	@Test
	public void createAccountTest() {
		final UUID expectedUUID = createUUID();

		final AccountSettlementCanonico accountSettlementCanonico = new AccountSettlementCanonico(CONTA_CONTABIL, DATA,
				VALOR1);

		final AccountSettlementData accountSettlementData = createAccountSettlementData(expectedUUID);

		Mockito.when(createAccountID.create(accountSettlementCanonico)).thenReturn(expectedUUID);

		Mockito.when(createAccountSettlementData.create(expectedUUID, accountSettlementCanonico))
				.thenReturn(accountSettlementData);

		Mockito.when(accountSettlementJpaRepository.save(accountSettlementData)).thenReturn(accountSettlementData);

		final UUID uuid = accountSettlementFacade.createAccount(accountSettlementCanonico);
		Assert.assertNotNull(uuid);
		Assert.assertEquals(expectedUUID, uuid);
	}

	@Test
	public void getAccountByIdTest() {

		final UUID expectedUUID = createUUID();

		final AccountSettlementData accountSettlementData = createAccountSettlementData(expectedUUID);
		Mockito.when(accountSettlementJpaRepository.findById(expectedUUID))
				.thenReturn(Optional.of(accountSettlementData));

		final AccountSettlementCanonico accountSettlementCanonicoExpected = new AccountSettlementCanonico(
				CONTA_CONTABIL, DATA, VALOR1);
		Mockito.when(accountSettlementDataToAccountSettlementCanonico
				.accountSettlementCanonicoToAccountSettlementData(accountSettlementData))
				.thenReturn(accountSettlementCanonicoExpected);

		final AccountSettlementCanonico accountSettlementCanonico = accountSettlementFacade
				.getAccountById(expectedUUID);

		Assert.assertNotNull(accountSettlementCanonico);
		Assert.assertEquals(accountSettlementCanonicoExpected, accountSettlementCanonico);
	}

	@Test(expected = AccountSettlementNotFoundException.class)
	public void getAccountByIdTestInvalidId() {

		final UUID expectedUUID = createUUID();
		Mockito.when(accountSettlementJpaRepository.findById(expectedUUID))
				.thenThrow(AccountSettlementNotFoundException.class);

		accountSettlementFacade.getAccountById(expectedUUID);
	}

	@Test
	public void getAccountByContaContabilTest() {
		final UUID expectedUUID = createUUID();
		final AccountSettlementCanonico accountSettlementCanonicoExpected = new AccountSettlementCanonico(
				CONTA_CONTABIL, DATA, VALOR1);
		final AccountSettlementData accountSettlementData = createAccountSettlementData(expectedUUID);

		Mockito.when(accountSettlementJpaRepository //
				.findByContaContabilOrderByIdDesc(CONTA_CONTABIL))
				.thenReturn(Arrays.asList(createAccountSettlementData(expectedUUID)));

		Mockito.when(accountSettlementDataToAccountSettlementCanonico
				.accountSettlementCanonicoToAccountSettlementData(accountSettlementData))
				.thenReturn(accountSettlementCanonicoExpected);

		final List<AccountSettlementCanonico> accountSettlementCanonicos = accountSettlementFacade
				.getAccountByContaContabil(CONTA_CONTABIL);

		Assert.assertArrayEquals(Arrays.asList(accountSettlementCanonicoExpected).toArray(),
				accountSettlementCanonicos.toArray());
	}

	@Test
	public void summaryTestFindAll() {
		final SummaryAccountSettlementCanonico summaryAccountSettlementCanonicoExpected = createSummaryAccountSettlementCanonico();
		final List<AccountSettlementData> dataList = createAccountSettlementDataList();
		Mockito.when(accountSettlementJpaRepository.findAll()).thenReturn(dataList);

		Mockito.when(createSummaryAccountSettlementCanonico.create(dataList))
				.thenReturn(summaryAccountSettlementCanonicoExpected);

		final SummaryAccountSettlementCanonico summaryAccountSettlementCanonico = accountSettlementFacade.summary(null);

		Assert.assertNotNull(summaryAccountSettlementCanonico);
		Assert.assertEquals(summaryAccountSettlementCanonicoExpected, summaryAccountSettlementCanonico);
	}

	@Test
	public void summaryTestFindById() {
		final SummaryAccountSettlementCanonico summaryAccountSettlementCanonicoExpected = createSummaryAccountSettlementCanonico();
		final List<AccountSettlementData> dataList = createAccountSettlementDataList();
		Mockito.when(accountSettlementJpaRepository.findByContaContabilOrderByIdDesc(CONTA_CONTABIL))
				.thenReturn(dataList);

		Mockito.when(createSummaryAccountSettlementCanonico.create(dataList))
				.thenReturn(summaryAccountSettlementCanonicoExpected);

		final SummaryAccountSettlementCanonico summaryAccountSettlementCanonico = accountSettlementFacade
				.summary(CONTA_CONTABIL);

		Assert.assertNotNull(summaryAccountSettlementCanonico);
		Assert.assertEquals(summaryAccountSettlementCanonicoExpected, summaryAccountSettlementCanonico);
	}

	private UUID createUUID() {
		final UUID expectedUUID = UUID.randomUUID();
		return expectedUUID;
	}

	private AccountSettlementData createAccountSettlementData(final UUID expectedUUID) {
		final AccountSettlementData accountSettlementData = new AccountSettlementData();
		accountSettlementData.setContaContabil(CONTA_CONTABIL);
		accountSettlementData.setData(DATA);
		accountSettlementData.setValor(VALOR1);
		accountSettlementData.setId(expectedUUID);
		return accountSettlementData;
	}

}
