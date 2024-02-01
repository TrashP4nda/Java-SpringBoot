package WindowBuilder;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.awt.event.ActionEvent;

public class Login extends JFrame {
    private JTextField textFieldUsername;
    private JPasswordField passwordField;

    public Login() {
        setTitle("Login");
        setBounds(100, 100, 288, 229);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(10, 11, 86, 14);
        getContentPane().add(lblUsername);

        textFieldUsername = new JTextField();
        textFieldUsername.setBounds(106, 8, 86, 20);
        getContentPane().add(textFieldUsername);
        textFieldUsername.setColumns(10);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(10, 36, 86, 14);
        getContentPane().add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(106, 33, 86, 20);
        getContentPane().add(passwordField);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {

					String formParams = "username=" + URLEncoder.encode(textFieldUsername.getText(), StandardCharsets.UTF_8) + "&password="
							+ URLEncoder.encode(passwordField.getText(), StandardCharsets.UTF_8);

					HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/login")) // Replace
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
        btnLogin.setBounds(106, 65, 89, 23);
        getContentPane().add(btnLogin);
        
        JButton btnRegister = new JButton("Register");
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	RegistrationForm registrationForm = new RegistrationForm();
                registrationForm.setVisible(true);
            }
        });
        btnRegister.setBounds(96, 99, 109, 23);
        getContentPane().add(btnRegister);
    }

    public static void main(String[] args) {
        try {
            Login frame = new Login();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
