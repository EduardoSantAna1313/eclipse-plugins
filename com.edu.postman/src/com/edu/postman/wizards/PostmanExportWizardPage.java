package com.edu.postman.wizards;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.edu.postman.messages.Messages;
import com.edu.postman.service.postman.PostmanServiceBuilder;
import com.edu.postman.service.postman.workspace.Workspace;

/**
 * @author Eduardo
 */
public class PostmanExportWizardPage extends WizardPage {

    /**
     * String - PostmanImportWizardPage.java
     */
    private final String apiKey;

    private ComboViewer comboWorkspaces;

    /**
     * New instance to PostmanImportWizardPage.
     *
     * @param pageName
     * @param apiKey
     */
    public PostmanExportWizardPage(final String pageName, final String apiKey, final IFile selectedFile) {
        super(pageName);
        setTitle(pageName);
        setDescription("Exporting " + selectedFile.getName());

        this.apiKey = apiKey;
    }

    @Override
    public void createControl(final Composite parent) {
        final Composite container = new Composite(parent, SWT.NONE);
        final GridData fileSelectionData = new GridData(GridData.GRAB_HORIZONTAL | GridData.FILL_HORIZONTAL);
        container.setLayoutData(fileSelectionData);

        container.setLayout(new GridLayout(1, true));

        final GridLayout fileSelectionLayout = new GridLayout();
        fileSelectionLayout.numColumns = 3;
        fileSelectionLayout.makeColumnsEqualWidth = false;
        fileSelectionLayout.marginWidth = 0;
        fileSelectionLayout.marginHeight = 0;

        final Group groupConfig = new Group(container, SWT.BORDER);
        groupConfig.setText(Messages.PostmanMessage_PostmanConfiguration);
        groupConfig.setLayout(fileSelectionLayout);
        groupConfig.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

        new Label(groupConfig, SWT.BORDER).setText(Messages.PostmanMessage_PostmanWorkspaces);
        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        gridData.horizontalSpan = 2;

        comboWorkspaces = new ComboViewer(groupConfig, SWT.BORDER);
        comboWorkspaces.getCombo().setLayoutData(gridData);
        comboWorkspaces.setContentProvider(new ArrayContentProvider());
        comboWorkspaces.setLabelProvider(new LabelProvider() {

            @Override
            public String getText(final Object element) {

                if (element instanceof Workspace) {
                    final Workspace workspace = (Workspace) element;

                    return workspace.getName();
                }

                return super.getText(element);
            }

        });

        loadPostmanWorkspaces(comboWorkspaces);

        comboWorkspaces.addSelectionChangedListener(event -> {

            setErrorMessage(null);

            final StructuredSelection sel = (StructuredSelection) event.getSelection();
            final Workspace workspace = (Workspace) sel.getFirstElement();

            if (workspace == null) {
                setErrorMessage(Messages.PostmanMessage_TheWorkspaceDoesNotHaveAnyCollections);
                return;
            }

            getContainer().updateButtons();

        });

        gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        gridData.horizontalSpan = 3;

        container.moveAbove(null);

        setControl(parent);
    }

    /**
     * Load postman workspaces.
     *
     * @param comboWorkspaces
     */
    private void loadPostmanWorkspaces(final ComboViewer comboWorkspaces) {
        Display.getDefault().asyncExec(() -> {

            comboWorkspaces.getCombo().setEnabled(false);
            final List<Workspace> workspaces = new PostmanServiceBuilder(apiKey).build().workspaces().stream()
                    .sorted(Comparator.comparing(Workspace::getName)).collect(Collectors.toList());

            workspaces.forEach(comboWorkspaces::add);
            comboWorkspaces.getCombo().setEnabled(true);
        });
    }

    /**
     * Get selected workspace.
     *
     * @return
     */
    public Workspace getSelectedWorkspace() {
        final Object obj = comboWorkspaces.getStructuredSelection().getFirstElement();
        return (Workspace) obj;
    }

}
