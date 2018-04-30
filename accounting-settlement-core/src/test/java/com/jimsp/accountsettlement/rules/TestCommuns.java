package com.jimsp.accountsettlement.rules;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.jimsp.accountsettlement.canonico.SummaryAccountSettlementCanonico;
import com.jimsp.accountsettlement.model.AccountSettlementData;

public class TestCommuns {

	public static final Long CONTA_CONTABIL = 1111001L;
	public static final Date DATA = new Date();
	public static final BigDecimal VALOR1 = BigDecimal.TEN;
	public static final BigDecimal VALOR2 = BigDecimal.ONE;

	@SafeVarargs
	public static <T> Supplier<Stream<T>> createSupplierStream(final T... item) {
		return () -> Arrays.asList(item).stream();
	}

	public static List<AccountSettlementData> createAccountSettlementDataList() {
		final AccountSettlementData data1 = new AccountSettlementData();
		data1.setContaContabil(CONTA_CONTABIL);
		data1.setData(DATA);
		data1.setId(UUID.randomUUID());
		data1.setValor(new BigDecimal(5L));

		final AccountSettlementData data2 = new AccountSettlementData();
		data2.setContaContabil(CONTA_CONTABIL);
		data2.setData(DATA);
		data2.setId(UUID.randomUUID());
		data2.setValor(new BigDecimal(3L));

		final AccountSettlementData data3 = new AccountSettlementData();
		data3.setContaContabil(CONTA_CONTABIL);
		data3.setData(DATA);
		data3.setId(UUID.randomUUID());
		data3.setValor(BigDecimal.ONE);

		final AccountSettlementData data4 = new AccountSettlementData();
		data4.setContaContabil(CONTA_CONTABIL);
		data4.setData(DATA);
		data4.setId(UUID.randomUUID());
		data4.setValor(BigDecimal.ONE);

		return Arrays.asList(data1, data2, data3, data4);
	}

	public static SummaryAccountSettlementCanonico createSummaryAccountSettlementCanonico() {
		return new SummaryAccountSettlementCanonico(BigDecimal.TEN, BigDecimal.ONE, BigDecimal.valueOf(5L),
				BigDecimal.valueOf(2.50D), BigInteger.valueOf(4L));
	}

}
