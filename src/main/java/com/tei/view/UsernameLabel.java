package com.tei.view;

import com.tei.model.UserModel;
import com.tei.observer.Observer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UsernameLabel extends JLabel implements Observer {

	private final UserModel userModel;

	public UsernameLabel(UserModel userModel) {
		this.userModel = userModel;
		this.setFont(new Font("Arial", Font.BOLD, 26));
		userModel.subscribe(this);
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
	}

	@Override
	public void updateWith(Object state) {
		String username = (String) state;
		this.setText("User: " + username);
	}
}
