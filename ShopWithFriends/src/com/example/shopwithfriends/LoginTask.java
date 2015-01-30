package com.example.shopwithfriends;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.HttpStatus;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public class LoginTask extends AsyncTask<Context, Void, Boolean> {

	private String username, password;
	private Context mContext;

	public LoginTask(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	protected Boolean doInBackground(Context... params) {
		mContext = params[0];
		HttpURLConnection conn = null;
		URL url = null;
		int response = 400;
		String query = String.format("username=%s&password=%s", username, password);
		System.out.println(query);
		try {
			url = new URL("http://ythogh.com/shopwf/verify_login.php");
			String agent = "Applet";
			String type = "application/x-www-form-urlencoded";
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("User-Agent", agent);
			conn.setRequestProperty("Content-Type", type);
			conn.setRequestProperty("Content-Length", "" + query.length());
			OutputStream out = conn.getOutputStream();
			out.write(query.getBytes());
			response = conn.getResponseCode();
			conn.disconnect();
			out.close();
			if (response == HttpStatus.SC_ACCEPTED) {
				return true;
			} else if (response == HttpStatus.SC_EXPECTATION_FAILED) {
				return false;
			}
			return false;

		} catch (Exception e) {
			conn.disconnect();
			Log.e("Login", "Exception when logging in: " + response);
			e.printStackTrace();
			return false;
		}
	}

	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		if (result) {
			Login.onLoginSuccess();
			Intent intent = new Intent(mContext, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			mContext.startActivity(intent);
		} else {
			Login.onLoginFail();
			System.out.println("Problem");
		}
	}

}
