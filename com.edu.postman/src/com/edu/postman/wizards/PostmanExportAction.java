package com.edu.postman.wizards;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.edu.postman.messages.Messages;
import com.edu.postman.service.postman.PostmanRepository;

/**
 * Postman export menu action.
 *
 * @author Eduardo
 */
public class PostmanExportAction implements IObjectActionDelegate {

    private Shell shell;

    private IFile selectedFile;

    @Override
    public void run(final IAction action) {
        final String apiKey = PostmanRepository.getInstance().getApiKey();

        if (apiKey == null) {
            MessageDialog.openError(shell, Messages.PostmanMessage_Error, Messages.PostmanMessage_ConfigureAnAPIKey);
            return;
        }

        final WizardDialog dialog = new WizardDialog(shell, new PostmanExportWizard(apiKey, selectedFile));
        dialog.create();
        dialog.open();
    }

    @Override
    public void selectionChanged(final IAction action, final ISelection selection) {

        if (selection.isEmpty()) {
            return;
        }

        if (selection instanceof IStructuredSelection) {
            final IStructuredSelection iselection = (IStructuredSelection) selection;

            if (iselection.getFirstElement() instanceof IFile) {
                selectedFile = (IFile) iselection.getFirstElement();
            }

        }

    }

    @Override
    public void setActivePart(final IAction action, final IWorkbenchPart targetPart) {
        shell = targetPart.getSite().getShell();
    }

}