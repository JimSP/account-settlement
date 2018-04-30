package com.jimsp.accountsettlement.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import com.jimsp.accountsettlement.remote.AccountSettlementRemote;
import com.jimsp.accountsettlement.service.AccountSettlementService;

@Configuration
public class RemoteConfiguration {

	@Bean("/accountSettlement")
	public HttpInvokerServiceExporter accountSettlement() {
		HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
		exporter.setService(new AccountSettlementService());
		exporter.setServiceInterface(AccountSettlementRemote.class);
		return exporter;
	}
}
