package com.thoughtmechanix.licensingservice.hystrix;

import com.thoughtmechanix.licensingservice.utils.UserContext;
import com.thoughtmechanix.licensingservice.utils.UserContextHolder;
import org.h2.command.dml.Call;

import java.util.concurrent.Callable;

public final class DelegatingUserContextCallable<V> implements Callable<V> {
    private final Callable<V> delegate;
    private UserContext originalUserContext;

    public DelegatingUserContextCallable(Callable<V> delegate, UserContext originalUserContext) {
        this.delegate = delegate;
        this.originalUserContext = originalUserContext;
    }

    @Override
    public V call() throws Exception {
        UserContextHolder.setContext(originalUserContext);
        try {
            return delegate.call();
        } catch (Exception e) {
           originalUserContext = null;
        }
        return null;
    }

    private static <V> Callable<V> create (Callable<V> delegate, UserContext userContext) {
        return new DelegatingUserContextCallable<>(delegate, userContext);
    }
}
