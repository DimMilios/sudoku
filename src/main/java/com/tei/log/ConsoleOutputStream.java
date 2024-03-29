package com.tei.log;

import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/*
 *	Class to intercept output from a PrintStream and add it to a Document.
 *  The output can optionally be redirected to a different PrintStream.
 *  The text displayed in the Document can be color coded to indicate
 *  the output source.
 */
class ConsoleOutputStream extends ByteArrayOutputStream {
	private final MessageConsole messageConsole;
	private final String EOL = System.getProperty("line.separator");
	private SimpleAttributeSet attributes;
	private PrintStream printStream;
	private StringBuffer buffer = new StringBuffer(80);
	private boolean isFirstLine = true;

	/*
	 *  Specify the option text color and PrintStream
	 */
	public ConsoleOutputStream(MessageConsole messageConsole,
							   Color textColor,
							   PrintStream printStream) {
		this.messageConsole = messageConsole;
		if (textColor != null) {
			attributes = new SimpleAttributeSet();
			StyleConstants.setForeground(attributes, textColor);
		}

		this.printStream = printStream;
	}

	/*
	 *  Override this method to intercept the output text. Each line of text
	 *  output will actually involve invoking this method twice:
	 *
	 *  a) for the actual text message
	 *  b) for the newLine string
	 *
	 *  The message will be treated differently depending on whether the line
	 *  will be appended or inserted into the Document
	 */
	public void flush() {
		String message = toString();

		if (message.length() == 0) return;

		handleAppend(message);

		reset();
	}

	/*
	 *	We don't want to have blank lines in the Document. The first line
	 *  added will simply be the message. For additional lines it will be:
	 *
	 *  newLine + message
	 */
	private void handleAppend(String message) {
		//  This check is needed in case the text in the Document has been
		//	cleared. The buffer may contain the EOL string from the previous
		//  message.

		if (messageConsole.getDocument().getLength() == 0)
			buffer.setLength(0);

		if (EOL.equals(message)) {
			buffer.append(message);
		} else {
			buffer.append(message);
			clearBuffer();
		}

	}

	/*
	 *  The message and the newLine have been added to the buffer in the
	 *  appropriate order so we can now update the Document and send the
	 *  text to the optional PrintStream.
	 */
	private void clearBuffer() {
		//  In case both the standard out and standard err are being redirected
		//  we need to insert a newline character for the first line only

		if (isFirstLine && messageConsole.getDocument().getLength() != 0) {
			buffer.insert(0, "\n");
		}

		isFirstLine = false;
		String line = buffer.toString();

		try {
			int offset = messageConsole.getDocument().getLength();
			messageConsole.getDocument().insertString(offset, line, attributes);
			messageConsole.getTextComponent().setCaretPosition(messageConsole.getDocument().getLength());
		} catch (BadLocationException ble) {
		}

		if (printStream != null) {
			printStream.print(line);
		}

		buffer.setLength(0);
	}
}
