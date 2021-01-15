package view;

import model.UserModel;
import observer.Observer;

import javax.swing.*;
import java.awt.*;

public class UsernameLabel extends JLabel implements Observer {

	public UsernameLabel() {
		this.setFont(new Font("Arial", Font.BOLD, 26));
		UserModel.getInstance().subscribe(this);
	}

	@Override
	public void update(Object state) {
		String username = (String) state;
		this.setText(username);
	}
}
