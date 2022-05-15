package com.edu.postman.service.postman.collection;

import com.google.gson.annotations.SerializedName;

/**
 * @author Eduardo
 */
public class CollectionEvent {

    /**
     * String - listen.
     */
    @SerializedName("listen")
    private String listen;

    /**
     * EventScript - script.
     */
    @SerializedName("script")
    private EventScript script;

    /**
     * New instance of CollectionEvent
     */
    public CollectionEvent() {
        super();
    }

    /**
     * Copy constructor.
     *
     * @param copy
     */
    public CollectionEvent(final CollectionEvent copy) {
        super();
        this.listen = copy.getListen();

        if (copy.getScript() != null) {
            this.script = new EventScript(copy.getScript());
        }

    }

    public String getListen() {
        return listen;
    }

    public void setListen(final String listen) {
        this.listen = listen;
    }

    public EventScript getScript() {
        return script;
    }

    public void setScript(final EventScript script) {
        this.script = script;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("CollectionEvent [listen=");
        builder.append(listen);
        builder.append(", script=");
        builder.append(script);
        builder.append("]");
        return builder.toString();
    }

}
