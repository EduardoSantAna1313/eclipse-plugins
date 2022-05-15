package com.edu.postman.messages;

import org.eclipse.osgi.util.NLS;

/**
 * @author Eduardo
 */
public class Messages extends NLS {

    private static final String BUNDLE_NAME = "com.edu.postman.messages.messages";

    public static String PostmanMessage_ConfigureAnAPIKey;

    public static String PostmanMessage_Downloading;

    public static String PostmanMessage_Error;

    public static String PostmanMessage_FileImportWizard;

    public static String PostmanMessage_OutputFolder;

    public static String PostmanMessage_PageDescription;

    public static String PostmanMessage_PostmanCollections;

    public static String PostmanMessage_PostmanConfiguration;

    public static String PostmanMessage_PostmanFolder;

    public static String PostmanMessage_PostmanImport;

    public static String PostmanMessage_PostmanPreferences;

    public static String PostmanMessage_PostmanWorkspaces;

    public static String PostmanMessage_Search;

    public static String PostmanMessage_SelectAnOutputFolder;

    public static String PostmanMessage_SelectAtLastOneCollection;

    public static String PostmanMessage_SelectFolder;

    public static String PostmanMessage_TheWorkspaceDoesNotHaveAnyCollections;

    public static String PostmanMessage_SelectAWorkspace;

    static {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }

}
