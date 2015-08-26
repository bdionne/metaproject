package edu.stanford.protege.metaproject.api;

import java.util.Map;

/**
 * Manager for server-side configurations
 *
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public interface ServerConfiguration extends Configuration {

    /**
     * Get the server host
     *
     * @return Server host
     */
    Host getHost();

    /**
     * Get the access control policy for the server
     *
     * @return Access control policy
     */
    Policy getPolicy();

    /**
     * Get the ontology term identifier status, which contains the last generated identifiers and/or their prefixes
     *
     * @return Ontology term identifier status instance
     */
    OntologyTermIdStatus getOntologyTermIdStatus();

    /**
     * Get the key-value server configuration properties
     *
     * @return Server configuration properties (aside from policy, host, and term identifiers status)
     */
    Map<String,String> getProperties();

}