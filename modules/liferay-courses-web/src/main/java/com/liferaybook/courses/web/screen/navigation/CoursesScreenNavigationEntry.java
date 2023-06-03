package com.liferaybook.courses.web.screen.navigation;

import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferaybook.courses.manager.model.Course;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(
        property = "screen.navigation.entry.order:Integer=10",
        service = ScreenNavigationEntry.class
)
public class CoursesScreenNavigationEntry extends CoursesScreenNavigationCategory implements ScreenNavigationEntry<Course> {

    @Override
    public String getEntryKey() {
        return getCategoryKey();
    }

    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) throws IOException {
        jspRenderer.renderJSP(request, response,"/courses_admin/screens/courses.jsp");
    }

    @Reference
    protected JSPRenderer jspRenderer;

}
