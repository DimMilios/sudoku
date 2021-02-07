package com.tei.log;

import java.io.*;
import java.awt.*;
import javax.swing.text.*;

/*
 *  Create a simple console to display text messages.
 *
 *  Messages can be directed here from different sources. Each source can
 *  have its messages displayed in a different color.
 *
 *  Messages can either be appended to the console or inserted as the first
 *  line of the console
 *
 *  You can limit the number of lines to hold in the Document.
 */
public class MessageConsole {
	private JTextComponent textComponent;
	private Document document;

	/*
	 *	Use the text component specified as a simply console to display
	 *  text messages.
	 *
	 *  The messages can either be appended to the end of the console or
	 *  inserted as the first line of the console.
	 */
	public MessageConsole(JTextComponent textComponent) {
		this.textComponent = textComponent;
		this.document = textComponent.getDocument();
		textComponent.setEditable(false);
	}

	/*
	 *  Redirect the output from the standard output to the console
	 *  using the default text color and null PrintStream
	 */
	public void redirectOut() {
		redirectOut(null, null);
	}

	/*
	 *  Redirect the output from the standard output to the console
	 *  using the specified color and PrintStream. When a PrintStream
	 *  is specified the message will be added to the Document before
	 *  it is also written to the PrintStream.
	 */
	public void redirectOut(Color textColor, PrintStream printStream) {
		ConsoleOutputStream cos = new ConsoleOutputStream(this, textColor, printStream);
		System.setOut(new PrintStream(cos, true));
	}

	/*
	 *  Redirect the output from the standard error to the console
	 *  using the default text color and null PrintStream
	 */
	public void redirectErr() {
		redirectErr(null, null);
	}

	/*
	 *  Redirect the output from the standard error to the console
	 *  using the specified color and PrintStream. When a PrintStream
	 *  is specified the message will be added to the Document before
	 *  it is also written to the PrintStream.
	 */
	public void redirectErr(Color textColor, PrintStream printStream) {
		ConsoleOutputStream cos = new ConsoleOutputStream(this, textColor, printStream);
		System.setErr(new PrintStream(cos, true));
	}

	public Document getDocument() {
		return document;
	}

	public JTextComponent getTextComponent() {
		return textComponent;
	}
}
