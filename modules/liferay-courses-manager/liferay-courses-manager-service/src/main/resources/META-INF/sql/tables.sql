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
	description VARCHAR(1000) null,
	urlTitle VARCHAR(75) null
);

create table lb_CourseSubscription (
	courseSubscriptionId LONG not null primary key,
	courseId LONG,
	userId LONG
);

create table lb_Lecture (
	uuid_ VARCHAR(75) null,
	lectureId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	courseId LONG,
	name VARCHAR(100) null,
	description VARCHAR(1000) null,
	videoLink VARCHAR(100) null,
	urlTitle VARCHAR(75) null
);