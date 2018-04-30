package com.jimsp.accountsettlement.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jimsp.accountsettlement.canonico.AccountSettlementCanonico;
import com.jimsp.accountsettlement.canonico.SummaryAccountSettlementCanonico;
import com.jimsp.accountsettlement.converter.AccountSettlementCanonicoToAccountSettlementDto;
import com.jimsp.accountsettlement.converter.AccountSettlementDtoToAccountSettlementCanonico;
import com.jimsp.accountsettlement.converter.DateToInteger;
import com.jimsp.accountsettlement.converter.SummaryAccountSettlementCanonicoToSummaryAccountSettlementDto;
import com.jimsp.accountsettlement.dto.AccountIdDto;
import com.jimsp.accountsettlement.dto.AccountSettlementDto;
import com.jimsp.accountsettlement.dto.SummaryAccountSettlementDto;
import com.jimsp.accountsettlement.exceptions.AccountSettlementNotFoundException;
import com.jimsp.accountsettlement.remote.AccountSettlementRemote;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { AccountSettlementController.class })
public class AccountSettlementControllerTest {

	private static final String CONTA_INVALIDA = "CONTA_INVALIDA";
	private static final long PRECISION_MAX = 10000000000000L;
	private static final String INVALIDUUID = "INVALIDUUID";
	private static final String PARAM_ID = "{id}";
	private static final String PARAM_NAME = "contaContabil";
	private static final String ACTION_STATS = "/lancamentos-contabeis/_stats";
	private static final String ACTION_ACCOUNT = "/lancamentos-contabeis";
	private static final String ACTION_RECOVERY = "/lancamentos-contabeis/{id}";
	private static final String DATE_MASK = "yyyyMMdd";
	private static final String ENCOLDING = "UTF-8";

	private static final Long CONTA_CONTABIL = 1110011L;
	private static final Integer DATA = 20170130;
	private static final BigDecimal VALOR = new BigDecimal(25000.15D, MathContext.DECIMAL32);
	private static final BigDecimal VALOR_1 = new BigDecimal(150.74D, MathContext.DECIMAL32);
	private static final String ID = "97c9e6e8-dedb-497a-bb94-7fe01bf415ed";
	
	private static final BigInteger QTDE = BigInteger.valueOf(2L);
	private static final BigDecimal MEDIA = new BigDecimal(12575.46D, MathContext.DECIMAL32);
	private static final BigDecimal MAX = new BigDecimal(2500.17D, MathContext.DECIMAL32);
	private static final BigDecimal MIN = new BigDecimal(150.74D, MathContext.DECIMAL32);
	private static final BigDecimal SOMA = new BigDecimal(25150.91D, MathContext.DECIMAL32);

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private AccountSettlementRemote accountSettlementRemote;

	@MockBean
	private AccountSettlementDtoToAccountSettlementCanonico accountDtoToAccountCanonico;

	@MockBean
	private AccountSettlementCanonicoToAccountSettlementDto accountCanonicoToAccountDto;
	
	@MockBean
	private SummaryAccountSettlementCanonicoToSummaryAccountSettlementDto summaryAccountSettlementCanonicoToSummaryAccountSettlementDto;

