package com.liferaybook.courses.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferaybook.courses.api.LiferayCoursesAPI;
import com.liferaybook.courses.manager.model.Lecture;
import com.liferaybook.courses.web.constants.LiferayCoursesAdminPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Component(
    immediate = true,
    property = {
        "javax.portlet.name=" + LiferayCoursesAdminPortletKeys.PORTLET_ID,
        "mvc.command.name=/courses/edit_lecture"
    },
    service = MVCActionCommand.class
)
public class EditLectureMVCActionCommand extends BaseMVCActionCommand {

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) {
        try {
            long courseId = ParamUtil.getLong(actionRequest, "courseId");
            long lectureId = ParamUtil.getLong(actionRequest, "lectureId");
            String name = ParamUtil.getString(actionRequest, "name");
            String videoLink = ParamUtil.getString(actionRequest, "videoLink");
            String description = ParamUtil.getString(actionRequest, "description");
            long userId = portal.getUserId(actionRequest);
            ServiceContext serviceContext = ServiceContextFactory.getInstance(Lecture.class.getName(), actionRequest);
            if (lectureId > 0) {
                liferayCoursesAPI.updateLecture(userId, lectureId, name, description, videoLink, serviceContext);
            } else {
                liferayCoursesAPI.saveLecture(userId, courseId, name, description, videoLink, serviceContext);
            }
            actionResponse.getRenderParameters().setValue("mvcRenderCommandName", "/courses/view_lectures");
        } catch (Exception e) {
            SessionErrors.add(actionRequest, e.getClass());
            actionRequest.setAttribute("errorMsg", e.getMessage());
            actionResponse.getRenderParameters().setValue("mvcRenderCommandName", "/courses/edit_lecture");
            hideDefaultErrorMessage(actionRequest);
        }
    }

    @Reference
    private Portal portal;
    @Reference
    private LiferayCoursesAPI liferayCoursesAPI;

}