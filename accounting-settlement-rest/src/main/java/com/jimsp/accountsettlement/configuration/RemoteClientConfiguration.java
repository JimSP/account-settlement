package com.jimsp.accountsettlement.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import com.jimsp.accountsettlement.remote.AccountSettlementRemote;

@Configuration
public class RemoteClientConfiguration {

	@Value("${remote.account-settlement-url:http://localhost:8090}")
	private String accountSettlementUrl;

	@Bean
	public HttpInvokerProxyFactoryBean invoker() {
		final HttpInvokerProxyFactoryBean httpInvokerProxyFactoryBean = new HttpInvokerProxyFactoryBean();
		httpInvokerProxyFactoryBean.setServiceUrl(String.format("%s/accountSettlement", accountSettlementUrl));
		httpInvokerProxyFactoryBean.setServiceInterface(AccountSettlementRemote.class);
		return httpInvokerProxyFactoryBean;
	}
}
