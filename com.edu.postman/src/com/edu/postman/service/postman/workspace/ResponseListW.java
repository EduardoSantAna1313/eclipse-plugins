/*
 * COPYRIGHT...
 */
package com.edu.postman.service.postman.workspace;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to ResponseListW.
 *
 * @author Eduardo
 */
public class ResponseListW {

    private List<Workspace> workspaces;

    /**
     * New instance of ResponseListW
     */
    public ResponseListW() {
        super();
    }

    /**
     * Copy constructor.
     *
     * @param response
     */
    public ResponseListW(final ResponseListW response) {
        super();

        if (response.getWorkspaces() != null) {
            this.setWorkspaces(response.getWorkspaces().stream().map(Workspace::new).collect(Collectors.toList()));
        }

    }

    public List<Workspace> getWorkspaces() {
        return workspaces;
    }

    public void setWorkspaces(final List<Workspace> workspaces) {
        this.workspaces = workspaces;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ResponseListW [workspaces=");
        builder.append(workspaces);
        builder.append("]");
        return builder.toString();
    }

}
