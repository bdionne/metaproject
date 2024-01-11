package edu.stanford.protege.metaproject.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import edu.stanford.protege.metaproject.api.ProjectOptions;
import edu.stanford.protege.metaproject.api.ProjectOptionsWriter;
import edu.stanford.protege.metaproject.api.Serializer;

@Immutable
@ThreadSafe
public final class ProjectOptionsWriterImpl implements ProjectOptionsWriter {
    @Nonnull private final Serializer serializer;

    /**
     * Constructor
     *
     * @param serializer    Configuration serializer
     */
    public ProjectOptionsWriterImpl(@Nonnull Serializer serializer) {
        this.serializer = checkNotNull(serializer);
    }

    @Override
    public synchronized void saveProjectOptions(@Nonnull ProjectOptions projOptions, @Nonnull File outputFile) throws IOException {
        checkNotNull(outputFile, "Output configuration file must not be null");
        checkNotNull(projOptions, "Project Options must not be null");
        FileWriter fw = new FileWriter(outputFile);
        String json = serializer.write(projOptions, ProjectOptions.class);
        fw.write(json);
        fw.close();
    }
}