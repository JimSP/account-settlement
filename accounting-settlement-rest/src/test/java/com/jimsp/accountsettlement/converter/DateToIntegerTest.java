package com.jimsp.accountsettlement.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DateToIntegerTest {

	@InjectMocks
	private DateToInteger dateToInteger;

	@Test
	public void convert() {
		final Date date = new Date(1L);
		final Integer data = dateToInteger.convert(date);
		Assert.assertNotNull(data);
		Assert.assertEquals(Integer.valueOf(new SimpleDateFormat("yyyyMMdd").format(date)), data);
	}
}
