package edu.stanford.protege.metaproject.api;

import edu.stanford.protege.metaproject.api.exception.PolicyException;

import java.util.Map;
import java.util.Set;

/**
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public interface Policy {

    void addPolicy(UserId userId, RoleId roleId) throws PolicyException;

    void addPolicy(Set<UserId> userId, RoleId roleId) throws PolicyException;

    void addPolicy(UserId userId, Set<RoleId> roleId) throws PolicyException;

    void removePolicy(UserId userId, RoleId roleId) throws PolicyException;

    boolean isOperationAllowed(OperationId operationId, ProjectId projectId, UserId userId) throws PolicyException;

    boolean hasRole(UserId userId, RoleId roleId) throws PolicyException;

    Set<RoleId> getRoles(UserId userId) throws PolicyException;

    Map<UserId,Set<RoleId>> getUserRoleMappings();

}