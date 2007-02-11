package com.googlecode.instinct.integrate.junit;

import com.googlecode.instinct.internal.runner.BehaviourContextResult;

public interface BehaviourContextClass {
    BehaviourContextResult run(BehaviourContextRunStrategy behaviourContextRunStrategy, SpecificationRunStrategy specificationRunStrategy);

    <T> Class<T> getType();

    String getName();
}