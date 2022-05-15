package com.edu.postman.service.postman.collection;

import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.annotations.SerializedName;

/**
 * @author Eduardo
 */
public class RequestItemCollection {

    @SerializedName("method")
    private String method;

    @SerializedName("header")
    private List<Header> header;

    @SerializedName("url")
    private UrlRequest url;

    @SerializedName("description")
    private String description;

    public String getMethod() {
        return method;
    }

    /**
     * Create a new instance of RequestItemCollection
     */
    public RequestItemCollection() {
        super();
    }

    /**
     * Create a new instance of RequestItemCollection
     *
     * @param method
     * @param url
     * @param description
     */
    public RequestItemCollection(final String method, final String description) {
        super();
        this.method = method;
        this.description = description;
    }

    /**
     * Copy Constructor.
     *
     * @param copy
     */
    public RequestItemCollection(final RequestItemCollection copy) {
        super();
        this.description = copy.getDescription();
        this.method = copy.getMethod();

        if (copy.getUrl() != null) {
            this.url = new UrlRequest(copy.getUrl());
        }

        if (copy.getHeader() != null) {
            this.header = copy.getHeader().stream().map(Header::new).collect(Collectors.toList());
        }

    }

    public List<Header> getHeader() {
        return header;
    }

    public void setHeader(final List<Header> header) {
        this.header = header;
    }

    public UrlRequest getUrl() {
        return url;
    }

    public void setUrl(final UrlRequest url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setMethod(final String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("RequestItemCollection [method=");
        builder.append(method);
        builder.append(", header=");
        builder.append(header);
        builder.append(", url=");
        builder.append(url);
        builder.append(", description=");
        builder.append(description);
        builder.append("]");
        return builder.toString();
    }

}
