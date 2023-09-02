package com.lotrel.ltserwer.lotrelCommons.common;

public class ApiPath {

    public static class ProjectPath {
        public final static String PROJECT_CREATE = "/api/portal/project/create";
        public final static String PROJECT_EDIT_OWNER = "/api/portal/project/users/setowner";
        public final static String PROJECT_CLOSE = "/api/portal/project/close";
        public final static String PROJECT_INFO = "/api/portal/project";
        public final static String PROJECT_TASKS = "/api/portal/project/tasks";
        public final static String PROJECT_SPRINTS = "/api/portal/project/sprints";
        public final static String PROJECT_USERS = "/api/portal/project/users";
        public final static String PROJECT_USERS_SEARCH = "/api/portal/project/users/search";
        public final static String PROJECT_ADD_USER = "/api/portal/project/users/add";
        public final static String PROJECT_ROLE_ASSIGN = "/api/portal/project/users/assignrole";

    }

    public static class TaskPath {
        public final static String TASK_CREATE = "/api/portal/task/create";
        public final static String TASK_ASSIGN = "/api/portal/task/assign";
        public final static String TASK_INFO = "/api/portal/task";
        public final static String TASK_EDIT = "/api/portal/task";
        public final static String TASK_LOG_TIME = "/api/portal/task/log";
    }
    public static class TaskCommentPath {
        public final static String TASK_COMMENT = "/api/portal/task/comment";
    }

    public static class SprintPath {
        public final static String SPRINT_CREATE = "/api/portal/sprint/create";
        public final static String SPRINT_INFO = "/api/portal/sprint";
        public final static String SPRINT_EDIT = "/api/portal/sprint/edit";
        public final static String SPRINT_CLOSE = "/api/portal/sprint/close";
    }

    public static class UserPath {
        public final static String USER_CURRENT_INFO = "/api/portal/user/current";
        public final static String USER_INFO = "/api/portal/user";
        public final static String USER_PROJECTS = "/api/portal/user/projects";
        public final static String USER_ALL = "/api/portal/user/all";
    }

}
