package com.liferaybook.courses.web.info.item.renderer;

import com.liferay.info.item.renderer.InfoItemRenderer;
import com.liferay.portal.kernel.language.Language;
import com.liferaybook.courses.manager.model.Course;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public abstract class BaseCourseInfoItemRenderer implements InfoItemRenderer<Course> {

	protected static final String ABSTRACT_LABEL_KEY = "abstract";
	protected static final String ABSTRACT_JSP = "abstract.jsp";
	protected static final String FULL_CONTENT_LABEL_KEY = "full-content";
	protected static final String FULL_CONTENT_JSP_NAME = "full_content.jsp";
	protected static final String CARD_LABEL_KEY = "card";
	protected static final String CARD_JSP = "card.jsp";

	@Override
	public String getLabel(Locale locale) {
		return language.get(locale, getLabelKey());
	}

	@Override
	public void render(Course course, HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("course", course);
			String jspPath = "/courses/info/item/renderer/" + getJspName();
			RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
			requestDispatcher.include(request, response);
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Reference
	protected Language language;
	@Reference(target = "(osgi.web.symbolicname=com.liferaybook.courses.web)")
	protected ServletContext servletContext;

	public abstract String getLabelKey();
	public abstract String getJspName();

}