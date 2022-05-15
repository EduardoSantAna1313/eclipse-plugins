/*
 * COPYRIGHT...
 */
package com.edu.postman.service.postman.workspace;

/**
 * Class to ResponseListW.
 *
 * @author Eduardo
 */
public class ResponseDetailW {

    private Workspace workspace;

    /**
     * New instance of ResponseListW
     */
    public ResponseDetailW() {
        super();
    }

    /**
     * * Copy constructor.
     *
     * @param response
     */
    public ResponseDetailW(final ResponseDetailW response) {
        super();

        if (response.getWorkspace() != null) {
            this.workspace = new Workspace(response.getWorkspace());
        }

    }

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(final Workspace workspace) {
        this.workspace = workspace;
    }

}
