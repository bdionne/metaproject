package edu.stanford.protege.metaproject.impl;

import edu.stanford.protege.metaproject.TestUtils;
import edu.stanford.protege.metaproject.api.OperationId;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Rafael Gonçalves <br>
 * Center for Biomedical Informatics Research <br>
 * Stanford University
 */
public class OperationIdTest {
    private static final String
            operationIdStr = "testOperationId1",
            diffIdStr = "testOperationId2",
            toStringHead = OperationId.class.getSimpleName();

    private OperationId operationId, otherOperationId, diffOperationId;

    @Before
    public void setUp() {
        operationId = TestUtils.getOperationId(operationIdStr);
        otherOperationId = TestUtils.getOperationId(operationIdStr);
        diffOperationId = TestUtils.getOperationId(diffIdStr);
    }

    @Test
    public void testNotNull() {
        assertThat(operationId, is(not(equalTo(null))));
    }

    @Test
    public void testGet() {
        assertThat(operationId.get(), is(operationIdStr));
    }

    @Test
    public void testEqualToSelf() {
        assertThat(operationId, is(operationId));
    }

    @Test
    public void testEquals() {
        assertThat(operationId, is(otherOperationId));
    }

    @Test
    public void testNotEquals() {
        assertThat(operationId, is(not(diffOperationId)));
    }

    @Test
    public void testHashCode() {
        assertThat(operationId.hashCode(), is(otherOperationId.hashCode()));
    }

    @Test
    public void testToString() {
        assertThat(operationId.toString(), startsWith(toStringHead));
    }
}