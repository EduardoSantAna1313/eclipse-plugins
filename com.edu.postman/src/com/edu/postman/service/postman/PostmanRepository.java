/**
 * Código para PostmanRepository.java criado em 2022-05-13
 */
package com.edu.postman.service.postman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * @author Eduardo
 */
public class PostmanRepository {

    /**
     * PostmanRepository - PostmanRepository.java
     */
    private static final PostmanRepository INSTANCE = new PostmanRepository();

    /**
     * String - PostmanRepository.java
     */
    private static final String API_KEY = "api_key";

    /**
     * Properties - PostmanRepository.java
     */
    private final Properties props = new Properties();

    /**
     * New instance to PostmanRepository.
     */
    private PostmanRepository() {
        final File folderDb = getFile();

        if (folderDb.exists()) {

            try (final InputStream is = new FileInputStream(folderDb);) {
                props.load(is);
            } catch (final Exception error) {
                throw new RuntimeException(error);
            }

        }

    }

    /**
     * Returns the value to instance.
     *
     * @return the instance
     */
    public static PostmanRepository getInstance() {
        return INSTANCE;
    }

    /**
     * Get the api key.
     *
     * @return
     */
    public String getApiKey() {
        return props.getProperty(API_KEY);
    }

    public boolean save(final String labelText) {
        props.setProperty(API_KEY, labelText);

        final File file = getFile();

        try (final OutputStream out = new FileOutputStream(file)) {

            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            props.store(out, "Postman preferences");

            return true;
        } catch (final Exception error) {
            error.printStackTrace();
        }

        return false;
    }

    /**
     * Get file.
     *
     * @return
     */
    private static final File getFile() {
        final Bundle bundle = FrameworkUtil.getBundle(PostmanRepository.class);

        final IPath stateLoc = Platform.getStateLocation(bundle);

        final File localPlugin = stateLoc.toFile();

        final String fDb = localPlugin.getAbsolutePath();

        return new File(fDb + "//postman//postman.properties");
    }

}
