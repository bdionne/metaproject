package edu.stanford.protege.metaproject;

import edu.stanford.protege.metaproject.api.ProjectOptionsWriter;
import edu.stanford.protege.metaproject.api.Serializer;
import edu.stanford.protege.metaproject.impl.ProjectOptionsWriterImpl;
import edu.stanford.protege.metaproject.serialization.DefaultJsonSerializer;

public final class ProjectOptionsManager {
	/**
     * Get the default projectOptions serializer
     *
     * @return Serializer
     */
    private static Serializer getSerializer() {
        return new DefaultJsonSerializer();
    }
	
	/**
     * Get the configuration writer
     *
     * @return Configuration writer
     */
    public static ProjectOptionsWriter getProjectOptionsWriter() {
        return new ProjectOptionsWriterImpl(getSerializer());
    }
}
