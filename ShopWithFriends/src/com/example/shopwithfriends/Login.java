package com.example.shopwithfriends;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class Login extends Activity {

	private Button btLogin, btCancel;
	private EditText etUsername, etPassword;
	private static TextView tvInvalidLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		btLogin = (Button) findViewById(R.id.LOGIN_BUTTON_LOGIN);
		btCancel = (Button) findViewById(R.id.LOGIN_BUTTON_CANCEL);
		etUsername = (EditText) findViewById(R.id.LOGIN_EDITTEXT_USERNAME);
		etPassword = (EditText) findViewById(R.id.LOGIN_EDITTEXT_PASSWORD);
		etUsername.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				InputMethodManager imm = (InputMethodManager) getBaseContext().getSystemService(
						Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
				return true;
			}

		});
		etPassword.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				InputMethodManager imm = (InputMethodManager) getBaseContext().getSystemService(
						Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
				return true;
			}

		});
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

	public static void onLoginFail() {
		tvInvalidLogin.setVisibility(View.VISIBLE);
	}

	public static void onLoginSuccess() {
		tvInvalidLogin.setVisibility(View.GONE);
	}

}
