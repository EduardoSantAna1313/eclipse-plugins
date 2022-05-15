package com.edu.postman.wizards;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

import com.edu.postman.messages.Messages;
import com.edu.postman.service.postman.PostmanServiceBuilder;
import com.edu.postman.service.postman.collection.Collection;
import com.edu.postman.service.postman.workspace.Workspace;

/**
 * @author Eduardo
 */
public class PostmanImportWizardPage extends WizardPage {

    /**
     * String - PostmanImportWizardPage.java
     */
    private static final String OUTPUT_FOLDER_TIP_TEXT = "../../workspace/project.test/src/test/resources/"; //$NON-NLS-1$

    /**
     * String - PostmanImportWizardPage.java
     */
    private final String apiKey;

    /**
     * Text - PostmanImportWizardPage.java
     */
    private Text textOutput;

    /**
     * TableViewer - PostmanImportWizardPage.java
     */
    private TableViewer viewer;

    /**
     * New instance to PostmanImportWizardPage.
     *
     * @param pageName
     * @param apiKey
     */
    public PostmanImportWizardPage(final String pageName, final String apiKey) {
        super(pageName);
        setTitle(pageName);
        setDescription(Messages.PostmanMessage_PageDescription);

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

        new Label(groupConfig, SWT.BORDER).setText(Messages.PostmanMessage_OutputFolder);
        textOutput = new Text(groupConfig, SWT.BORDER);
        textOutput.setText(""); //$NON-NLS-1$
        textOutput.setToolTipText(OUTPUT_FOLDER_TIP_TEXT);
        textOutput.setMessage(OUTPUT_FOLDER_TIP_TEXT);
        textOutput.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        textOutput.addModifyListener(e -> {

            if (e.data != null) {
                getContainer().updateButtons();
            }

        });

        final Button buttonSeach = new Button(groupConfig, SWT.BORDER);
        buttonSeach.setText(Messages.PostmanMessage_Search);
        buttonSeach.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseUp(final MouseEvent e) {

                final IPath path = getSelectedOutputFolder(Messages.PostmanMessage_SelectFolder,
                        Messages.PostmanMessage_PostmanFolder);

                final IResource r = ResourcesPlugin.getWorkspace().getRoot().findMember(path.toOSString());

                textOutput.setText(r.getLocation().toString());

                getContainer().updateButtons();
            }

        });

        new Label(groupConfig, SWT.BORDER).setText(Messages.PostmanMessage_PostmanWorkspaces);
        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        gridData.horizontalSpan = 2;

        final ComboViewer comboWorkspaces = new ComboViewer(groupConfig, SWT.BORDER);
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

            viewer.getTable().removeAll();
            setErrorMessage(null);

            final StructuredSelection sel = (StructuredSelection) event.getSelection();
            final Workspace workspace = (Workspace) sel.getFirstElement();

            final Workspace postmanWorkspace = new PostmanServiceBuilder(apiKey).build().workspace(workspace.getId());

            if (postmanWorkspace.getCollections() == null || postmanWorkspace.getCollections().isEmpty()) {
                setErrorMessage(Messages.PostmanMessage_TheWorkspaceDoesNotHaveAnyCollections);
                return;
            }

            final List<Collection> collections = postmanWorkspace.getCollections().stream()
                    .sorted(Comparator.comparing(Collection::getName)).collect(Collectors.toList());
            collections.forEach(viewer::add);
        });

        gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        gridData.horizontalSpan = 3;

        final Group group = new Group(container, SWT.BORDER);
        group.setLayoutData(gridData);
        group.setText(Messages.PostmanMessage_PostmanCollections);
        group.setLayout(new GridLayout(1, true));

        viewer = new TableViewer(group, SWT.BORDER | SWT.MULTI | SWT.CHECK);
        viewer.getTable().setLayoutData(gridData);
        viewer.setContentProvider(new ArrayContentProvider());
        viewer.setLabelProvider(new LabelProvider() {

            @Override
            public String getText(final Object element) {

                if (element instanceof Collection) {
                    final Collection collection = (Collection) element;

                    return collection.getName();
                }

                return super.getText(element);
            }

        });

        viewer.addSelectionChangedListener(e -> {
            final StructuredSelection sel = (StructuredSelection) e.getStructuredSelection();

            final Collection collection = (Collection) sel.getFirstElement();

            if (collection != null) {
                getContainer().updateButtons();

                this.setMessage(collection.getName() + ".json"); //$NON-NLS-1$
            }

        });

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
     * Get selected output folder..
     *
     * @param msg
     * @param title
     *
     * @return
     */
    protected IPath getSelectedOutputFolder(final String msg, final String title) {

        final IContainer initialSelection = ResourcesPlugin.getWorkspace().getRoot().getParent();

        final ContainerSelectionDialog dialog = new ContainerSelectionDialog(getControl().getShell(), initialSelection,
                true, msg);

        if (title != null) {
            dialog.setTitle(title);
        }

        dialog.showClosedProjects(false);
        dialog.open();
        final Object[] result = dialog.getResult();

        if (result != null && result.length == 1) {
            return (IPath) result[0];
        }

        return null;
    }

    /**
     * Get the selected collections.
     *
     * @return
     */
    public List<Collection> getSelectedCollections() {
        final TableItem[] items = viewer.getTable().getItems();

        return Stream.of(items).filter(TableItem::getChecked).map(i -> (Collection) i.getData())
                .collect(Collectors.toList());
    }

    /**
     * Get the output folder.
     *
     * @return
     */
    public String getOutputFolder() {
        return textOutput.getText();
    }

}
