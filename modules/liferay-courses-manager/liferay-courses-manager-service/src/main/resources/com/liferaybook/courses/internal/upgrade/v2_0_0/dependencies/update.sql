create table lb_Lecture (
    lectureId   LONG not null primary key,
    companyId   LONG,
    groupId     LONG,
    courseId    LONG,
    name        VARCHAR(75) null,
    description VARCHAR(75) null,
    videoLink   VARCHAR(75) null
);
COMMIT_TRANSACTION;