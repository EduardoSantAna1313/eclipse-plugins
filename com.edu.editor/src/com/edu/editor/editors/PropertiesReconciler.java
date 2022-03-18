package com.edu.editor.editors;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordPatternRule;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

/**
 * Property reconciler. Cria um formatter de código que irá colorir as tags
 * entre "{{ }}".
 *
 * @author edusilva
 *
 */
public class PropertiesReconciler extends PresentationReconciler {

	private final TextAttribute tagAttribute = new TextAttribute(
			Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN), null, SWT.BOLD);

	private final IToken keyword = new Token(tagAttribute);

	public PropertiesReconciler() {

		final IWordDetector iWordDetector = new IWordDetector() {
			@Override
			public boolean isWordStart(final char ch) {
				return Character.isLetter(ch) || ch == '_';
			}

			@Override
			public boolean isWordPart(final char ch) {
				return (ch >= '0' && ch <= 'Z') || ch == '{' || ch == '}' || ch == '_';
			}
		};
		final IRule ruleVariable = new WordPatternRule(iWordDetector, "{{", "}}", keyword);

		final RuleBasedScanner scanner = new RuleBasedScanner();
		scanner.setRules(new IRule[] { ruleVariable });

		final DefaultDamagerRepairer dr = new DefaultDamagerRepairer(scanner);
		this.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		this.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
	}
}