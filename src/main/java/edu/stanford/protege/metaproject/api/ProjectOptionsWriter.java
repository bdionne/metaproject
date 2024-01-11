package edu.stanford.protege.metaproject.api;

import java.io.File;
import java.io.IOException;

import javax.annotation.Nonnull;

public interface ProjectOptionsWriter {

    /**
     * Set the project options managed by this object, and saves it to the specified file
     *
     * @param projOptions ProjectOptions
     * @param outputFile    Output file
     * @throws IOException  IO exception
     */
    void saveProjectOptions(@Nonnull ProjectOptions projOptions, @Nonnull File outputFile) throws IOException;

}
