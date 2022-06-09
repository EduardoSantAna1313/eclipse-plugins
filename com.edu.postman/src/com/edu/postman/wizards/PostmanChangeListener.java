package com.edu.postman.wizards;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.runtime.SubMonitor;

/**
 * @author Eduardo
 */
public class PostmanChangeListener implements PropertyChangeListener {

    private final SubMonitor submonitor;

    private long totalSend = 0;

    /**
     * New instance to PostmanChangeListener.
     *
     * @param submonitor
     */
    public PostmanChangeListener(final SubMonitor submonitor) {
        super();
        this.submonitor = submonitor;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        final int lenght = (int) evt.getOldValue();
        final int newValue = (int) evt.getNewValue();

        totalSend += newValue;

        final int percent = (int) ((float) totalSend / lenght * 100);
        submonitor.subTask("Exported: " + percent + "%");
        submonitor.split(percent);
    }

}
