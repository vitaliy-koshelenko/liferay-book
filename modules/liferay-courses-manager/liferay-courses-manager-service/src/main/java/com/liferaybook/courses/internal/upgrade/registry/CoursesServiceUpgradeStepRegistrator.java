package com.liferaybook.courses.internal.upgrade.registry;

import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferaybook.courses.internal.upgrade.v_2_0_0.CoursesLectureUpgradeProcess;
import org.osgi.service.component.annotations.Component;

@Component(service = UpgradeStepRegistrator.class)
public class CoursesServiceUpgradeStepRegistrator implements UpgradeStepRegistrator {

    @Override
    public void register(Registry registry) {
        registry.register("1.0.0", "2.0.0", new CoursesLectureUpgradeProcess());
    }

}