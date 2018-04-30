package com.jimsp.accountsettlement.exceptions;

import java.util.UUID;

public class AccountSettlementNotFoundException extends IllegalArgumentException {

	private static final long serialVersionUID = 7962502403045514773L;

	private final UUID id;

	public AccountSettlementNotFoundException(final UUID id) {
		super(String.format("%s not exist!", id.toString()));
		this.id = id;
	}

	public UUID getId() {
		return id;
	}
}
