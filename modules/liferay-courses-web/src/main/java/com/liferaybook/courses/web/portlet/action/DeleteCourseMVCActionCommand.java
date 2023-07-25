package com.liferaybook.courses.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferaybook.courses.manager.service.CourseLocalService;
import com.liferaybook.courses.web.constants.CourseMVCCommandKeys;
import com.liferaybook.courses.web.constants.LiferayCoursesAdminPortletKeys;
import com.liferaybook.courses.web.constants.LiferayCoursesConstants;
import com.liferaybook.courses.web.constants.LiferayCoursesPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Component(
    immediate = true,
    property = {
        "javax.portlet.name=" + LiferayCoursesPortletKeys.PORTLET_ID,
        "javax.portlet.name=" + LiferayCoursesAdminPortletKeys.PORTLET_ID,
        "mvc.command.name=" + CourseMVCCommandKeys.DELETE_COURSE
    },
    service = MVCActionCommand.class
)
public class DeleteCourseMVCActionCommand extends BaseMVCActionCommand {

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) {
        try {
            long courseId = ParamUtil.getLong(actionRequest, LiferayCoursesConstants.COURSE_ID);
            courseLocalService.deleteCourse(courseId);
        } catch (Exception e) {
            SessionErrors.add(actionRequest, e.getClass());
            hideDefaultErrorMessage(actionRequest);
        }
    }

    @Reference
    private CourseLocalService courseLocalService;

}