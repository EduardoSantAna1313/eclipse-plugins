package com.edu.postman.wizards;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

import com.edu.postman.messages.Messages;
import com.edu.postman.service.postman.PostmanRepository;
import com.edu.postman.service.postman.PostmanService;
import com.edu.postman.service.postman.PostmanServiceBuilder;
import com.edu.postman.service.postman.collection.Collection;

/**
 * @author Eduardo
 */
public class PostmanImportWizard extends Wizard implements IImportWizard {

    /**
     * PostmanImportWizardPage - PostmanImportWizard.java
     */
    private PostmanImportWizardPage mainPage;

    /**
     * String - PostmanImportWizard.java
     */
    private String apiKey;

    /**
     * New instance to PostmanImportWizard.
     */
    public PostmanImportWizard() {
        super();
    }

    @Override
    public void init(final IWorkbench workbench, final IStructuredSelection selection) {
        setWindowTitle(Messages.PostmanMessage_FileImportWizard);
        setNeedsProgressMonitor(true);

        apiKey = PostmanRepository.getInstance().getApiKey();

        if (apiKey == null) {
            MessageDialog.openError(getShell(), Messages.PostmanMessage_Error,
                    Messages.PostmanMessage_ConfigureAnAPIKey);
            return;
        }

        mainPage = new PostmanImportWizardPage(Messages.PostmanMessage_PostmanImport, apiKey);
    }

    @Override
    public void addPages() {
        super.addPages();
        addPage(mainPage);
    }

    @Override
    public boolean canFinish() {
        final String outputFolder = mainPage.getOutputFolder();

        mainPage.setErrorMessage(null);

        if (outputFolder == null || outputFolder.isEmpty()) {
            mainPage.setErrorMessage(Messages.PostmanMessage_SelectAnOutputFolder);
            return false;
        }

        final List<Collection> file = mainPage.getSelectedCollections();

        if (file == null || file.isEmpty()) {
            mainPage.setErrorMessage(Messages.PostmanMessage_SelectAtLastOneCollection);
            return false;
        }

        return super.canFinish();
    }

    @Override
    public boolean performFinish() {
        final List<Collection> file = mainPage.getSelectedCollections();
        final String outputFolder = mainPage.getOutputFolder();

        final IRunnableWithProgress runnable = pProgressMonitor -> {

            try {

                final PostmanService service = new PostmanServiceBuilder(apiKey).build();

                final SubMonitor submonitor = SubMonitor.convert(pProgressMonitor, file.size());

                submonitor.setTaskName(Messages.PostmanMessage_Downloading);

                for (final Collection collection : file) {
                    submonitor.subTask(collection.getName() + ".json"); //$NON-NLS-1$
                    final InputStream is = service.collectionAsInputStream(collection.getUid());

                    final File output = Paths.get(outputFolder, collection.getName() + ".json").toFile(); //$NON-NLS-1$

                    if (!output.getParentFile().exists()) {
                        output.mkdirs();
                    }

                    try (final BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(output));) {
                        int r = 0;
                        final byte[] b = new byte[102400];

                        while ((r = is.read(b)) != -1 && !pProgressMonitor.isCanceled()) {
                            out.write(b, 0, r);
                        }

                        out.flush();
                    }

                    submonitor.split(1);
                }

            } catch (final Exception error) {
                error.printStackTrace();
                throw new RuntimeException(error);
            } finally {
                // NA
            }

        };

        try {

            final boolean cancelable = true;

            getContainer().run(true, cancelable, runnable);
        } catch (final Exception error) {
            MessageDialog.openError(getShell(), "Error", error.getMessage()); //$NON-NLS-1$
            return false;
        }

        return true;
    }

}
