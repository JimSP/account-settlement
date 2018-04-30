package com.jimsp.accountsettlement.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateToInteger {

	public Integer convert(final Date data) {
		return Integer.valueOf(new SimpleDateFormat("yyyyMMdd").format(data));
	}
}
