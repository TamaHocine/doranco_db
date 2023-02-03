package fr.doranco.salaires.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Dates {
	
	private static final String formatDate = "dd/MM/yyyy";

	public static final Date stringToDate(String dateStr) throws Exception {
		
		SimpleDateFormat formatter = new SimpleDateFormat(formatDate);
		return formatter.parse(dateStr);
	}
	
	public static final String dateToString(Date date) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat(formatDate);
		return formatter.format(date);
	}
	

	public static final java.util.Date dateSqlToUtil(java.sql.Date dateSql) {
		return new java.util.Date(dateSql.getTime());
	}
	
	public static final java.sql.Date dateUtilToSql(java.util.Date dateUtil) {
		return new java.sql.Date(dateUtil.getTime());
	}

}