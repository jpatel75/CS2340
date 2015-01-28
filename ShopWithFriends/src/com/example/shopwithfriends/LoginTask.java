package com.example.shopwithfriends;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.HttpStatus;
import android.os.AsyncTask;
import android.util.Log;

public class LoginTask extends AsyncTask<String, Void, Boolean> {

	private String username, password;

	public LoginTask(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	protected Boolean doInBackground(String... params) {
		HttpURLConnection conn = null;
		URL url = null;
		int response = 400;
		String query = String.format("username=%s&password=%s", username, password);
		try {
			url = new URL("http://ythogh.com/shopwf/scripts/add_user.php");
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
			// String inputLine = "";
			// BufferedReader in = new BufferedReader(new
			// InputStreamReader(conn.getInputStream()));
			response = conn.getResponseCode();
			conn.disconnect();
			out.close();
			return response == HttpStatus.SC_ACCEPTED;
		} catch (Exception e) {
			conn.disconnect();
			Log.e("Login", "Exception when logging in: " + response);
			return false;
		}
	}

	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		new Main().returnFromTask(result);
	}

}
