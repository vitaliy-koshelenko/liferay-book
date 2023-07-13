package com.liferaybook.courses.web.info.filter;

import com.liferay.info.filter.InfoFilterProvider;
import com.liferay.portal.kernel.util.StringUtil;
import org.osgi.service.component.annotations.Component;

import java.util.Map;

@Component(immediate = true, service = InfoFilterProvider.class)
public class AuthorInfoFilterProvider implements InfoFilterProvider<AuthorInfoFilter> {

	@Override
	public AuthorInfoFilter create(Map<String, String[]> values) {
		AuthorInfoFilter infoFilterAuthor = new AuthorInfoFilter();
		for (Map.Entry<String, String[]> entry : values.entrySet()) {
			if (StringUtil.startsWith(entry.getKey(), AuthorInfoFilter.FILTER_TYPE_NAME + "_")) {
				infoFilterAuthor.setAuthor(entry.getValue()[0]);
			}
		}
		return infoFilterAuthor;
	}

}