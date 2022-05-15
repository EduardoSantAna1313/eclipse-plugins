package com.edu.postman.service.postman.collection;

import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.annotations.SerializedName;

/**
 * @author Eduardo.
 */
public class CollectionItem {

    @SerializedName("name")
    private String name;

    @SerializedName("item")
    private List<CollectionItem> item;

    @SerializedName("event")
    private List<CollectionEvent> event;

    @SerializedName("_postman_id")
    private String postmanId;

    @SerializedName("protocolProfileBehavior")
    private ProtocolProfileBehavior protocolProfileBehavior;

    @SerializedName("request")
    private RequestItemCollection request;

    @SerializedName("response")
    private List<ResponseItemCollection> response;

    /**
     * Create a new instance of CollectionItem
     */
    public CollectionItem() {
        super();
    }

    /**
     * Copy constructor.
     *
     * @param copy
     */
    public CollectionItem(final CollectionItem copy) {
        super();
        this.name = copy.getName();
        this.postmanId = copy.getPostmanId();

        if (copy.getEvent() != null) {
            this.event = copy.getEvent().stream().map(CollectionEvent::new).collect(Collectors.toList());
        }

        if (copy.getItem() != null) {
            this.item = copy.getItem().stream().map(CollectionItem::new).collect(Collectors.toList());
        }

        if (copy.getProtocolProfileBehavior() != null) {
            this.protocolProfileBehavior = new ProtocolProfileBehavior(copy.getProtocolProfileBehavior());
        }

        if (copy.getRequest() != null) {
            this.request = new RequestItemCollection(copy.getRequest());
        }

    }

    /**
     * Create a new instance of CollectionItem
     *
     * @param name
     */
    public CollectionItem(final String name) {
        super();
        this.name = name;
    }

    /**
     * Retrieve the value of name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set a new value to name.
     *
     * @param name
     *                 the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Retrieve the value of item.
     *
     * @return the item
     */
    public List<CollectionItem> getItem() {
        return item;
    }

    /**
     * Set a new value to item.
     *
     * @param item
     *                 the item to set
     */
    public void setItem(final List<CollectionItem> item) {
        this.item = item;
    }

    /**
     * Retrieve the value of event.
     *
     * @return the event
     */
    public List<CollectionEvent> getEvent() {
        return event;
    }

    /**
     * Set a new value to event.
     *
     * @param event
     *                  the event to set
     */
    public void setEvent(final List<CollectionEvent> event) {
        this.event = event;
    }

    /**
     * Retrieve the value of postmanId.
     *
     * @return the postmanId
     */
    public String getPostmanId() {
        return postmanId;
    }

    /**
     * Set a new value to postmanId.
     *
     * @param postmanId
     *                      the postmanId to set
     */
    public void setPostmanId(final String postmanId) {
        this.postmanId = postmanId;
    }

    /**
     * Retrieve the value of protocolProfileBehavior.
     *
     * @return the protocolProfileBehavior
     */
    public ProtocolProfileBehavior getProtocolProfileBehavior() {
        return protocolProfileBehavior;
    }

    /**
     * Set a new value to protocolProfileBehavior.
     *
     * @param protocolProfileBehavior
     *                                    the protocolProfileBehavior to set
     */
    public void setProtocolProfileBehavior(final ProtocolProfileBehavior protocolProfileBehavior) {
        this.protocolProfileBehavior = protocolProfileBehavior;
    }

    /**
     * Retrieve the value of request.
     *
     * @return the request
     */
    public RequestItemCollection getRequest() {
        return request;
    }

    /**
     * Set a new value to request.
     *
     * @param request
     *                    the request to set
     */
    public void setRequest(final RequestItemCollection request) {
        this.request = request;
    }

    /**
     * Retrieve the value of response.
     *
     * @return the response
     */
    public List<ResponseItemCollection> getResponse() {
        return response;
    }

    /**
     * Set a new value to response.
     *
     * @param response
     *                     the response to set
     */
    public void setResponse(final List<ResponseItemCollection> response) {
        this.response = response;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("CollectionItem [name=");
        builder.append(name);
        builder.append(", item=");
        builder.append(item);
        builder.append(", event=");
        builder.append(event);
        builder.append(", postmanId=");
        builder.append(postmanId);
        builder.append(", protocolProfileBehavior=");
        builder.append(protocolProfileBehavior);
        builder.append(", request=");
        builder.append(request);
        builder.append(", response=");
        builder.append(response);
        builder.append("]");
        return builder.toString();
    }

}
