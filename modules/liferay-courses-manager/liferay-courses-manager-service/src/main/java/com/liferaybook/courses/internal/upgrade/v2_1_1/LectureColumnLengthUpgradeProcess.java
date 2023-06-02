package com.liferaybook.courses.internal.upgrade.v2_1_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

public class LectureColumnLengthUpgradeProcess extends UpgradeProcess {

    @Override
    protected void doUpgrade() throws Exception {
        alterColumnType("lb_Lecture", "name", "VARCHAR(100)");
        alterColumnType("lb_Lecture", "description", "VARCHAR(1000)");
        alterColumnType("lb_Lecture", "videoLink", "VARCHAR(100)");
    }

}