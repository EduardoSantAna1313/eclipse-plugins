package com.edu.postman.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.edu.postman.messages.Messages;
import com.edu.postman.service.postman.PostmanRepository;

/**
 * @author Eduardo
 */
public class PostmanPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    /**
     * Text - PostmanPreferencePage.java
     */
    private Text textApiKey;

    /**
     * New instance to PostmanPreferencePage.
     */
    public PostmanPreferencePage() {
        super(GRID);
    }

    @Override
    public void init(final IWorkbench arg0) {
        setDescription(Messages.PostmanMessage_PostmanPreferences);
    }

    @Override
    protected Control createContents(final Composite parent) {

        final Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new GridLayout(2, false));

        new Label(container, SWT.NONE).setText("Api Key");

        textApiKey = new Text(container, SWT.BORDER);
        textApiKey.setToolTipText("PMAK-1234aabb12abcd1a1a1abcd1-a1234a12ab12a1234a1a1a12ab12345678");
        textApiKey.setMessage("PMAK-1234aabb12abcd1a1a1abcd1-a1234a12ab12a1234a1a1a12ab12345678");
        textApiKey.setText(PostmanRepository.getInstance().getApiKey());
        textApiKey.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

        return super.createContents(parent);
    }

    @Override
    public boolean performOk() {

        return PostmanRepository.getInstance().save(textApiKey.getText());
    }

    @Override
    public void createFieldEditors() {
        // NA
    }

}