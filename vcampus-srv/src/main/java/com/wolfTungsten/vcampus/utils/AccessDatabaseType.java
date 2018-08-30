package com.wolfTungsten.vcampus.utils;

import com.j256.ormlite.db.BaseDatabaseType;

public class AccessDatabaseType extends BaseDatabaseType {
	private final static String DATABASE_URL_PORTION = "ucanaccess";
	private final static String DRIVER_CLASS_NAME = "net.ucanaccess.jdbc.UcanaccessDriver";
	private final static String DATABASE_NAME = "ucanaccess";

	@Override
	public boolean isDatabaseUrlThisType(String url, String dbTypePart) {
		return DATABASE_URL_PORTION.equals(dbTypePart);
	}

	@Override
	protected String getDriverClassName() {
		return DRIVER_CLASS_NAME;
	}

	@Override
	public String getDatabaseName() {
		return DATABASE_NAME;
	}
}
