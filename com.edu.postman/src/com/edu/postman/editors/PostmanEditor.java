package com.edu.postman.editors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * An example showing how to create a multi-page editor.
 * This example has 3 pages:
 * <ul>
 * <li>page 0 contains a nested text editor.
 * <li>page 1 allows you to change the font used in page 2
 * <li>page 2 shows the words in page 0 in sorted order
 * </ul>
 */
public class PostmanEditor extends MultiPageEditorPart implements IResourceChangeListener {

    /** The text editor used in page 0. */
    private TextEditor editor;

    /**
     * Creates a multi-page editor example.
     */
    public PostmanEditor() {
        super();
        ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
    }

    /**
     * Page with the text.
     */
    private void createPageJson() {

        try {
            editor = new TextEditor();
            final int index = addPage(editor, getEditorInput());
            setPageText(index, editor.getTitle());

            final String editorText = editor.getDocumentProvider().getDocument(editor.getEditorInput()).get();

            final Gson gson = new GsonBuilder().setPrettyPrinting().create();

            final JsonElement element = JsonParser.parseString(editorText);

            editor.getDocumentProvider().getDocument(editor.getEditorInput()).set(gson.toJson(element));

        } catch (final PartInitException e) {
            ErrorDialog.openError(getSite().getShell(), "Error creating nested text editor", null, e.getStatus());
        }

    }

    /**
     * The visual of the collection.
     */
    private void createPageUI() {

        final Composite composite = new Composite(getContainer(), SWT.NONE);
        final GridLayout layout = new GridLayout();
        composite.setLayout(layout);
        layout.numColumns = 2;

        final GridData gd = new GridData();
        gd.horizontalSpan = 2;
        final Label label = new Label(composite, SWT.NONE);
        label.setText(editor.getTitle());
        label.setLayoutData(gd);

        final int index = addPage(composite);
        setPageText(index, "Properties");
    }

    /**
     * Creates the pages of the multi-page editor.
     */
    @Override
    protected void createPages() {
        createPageJson();
        createPageUI();
    }

    /**
     * The <code>MultiPageEditorPart</code> implementation of this
     * <code>IWorkbenchPart</code> method disposes all nested editors.
     * Subclasses may extend.
     */
    @Override
    public void dispose() {
        ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
        super.dispose();
    }

    /**
     * Saves the multi-page editor's document.
     */
    @Override
    public void doSave(final IProgressMonitor monitor) {
        getEditor(0).doSave(monitor);
    }

    /**
     * Saves the multi-page editor's document as another file.
     * Also updates the text for page 0's tab, and updates this multi-page editor's
     * input
     * to correspond to the nested editor's.
     */
    @Override
    public void doSaveAs() {
        final IEditorPart editor = getEditor(0);
        editor.doSaveAs();
        setPageText(0, editor.getTitle());
        setInput(editor.getEditorInput());
    }

    /*
     * (non-Javadoc)
     * Method declared on IEditorPart
     */
    public void gotoMarker(final IMarker marker) {
        setActivePage(0);
        IDE.gotoMarker(getEditor(0), marker);
    }

    /**
     * The <code>MultiPageEditorExample</code> implementation of this method
     * checks that the input is an instance of <code>IFileEditorInput</code>.
     */
    @Override
    public void init(final IEditorSite site, final IEditorInput editorInput) throws PartInitException {

        if (!(editorInput instanceof IFileEditorInput)) {
            throw new PartInitException("Invalid Input: Must be IFileEditorInput");
        }

        super.init(site, editorInput);
    }

    /*
     * (non-Javadoc)
     * Method declared on IEditorPart.
     */
    @Override
    public boolean isSaveAsAllowed() {
        return true;
    }

    /**
     * Calculates the contents of page 2 when the it is activated.
     */
    @Override
    protected void pageChange(final int newPageIndex) {
        super.pageChange(newPageIndex);

    }

    /**
     * Closes all project files on project close.
     */
    @Override
    public void resourceChanged(final IResourceChangeEvent event) {

        if (event.getType() == IResourceChangeEvent.PRE_CLOSE) {
            Display.getDefault().asyncExec(() -> {
                final IWorkbenchPage[] pages = getSite().getWorkbenchWindow().getPages();

                for (final IWorkbenchPage page : pages) {

                    if (((FileEditorInput) editor.getEditorInput()).getFile().getProject()
                            .equals(event.getResource())) {
                        final IEditorPart editorPart = page.findEditor(editor.getEditorInput());
                        page.closeEditor(editorPart, true);
                    }

                }

            });
        }

    }

}
