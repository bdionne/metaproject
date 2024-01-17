package edu.stanford.protege.metaproject.api;

import java.io.File;
import java.io.FileNotFoundException;

import javax.annotation.Nonnull;

import edu.stanford.protege.metaproject.api.exception.ObjectConversionException;

public interface ProjectOptionsLoader {
	/**
     * Load project options from a file
     *
     * @param f project options file
     * @return Project Options
     * @throws FileNotFoundException    File specified was not found
     * @throws ObjectConversionException   JSON object could not be converted to a Java object
     */
    @Nonnull
    ProjectOptions loadProjectOptions(@Nonnull File f) throws FileNotFoundException, ObjectConversionException;

}
