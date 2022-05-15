package com.edu.postman.service.postman.collection;

import com.google.gson.annotations.SerializedName;

/**
 * @author Eduardo
 */
public class Header {

    @SerializedName("key")
    private String key;

    @SerializedName("type")
    private String type;

    @SerializedName("value")
    private String value;

    /**
     * New instance of Header
     */
    public Header() {
        super();
    }

    /**
     * Copy constructor.
     *
     * @param header
     */
    public Header(final Header header) {
        super();

        this.key = header.getKey();
        this.type = header.getType();
        this.value = header.getValue();
    }

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Header [key=");
        builder.append(key);
        builder.append(", type=");
        builder.append(type);
        builder.append(", value=");
        builder.append(value);
        builder.append("]");
        return builder.toString();
    }

}
