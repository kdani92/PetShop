package hu.bme.xj4vjg.petshop.mock.interceptors;

import android.net.Uri;

import java.util.HashMap;
import java.util.Map;

import hu.bme.xj4vjg.petshop.network.NetworkConfig;
import hu.bme.xj4vjg.petshop.network.auth.model.UserDetail;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

import static hu.bme.xj4vjg.petshop.mock.interceptors.MockHelper.ERROR_RESPONSE_CODE;
import static hu.bme.xj4vjg.petshop.mock.interceptors.MockHelper.getBody;
import static hu.bme.xj4vjg.petshop.mock.interceptors.MockHelper.makeResponse;


public class AuthMock {
	public static Map<String, String> users = new HashMap<>();

	static {
		users.put("user", "pass");
		users.put("u", "p");
		users.put("dani", "p");
		users.put("d", "p");
		users.put("admin", "p");
		users.put("a", "p");
	}

	public static Response process(Request request) {
		Uri uri = Uri.parse(request.url().toString());

		String responseString = "";
		int responseCode = ERROR_RESPONSE_CODE;
		Headers headers = request.headers();

		if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "auth/register") && request.method().equals("POST")) {
			UserDetail userDetail = getBody(request, UserDetail.class);
			if (userDetail != null) {
				if (users.containsKey(userDetail.getUsername())) {
					responseCode = 410;
				} else {
					users.put(userDetail.getUsername(), userDetail.getPassword());
					responseCode = 200;
				}
			}
		} else if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "auth/login") && request.method().equals("POST")) {
			UserDetail userDetail = getBody(request, UserDetail.class);
			if (userDetail != null) {
				if (!users.containsKey(userDetail.getUsername()) || !users.get(userDetail.getUsername()).equals(userDetail.getPassword())) {
					responseCode = 410;
				} else {
					responseCode = 200;
				}
			}
		}

		return makeResponse(request, headers, responseCode, responseString);
	}
}