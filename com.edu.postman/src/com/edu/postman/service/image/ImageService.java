/**
 * Código para ImageService.java criado em 2022-05-13
 */
package com.edu.postman.service.image;

import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.FrameworkUtil;

/**
 * @author Eduardo
 */
public class ImageService {

    private static Map<String, Image> images = new HashMap<>();

    /**
     * New instance to ImageService.
     */
    private ImageService() {
        // NA
    }

    /**
     * Image descriptor from icons folder.
     *
     * @param name
     *
     * @return
     */
    public static ImageDescriptor getImageDescriptor(final String name) {

        try {

            final URL url = FrameworkUtil.getBundle(ImageService.class).getEntry(Paths.get("/icons/", name).toString());
            return ImageDescriptor.createFromURL(url);
        } catch (final Exception error) {

            return ImageDescriptor.getMissingImageDescriptor();
        }

    }

    /**
     * Returns the reload icon.
     *
     * @return
     */
    public static Image reloadIcon() {

        return images.computeIfAbsent(ISharedImages.IMG_ELCL_SYNCED, a -> PlatformUI.getWorkbench().getSharedImages()
                .getImageDescriptor(ISharedImages.IMG_ELCL_SYNCED).createImage());
    }

}
