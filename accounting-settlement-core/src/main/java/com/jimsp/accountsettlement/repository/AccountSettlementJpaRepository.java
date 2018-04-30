package com.jimsp.accountsettlement.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jimsp.accountsettlement.model.AccountSettlementData;

@Repository
public interface AccountSettlementJpaRepository extends JpaRepository<AccountSettlementData, UUID>{

	public List<AccountSettlementData> findByContaContabilOrderByIdDesc(final Long contaContabil);

}
