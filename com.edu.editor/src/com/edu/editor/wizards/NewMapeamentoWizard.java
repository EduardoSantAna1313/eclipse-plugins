package com.edu.editor.wizards;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

/**
 * Wizard para criar novo mapeamento.
 *
 * @author edusilva
 *
 */
public class NewMapeamentoWizard extends Wizard implements INewWizard {

	private NewMapeamentoWizardPage page;

	private ISelection selection;

	/**
	 * Constructor for SampleNewWizard.
	 */
	public NewMapeamentoWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	/**
	 * Adding the page to the wizard.
	 */
	@Override
	public void addPages() {
		page = new NewMapeamentoWizardPage(selection);
		addPage(page);
	}

	@Override
	public boolean performFinish() {
		final var containerName = page.getContainerName();
		final var fileName = page.getFileName();
		final IRunnableWithProgress op = monitor -> {
			try {
				doFinish(containerName, fileName, monitor);
			} catch (final CoreException e) {
				throw new InvocationTargetException(e);
			} finally {
				monitor.done();
			}
		};
		try {
			getContainer().run(true, false, op);
		} catch (final InterruptedException e) {
			return false;
		} catch (final InvocationTargetException e) {
			final var realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", realException.getMessage());
			return false;
		}
		return true;
	}

	private void doFinish(final String containerName, final String fileName, final IProgressMonitor monitor)
			throws CoreException {
		// create a sample file
		monitor.beginTask("Creating " + fileName, 2);
		final var root = ResourcesPlugin.getWorkspace().getRoot();
		final var resource = root.findMember(new Path(containerName));
		if (!resource.exists() || !(resource instanceof IContainer)) {
			throwCoreException("Container \"" + containerName + "\" does not exist.");
		}
		final var container = (IContainer) resource;
		final var file = container.getFile(new Path(fileName));
		try {
			final var stream = openContentStream();
			if (file.exists()) {
				file.setContents(stream, true, true, monitor);
			} else {
				file.create(stream, true, monitor);
			}
			stream.close();
		} catch (final IOException e) {
			// NA
		}

		monitor.worked(1);
		monitor.setTaskName("Opening file for editing...");
		getShell().getDisplay().asyncExec(() -> {

			final var activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			try {
				IDE.openEditor(activePage, file, true);
			} catch (final PartInitException e) {
			}
		});

		monitor.worked(1);
	}

	private InputStream openContentStream() {
		final var contents = new StringBuilder();
		contents.append(" Teste inicial\n");
		contents.append(" {{IGNORE}}12345ABCDE\n");
		contents.append("123456{{VALOR_IR}}(.+?)\n");
		return new ByteArrayInputStream(contents.toString().getBytes());
	}

	private void throwCoreException(final String message) throws CoreException {
		final IStatus status = new Status(IStatus.ERROR, "com.edu.editor", IStatus.OK, message, null);
		throw new CoreException(status);
	}

	@Override
	public void init(final IWorkbench workbench, final IStructuredSelection selection) {
		this.selection = selection;
	}
}