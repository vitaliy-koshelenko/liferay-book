package com.liferaybook.courses.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferaybook.courses.manager.model.Lecture;
import com.liferaybook.courses.manager.service.LectureLocalService;
import com.liferaybook.courses.web.constants.CourseMVCCommandKeys;
import com.liferaybook.courses.web.constants.LiferayCoursesAdminPortletKeys;
import com.liferaybook.courses.web.constants.LiferayCoursesPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import java.util.Objects;

import static com.liferaybook.courses.web.constants.LiferayCoursesConstants.*;

@Component(
    immediate = true,
    property = {
        "javax.portlet.name=" + LiferayCoursesPortletKeys.PORTLET_ID,
        "javax.portlet.name=" + LiferayCoursesAdminPortletKeys.PORTLET_ID,
            "mvc.command.name=" + CourseMVCCommandKeys.EDIT_LECTURE
    },
    service = MVCActionCommand.class
)
public class EditLectureMVCActionCommand extends BaseMVCActionCommand implements CoursesMVCCommand {

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) {
        try {
            long courseId = ParamUtil.getLong(actionRequest, COURSE_ID);
            long lectureId = ParamUtil.getLong(actionRequest, LECTURE_ID);
            String name = ParamUtil.getString(actionRequest, NAME);
            String videoLink = ParamUtil.getString(actionRequest, VIDEO_LINK);
            String urlTitle = ParamUtil.getString(actionRequest, URL_TITLE);
            String description = ParamUtil.getString(actionRequest, DESCRIPTION);
            long userId = portal.getUserId(actionRequest);
            ServiceContext serviceContext = ServiceContextFactory.getInstance(Lecture.class.getName(), actionRequest);
            if (lectureId > 0) {
                lectureLocalService.updateLecture(userId, lectureId, name, description, videoLink, urlTitle, serviceContext);
            } else {
                lectureLocalService.addLecture(userId, courseId, name, description, videoLink, urlTitle, serviceContext);
            }
            boolean isAdminPortlet = Objects.equals(getPortletId(actionRequest), LiferayCoursesAdminPortletKeys.PORTLET_ID);
            String mvcRenderCommandName = isAdminPortlet ? CourseMVCCommandKeys.COURSE_LECTURES : CourseMVCCommandKeys.COURSE_DETAILS;
            actionResponse.getRenderParameters().setValue(MVC_RENDER_COMMAND_NAME, mvcRenderCommandName);
        } catch (Exception e) {
            SessionErrors.add(actionRequest, e.getClass());
            actionRequest.setAttribute(ERROR_MSG, e.getMessage());
            actionResponse.getRenderParameters().setValue(MVC_RENDER_COMMAND_NAME, CourseMVCCommandKeys.EDIT_LECTURE);
            hideDefaultErrorMessage(actionRequest);
        }
    }

    @Reference
    private Portal portal;
    @Reference
    private LectureLocalService lectureLocalService;

}