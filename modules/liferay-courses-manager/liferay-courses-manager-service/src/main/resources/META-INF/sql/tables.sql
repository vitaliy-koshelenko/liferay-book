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