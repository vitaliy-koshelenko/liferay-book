package com.liferaybook.courses.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferaybook.courses.api.LiferayCoursesAPI;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.web.constants.LiferayCoursesAdminPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import static com.liferaybook.courses.web.constants.LiferayCoursesConstants.*;

@Component(
    immediate = true,
    property = {
        "javax.portlet.name=" + LiferayCoursesAdminPortletKeys.PORTLET_ID,
        "mvc.command.name=/courses/edit_course"
    },
    service = MVCActionCommand.class
)
public class EditCourseMVCActionCommand extends BaseMVCActionCommand {

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) {
        try {
            long courseId = ParamUtil.getLong(actionRequest, COURSE_ID);
            String name = ParamUtil.getString(actionRequest, NAME);
            String description = ParamUtil.getString(actionRequest, DESCRIPTION);
            String urlTitle = ParamUtil.getString(actionRequest, URL_TITLE);
            long userId = portal.getUserId(actionRequest);
            ServiceContext serviceContext = ServiceContextFactory.getInstance(Course.class.getName(), actionRequest);
            if (courseId > 0) {
                liferayCoursesAPI.updateCourse(userId, courseId, name, description, urlTitle, serviceContext);
            } else {
                long groupId = portal.getScopeGroupId(actionRequest);
                liferayCoursesAPI.saveCourse(userId, groupId, name, description, urlTitle, serviceContext);
            }
        } catch (Exception e) {
            SessionErrors.add(actionRequest, e.getClass());
            actionRequest.setAttribute(ERROR_MSG, e.getMessage());
            actionResponse.getRenderParameters().setValue("mvcRenderCommandName", "/courses/edit_course");
            hideDefaultErrorMessage(actionRequest);
        }
    }

    @Reference
    private Portal portal;
    @Reference
    private LiferayCoursesAPI liferayCoursesAPI;

}