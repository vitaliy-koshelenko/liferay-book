create table lb_Course (
	uuid_ VARCHAR(75) null,
	courseId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(100) null,
	description VARCHAR(1000) null
);

create table lb_Lecture (
	uuid_ VARCHAR(75) null,
	lectureId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	courseId LONG,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	videoLink VARCHAR(75) null
);