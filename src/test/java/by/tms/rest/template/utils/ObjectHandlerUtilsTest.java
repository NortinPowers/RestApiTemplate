package by.tms.rest.template.utils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class ObjectHandlerUtilsTest {

    @Test
    void getIgnoreProperties() {
        TestIncludeObject testIncludeObject = new TestIncludeObject(1L, "testName");
        TestObject testObject = new TestObject(1L, "name", 18, true, testIncludeObject);
        assertArrayEquals(new String[] {}, ObjectHandlerUtils.getIgnoreProperties(testObject));
        testIncludeObject = new TestIncludeObject(1L, null);
        testObject = new TestObject(1L, "name", 18, true, testIncludeObject);
        assertArrayEquals(new String[] {}, ObjectHandlerUtils.getIgnoreProperties(testObject));
        testIncludeObject = null;
        testObject = new TestObject(1L, "name", 18, true, testIncludeObject);
        assertArrayEquals(new String[] {"includeObject"}, ObjectHandlerUtils.getIgnoreProperties(testObject));
        testIncludeObject = new TestIncludeObject(1L, "testName");
        testObject = new TestObject(1L, null, null, true, testIncludeObject);
        assertArrayEquals(new String[] {"name", "age"}, ObjectHandlerUtils.getIgnoreProperties(testObject));
        testIncludeObject = new TestIncludeObject(1L, "testName");
        testObject = new TestObject(1L, "name", 18, null, testIncludeObject);
        assertArrayEquals(new String[] {"trust"}, ObjectHandlerUtils.getIgnoreProperties(testObject));
    }

    record TestObject(Long id, String name, Integer age, Boolean trust, TestIncludeObject includeObject) {

    }

    record TestIncludeObject(Long id, String name) {

    }

}
