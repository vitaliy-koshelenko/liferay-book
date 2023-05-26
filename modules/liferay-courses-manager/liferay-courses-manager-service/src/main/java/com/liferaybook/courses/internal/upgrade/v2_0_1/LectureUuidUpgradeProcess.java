package com.liferaybook.courses.internal.upgrade.v2_0_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

public class LectureUuidUpgradeProcess extends UpgradeProcess {

    @Override
    protected void doUpgrade() throws Exception {
        alterTableAddColumn("lb_Lecture", "uuid_", "VARCHAR(75)");
    }

}
