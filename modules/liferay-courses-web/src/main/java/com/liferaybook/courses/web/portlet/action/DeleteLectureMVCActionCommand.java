package com.liferaybook.courses.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferaybook.courses.api.LiferayCoursesAPI;
import com.liferaybook.courses.web.constants.LiferayCoursesAdminPortletKeys;
import com.liferaybook.courses.web.constants.LiferayCoursesConstants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Component(
    immediate = true,
    property = {
        "javax.portlet.name=" + LiferayCoursesAdminPortletKeys.PORTLET_ID,
        "mvc.command.name=/courses/delete_lecture"
    },
    service = MVCActionCommand.class
)
public class DeleteLectureMVCActionCommand extends BaseMVCActionCommand {

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) {
        try {
            long courseId = ParamUtil.getLong(actionRequest, LiferayCoursesConstants.COURSE_ID);
            long lectureId = ParamUtil.getLong(actionRequest, LiferayCoursesConstants.LECTURE_ID);
            actionRequest.setAttribute(LiferayCoursesConstants.COURSE_ID, courseId);
            liferayCoursesAPI.deleteLecture(lectureId);
        } catch (Exception e) {
            SessionErrors.add(actionRequest, e.getClass());
            hideDefaultErrorMessage(actionRequest);
        } finally {
            actionResponse.getRenderParameters().setValue("mvcRenderCommandName", "/courses/view_lectures");
            actionResponse.getRenderParameters().setValue("screenNavigationCategoryKey", "lectures");
        }
    }

    @Reference
    private LiferayCoursesAPI liferayCoursesAPI;

}