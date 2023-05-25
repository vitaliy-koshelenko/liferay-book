package com.liferaybook.courses.internal.upgrade.v_2_0_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringUtil;

public class CoursesLectureUpgradeProcess extends UpgradeProcess {

    @Override
    protected void doUpgrade() throws Exception {
        String template = StringUtil.read(CoursesLectureUpgradeProcess.class.getResourceAsStream("dependencies/update.sql"));
        runSQLTemplateString(template, false);
    }

}