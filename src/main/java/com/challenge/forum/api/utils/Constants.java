package com.challenge.forum.api.utils;

import org.springframework.stereotype.Component;

@Component
public class Constants {
    public static final String ISSUER = "api-forum";

    //    Routes
    public static final String AUTHENTICATION_CONTROLLER_ROUTE_MAP = "/auth";
    public static final String AUTHENTICATION_ROUTE_WITH_WILDCARD = "/auth/*";
    public static final String POST_USER_LOGIN_ROUTE = "/login";
    public static final String POST_USER_REGISTER_ROUTE = "/register";

    public static final String TOPIC_CONTROLLER_ROUTE_MAP = "/topics";
    public static final String TOPIC_ROUTE_WITH_WILDCARD = "/topics/*";
    public static final String GET_DETAILS_TOPIC_ROUTE = "/{id}";
    public static final String DELETE_TOPIC_ROUTE = "/{id}";

    public static final String COURSE_CONTROLLER_ROUTE_MAP = "/courses";

    public static final String REPLY_CONTROLLER_ROUTE_MAP = "/replies";
    public static final String REPLY_ROUTE_WITH_WILDCARD = "/replies/*";
    public static final String GET_REPLIES_FROM_TOPIC_ROUTE = "/{id}";
    public static final String DELETE_REPLY_ROUTE = "/{id}";

    //    Exceptions
    public static final String USERNAME_REGISTERED = "Username or Email already taken";
}