	@Test
	public void summaryTestWithContaContabilOK() throws Exception {
		
		final SummaryAccountSettlementDto summaryAccountSettlementDto = new SummaryAccountSettlementDto(SOMA, MIN, MAX, MEDIA, QTDE);
		final SummaryAccountSettlementCanonico summaryAccountSettlementCanonico = new SummaryAccountSettlementCanonico(SOMA, MIN, MAX, MEDIA, QTDE);
		final String contentResponse = objectMapper.writeValueAsString(summaryAccountSettlementDto);
		
		when(accountSettlementRemote.summary(CONTA_CONTABIL)).thenReturn(summaryAccountSettlementCanonico);
		when(summaryAccountSettlementCanonicoToSummaryAccountSettlementDto.convert(summaryAccountSettlementCanonico)).thenReturn(summaryAccountSettlementDto);
		
		mockMvc //
				.perform(get(ACTION_STATS) //
						.param(PARAM_NAME, String.valueOf(CONTA_CONTABIL))) //
				.andDo(print()) //
				.andExpect(status() //
						.isOk()) //
				.andExpect(content() //
						.json(contentResponse)) //
				.andExpect(content() //
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(content().encoding(ENCOLDING));
	}
	
	@Test
	public void summaryTestWithInvalidContaContabil() throws Exception {
		mockMvc //
				.perform(get(ACTION_STATS) //
						.param(PARAM_NAME, CONTA_INVALIDA)) //
				.andDo(print()) //
				.andExpect(status() //
						.is4xxClientError());
	}
	
	@Test
	public void summaryTestWithOutContaContabilOK() throws Exception {
		final SummaryAccountSettlementDto summaryAccountSettlementDto = new SummaryAccountSettlementDto(SOMA, MIN, MAX, MEDIA, QTDE);
		final SummaryAccountSettlementCanonico summaryAccountSettlementCanonico = new SummaryAccountSettlementCanonico(SOMA, MIN, MAX, MEDIA, QTDE);
		final String contentResponse = objectMapper.writeValueAsString(summaryAccountSettlementDto);
		
		when(accountSettlementRemote.summary(null)).thenReturn(summaryAccountSettlementCanonico);
		when(summaryAccountSettlementCanonicoToSummaryAccountSettlementDto.convert(summaryAccountSettlementCanonico)).thenReturn(summaryAccountSettlementDto);
		
		mockMvc //
				.perform(get(ACTION_STATS)) //
				.andDo(print()) //
				.andExpect(status() //
						.isOk()) //
				.andExpect(content() //
						.json(contentResponse)) //
				.andExpect(content() //
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(content().encoding(ENCOLDING));
	}

	@Test
	public void getAccountByContaContabilTestOK() throws Exception {
		final DateToInteger dateToInteger = new DateToInteger();

		final List<AccountSettlementCanonico> list = createAccountCanonicoList();
		final List<AccountSettlementDto> responseList = list.stream()
				.map(accountSettlementCanonico -> new AccountSettlementDto(accountSettlementCanonico.getContaContabil(),
						formatDate(accountSettlementCanonico.getData()), accountSettlementCanonico.getValor()))
				.collect(Collectors.toList());

		final String contentResponse = objectMapper.writeValueAsString(responseList);

		when(accountSettlementRemote.getAccountByContaContabil(CONTA_CONTABIL)).thenReturn(list);

		list //
				.stream() //
				.forEach(action -> { //
					when(accountCanonicoToAccountDto.convert(action)) //
							.thenReturn( //
									new AccountSettlementDto( //
											action.getContaContabil(), //
											dateToInteger.convert(action.getData()), //
											action.getValor())); //
				});

		mockMvc //
				.perform(get(ACTION_ACCOUNT) //
						.param(PARAM_NAME, String.valueOf(CONTA_CONTABIL))) //
				.andDo(print()) //
				.andExpect(status() //
						.isOk()) //
				.andExpect(content() //
						.json(contentResponse)) //
				.andExpect(content() //
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(content().encoding(ENCOLDING));
	}

	@Test
	public void getAccountByContaContabilTestInvalidContaContabil() throws Exception {
		mockMvc //
				.perform(get(ACTION_ACCOUNT) //
						.param(PARAM_NAME, CONTA_INVALIDA)) //
				.andDo(print()) //
				.andExpect(status() //
						.is4xxClientError());
	}

	@Test
	public void getAccountByContaContabilTestIsBlankContaConbil() throws Exception {
		mockMvc //
				.perform(get(ACTION_ACCOUNT) //
						.param(PARAM_NAME, "")) //
				.andDo(print()) //
				.andExpect(status() //
						.is4xxClientError());
	}

	@Test
	public void getAccountByContaContabilTestIsNullContaConbil() throws Exception {
		mockMvc //
				.perform(get(ACTION_ACCOUNT) //
						.param(PARAM_NAME, new String[] { null })) //
				.andDo(print()) //
				.andExpect(status() //
						.is4xxClientError());
	}

	@Test
	public void getAccountByContaContabilTestNotParameter() throws Exception {
		mockMvc //
				.perform(get(ACTION_ACCOUNT)) //
				.andDo(print()) //
				.andExpect(status() //
						.is4xxClientError());
	}

	@Test
	public void getAccountByIdTestOK() throws Exception {
		final AccountSettlementDto accountSettlementDto = cretaeAccountSettlementDto();
		final String contentResponse = objectMapper.writeValueAsString(accountSettlementDto);

		final AccountSettlementCanonico accountSettlementCanonico = createAccountSettlementCanonico();
		when(accountSettlementRemote.getAccountById(UUID.fromString(ID))).thenReturn(accountSettlementCanonico);

		when(accountCanonicoToAccountDto.convert(accountSettlementCanonico)).thenReturn(accountSettlementDto);

		mockMvc //
				.perform(get(ACTION_RECOVERY.replace(PARAM_ID, ID))) //
				.andDo(print()) //
				.andExpect(status() //
						.isOk()) //
				.andExpect(content() //
						.json(contentResponse)) //
				.andExpect(content() //
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(content().encoding(ENCOLDING));
	}

	@Test
	public void getAccountByIdTestWithOutId() throws Exception {
		mockMvc //
				.perform(get(ACTION_ACCOUNT)) //
				.andDo(print()) //
				.andExpect(status() //
						.is4xxClientError());
	}

	@Test
	public void getAccountByIdTestInvalidId() throws Exception {
		mockMvc //
				.perform(get(ACTION_RECOVERY.replace(PARAM_ID, INVALIDUUID))) //
				.andDo(print()) //
				.andExpect(status() //
						.is4xxClientError());
	}

	@Test
	public void getAccountByIdTestIdNotFound() throws Exception {
		final AccountSettlementDto accountSettlementDto = cretaeAccountSettlementDto();

		final AccountSettlementCanonico accountSettlementCanonico = createAccountSettlementCanonico();
		when(accountSettlementRemote.getAccountById(UUID.fromString(ID)))
				.thenThrow(new AccountSettlementNotFoundException(UUID.fromString(ID)));

		when(accountCanonicoToAccountDto.convert(accountSettlementCanonico)).thenReturn(accountSettlementDto);

		mockMvc //
				.perform(get(ACTION_RECOVERY.replace(PARAM_ID, ID))) //
				.andDo(print()) //
				.andExpect(status() //
						.is4xxClientError());
	}

	@Test
	public void createAccountTestOK() throws Exception {
		final AccountSettlementDto accountDto = cretaeAccountSettlementDto();
		final String contentRequest = objectMapper.writeValueAsString(accountDto);

		final AccountSettlementCanonico accountCanonico = createAccountSettlementCanonico();
		when(accountDtoToAccountCanonico.convert(accountDto)).thenReturn(accountCanonico);

		final UUID uuid = UUID.fromString(ID);
		when(accountSettlementRemote.createAccount(accountCanonico)).thenReturn(uuid);

		final AccountIdDto accountIdDto = new AccountIdDto(uuid);
		final String contentResponse = objectMapper.writeValueAsString(accountIdDto);

		mockMvc //
				.perform(post(ACTION_ACCOUNT) //
						.contentType(MediaType.APPLICATION_JSON) //
						.characterEncoding(ENCOLDING) //
						.content(contentRequest)) //
				.andDo(print()) //
				.andExpect(status() //
						.is(201)) //
				.andExpect(content() //
						.json(contentResponse)) //
				.andExpect(content() //
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(content().encoding(ENCOLDING));
	}

	@Test
	public void createAccountTestInvalidDataWithAllNulls() throws Exception {
		final AccountSettlementDto accountDto = new AccountSettlementDto(null, null, null);
		final String contentRequest = objectMapper.writeValueAsString(accountDto);

		mockMvc //
				.perform(post(ACTION_ACCOUNT) //
						.contentType(MediaType.APPLICATION_JSON) //
						.characterEncoding(ENCOLDING) //
						.content(contentRequest)) //
				.andDo(print()) //
				.andExpect(status() //
						.is4xxClientError());
	}

	@Test
	public void createAccountTestInvalidDataWithNull() throws Exception {
		final AccountSettlementDto accountDto = null;
		final String contentRequest = objectMapper.writeValueAsString(accountDto);

		mockMvc //
				.perform(post(ACTION_ACCOUNT) //
						.contentType(MediaType.APPLICATION_JSON) //
						.characterEncoding(ENCOLDING) //
						.content(contentRequest)) //
				.andDo(print()) //
				.andExpect(status() //
						.is4xxClientError());
	}

	@Test
	public void createAccountTestInvalidDataWithInvalidContaContabil() throws Exception {
		final AccountSettlementDto accountDto = new AccountSettlementDto(null, DATA, VALOR);
		final String contentRequest = objectMapper.writeValueAsString(accountDto);

		mockMvc //
				.perform(post(ACTION_ACCOUNT) //
						.contentType(MediaType.APPLICATION_JSON) //
						.characterEncoding(ENCOLDING) //
						.content(contentRequest)) //
				.andDo(print()) //
				.andExpect(status() //
						.is4xxClientError());
	}

	@Test
	public void createAccountTestInvalidDataWithInvalidContaContabilNegativeValue() throws Exception {
		final AccountSettlementDto accountDto = new AccountSettlementDto(-1 * CONTA_CONTABIL, DATA, VALOR);
		final String contentRequest = objectMapper.writeValueAsString(accountDto);

		mockMvc //
				.perform(post(ACTION_ACCOUNT) //
						.contentType(MediaType.APPLICATION_JSON) //
						.characterEncoding(ENCOLDING) //
						.content(contentRequest)) //
				.andDo(print()) //
				.andExpect(status() //
						.is4xxClientError());
	}

	@Test
	public void createAccountTestInvalidDataWithInvalidContaContabilOverRangeValue() throws Exception {
		final AccountSettlementDto accountDto = new AccountSettlementDto(PRECISION_MAX * CONTA_CONTABIL, DATA, VALOR);
		final String contentRequest = objectMapper.writeValueAsString(accountDto);

		mockMvc //
				.perform(post(ACTION_ACCOUNT) //
						.contentType(MediaType.APPLICATION_JSON) //
						.characterEncoding(ENCOLDING) //
						.content(contentRequest)) //
				.andDo(print()) //
				.andExpect(status() //
						.is4xxClientError());
	}

	@Test
	public void createAccountTestInvalidDataWithInvalidValor() throws Exception {
		final AccountSettlementDto accountDto = new AccountSettlementDto(CONTA_CONTABIL, DATA, null);
		final String contentRequest = objectMapper.writeValueAsString(accountDto);

		mockMvc //
				.perform(post(ACTION_ACCOUNT) //
						.contentType(MediaType.APPLICATION_JSON) //
						.characterEncoding(ENCOLDING) //
						.content(contentRequest)) //
				.andDo(print()) //
				.andExpect(status() //
						.is4xxClientError());
	}

	@Test
	public void createAccountTestInvalidDataWithInvalidData() throws Exception {
		final AccountSettlementDto accountDto = new AccountSettlementDto(CONTA_CONTABIL, null, VALOR);
		final String contentRequest = objectMapper.writeValueAsString(accountDto);

		mockMvc //
				.perform(post(ACTION_ACCOUNT) //
						.contentType(MediaType.APPLICATION_JSON) //
						.characterEncoding(ENCOLDING) //
						.content(contentRequest)) //
				.andDo(print()) //
				.andExpect(status() //
						.is4xxClientError());
	}

	private Date parseDate(final Integer data) throws ParseException {
		return new SimpleDateFormat(DATE_MASK).parse(String.valueOf(data));
	}

	private Integer formatDate(final Date data) {
		return Integer.parseInt(new SimpleDateFormat(DATE_MASK).format(data));
	}

	private List<AccountSettlementCanonico> createAccountCanonicoList() throws ParseException {
		return Arrays.asList(new AccountSettlementCanonico(CONTA_CONTABIL, parseDate(DATA), VALOR),
				new AccountSettlementCanonico(CONTA_CONTABIL, parseDate(DATA), VALOR_1));
	}

	private AccountSettlementCanonico createAccountSettlementCanonico() throws ParseException {
		return new AccountSettlementCanonico(CONTA_CONTABIL, parseDate(DATA), VALOR);
	}

	private AccountSettlementDto cretaeAccountSettlementDto() {
		return new AccountSettlementDto(CONTA_CONTABIL, DATA, VALOR);
	}

}
