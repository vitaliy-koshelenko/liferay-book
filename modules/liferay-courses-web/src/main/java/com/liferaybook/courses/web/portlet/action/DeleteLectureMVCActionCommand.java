package com.liferaybook.courses.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferaybook.courses.manager.service.LectureLocalService;
import com.liferaybook.courses.web.constants.CourseMVCCommandKeys;
import com.liferaybook.courses.web.constants.LiferayCoursesAdminPortletKeys;
import com.liferaybook.courses.web.constants.LiferayCoursesConstants;
import com.liferaybook.courses.web.constants.LiferayCoursesPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.util.Objects;

@Component(
    immediate = true,
    property = {
        "javax.portlet.name=" + LiferayCoursesPortletKeys.PORTLET_ID,
        "javax.portlet.name=" + LiferayCoursesAdminPortletKeys.PORTLET_ID,
        "mvc.command.name=" + CourseMVCCommandKeys.DELETE_LECTURE
    },
    service = MVCActionCommand.class
)
public class DeleteLectureMVCActionCommand extends BaseMVCActionCommand implements CoursesMVCCommand {

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) {
        try {
            long courseId = ParamUtil.getLong(actionRequest, LiferayCoursesConstants.COURSE_ID);
            long lectureId = ParamUtil.getLong(actionRequest, LiferayCoursesConstants.LECTURE_ID);
            actionRequest.setAttribute(LiferayCoursesConstants.COURSE_ID, courseId);
            lectureLocalService.deleteLecture(lectureId);
        } catch (Exception e) {
            SessionErrors.add(actionRequest, e.getClass());
            hideDefaultErrorMessage(actionRequest);
        } finally {
            boolean isAdminPortlet = Objects.equals(getPortletId(actionRequest), LiferayCoursesAdminPortletKeys.PORTLET_ID);
            String mvcRenderCommandName = isAdminPortlet ? CourseMVCCommandKeys.COURSE_LECTURES : CourseMVCCommandKeys.COURSE_DETAILS;
            actionResponse.getRenderParameters().setValue(MVC_RENDER_COMMAND_NAME, mvcRenderCommandName);
        }
    }

    @Reference
    private LectureLocalService lectureLocalService;

}