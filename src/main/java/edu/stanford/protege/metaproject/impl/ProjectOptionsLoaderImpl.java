package edu.stanford.protege.metaproject.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.io.FileNotFoundException;

import javax.annotation.Nonnull;

import edu.stanford.protege.metaproject.api.ProjectOptions;
import edu.stanford.protege.metaproject.api.ProjectOptionsLoader;
import edu.stanford.protege.metaproject.api.Serializer;
import edu.stanford.protege.metaproject.api.exception.ObjectConversionException;

public class ProjectOptionsLoaderImpl implements ProjectOptionsLoader {
	@Nonnull private final Serializer serializer;

	/**
     * Constructor
     *
     * @param serializer    Configuration serializer
     */
    public ProjectOptionsLoaderImpl(@Nonnull Serializer serializer) {
        this.serializer = checkNotNull(serializer);
    }
    
	@Override
	@Nonnull
	public synchronized ProjectOptions loadProjectOptions(File f) throws FileNotFoundException, ObjectConversionException {
		checkNotNull(f, "Input configuration file must not be null");
        return new ProjectOptionsBuilder(checkNotNull(serializer.parse(f, ProjectOptions.class)))
                .createProjectOptions();
	}

}
