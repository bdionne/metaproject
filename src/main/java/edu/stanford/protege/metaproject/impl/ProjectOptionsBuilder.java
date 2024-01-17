package edu.stanford.protege.metaproject.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;

import edu.stanford.protege.metaproject.api.ProjectOptions;

public class ProjectOptionsBuilder {
	private static final Logger logger = LoggerFactory.getLogger(ProjectOptionsBuilder.class.getName());
    private Map<String,Set<String>> options = new HashMap<>();
    
    /**
     * No-arguments constructor
     */
    public ProjectOptionsBuilder() { }

    /**
     * Constructor that reuses the given project options 
     *
     * @param projectOptions   Project options 
     */
    public ProjectOptionsBuilder(ProjectOptions projectOptions) {
        this.options = checkNotNull(projectOptions.getOptions());
    }

    /**
     * Set the project options information
     *
     * @param options  Map
     * @return ProjectOptionsBuilder
     */
    public ProjectOptionsBuilder setProjectOptions(Map<String,Set<String>> options) {
        this.options = options;
        return this;
    }
    
    
    /**
     * Create a project options instance
     *
     * @return ProjectOptions
     */
    public ProjectOptions createProjectOptions() {
        return new ProjectOptionsImpl(options);
    }
}
