package com.thoughtmechanix.licensingservice.utils;

import org.apache.catalina.User;
import org.springframework.util.Assert;

public class UserContextHolder {
    private static final ThreadLocal<UserContext> userContext = new ThreadLocal<>();

    public static UserContext getContext() {
        UserContext context = userContext.get();
        if (context == null) {
            context = createNewContext();
            userContext.set(context);
        }

        return userContext.get();
    }

    public static void setContext(UserContext context) {
        Assert.notNull(context, "Ony non-null user context instances are permitted!");
        userContext.set(context);
    }

    private static UserContext createNewContext () { return new UserContext(); }
}
