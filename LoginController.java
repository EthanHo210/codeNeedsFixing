package JavaFiles;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label loginMessageLabel;

    @FXML
    private void handleLoginAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = UserDAO.authenticate(username, password);
        if (user != null) {
            loginMessageLabel.setText("Login successful");
            // Proceed to the next stage or dashboard based on the user's role
        } else {
            loginMessageLabel.setText("Invalid username or password");
        }
    }
}
