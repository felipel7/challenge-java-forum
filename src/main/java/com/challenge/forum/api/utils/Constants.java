package com.challenge.forum.api.utils;

import org.springframework.stereotype.Component;

@Component
public class Constants {
    public static final String ISSUER = "api-forum";

    //    Routes
    public static final String AUTHENTICATION_CONTROLLER_ROUTE_MAP = "/auth";
    public static final String AUTHENTICATION_ROUTE_WITH_WILDCARD = "/auth/**";
    public static final String POST_USER_LOGIN_ROUTE = "/login";
    public static final String POST_USER_REGISTER_ROUTE = "/register";

    public static final String TOPIC_CONTROLLER_ROUTE_MAP = "/topics";
    public static final String TOPIC_ROUTE_WITH_WILDCARD = "/topics/**";
    public static final String GET_DETAILS_TOPIC_ROUTE = "/{id}";
    public static final String DELETE_TOPIC_ROUTE = "/{id}";

    public static final String COURSE_CONTROLLER_ROUTE_MAP = "/courses";

    public static final String REPLY_CONTROLLER_ROUTE_MAP = "/replies";
    public static final String REPLY_ROUTE_WITH_WILDCARD = "/replies/**";
    public static final String GET_REPLIES_FROM_TOPIC_ROUTE = "/{id}";
    public static final String DELETE_REPLY_ROUTE = "/{id}";

    //    Docs
    public static final String[] SWAGGER_LIST = {"/v3/api-docs/**", "/swagger-ui/index.html", "/swagger-ui/**", "/swagger-resources/**"};
    public static final String DOCS_BEARER_KEY = "bearer-key";
    public static final String DOCS_APP_TITLE = "Forum - ONE";
    public static final String DOCS_APP_DESCRIPTION = "Challenge java-spring API Rest";
    public static final String DOCS_APP_AUTHOR = "Felipe Silva";
    public static final String DOCS_APP_AUTHOR_GITHUB = "https://github.com/felipel7";
    
    public static final String DOCS_TOPIC_TAG_NAME = "Topics";

    public static final String DOCS_COURSE_TAG_NAME = "Courses";

    public static final String DOCS_REPLY_TAG_NAME = "Replies";

    public static final String DOCS_AUTH_TAG_NAME = "Authentication";
    public static final String DOCS_AUTH_TAG_DESCRIPTION = "Authentication and Authorization";

    //    Exceptions
    public static final String USERNAME_REGISTERED = "Username or Email already taken";
}
