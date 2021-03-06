package edu.stanford.protege.metaproject.impl;

import edu.stanford.protege.metaproject.TestUtils;
import edu.stanford.protege.metaproject.api.RoleId;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Rafael Gonçalves <br>
 * Center for Biomedical Informatics Research <br>
 * Stanford University
 */
public class RoleIdTest {
    private static final String
            roleIdStr = "testRoleId1",
            diffIdStr = "testRoleId2",
            toStringHead = RoleId.class.getSimpleName();

    private RoleId roleId, otherRoleId, diffRoleId;

    @Before
    public void setUp() {
        roleId = TestUtils.getRoleId(roleIdStr);
        otherRoleId = TestUtils.getRoleId(roleIdStr);
        diffRoleId = TestUtils.getRoleId(diffIdStr);
    }

    @Test
    public void testNotNull() {
        assertThat(roleId, is(not(equalTo(null))));
    }

    @Test
    public void testGet() {
        assertThat(roleId.get(), is(roleIdStr));
    }

    @Test
    public void testEqualToSelf() {
        assertThat(roleId, is(roleId));
    }

    @Test
    public void testEquals() {
        assertThat(roleId, is(otherRoleId));
    }

    @Test
    public void testNotEquals() {
        assertThat(roleId, is(not(diffRoleId)));
    }

    @Test
    public void testHashCode() {
        assertThat(roleId.hashCode(), is(otherRoleId.hashCode()));
    }

    @Test
    public void testToString() {
        assertThat(roleId.toString(), startsWith(toStringHead));
    }
}