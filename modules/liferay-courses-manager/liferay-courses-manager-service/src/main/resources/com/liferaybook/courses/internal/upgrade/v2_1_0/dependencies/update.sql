create table lb_CourseSubscription (
    courseSubscriptionId LONG not null primary key,
    courseId             LONG,
    userId               LONG
);
COMMIT_TRANSACTION;