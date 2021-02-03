package com.tei.view;

import javax.swing.*;
import java.awt.*;

public class AboutPanel extends JPanel {

	private JLabel header;
	private JLabel footer;
	private JTextArea infoArea;

	public AboutPanel() {
		init();
	}

	private void init() {
		this.header = new JLabel("Σχετικά με το Project");
		this.header.setFont(new Font("Arial", Font.BOLD, 26));
		this.footer = new JLabel("Δημήτρης Μήλιος - 2021");

		this.infoArea = new JTextArea();
		this.infoArea.setEditable(false);
		this.infoArea.setFont(new Font("Arial", Font.PLAIN, 16));
		this.infoArea.setSize(MainView.WIDTH - 20, MainView.HEIGHT);
		this.infoArea.append(
				"Αυτό το project αναπτύχθηκε στο πλαίσια του μαθήματος Προσχεδιασμένος "
						+ "Αντικειμενοστραφής και Ευέλικτος Προγραμματισμός"
						+ " του προγράμματος σπουδών του τμήματος Μηχανικών Πληροφορικής του " +
						"ΤΕΙ Κρήτης (ΕΛΜΕΠΑ). Για το project υλοποιείται το πολύ γνωστό παιχνίδι" +
						" Sudoku.\n\nΤο Sudoku είναι ένα πλέγμα που αποτελείται από 9x9 πεδία κατανεμημένα " +
						"σε 9 σειρές (τοποθετημένες οριζόντια) και 9 στήλες (τοποθετημένες κάθετα) και " +
						"ομαδοποιημένα σε τετράγωνα με διαστάσεις 3x3.\nΚάθε σειρά, στήλη και " +
						"τετράγωνο (9 πεδία το καθένα) πρέπει να συμπληρωθεί με τα ψηφία " +
						"από το 1 ως το 9, χωρίς τα ψηφία να επαναλαμβάνονται σε κάθε σειρά, στήλη ή τετράγωνο.\n\n" +
						"Στη συγκεκριμένη υλοποίηση υποστηρίζεται η δυνατότητα επιλογής επιπέδου δυσκολίας" +
						" μεταξύ τριών επιλογών (εύκολο, κανονικό, δύσκολο). Επίσης, ο χρήστης πρέπει" +
						" να δώσει ένα username για να ξεκινήσει να παίζει. Οι πληροφορίες του παιχνιδιού" +
						" καταγράφονται σε μια βάση δεδομένων και οι διάφορες ενέργειες του παιχνιδιού είναι" +
						" ορατές στη σελίδα του Ιστορικού του παιχνιδιού.");
		this.infoArea.setLineWrap(true);
		this.infoArea.setWrapStyleWord(true);

		this.add(header, BorderLayout.NORTH);
		this.add(infoArea, BorderLayout.SOUTH);
		this.add(footer, BorderLayout.SOUTH);
	}
}
