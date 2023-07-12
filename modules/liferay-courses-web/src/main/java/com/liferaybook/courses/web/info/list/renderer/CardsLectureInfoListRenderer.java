package com.liferaybook.courses.web.info.list.renderer;

import com.liferay.info.list.renderer.InfoListRenderer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferaybook.courses.manager.model.Lecture;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

@Component(service = InfoListRenderer.class)
public class CardsLectureInfoListRenderer implements InfoListRenderer<Lecture> {

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "cards");
	}

	@Override
	public void render(List<Lecture> lectures, HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("lectures", lectures);
			RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/courses/info/item/renderer/lecture_cards.jsp");
			requestDispatcher.include(request, response);
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Reference(target = "(osgi.web.symbolicname=com.liferaybook.courses.web)")
	private ServletContext servletContext;
}