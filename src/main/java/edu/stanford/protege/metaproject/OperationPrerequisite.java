package edu.stanford.protege.metaproject;

import org.semanticweb.owlapi.model.OWLEntity;

/**
 * A representation of an operation prerequisite (e.g., presence or absence of some OWL entity)
 *
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public interface OperationPrerequisite {

    /**
     * Get the OWL entity
     *
     * @return Operation prerequisites
     */
    OWLEntity getEntity();

    /**
     * Get the modifier pertaining to the OWL entity prerequisite that determines whether,
     * for example, the entity should be present or absent from the ontology
     *
     * @return Operation prerequisite modifier
     */
    OperationPrerequisiteModifier getModifier();

    /**
     * An operation prerequisite modifier determines the status of the OWL entity defined
     * as a prerequisite; this entity could be assumed to be present or absent
     */
    enum OperationPrerequisiteModifier {
        PRESENT, ABSENT
    }

}