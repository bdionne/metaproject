package edu.stanford.protege.metaproject.api;

import edu.stanford.protege.metaproject.Utils;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public class ProjectTest {
    private static final String
            projectIdStr = "testProjectId1",
            otherIdStr = "testProjectId2",
            projectNameStr = "test project name",
            projectDescriptionStr = "test project description",
            toStringHead = "Project";

    private static final ProjectId projectId = Utils.getProjectId(projectIdStr), diffProjectId = Utils.getProjectId(otherIdStr);
    private static final Name projectName = Utils.getName(projectNameStr);
    private static final Description projectDescription = Utils.getDescription(projectDescriptionStr);
    private static final Address projectLocation = Utils.getAddress("/Users/test/folder/project.owl");
    private static final UserId ownerId = Utils.getUserId("owner");
    private static final UserId adminId = Utils.getUserId("admin1");
    private static Set<UserId> admins = Utils.getUserIdSet(adminId);

    private Project project, otherProject, diffProject;

    @Before
    public void setUp() {
        project = Utils.getProject(projectId, projectName, projectDescription, projectLocation, ownerId, admins);
        otherProject = Utils.getProject(projectId, projectName, projectDescription, projectLocation, ownerId, admins);
        diffProject = Utils.getProject(diffProjectId, projectName, projectDescription, projectLocation, ownerId, admins);
    }

    @Test
    public void testNotNull() {
        assertThat(project, is(not(equalTo(null))));
    }

    @Test
    public void testGetId() {
        assertThat(project.getId().get(), is(projectIdStr));
    }

    @Test
    public void testGetName() {
        assertThat(project.getName().get(), is(projectNameStr));
    }

    @Test
    public void testGetDescription() {
        assertThat(project.getDescription().get(), is(projectDescriptionStr));
    }

    @Test
    public void testGetAddress() {
        assertThat(project.getAddress(), is(projectLocation));
    }

    @Test
    public void testGetOwner() {
        assertThat(project.getOwner(), is(ownerId));
    }

    @Test
    public void testGetAdministrators() {
        assertThat(project.getAdministrators(), is(admins));
    }

    @Test
    public void testHasAdministrator() {
        assertThat(project.hasAdministrator(adminId), is(true));
    }

    @Test
    public void testEqualToSelf() {
        assertThat(project, is(project));
    }

    @Test
    public void testEquals() {
        assertThat(project, is(otherProject));
    }

    @Test
    public void testNotEquals() {
        assertThat(project, is(not(diffProject)));
    }

    @Test
    public void testHashCode() {
        assertThat(project.hashCode(), is(otherProject.hashCode()));
    }

    @Test
    public void testToString() {
        assertThat(project.toString(), startsWith(toStringHead));
    }

    @Test
    public void testCompareToSelf() {
        assertThat(project.compareTo(project), is(0));
    }

    @Test
    public void testCompareTo() {
        assertThat(project.compareTo(otherProject), is(0));
    }

    @Test
    public void testCompareToAnother() {
        assertThat(project.compareTo(diffProject), is(-1));
    }

    @Test
    public void testCompareToAnotherReversed() {
        assertThat(diffProject.compareTo(project), is(1));
    }
}