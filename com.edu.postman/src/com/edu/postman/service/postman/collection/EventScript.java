package com.edu.postman.service.postman.collection;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author Eduardo
 */
public class EventScript {

    @SerializedName("id")
    private String id;

    @SerializedName("exec")
    private List<String> exec;

    @SerializedName("type")
    private String type;

    /**
     * New instance of EventScript
     */
    public EventScript() {
        super();
    }

    /**
     * Copy constructor.
     *
     * @param script
     */
    public EventScript(final EventScript script) {
        this.id = script.getId();
        this.type = script.getType();

        if (script.getExec() != null) {
            this.exec = new ArrayList<>(script.getExec());
        }

    }

    /**
     * Retrieve the value of id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Set a new value to id.
     *
     * @param id
     *               the id to set
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Retrieve the value of exec.
     *
     * @return the exec
     */
    public List<String> getExec() {
        return exec;
    }

    /**
     * Set a new value to exec.
     *
     * @param exec
     *                 the exec to set
     */
    public void setExec(final List<String> exec) {
        this.exec = exec;
    }

    /**
     * Retrieve the value of type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Set a new value to type.
     *
     * @param type
     *                 the type to set
     */
    public void setType(final String type) {
        this.type = type;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("EventScript [id=");
        builder.append(id);
        builder.append(", exec=");
        builder.append(exec);
        builder.append(", type=");
        builder.append(type);
        builder.append("]");
        return builder.toString();
    }

}
