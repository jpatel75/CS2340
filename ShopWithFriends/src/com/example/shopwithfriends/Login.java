package com.example.shopwithfriends;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Activity {

	private Button btLogin, btCancel;
	private EditText etUsername, etPassword;
	private TextView tvInvalidLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		btLogin = (Button) findViewById(R.id.LOGIN_BUTTON_LOGIN);
		btCancel = (Button) findViewById(R.id.LOGIN_BUTTON_CANCEL);
		etUsername = (EditText) findViewById(R.id.LOGIN_EDITTEXT_USERNAME);
		etPassword = (EditText) findViewById(R.id.LOGIN_EDITTEXT_PASSWORD);
		tvInvalidLogin = (TextView) findViewById(R.id.LOGIN_TEXTVIEW_INVALIDLOGIN);
		btLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new LoginTask(etUsername.getText().toString(), etPassword.getText().toString())
						.execute(Login.this.getApplicationContext());
			}

		});
		btCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setResult(RESULT_CANCELED);
				finish();
			}

		});
	}

}
