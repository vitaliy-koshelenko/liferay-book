package com.liferaybook.courses.internal.upgrade.v2_0_2;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

public class LectureAuditUpgradeProcess extends UpgradeProcess {

    @Override
    protected void doUpgrade() throws Exception {
        alterTableAddColumn("lb_Lecture", "userId", "LONG");
        alterTableAddColumn("lb_Lecture", "userName", "VARCHAR(75)");
        alterTableAddColumn("lb_Lecture", "createDate", "DATE");
        alterTableAddColumn("lb_Lecture", "modifiedDate", "DATE");
    }

}