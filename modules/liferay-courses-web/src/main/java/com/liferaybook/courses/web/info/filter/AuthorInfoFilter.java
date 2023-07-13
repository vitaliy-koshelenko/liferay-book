package com.liferaybook.courses.web.info.filter;

import com.liferay.info.filter.InfoFilter;

public class AuthorInfoFilter implements InfoFilter {
	public static final String FILTER_TYPE_NAME = "author";

	private String author;

	@Override
	public String getFilterTypeName() {
		return FILTER_TYPE_NAME;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}