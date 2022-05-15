package com.edu.postman.service.postman.collection;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author Eduardo
 */
public class UrlRequest {

    /**
     * String - raw.
     */
    @SerializedName("raw")
    private String raw;

    /**
     * String - protocol.
     */
    @SerializedName("protocol")
    private String protocol;

    /**
     * List<String> - host.
     */
    @SerializedName("host")
    private List<String> host;

    /**
     * List<String> - path.
     */
    @SerializedName("path")
    private List<String> path;

    /**
     * New instance of UrlRequest
     */
    public UrlRequest() {
        super();
    }

    /**
     * Copy constructor.
     *
     * @param url
     */
    public UrlRequest(final UrlRequest url) {
        super();
        this.protocol = url.getProtocol();
        this.raw = url.getRaw();

        if (url.getHost() != null) {
            this.host = new ArrayList<>(url.getHost());
        }

        if (url.getPath() != null) {
            this.path = new ArrayList<>(url.getPath());
        }

    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(final String raw) {
        this.raw = raw;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(final String protocol) {
        this.protocol = protocol;
    }

    public List<String> getHost() {
        return host;
    }

    public void setHost(final List<String> host) {
        this.host = host;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(final List<String> path) {
        this.path = path;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UrlRequest [raw=");
        builder.append(raw);
        builder.append(", protocol=");
        builder.append(protocol);
        builder.append(", host=");
        builder.append(host);
        builder.append(", path=");
        builder.append(path);
        builder.append("]");
        return builder.toString();
    }

}
