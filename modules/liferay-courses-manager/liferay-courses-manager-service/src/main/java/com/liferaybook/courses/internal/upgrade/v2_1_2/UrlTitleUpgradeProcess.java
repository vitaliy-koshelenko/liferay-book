package com.liferaybook.courses.internal.upgrade.v2_1_2;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

public class UrlTitleUpgradeProcess extends UpgradeProcess {

    @Override
    protected void doUpgrade() throws Exception {
        alterTableAddColumn("lb_Course", "urlTitle", "VARCHAR(75)");
        alterTableAddColumn("lb_Lecture", "urlTitle", "VARCHAR(75)");
    }

}
