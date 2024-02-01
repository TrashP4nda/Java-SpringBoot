package WindowBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class RegistrationForm extends JFrame {
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	// Add more fields if needed

	public RegistrationForm() {
		setTitle("Registration");
		setBounds(100, 100, 300, 300); // Adjust size as needed
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(10, 10, 80, 25);
		getContentPane().add(lblUsername);

		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(100, 10, 160, 25);
		getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 40, 80, 25);
		getContentPane().add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(100, 40, 160, 25);
		getContentPane().add(passwordField);

		// Add more components for other fields if needed

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {

					String formParams = "username=" + URLEncoder.encode(textFieldUsername.getText(), StandardCharsets.UTF_8) + "&password="
							+ URLEncoder.encode(passwordField.getText(), StandardCharsets.UTF_8);

					HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/register")) // Replace
																														// with
																														// your
																														// URL
							.header("Content-Type", "application/x-www-form-urlencoded")
							.POST(HttpRequest.BodyPublishers.ofString(formParams)).build();

					// Send the request using HttpClient
					HttpClient client = HttpClient.newHttpClient();
					client.sendAsync(request, java.net.http.HttpResponse.BodyHandlers.ofString())
							.thenApply(java.net.http.HttpResponse::body).thenAccept(System.out::println).join();

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		btnSubmit.setBounds(100, 80, 89, 23);
		getContentPane().add(btnSubmit);
	}
}
