create table lb_Course (
	courseId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	name VARCHAR(100) null,
	description VARCHAR(1000) null
);