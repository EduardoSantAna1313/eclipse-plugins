package com.edu.postman.wizards;

import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

import com.edu.postman.messages.Messages;
import com.edu.postman.service.postman.PostmanService;
import com.edu.postman.service.postman.PostmanServiceBuilder;
import com.edu.postman.service.postman.collection.Collection;
import com.edu.postman.service.postman.workspace.Workspace;

/**
 * @author Eduardo
 */
public class PostmanExportWizard extends Wizard implements IExportWizard {

    /**
     * PostmanExportWizardPage - PostmanExportWizard.java
     */
    private final PostmanExportWizardPage mainPage;

    /**
     * IFile - PostmanExportWizard.java
     */
    private final IFile selectedFile;

    /**
     * String - PostmanImportWizard.java
     */
    private final String apiKey;

    /**
     * New instance to PostmanExportWizard.
     *
     * @param selectedFile
     */
    public PostmanExportWizard(final String apiKey, final IFile selectedFile) {
        this.apiKey = apiKey;
        this.selectedFile = selectedFile;
        setWindowTitle(Messages.PostmanMessage_FileImportWizard);
        setNeedsProgressMonitor(true);

        mainPage = new PostmanExportWizardPage(Messages.PostmanMessage_PostmanImport, apiKey, selectedFile);
    }

    @Override
    public void init(final IWorkbench workbench, final IStructuredSelection selection) {
        setWindowTitle(Messages.PostmanMessage_FileImportWizard);
        setNeedsProgressMonitor(true);
    }

    @Override
    public void addPages() {
        super.addPages();
        addPage(mainPage);
    }

    @Override
    public boolean canFinish() {
        mainPage.setErrorMessage(null);

        if (mainPage.getSelectedWorkspace() == null) {
            mainPage.setErrorMessage(Messages.PostmanMessage_SelectAWorkspace);
            return false;
        }

        return super.canFinish();
    }

    @Override
    public boolean performFinish() {

        final Workspace workspace = mainPage.getSelectedWorkspace();

        final IRunnableWithProgress runnable = pProgressMonitor -> {

            try (final BufferedReader reader = new BufferedReader(new InputStreamReader(selectedFile.getContents()))) {

                final SubMonitor submonitor = SubMonitor.convert(pProgressMonitor, 100);

                final PostmanService service = new PostmanServiceBuilder(apiKey).build();

                final String json = reader.lines().collect(Collectors.joining("\n"));

                submonitor.setTaskName("Exportando a collection para o workspace " + workspace.getName());
                final PropertyChangeListener listener = new PostmanChangeListener(submonitor);

                final Collection newCollection = service.save(workspace.getId(), json, listener);
                newCollection.setName(newCollection.getName() + " " + System.currentTimeMillis());

                submonitor.done();

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
