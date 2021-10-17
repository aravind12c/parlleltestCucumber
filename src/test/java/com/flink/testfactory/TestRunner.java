package com.flink.testfactory;

import org.testng.annotations.DataProvider;

import com.flink.driverInit.DriverInitialization;

public class TestRunner extends DriverInitialization {

	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}

}
