package com.liferaybook.courses.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferaybook.courses.api.LiferayCoursesAPI;
import com.liferaybook.courses.web.constants.LiferayCoursesPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Component(
    immediate = true,
    property = {
        "javax.portlet.name=" + LiferayCoursesPortletKeys.PORTLET_ID,
        "mvc.command.name=/courses/edit_course"
    },
    service = MVCActionCommand.class
)
public class EditCourseMVCActionCommand extends BaseMVCActionCommand {

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) {
        try {
            long courseId = ParamUtil.getLong(actionRequest, "courseId");
            String name = ParamUtil.getString(actionRequest, "name");

            if (Validator.isBlank(name) || name.trim().length() < 5) {
                SessionErrors.add(actionRequest, "courseNameNotValid");
                actionResponse.getRenderParameters().setValue("mvcRenderCommandName", "/courses/edit_course");
                hideDefaultErrorMessage(actionRequest);
                return;
            }

            long userId = portal.getUserId(actionRequest);
            String description = ParamUtil.getString(actionRequest, "description");
            ServiceContext serviceContext = ServiceContextFactory.getInstance("com.liferaybook.courses.manager.model.Course", actionRequest);
            if (courseId > 0) {
                liferayCoursesAPI.updateCourse(userId, courseId, name, description, serviceContext);
            } else {
                long groupId = portal.getScopeGroupId(actionRequest);
                liferayCoursesAPI.saveCourse(userId, groupId, name, description, serviceContext);
            }

        } catch (Exception e) {
            SessionErrors.add(actionRequest, e.getClass());
            actionResponse.getRenderParameters().setValue("mvcRenderCommandName", "/courses/edit_course");
            hideDefaultErrorMessage(actionRequest);
        }
    }

    @Reference
    private Portal portal;
    @Reference
    private LiferayCoursesAPI liferayCoursesAPI;

}