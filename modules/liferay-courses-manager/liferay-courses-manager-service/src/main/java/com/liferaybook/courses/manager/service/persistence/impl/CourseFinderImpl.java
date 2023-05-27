package com.liferaybook.courses.manager.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.manager.model.impl.CourseImpl;
import com.liferaybook.courses.manager.service.persistence.CourseFinder;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;

@Component(service = CourseFinder.class)
public class CourseFinderImpl extends CourseFinderBaseImpl implements CourseFinder {

    public static final String FIND_SITE_COURSES_FOR_USER = CourseFinder.class.getName() + ".findSiteCoursesForUser";

    @SuppressWarnings("unchecked")
    public List<Course> findSiteCoursesForUser(long groupId, long userId) {
        Session session = null;
        try {
            session = openSession();

            String sql = _customSQL.get(getClass(), FIND_SITE_COURSES_FOR_USER);

            SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);
            sqlQuery.addEntity("Course", CourseImpl.class);

            QueryPos queryPos = QueryPos.getInstance(sqlQuery);
            queryPos.add(groupId);
            queryPos.add(userId);

            return (List<Course>) QueryUtil.list(sqlQuery, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

        } catch (Exception e) {
            throw new SystemException(e);
        } finally {
            closeSession(session);
        }
    }

    @Reference
    private CustomSQL _customSQL;

}