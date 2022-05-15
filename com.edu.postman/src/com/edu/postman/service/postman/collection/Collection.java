/*
 * COPYRIGHT...
 */
package com.edu.postman.service.postman.collection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Class to .
 *
 * @author Eduardo
 */
public class Collection {

    private String id;

    private String name;

    private String owner;

    private Date createdAt;

    private Date updatedAt;

    private String uid;

    private boolean isPublic;

    private CollectionInfo info;

    private List<CollectionItem> item;

    /**
     * New instance of Collection
     */
    public Collection() {
        super();
    }

    /**
     * Copy Constructor.
     *
     * @param collection
     */
    public Collection(final Collection collection) {
        super();
        this.id = collection.getId();
        this.isPublic = collection.isPublic();
        this.name = collection.getName();
        this.owner = collection.getOwner();
        this.uid = collection.getUid();

        if (collection.getCreatedAt() != null) {
            this.createdAt = new Date(collection.getCreatedAt().getTime());
        }

        if (collection.getUpdatedAt() != null) {
            this.updatedAt = new Date(this.getUpdatedAt().getTime());
        }

        if (collection.getInfo() != null) {
            this.info = new CollectionInfo(collection.getInfo());
        }

        if (collection.getItem() != null) {
            this.item = collection.getItem().stream().map(CollectionItem::new).collect(Collectors.toList());
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(final String owner) {
        this.owner = owner;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(final String uid) {
        this.uid = uid;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(final boolean isPublic) {
        this.isPublic = isPublic;
    }

    public CollectionInfo getInfo() {
        return info;
    }

    public void setInfo(final CollectionInfo info) {
        this.info = info;
    }

    public List<CollectionItem> getItem() {
        return item;
    }

    public void setItem(final List<CollectionItem> item) {
        this.item = item;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Collection [\n\tid: ");
        builder.append(id);
        builder.append(",\n\tname: ");
        builder.append(name);
        builder.append(",\n\towner: ");
        builder.append(owner);
        builder.append(",\n\tcreatedAt: ");
        builder.append(createdAt);
        builder.append(",\n\tupdatedAt: ");
        builder.append(updatedAt);
        builder.append(",\n\tuid: ");
        builder.append(uid);
        builder.append(",\n\tisPublic: ");
        builder.append(isPublic);
        builder.append("\n]");
        return builder.toString();
    }

    /**
     * @param contents
     */
    public static Collection fromJsom(final InputStream contents) {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(contents));

        final String json = reader.lines().collect(Collectors.joining("\n"));

        return fromJsom(json);

    }

    public static Collection fromJsom(final String json) {

        final GsonBuilder b = new GsonBuilder().setLenient();

        final Gson g = b.create();
        return g.fromJson(json, Collection.class);
    }

}
