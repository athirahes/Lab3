import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import java.awt.Color;

public class ClientTranslationFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private String text, language;
	int textIndex;
	
	private JLabel lblTranslatedText;

	public ClientTranslationFrame() {
		getContentPane().setBackground(Color.WHITE);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.setTitle("TCP Translation Application: Client Side");
		this.setSize(new Dimension(500, 250));

		// Must close on X
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Center the frame on the screen
		this.setLocationRelativeTo(null);

		// Initialize component
		lblTranslatedText = new JLabel("Translation: -");

		// Load more component
		loadComponent();
	}

	private JPanel SendTextAndLanguage() {

		String textList[] = { "Good morning", "Good night", "How are you?", "Thank you", "Goodbye", "What's up?" };
		String languageList[] = { "Bahasa Malaysia", "Arabic", "Korean" };
		JPanel pnl1 = new JPanel();
		pnl1.setBackground(Color.DARK_GRAY);
		pnl1.setLayout(new GridLayout(3, 1, 5, 5));

		// initialize components in pnl1
		JPanel pnlText = new JPanel();
		JPanel pnlLanguage = new JPanel();

		JComboBox<String> jcText = new JComboBox<String>(textList);
		JComboBox<String> jcLang = new JComboBox<String>(languageList);
		
		JButton btn = new JButton("Translate");
		
		// customize components
		pnlText.setLayout(new FlowLayout());
		pnlText.add(new JLabel("Select Text To Send:"));
		pnlText.add(jcText);

		pnlLanguage.setLayout(new FlowLayout());
		pnlLanguage.add(new JLabel("Select Language:"));
		pnlLanguage.add(jcLang);
		
		// add components to pnl1
		pnl1.add(pnlText);
		pnl1.add(pnlLanguage);
		pnl1.add(btn);

		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				text = String.valueOf(jcText.getSelectedItem());
				textIndex = jcText.getSelectedIndex();
				language = String.valueOf(jcLang.getSelectedItem());
				System.out.println("\n[Frame] Text (" + textIndex + "):" + text + " to " + language);

				try {
					new ClientTranslationApplication().SendText(textIndex, text, language);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		});
		return pnl1;
	}

	public void updateTranslatedText(String inText) {
		this.lblTranslatedText.setText("Translation: " + inText);
	}

	private void loadComponent() {

		// main panel
		this.setLayout(new BorderLayout());
		JPanel pnl1 = this.SendTextAndLanguage();

		lblTranslatedText.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(pnl1, BorderLayout.NORTH);
		this.add(lblTranslatedText, BorderLayout.CENTER);

	}
}
