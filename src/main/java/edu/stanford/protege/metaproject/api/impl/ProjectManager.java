package edu.stanford.protege.metaproject.api.impl;

import com.google.common.base.Objects;
import edu.stanford.protege.metaproject.api.*;
import edu.stanford.protege.metaproject.api.exception.ProjectNotFoundException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A manager for accessing, adding, removing or editing existing projects.
 *
 * The projects manager follows the singleton pattern; only one instance of this manager may exist.
 *
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public final class ProjectManager implements Manager {
    private static ProjectManager instance = null;
    private static Set<Project> projects;

    /**
     * Private constructor
     *
     * @param projects  Set of projects
     */
    private ProjectManager(Set<Project> projects) {
        this.projects = checkNotNull(projects);
    }

    /**
     * Get the singleton instance of the project manager. If the instance has not been created, or the given set of projects is different
     * than that in the existing instance, then a new project manager instance is created with the given project set
     *
     * @param projectSet   Set of projects
     * @return Project manager
     */
    public static ProjectManager getInstance(Set<Project> projectSet) {
        if(instance == null || !projects.equals(projectSet)) {
            instance = new ProjectManager(projectSet);
        }
        return instance;
    }

    /**
     * Get the singleton instance of the project manager. If the instance has not been created, then a project manager instance is
     * created with an empty set of projects
     *
     * @return Project manager
     */
    public static ProjectManager getInstance() {
        if(instance == null) {
            instance = new ProjectManager(new HashSet<>());
        }
        return instance;
    }

    /**
     * Add a project to the project registry
     *
     * @param project   New project
     */
    public void addProject(Project project) {
        projects.add(checkNotNull(project));
    }

    /**
     * Add a given set of projects
     *
     * @param projects  Set of projects
     */
    public void addProjects(Set<Project> projects) {
        projects.forEach(this::addProject);
    }

    /**
     * Remove a project from the project registry
     *
     * @param project   Project to be removed
     * @throws ProjectNotFoundException Project not found
     */
    public void removeProject(Project project) throws ProjectNotFoundException {
        if(!projects.contains(checkNotNull(project))) {
            throw new ProjectNotFoundException("The specified project does not exist");
        }
        projects.remove(project);
    }

    /**
     * Remove a given set of projects
     *
     * @param projects  Set of projects
     * @throws ProjectNotFoundException Project not found
     */
    public void removeProjects(Set<Project> projects) throws ProjectNotFoundException {
        for(Project project : projects) {
            removeProject(project);
        }
    }

    /**
     * Get the set of all projects
     *
     * @return Set of projects
     */
    public Set<Project> getProjects() {
        return projects;
    }

    /**
     * Get a project by its identifier
     *
     * @param projectId   Project identifier
     * @return Project instance
     */
    public Optional<Project> getProjectOptional(Id projectId) {
        Project project = null;
        for(Project p : projects) {
            if(p.getId().equals(projectId)) {
                project = p; break;
            }
        }
        return Optional.ofNullable(project);
    }

    /**
     * A convenience method to fetch a project or die trying (with an exception)
     *
     * @param projectId    Project identifier
     * @return Project instance
     * @throws ProjectNotFoundException    Project not found
     */
    public Project getProject(Id projectId) throws ProjectNotFoundException {
        Optional<Project> project = getProjectOptional(checkNotNull(projectId));
        if(project.isPresent()) {
            return project.get();
        }
        else {
            throw new ProjectNotFoundException("The specified project identifier does not correspond to an existing project");
        }
    }

    /**
     * Get the set of projects with the specified project name
     *
     * @param projectName   Project name
     * @return Set of projects
     */
    public Set<Project> getProjects(Name projectName) {
        return projects.stream().filter(project -> project.getName().equals(checkNotNull(projectName))).collect(Collectors.toSet());
    }

    /**
     * Change the name of the given project
     *
     * @param project   Project
     * @param projectName   New project name
     * @throws ProjectNotFoundException Project not found
     */
    public void changeProjectName(Project project, Name projectName) throws ProjectNotFoundException {
        removeProject(project);
        Project newProject = new ProjectImpl(project.getId(), checkNotNull(projectName), project.getDescription(), project.getAddress(), project.getOwner(), project.getAdministrators());
        addProject(newProject);
    }

    /**
     * Change the description of the given project
     *
     * @param project   Project
     * @param projectDescription    New project description
     * @throws ProjectNotFoundException Project not found
     */
    public void changeProjectDescription(Project project, Description projectDescription) throws ProjectNotFoundException {
        removeProject(project);
        Project newProject = new ProjectImpl(project.getId(), project.getName(), checkNotNull(projectDescription), project.getAddress(), project.getOwner(), project.getAdministrators());
        addProject(newProject);
    }

    /**
     * Change the owner of the specified project
     *
     * @param project   Project
     * @param userId    New owner user identifier
     * @throws ProjectNotFoundException Project not found
     */
    public void changeOwner(Project project, UserId userId) throws ProjectNotFoundException {
        removeProject(project);
        Project newProject = new ProjectImpl(project.getId(), project.getName(), project.getDescription(), project.getAddress(), checkNotNull(userId), project.getAdministrators());
        addProject(newProject);
    }

    /**
     * Change the location of the specified project
     *
     * @param project Project
     * @param projectAddress   Project address
     * @throws ProjectNotFoundException Project not found
     */
    public void changeLocation(Project project, Address projectAddress) throws ProjectNotFoundException {
        removeProject(project);
        Project newProject = new ProjectImpl(project.getId(), project.getName(), project.getDescription(), checkNotNull(projectAddress), project.getOwner(), project.getAdministrators());
        addProject(newProject);
    }

    /**
     * Add an administrator user to the specified project
     *
     * @param project   Project
     * @param userId    Administrator user identifier user to be added
     * @throws ProjectNotFoundException Project not found
     */
    public void addAdministrator(Project project, UserId userId) throws ProjectNotFoundException {
        Set<UserId> users = new HashSet<>();
        users.add(checkNotNull(userId));
        addAdministrators(checkNotNull(project), users);
    }

    /**
     * Add a set of administrator users to the specified project
     *
     * @param project   Project
     * @param users    Set of user identifiers of administrators to be added
     * @throws ProjectNotFoundException Project not found
     */
    public void addAdministrators(Project project, Set<UserId> users) throws ProjectNotFoundException {
        removeProject(project);

        Set<UserId> administrators = project.getAdministrators();
        administrators.addAll(checkNotNull(users));

        Project newProject = new ProjectImpl(project.getId(), project.getName(), project.getDescription(), project.getAddress(), project.getOwner(), administrators);
        addProject(newProject);
    }

    /**
     * Remove an administrator user from the specified project
     *
     * @param project   Project
     * @param userId    User identifier of administrator to be removed
     * @throws ProjectNotFoundException Project not found
     */
    public void removeAdministrator(Project project, UserId userId) throws ProjectNotFoundException {
        Set<UserId> users = new HashSet<>();
        users.add(checkNotNull(userId));
        removeAdministrators(checkNotNull(project), users);
    }

    /**
     * Remove a set of administrator users from the specified project
     *
     * @param project   Project
     * @param users    Set of user identifiers of administrators to be removed
     * @throws ProjectNotFoundException Project not found
     */
    public void removeAdministrators(Project project, Set<UserId> users) throws ProjectNotFoundException {
        removeProject(project);

        Set<UserId> administrators = project.getAdministrators();
        administrators.removeAll(checkNotNull(users));

        Project newProject = new ProjectImpl(project.getId(), project.getName(), project.getDescription(), project.getAddress(), project.getOwner(), administrators);
        addProject(newProject);
    }

    /**
     * Verify whether a given project identifier corresponds to a registered project
     *
     * @param projectId Project identifier
     * @return true if project with the given project identifier exists, false otherwise
     */
    public boolean exists(Id projectId) {
        for(Project p : projects) {
            if(p.getId().equals(projectId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectManager that = (ProjectManager) o;
        return Objects.equal(projects, that.projects);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(projects);
    }
}