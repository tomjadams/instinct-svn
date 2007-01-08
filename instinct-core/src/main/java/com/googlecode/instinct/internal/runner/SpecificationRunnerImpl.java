package com.googlecode.instinct.internal.runner;

import java.lang.reflect.Method;
import com.googlecode.instinct.internal.mock.MockAutoWirer;
import com.googlecode.instinct.internal.mock.MockAutoWirerImpl;
import com.googlecode.instinct.internal.mock.MockVerifier;
import com.googlecode.instinct.internal.mock.MockVerifierImpl;
import com.googlecode.instinct.internal.util.ConstructorInvoker;
import com.googlecode.instinct.internal.util.ConstructorInvokerImpl;
import com.googlecode.instinct.internal.util.MethodInvoker;
import com.googlecode.instinct.internal.util.MethodInvokerImpl;
import static com.googlecode.instinct.internal.util.ParamChecker.checkNotNull;
import com.googlecode.instinct.internal.util.Suggest;

final class SpecificationRunnerImpl implements SpecificationRunner {
    private final ConstructorInvoker constructorInvoker = new ConstructorInvokerImpl();
    private MethodInvoker methodInvoker = new MethodInvokerImpl();
    private LifeCycleMethodValidator methodValidator = new LifeCycleMethodValidatorImpl();
    private final MockAutoWirer mockAutoWirer = new MockAutoWirerImpl();
    private final MockVerifier mockVerifier = new MockVerifierImpl();

    @Suggest("Check JUNit 4 code for the way they are now doing finally in runBare()")
    public void run(final SpecificationContext context) {
        checkNotNull(context);
        final Object instance = invokeConstructor(context.getBehaviourContextClass());
        try {
            mockAutoWirer.wire(instance);
            runMethods(instance, context.getBeforeSpecificationMethods());
            methodInvoker.invokeMethod(instance, context.getSpecificationMethod());
            mockVerifier.verify(instance);
        } finally {
            runMethods(instance, context.getAfterSpecificationMethods());
        }
    }

    private void runMethods(final Object instance, final Method[] methods) {
        for (final Method method : methods) {
            methodValidator.checkMethodHasNoParameters(method);
            methodInvoker.invokeMethod(instance, method);
        }
    }

    private <T> Object invokeConstructor(final Class<T> cls) {
        methodValidator.checkContextConstructor(cls);
        return constructorInvoker.invokeNullaryConstructor(cls);
    }
}
