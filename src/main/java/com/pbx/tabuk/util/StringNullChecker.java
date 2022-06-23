package com.pbx.tabuk.util;

import java.util.Objects;

public class StringNullChecker {

	private StringNullChecker() {

	}
	public static String getNonNullStringValue( final String string ) {
		return Objects.isNull( string ) ? "" : string.trim() + " ";
	}
}
