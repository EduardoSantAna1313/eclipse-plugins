package com.edu.annotation.processor;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

/**
 * @author Eduardo
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({
    QueryAnnotationProcessor.ENTITY_TYPE
})
public class QueryAnnotationProcessor extends AbstractProcessor {

    /**
     * String - ENTITY_TYPE.
     */
    public static final String ENTITY_TYPE = "com.fh.camaleo.core.database.Query";

    @Override
    public boolean process(final Set<? extends TypeElement> pAnnotations, final RoundEnvironment pRoundEnvironment) {

        System.out.println("Init processing...");

        final MessageConsole console = getConsole("AnnotationProcessor");

        try (final MessageConsoleStream out = console.newMessageStream();) {

            console.clearConsole();

            for (final TypeElement el : pAnnotations) {
                final Set<? extends Element> elementsAnnotatedWith = pRoundEnvironment.getElementsAnnotatedWith(el);

                for (final Element element : elementsAnnotatedWith) {

                    final TypeElement type = (TypeElement) element;

                    System.out.println(type);

                    out.write(type.toString().getBytes());

                }

            }

        } catch (final Exception error) {
            error.printStackTrace();
        }

        System.out.println("End processing");

        return false;
    }

    /**
     * Get the console.
     *
     * @param name
     *
     * @return
     */
    public static MessageConsole getConsole(final String name) {
        final ConsolePlugin plugin = ConsolePlugin.getDefault();
        final IConsoleManager conMan = plugin.getConsoleManager();
        final IConsole[] existing = conMan.getConsoles();

        for (final IConsole element : existing) {

            if (name.equals(element.getName())) {
                return (MessageConsole) element;
            }

        }

        final MessageConsole myConsole = new MessageConsole(name, null);
        conMan.addConsoles(new IConsole[] {
            myConsole
        });
        myConsole.setConsoleAutoScrollLock(true);
        return myConsole;
    }

}
