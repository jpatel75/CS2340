package com.example.shopwithfriends;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main extends Activity implements OnClickListener {

	private Button btLogin, btRegister;
	private EditText etUsername, etPassword;
	private TextView tvInvalidLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btLogin = (Button) findViewById(R.id.LOGIN_BUTTON_LOGIN);
		btRegister = (Button) findViewById(R.id.LOGIN_BUTTON_REGISTER);
		etUsername = (EditText) findViewById(R.id.LOGIN_EDITTEXT_USERNAME);
		etPassword = (EditText) findViewById(R.id.LOGIN_EDITTEXT_PASSWORD);
		tvInvalidLogin = (TextView) findViewById(R.id.LOGIN_TEXTVIEW_INVALIDLOGIN);
		btLogin.setOnClickListener(this);
		btRegister.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int thisId = v.getId();
		if (thisId == R.id.LOGIN_BUTTON_LOGIN) {
			new LoginTask(etUsername.getText().toString(), etPassword.getText().toString())
					.execute();
		} else if (thisId == R.id.LOGIN_BUTTON_REGISTER) {

			startActivity(new Intent(getApplicationContext(), Register.class));
		}
	}

	public void returnFromTask(boolean success) {
		if (success) {
			startActivity(new Intent(getApplicationContext(), Main.class));
		} else {
			tvInvalidLogin.setVisibility(View.VISIBLE);
		}
	}
}
