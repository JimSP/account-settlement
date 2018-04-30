package com.jimsp.accountsettlement.rules;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.UUID;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jimsp.accountsettlement.canonico.AccountSettlementCanonico;

@Component
@Scope("singleton")
public class CreateAccountID {

	private static final int RANDOM_LENGTH = 89;
	private static final String LEFT_PAD_CHAR = "0";
	private static final String MASK = "yyyyMMdd";
	private static final String ALGORITHM = "SHA1PRNG";

	@Autowired
	private ValidateAccount validateAccount;

	private final SecureRandom secureRandom;

	public CreateAccountID() throws NoSuchAlgorithmException {
		this.secureRandom = SecureRandom.getInstance(ALGORITHM);
	}

	public UUID create(final AccountSettlementCanonico accountSettlementCanonico) {
		validateAccount.validate(accountSettlementCanonico);

		final String contaContabil = StringUtils.leftPad(accountSettlementCanonico.getContaContabil().toString(), 19, LEFT_PAD_CHAR);
		final String data = new SimpleDateFormat(MASK).format(accountSettlementCanonico.getData());
		final String valor = StringUtils.leftPad(accountSettlementCanonico.getValor().toPlainString(), 12, LEFT_PAD_CHAR);

		final byte[] randomByteArray = new byte[RANDOM_LENGTH];
		secureRandom.nextBytes(randomByteArray);

		return UUID.nameUUIDFromBytes(ArrayUtils.addAll(randomByteArray,
				StringUtils.appendIfMissingIgnoreCase(contaContabil, data, valor).getBytes()));
	}
}
