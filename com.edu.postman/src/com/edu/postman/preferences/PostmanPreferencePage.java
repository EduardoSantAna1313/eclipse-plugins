package com.edu.postman.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.edu.postman.messages.Messages;
import com.edu.postman.service.postman.PostmanRepository;

/**
 * @author Eduardo
 */
public class PostmanPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    /**
     * StringFieldEditor - PostmanPreferencePage.java
     */
    private StringFieldEditor editor;

    /**
     * New instance to PostmanPreferencePage.
     */
    public PostmanPreferencePage() {
        super(GRID);
        setDescription(Messages.PostmanMessage_PostmanPreferences);
    }

    @Override
    public boolean performOk() {

        return PostmanRepository.getInstance().save(editor.getStringValue());
    }

    @Override
    public void createFieldEditors() {
        editor = new StringFieldEditor("API_KEY", "API Key", getFieldEditorParent());
        editor.setStringValue(PostmanRepository.getInstance().getApiKey());
        addField(editor);
    }

    @Override
    public void init(final IWorkbench workbench) {

    }

}