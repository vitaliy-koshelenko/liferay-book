package com.liferaybook.courses.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferaybook.courses.manager.service.CourseSubscriptionLocalService;
import com.liferaybook.courses.web.constants.LiferayCoursesConstants;
import com.liferaybook.courses.web.constants.MyLiferayCoursesPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Component(
    immediate = true,
    property = {
        "javax.portlet.name=" + MyLiferayCoursesPortletKeys.PORTLET_ID,
        "mvc.command.name=/courses/subscribe"
    },
    service = MVCActionCommand.class
)
public class SubscribeMVCActionCommand extends BaseMVCActionCommand {

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) {
        try {
            long userId = portal.getUserId(actionRequest);
            long courseId = ParamUtil.getLong(actionRequest, LiferayCoursesConstants.COURSE_ID);
            boolean isSubscribe = ParamUtil.getBoolean(actionRequest, LiferayCoursesConstants.SUBSCRIBE);
            if (isSubscribe) {
                courseSubscriptionLocalService.addSubscription(userId, courseId);
            } else {
                courseSubscriptionLocalService.removeSubscription(userId, courseId);
            }
        } catch (Exception e) {
            SessionErrors.add(actionRequest, e.getClass());
            hideDefaultErrorMessage(actionRequest);
        }
    }

    @Reference
    private Portal portal;
    @Reference
    private CourseSubscriptionLocalService courseSubscriptionLocalService;

}