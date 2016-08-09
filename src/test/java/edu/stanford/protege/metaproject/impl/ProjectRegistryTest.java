//package edu.stanford.protege.metaproject.impl;
//
//import edu.stanford.protege.metaproject.Utils;
//import edu.stanford.protege.metaproject.api.*;
//import edu.stanford.protege.metaproject.api.exception.IdAlreadyInUseException;
//import edu.stanford.protege.metaproject.api.exception.UnknownPolicyObjectIdException;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.File;
//import java.util.Set;
//
//import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.MatcherAssert.assertThat;
//
///**
// * @author Rafael Gonçalves <br>
// * Stanford Center for Biomedical Informatics Research
// */
//public class ProjectRegistryTest {
//    private static final Project project1 = Utils.getProject(), project2 = Utils.getProject(), project3 = Utils.getProject(), project4 = Utils.getProject();
//    private static final Set<Project> projectSet = Utils.getProjectSet(project1, project2, project3, project4);
//    private static final String toStringHead = ProjectRegistry.class.getSimpleName();
//
//    private ProjectRegistry projectRegistry, otherProjectRegistry, diffProjectRegistry;
//
//    @Before
//    public void setUp() {
//        projectRegistry = Utils.getProjectRegistry(projectSet);
//        otherProjectRegistry = Utils.getProjectRegistry(projectSet);
//        diffProjectRegistry = Utils.getProjectRegistry();
//    }
//
//    @Test
//    public void testNotNull() {
//        assertThat(projectRegistry, is(not(equalTo(null))));
//    }
//
//    @Test
//    public void testGetProjects() {
//        assertThat(projectRegistry.getEntries(), is(projectSet));
//    }
//
//    @Test
//    public void testGetProjectById() throws UnknownPolicyObjectIdException {
//        assertThat(projectRegistry.get(project1.getId()), is(project1));
//    }
//
//    @Test
//    public void testRemoveProject() {
//        assertThat(projectRegistry.getEntries().contains(project4), is(true));
//        projectRegistry.remove(project4);
//        assertThat(projectRegistry.getEntries().contains(project4), is(false));
//    }
//
//    @Test
//    public void testAddProject() throws UnknownPolicyObjectIdException, IdAlreadyInUseException {
//        Project project5 = Utils.getProject();
//        assertThat(projectRegistry.getEntries().contains(project5), is(false));
//
//        projectRegistry.add(project5);
//        assertThat(projectRegistry.getEntries().contains(project5), is(true));
//        assertThat(projectRegistry.get(project5.getId()), is(project5));
//    }
//
//    @Test
//    public void testSetDescription() throws UnknownPolicyObjectIdException {
//        Description newDescription = Utils.getDescription("new test description");
//        projectRegistry.setDescription(project2.getId(), newDescription);
//        assertThat(projectRegistry.get(project2.getId()).getDescription(), is(newDescription));
//    }
//
//    @Test
//    public void testSetName() throws UnknownPolicyObjectIdException {
//        Name newName = Utils.getName("new test name");
//        projectRegistry.setName(project2.getId(), newName);
//        assertThat(projectRegistry.get(project2.getId()).getName(), is(newName));
//    }
//
//    @Test
//    public void testSetFile() throws UnknownPolicyObjectIdException {
//        File newFile = Utils.getFile("documents/test/somefile.txt");
//        projectRegistry.setFile(project2.getId(), newFile);
//        assertThat(projectRegistry.get(project2.getId()).getFile(), is(newFile));
//    }
//
//    @Test
//    public void testSetOwner() throws UnknownPolicyObjectIdException {
//        UserId newOwner = Utils.getUserId();
//        projectRegistry.setOwner(project2.getId(), newOwner);
//        assertThat(projectRegistry.get(project2.getId()).getOwner(), is(newOwner));
//    }
//
//    @Test
//    public void testExists() {
//        ProjectId projectId = Utils.getProjectId("newTestProjectId");
//        assertThat(projectRegistry.contains(project1.getId()), is(true));
//        assertThat(projectRegistry.contains(projectId), is(false));
//    }
//
//    @Test
//    public void testEqualToSelf() {
//        assertThat(projectRegistry, is(projectRegistry));
//    }
//
//    @Test
//    public void testEquals() {
//        assertThat(projectRegistry, is(otherProjectRegistry));
//    }
//
//    @Test
//    public void testNotEquals() {
//        assertThat(projectRegistry, is(not(diffProjectRegistry)));
//    }
//
//    @Test
//    public void testHashCode() {
//        assertThat(projectRegistry.hashCode(), is(otherProjectRegistry.hashCode()));
//    }
//
//    @Test
//    public void testToString() {
//        assertThat(projectRegistry.toString(), startsWith(toStringHead));
//    }
//}
