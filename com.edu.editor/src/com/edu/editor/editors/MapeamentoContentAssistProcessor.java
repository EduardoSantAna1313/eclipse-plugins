package com.edu.editor.editors;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

/**
 * Conent assist para mapeamentos. Exibie propstas de código.
 *
 * @author Eduardo
 *
 */
public class MapeamentoContentAssistProcessor implements IContentAssistProcessor {

    private static final List<String> PROPOSALS = Arrays.asList("NOTA_NUMERO", "NOTA_DATA", "PRESTADOR_CPFCNPJ",
            "TOMADOR_CPF_CNPJ", "DISCRIMINACAO", "VALOR_IR", "VALOR_INSS", "VALOR_LIQUIDO", "VALOR_SERVICO",
            "VALOR_CSLL", "VALOR_COFINS", "VALOR_PIS", "IGNORE");

    @Override
    public ICompletionProposal[] computeCompletionProposals(final ITextViewer viewer, final int offset) {

        final IDocument document = viewer.getDocument();

        try {
            final int lineOfOffset = document.getLineOfOffset(offset);
            final int lineOffset = document.getLineOffset(lineOfOffset);

            final String[] lines = document.get().split("\n");

            if (lineOfOffset >= lines.length) {
                return new ICompletionProposal[0];
            }

            final String line = lines[lineOfOffset];
            final String sline = line.substring(0, offset - lineOffset);

            if (sline.contains("{{")) {
                final int i = sline.indexOf("{{");

                if (sline.length() > i + 2) {
                    final String digitedText = sline.substring(i + 2).toUpperCase();

                    final int replacementLength = digitedText.length();

                    return PROPOSALS.stream().filter(proposal -> proposal.startsWith(digitedText))
                            .map(proposal -> new CompletionProposal(proposal, offset - replacementLength,
                                    replacementLength, proposal.length() + replacementLength))
                            .toArray(ICompletionProposal[]::new);
                }
                return PROPOSALS.stream()
                        .map(proposal -> new CompletionProposal(proposal, offset, 0, proposal.length()))
                        .toArray(ICompletionProposal[]::new);
            }

            if (offset != lineOffset) {
                return new ICompletionProposal[0];
            }
        } catch (final BadLocationException error) {
            error.printStackTrace();
        }
        return PROPOSALS.stream().map(proposal -> new CompletionProposal(proposal, offset, 0, proposal.length()))
                .toArray(ICompletionProposal[]::new);
    }

    @Override
    public IContextInformation[] computeContextInformation(final ITextViewer viewer, final int offset) {
        return null;
    }

    @Override
    public char[] getCompletionProposalAutoActivationCharacters() {
        return "{".toCharArray();
    }

    @Override
    public char[] getContextInformationAutoActivationCharacters() {
        return null;
    }

    @Override
    public String getErrorMessage() {
        return null;
    }

    @Override
    public IContextInformationValidator getContextInformationValidator() {
        return null;
    }

}