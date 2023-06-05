create index IX_475877FE on lb_Course (companyId);
create unique index IX_D36014DD on lb_Course (groupId, urlTitle[$COLUMN_LENGTH:75$]);
create unique index IX_512C4E29 on lb_Course (name[$COLUMN_LENGTH:100$]);
create index IX_5E40315E on lb_Course (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_22417760 on lb_Course (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_FE72906B on lb_CourseSubscription (courseId, userId);
create index IX_CE0D5321 on lb_CourseSubscription (userId);

create unique index IX_64421C64 on lb_Lecture (courseId, name[$COLUMN_LENGTH:100$]);
create index IX_5F29474F on lb_Lecture (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_5DA95591 on lb_Lecture (uuid_[$COLUMN_LENGTH:75$], groupId);