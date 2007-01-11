package com.googlecode.instinct.internal.mock.instance;

import com.googlecode.instinct.internal.util.Suggest;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public final class ConcreteGeneratorImpl implements ProxyGenerator {
//    private static final Map<Class<?>, Object> PROXIES = synchronizedMap(new WeakHashMap<Class<?>, Object>());

    @Suggest("Provide the ability to proxy more than one type")
    public <T> Object newProxy(final Class<T> classToProxy, final MethodInterceptor methodInterceptor) {
        return createProxy(classToProxy, methodInterceptor);
    }

    @Suggest("Use JavaAssist and return an instumented class instead")
    private <T> Object createProxy(final Class<T> classToProxy, final MethodInterceptor methodInterceptor) {
        final Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(classToProxy);
        enhancer.setCallback(methodInterceptor);
        return enhancer.create();
    }
}