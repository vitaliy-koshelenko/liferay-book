package com.liferaybook.courses.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
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
        long courseId = ParamUtil.getLong(actionRequest, "courseId");
        String name = ParamUtil.getString(actionRequest, "name");
        String description = ParamUtil.getString(actionRequest, "description");
        if (courseId > 0) {
            liferayCoursesAPI.updateCourse(courseId, name, description);
        } else {
            liferayCoursesAPI.saveCourse(name, description);
        }
    }

    @Reference
    private LiferayCoursesAPI liferayCoursesAPI;

}