package com.example.shopwithfriends;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Welcome extends Activity {

	private final static int LOGIN_CODE = 100;
	private Button btLogin, btRegister;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		btLogin = (Button) findViewById(R.id.WELCOME_BUTTON_LOGIN);
		btRegister = (Button) findViewById(R.id.WELCOME_BUTTON_REGISTER);
		btLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), Login.class);
				startActivityForResult(intent, LOGIN_CODE);
			}
		});
		btRegister.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Can't do that yet buddy!",
						Toast.LENGTH_SHORT).show();
			}

		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (resultCode) {
			case RESULT_OK:
			break;
			case RESULT_CANCELED:
				Toast.makeText(getApplicationContext(), "Login cancelled", Toast.LENGTH_SHORT)
				.show();
			break;
			default:
				break;
		}
	}
}
