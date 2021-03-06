package com.edu.editor;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * Activator para o plugin.
 *
 * @author Eduardo
 */
public class Activator extends AbstractUIPlugin {

    public static final String PLUGIN_ID = "com.edu.editor";

    private static Activator plugin;

    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    @Override
    public void stop(final BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    public static Activator getDefault() {
        return plugin;
    }

}
