package com.googlecode.instinct.internal.util.instance;

import java.io.FilterReader;
import java.io.FilterWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import static com.googlecode.instinct.mock.Mocker.mock;
import com.googlecode.instinct.test.InstinctTestCase;
import static com.googlecode.instinct.test.checker.AssertThrowsChecker.assertThrows;
import static com.googlecode.instinct.test.checker.ClassChecker.checkClass;

@SuppressWarnings({"ALL"})
public final class ObjectFactoryImplAtomicTest extends InstinctTestCase {
    private static final Class<ObjectFactory> AN_INTERFACE_1 = ObjectFactory.class;
    private static final Class<Serializable> AN_INTERFACE_2 = Serializable.class;
    private ObjectFactory factory;
    private Reader reader;
    private Writer writer;
    private String string;

    public void testProperties() {
        checkClass(ObjectFactoryImpl.class, ObjectFactory.class);
    }

    public void testOnlyAcceptConcreteClasses() {
        checkRejectsInterfaces(AN_INTERFACE_1);
        checkRejectsInterfaces(AN_INTERFACE_2);
    }

    public void testCreatePerformsCorrectTypeInference() {
        checkSimpleArgumentsSucceed();
        checkMultipleConstructorSucceeds();
        checkSubclassTypeInferenceSucceed();
        checkIncorrectOrderingFails();
        checkBadConstructorParametersFails();
        checkInstatiationOfInterfaceFails();
        checkInstantiationConstructorsWithoutAccessFails();
    }

    private void checkSimpleArgumentsSucceed() {
        checkSucceeds(ObjectFactoryImpl.class);
    }

    private void checkMultipleConstructorSucceeds() {
        checkSucceeds(ClassWithConstructors.class, reader);
        checkSucceeds(ClassWithConstructors.class, string);
        checkSucceeds(ClassWithConstructors.class, reader, writer);
    }

    private void checkSubclassTypeInferenceSucceed() {
        checkSucceeds(ClassWithConstructors.class, reader, writer);
        checkSucceeds(ClassWithConstructors.class, mock(InputStreamReader.class), mock(PrintWriter.class));
        checkSucceeds(ClassWithConstructors.class, mock(InputStreamReader.class), mock(PrintWriter.class));
        checkSucceeds(ClassWithConstructors.class, mock(FilterReader.class), mock(FilterWriter.class));
    }

    private void checkIncorrectOrderingFails() {
        checkFails(ClassWithConstructors.class, writer, reader);
    }

    private void checkBadConstructorParametersFails() {
        checkFails(ObjectCreationException.class);
        checkFails(ClassWithConstructors.class, writer, reader);
    }

    private void checkInstatiationOfInterfaceFails() {
        checkFailsWithException(IllegalArgumentException.class, ObjectFactory.class);
        checkFailsWithException(IllegalArgumentException.class, Serializable.class);
    }

    private void checkInstantiationConstructorsWithoutAccessFails() {
        checkFails(ClassWithConstructors.class);
    }

    private <T> void checkSucceeds(final Class<T> toCreate, final Object... values) {
        factory.create(toCreate, values);
    }

    private <T> void checkFails(final Class<T> toCreate, final Object... values) {
        checkFailsWithException(ObjectCreationException.class, toCreate, values);
    }

    private <T, E extends RuntimeException> void checkFailsWithException(final Class<E> expectedException, final Class<T> toCreate,
            final Object... values) {
        assertThrows(expectedException, new Runnable() {
            public void run() {
                factory.create(toCreate, values);
            }
        });
    }

    private <T> void checkRejectsInterfaces(final Class<T> anInterface) {
        assertThrows(IllegalArgumentException.class, new Runnable() {
            public void run() {
                new ObjectFactoryImpl().create(anInterface);
            }
        });
    }

    @Override
    public void setUpTestDoubles() {
        string = "Some string";
        reader = mock(Reader.class);
        writer = mock(Writer.class);
    }

    @Override
    public void setUpSubject() {
        factory = new ObjectFactoryImpl();
    }

    public static class ClassWithConstructors {
        private ClassWithConstructors() {
        }

        ClassWithConstructors(Integer i) {
        }

        public ClassWithConstructors(final String s) {
        }

        public ClassWithConstructors(final Reader r) {
        }

        public ClassWithConstructors(final Reader r, final Writer w) {
        }
    }
}