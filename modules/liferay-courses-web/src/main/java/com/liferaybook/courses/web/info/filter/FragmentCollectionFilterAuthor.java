package com.liferaybook.courses.web.info.filter;

import com.liferay.fragment.collection.filter.FragmentCollectionFilter;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.renderer.FragmentRendererContext;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferaybook.courses.manager.service.CourseLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

@Component
public class FragmentCollectionFilterAuthor implements FragmentCollectionFilter {

	@Override
	public String getFilterKey() {
		return AuthorInfoFilter.FILTER_TYPE_NAME;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "author");
	}

	@Override
	public void render(FragmentRendererContext context, HttpServletRequest request, HttpServletResponse response) {
		try {
			long groupId = portal.getScopeGroupId(request);

			List<String> authors = courseLocalService.getCourseAuthorNames(groupId);
			request.setAttribute("authors", authors);

			FragmentEntryLink fragmentEntryLink = context.getFragmentEntryLink();
			long fragmentEntryLinkId = fragmentEntryLink.getFragmentEntryLinkId();

			String filterName = "filter_author_" + fragmentEntryLinkId;
			request.setAttribute("filterName", filterName);

			String filterAuthor = ParamUtil.getString(request, filterName);
			request.setAttribute("filterAuthor", filterAuthor);

			RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/courses/filter/author.jsp");
			requestDispatcher.include(request, response);
		}
		catch (Exception exception) {
			_log.error("Unable to render author filter", exception);
		}
	}

	@Reference
	private Portal portal;
	@Reference(target = "(osgi.web.symbolicname=com.liferaybook.courses.web)")
	private ServletContext servletContext;
	@Reference
	private CourseLocalService courseLocalService;

	private static final Log _log = LogFactoryUtil.getLog(FragmentCollectionFilterAuthor.class);

}