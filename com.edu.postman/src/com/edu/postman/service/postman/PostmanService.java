package com.edu.postman.service.postman;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import com.edu.postman.service.postman.collection.Collection;
import com.edu.postman.service.postman.collection.CollectionDetail;
import com.edu.postman.service.postman.collection.CollectionResponse;
import com.edu.postman.service.postman.collection.ResponseCreate;
import com.edu.postman.service.postman.workspace.ResponseDetailW;
import com.edu.postman.service.postman.workspace.ResponseListW;
import com.edu.postman.service.postman.workspace.Workspace;
import com.google.gson.Gson;

/**
 * Class to PostmanService.
 *
 * @author Eduardo
 */
public class PostmanService {

    /**
     * String - apiKey.
     */
    private final String apiKey;

    /**
     * Gson - gson.
     */
    private final Gson gson = new Gson();

    /**
     * New instance of PostmanService
     */
    protected PostmanService(final String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * List the collections.
     *
     * @return
     */
    public List<Collection> collections() {

        try {
            final String result = PostmanConnection.get("collections", apiKey);

            final CollectionResponse response = gson.fromJson(result, CollectionResponse.class);

            return response.getCollections();
        } catch (final IOException error) {
            error.printStackTrace();
        }

        return Collections.emptyList();

    }

    /**
     * List the collections.
     *
     * @return
     */
    public List<Workspace> workspaces() {

        try {
            final String result = PostmanConnection.get("workspaces", apiKey);

            final ResponseListW response = gson.fromJson(result, ResponseListW.class);

            return response.getWorkspaces();
        } catch (final IOException error) {
            error.printStackTrace();
        }

        return Collections.emptyList();

    }

    /**
     * Detail a collection.
     *
     * @param uid
     *
     * @return
     *
     * @throws IOException
     */
    public Collection collection(final String uid) throws IOException {
        final String result = PostmanConnection.get("collections/" + uid, apiKey);

        final CollectionDetail response = gson.fromJson(result, CollectionDetail.class);

        return response.getCollection();
    }

    /**
     * Detail a collection.
     *
     * @param uid
     *
     * @return
     *
     * @throws IOException
     */
    public InputStream collectionAsInputStream(final String uid) throws IOException {
        return PostmanConnection.getStream("collections/" + uid, apiKey);
    }

    /**
     * Detail a workspace.
     *
     * @param uid
     *
     * @return
     *
     * @throws IOException
     */
    public Workspace workspace(final String uid) {

        try {
            final String result = PostmanConnection.get("workspaces/" + uid, apiKey);

            final ResponseDetailW response = gson.fromJson(result, ResponseDetailW.class);

            return response.getWorkspace();
        } catch (final Exception e) {
            return null;
        }

    }

    /**
     * Create a collection.
     *
     * @param collection
     *
     * @return
     *
     * @throws IOException
     */
    public Collection save(final CollectionDetail collection) throws IOException {

        final String content = gson.toJson(collection);

        final String json = PostmanConnection.post("collections", apiKey, content);

        return gson.fromJson(json, ResponseCreate.class).getCollection();

    }

    public Collection save(final String workspaceId, final String content) throws IOException {

        final String json = PostmanConnection.post("collections?workspace=" + workspaceId, apiKey, content);

        return gson.fromJson(json, ResponseCreate.class).getCollection();

    }

    public Collection save(final String workspaceId, final String content, final PropertyChangeListener listener)
            throws IOException {

        final String json = PostmanConnection.post("collections?workspace=" + workspaceId, apiKey, content, listener);

        return gson.fromJson(json, ResponseCreate.class).getCollection();

    }

    /**
     * Update a collection.
     *
     * @param uid
     * @param collection
     *
     * @return
     *
     * @throws IOException
     */
    public Collection update(final String uid, final CollectionDetail collection) throws IOException {

        final String content = gson.toJson(collection);

        final String json = PostmanConnection.put("collections/" + uid, apiKey, content);

        return gson.fromJson(json, ResponseCreate.class).getCollection();

    }

    /**
     * Update a collection.
     *
     * @param uid
     * @param workspace
     *
     * @return
     *
     * @throws IOException
     */
    public String update(final String uid, final Workspace workspace) throws IOException {

        final String content = gson.toJson(workspace);

        return PostmanConnection.put("workspaces/" + uid, apiKey, content);
    }

    /**
     * Delete a collection.
     *
     * @param uid
     *
     * @return
     *
     * @throws IOException
     */
    public Collection delete(final String uid) throws IOException {

        final String json = PostmanConnection.delete("collections/" + uid, apiKey);

        return gson.fromJson(json, ResponseCreate.class).getCollection();

    }

}
