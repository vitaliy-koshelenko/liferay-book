package com.liferaybook.courses.web.info.list.renderer;

import com.liferay.info.item.renderer.InfoItemRenderer;
import com.liferay.info.item.renderer.InfoItemRendererRegistry;
import com.liferay.info.list.renderer.DefaultInfoListRendererContext;
import com.liferay.info.list.renderer.InfoListRendererContext;
import com.liferay.info.taglib.list.renderer.BasicInfoListRenderer;
import com.liferay.info.taglib.servlet.taglib.InfoListBasicListTag;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.web.info.item.renderer.CourseAbstractInfoItemRenderer;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public abstract class BaseCourseBasicInfoListRenderer implements BasicInfoListRenderer<Course> {

	@Override
	public List<InfoItemRenderer<?>> getAvailableInfoItemRenderers() {
		return infoItemRendererRegistry.getInfoItemRenderers(Course.class.getName());
	}

	@Override
	public void render(List<Course> courses, HttpServletRequest request, HttpServletResponse response) {
		render(courses, new DefaultInfoListRendererContext(request, response));
	}

	@Override
	public void render(List<Course> courses, InfoListRendererContext context) {
		InfoListBasicListTag infoListBasicListTag = new InfoListBasicListTag();
		infoListBasicListTag.setInfoListObjects(courses);
		String listItemRendererKey = context.getListItemRendererKey();
		if (Validator.isNotNull(listItemRendererKey)) {
			infoListBasicListTag.setItemRendererKey(listItemRendererKey);
		}
		else {
			infoListBasicListTag.setItemRendererKey(CourseAbstractInfoItemRenderer.class.getName());
		}
		infoListBasicListTag.setListStyleKey(getListStyle());
		try {
			infoListBasicListTag.doTag(context.getHttpServletRequest(), context.getHttpServletResponse());
		}
		catch (Exception exception) {
			_log.error("Unable to render blog entries list", exception);
		}
	}

	@Reference
	protected InfoItemRendererRegistry infoItemRendererRegistry;

	private static final Log _log = LogFactoryUtil.getLog(BaseCourseBasicInfoListRenderer.class);

}