package com.liferaybook.courses.web.layout.display.page;

import com.liferay.info.item.ClassPKInfoItemIdentifier;
import com.liferay.info.item.GroupUrlTitleInfoItemIdentifier;
import com.liferay.info.item.InfoItemIdentifier;
import com.liferay.info.item.InfoItemReference;
import com.liferay.layout.display.page.LayoutDisplayPageObjectProvider;
import com.liferay.layout.display.page.LayoutDisplayPageProvider;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.manager.service.CourseLocalService;
import com.liferaybook.courses.web.constants.FriendlyURLConstants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        property = "service.ranking:Integer=99",
        service = LayoutDisplayPageProvider.class
)
public class CourseLayoutDisplayPageProvider implements LayoutDisplayPageProvider<Course> {

    @Override
    public String getClassName() {
        return Course.class.getName();
    }

    @Override
    public LayoutDisplayPageObjectProvider<Course> getLayoutDisplayPageObjectProvider(InfoItemReference infoItemReference) {
        InfoItemIdentifier infoItemIdentifier = infoItemReference.getInfoItemIdentifier();
        Course course = null;
        if (infoItemIdentifier instanceof ClassPKInfoItemIdentifier) {
            long courseId = ((ClassPKInfoItemIdentifier) infoItemIdentifier).getClassPK();
            course = courseLocalService.fetchCourse(courseId);
        } else if (infoItemIdentifier instanceof GroupUrlTitleInfoItemIdentifier) {
            GroupUrlTitleInfoItemIdentifier identifier = (GroupUrlTitleInfoItemIdentifier) infoItemIdentifier;
            course = courseLocalService.getCourseByUrlTitle(identifier.getGroupId(), identifier.getUrlTitle());
        }
        return new CourseLayoutDisplayPageObjectProvider(course);
    }

    @Override
    public LayoutDisplayPageObjectProvider<Course> getLayoutDisplayPageObjectProvider(long groupId, String urlTitle) {
        Course course = courseLocalService.getCourseByUrlTitle(groupId, urlTitle);
        return new CourseLayoutDisplayPageObjectProvider(course);
    }

    @Override
    public String getURLSeparator() {
        return FriendlyURLConstants.URL_SEPARATOR_COURSE;
    }

    @Reference
    private CourseLocalService courseLocalService;
}