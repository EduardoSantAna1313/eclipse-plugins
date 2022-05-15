/*
 * COPYRIGHT...
 */
package com.edu.postman.service.postman.workspace;

import java.util.List;
import java.util.stream.Collectors;

import com.edu.postman.service.postman.collection.Collection;

/**
 * Class to .
 *
 * @author Eduardo
 */
public class Workspace {

    private String id;

    private String name;

    private String type;

    private String description;

    private List<Collection> collections;

    /**
     * New instance of Workspace
     */
    public Workspace() {
        super();
    }

    /**
     * Copy constructor.
     *
     * @param workspace
     */
    public Workspace(final Workspace workspace) {
        super();
        this.id = workspace.getId();
        this.name = workspace.getName();
        this.type = workspace.getType();
        this.description = workspace.getDescription();

        if (workspace.getCollections() != null) {
            this.setCollections(workspace.getCollections().stream().map(Collection::new).collect(Collectors.toList()));
        }

    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public List<Collection> getCollections() {
        return collections;
    }

    public void setCollections(final List<Collection> collections) {
        this.collections = collections;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Workspace [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", type=");
        builder.append(type);
        builder.append(", description=");
        builder.append(description);
        builder.append(", collections=");
        builder.append(collections);
        builder.append("]");
        return builder.toString();
    }

}
