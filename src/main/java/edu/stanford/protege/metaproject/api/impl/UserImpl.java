package edu.stanford.protege.metaproject.api.impl;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import edu.stanford.protege.metaproject.api.*;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Representation of a user, consisting of a unique identifier used to log in, a display name, and an email address
 *
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public final class UserImpl implements User, Serializable {
    private static final long serialVersionUID = 7145049739576315983L;
    private final UserId id;
    private final Name name;
    private final Address emailAddress;

    /**
     * Constructor
     *
     * @param id    User identifier
     * @param name  User display name
     * @param emailAddress  Email address
     */
    public UserImpl(UserId id, Name name, Address emailAddress) {
        this.id = checkNotNull(id);
        this.name = checkNotNull(name);
        this.emailAddress = checkNotNull(emailAddress);
    }

    /**
     * Get user's identifier that is used for logging in
     *
     * @return User identifier
     */
    @Override
    public UserId getId() {
        return id;
    }

    /**
     * Get user's name as used for displaying purposes
     *
     * @return User name
     */
    @Override
    public Name getName() {
        return name;
    }

    /**
     * Get user's email address
     *
     * @return Email address instance
     */
    @Override
    public Address getAddress() {
        return emailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserImpl user = (UserImpl) o;
        return Objects.equal(id, user.id) &&
                Objects.equal(name, user.name) &&
                Objects.equal(emailAddress, user.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, emailAddress);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("emailAddress", emailAddress)
                .toString();
    }
}
