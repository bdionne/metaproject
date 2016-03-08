package edu.stanford.protege.metaproject.api;

import edu.stanford.protege.metaproject.Utils;
import edu.stanford.protege.metaproject.api.exception.UnknownAccessControlObjectIdException;
import edu.stanford.protege.metaproject.api.exception.UnknownProjectIdException;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public class ProjectRegistryTest {
    private static final Project project1 = Utils.getProject(), project2 = Utils.getProject(), project3 = Utils.getProject(), project4 = Utils.getProject();
    private static final Set<Project> projectSet = Utils.getProjectSet(project1, project2, project3, project4);
    private static final String toStringHead = ProjectRegistry.class.getSimpleName();

    private ProjectRegistry projectRegistry, otherProjectRegistry, diffProjectRegistry;

    @Before
    public void setUp() {
        projectRegistry = Utils.getProjectManager(projectSet);
        otherProjectRegistry = Utils.getProjectManager(projectSet);
        diffProjectRegistry = Utils.getProjectManager();
    }

    @Test
    public void testNotNull() {
        assertThat(projectRegistry, is(not(equalTo(null))));
    }

    @Test
    public void testGetProjects() {
        assertThat(projectRegistry.getProjects(), is(projectSet));
    }

    @Test
    public void testGetProjectById() throws UnknownProjectIdException {
        assertThat(projectRegistry.getProject(project1.getId()), is(project1));
    }

    @Test
    public void testRemoveProject() throws UnknownAccessControlObjectIdException {
        assertThat(projectRegistry.getProjects().contains(project4), is(true));
        projectRegistry.remove(project4);
        assertThat(projectRegistry.getProjects().contains(project4), is(false));
    }

    @Test
    public void testAddProject() throws UnknownProjectIdException {
        Project project5 = Utils.getProject();
        assertThat(projectRegistry.getProjects().contains(project5), is(false));

        projectRegistry.add(project5);
        assertThat(projectRegistry.getProjects().contains(project5), is(true));
        assertThat(projectRegistry.getProject(project5.getId()), is(project5));
    }

    @Test
    public void testChangeDescription() throws UnknownProjectIdException {
        Description newDescription = Utils.getDescription("new test description");
        projectRegistry.changeDescription(project2.getId(), newDescription);
        assertThat(projectRegistry.getProject(project2.getId()).getDescription(), is(newDescription));
    }

    @Test
    public void testChangeName() throws UnknownProjectIdException {
        Name newName = Utils.getName("new test name");
        projectRegistry.changeName(project2.getId(), newName);
        assertThat(projectRegistry.getProject(project2.getId()).getName(), is(newName));
    }

    @Test
    public void testChangeAddress() throws UnknownProjectIdException {
        Address newAddress = Utils.getAddress("new test address");
        projectRegistry.changeAddress(project2.getId(), newAddress);
        assertThat(projectRegistry.getProject(project2.getId()).getAddress(), is(newAddress));
    }

    @Test
    public void testChangeOwner() throws UnknownProjectIdException {
        UserId newOwner = Utils.getUserId();
        projectRegistry.changeOwner(project2.getId(), newOwner);
        assertThat(projectRegistry.getProject(project2.getId()).getOwner(), is(newOwner));
    }

    @Test
    public void testExists() {
        ProjectId projectId = Utils.getProjectId("newTestProjectId");
        assertThat(projectRegistry.contains(project1.getId()), is(true));
        assertThat(projectRegistry.contains(projectId), is(false));
    }

    @Test
    public void testAddAdministrator() throws UnknownProjectIdException {
        UserId newAdmin = Utils.getUserId();
        assertThat(projectRegistry.getProject(project2.getId()).getAdministrators().contains(newAdmin), is(false));

        projectRegistry.addAdministrator(project2.getId(), newAdmin);
        assertThat(projectRegistry.getProject(project2.getId()).getAdministrators().contains(newAdmin), is(true));
    }

    @Test
    public void testRemoveAdministrator() throws UnknownProjectIdException {
        UserId userId = project2.getAdministrators().iterator().next();
        projectRegistry.removeAdministrator(project2.getId(), userId);
        assertThat(projectRegistry.getProject(project2.getId()).getAdministrators().contains(userId), is(false));
    }

    @Test
    public void testEqualToSelf() {
        assertThat(projectRegistry, is(projectRegistry));
    }

    @Test
    public void testEquals() {
        assertThat(projectRegistry, is(otherProjectRegistry));
    }

    @Test
    public void testNotEquals() {
        assertThat(projectRegistry, is(not(diffProjectRegistry)));
    }

    @Test
    public void testHashCode() {
        assertThat(projectRegistry.hashCode(), is(otherProjectRegistry.hashCode()));
    }

    @Test
    public void testToString() {
        assertThat(projectRegistry.toString(), startsWith(toStringHead));
    }
}