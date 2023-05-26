package com.liferaybook.courses.internal.upgrade.v2_1_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringUtil;

public class CourseSubscriptionUpgradeProcess extends UpgradeProcess {

    @Override
    protected void doUpgrade() throws Exception {
        String template = StringUtil.read(CourseSubscriptionUpgradeProcess.class.getResourceAsStream("dependencies/update.sql"));
        runSQLTemplateString(template, false);
    }

}