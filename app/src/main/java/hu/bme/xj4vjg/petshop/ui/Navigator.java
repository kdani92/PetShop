package hu.bme.xj4vjg.petshop.ui;

import android.app.Activity;
import android.content.Intent;

import hu.bme.xj4vjg.petshop.ui.login.LoginActivity;
import hu.bme.xj4vjg.petshop.ui.main.MainActivity;

public class Navigator {
	public static void navigateToMain(Activity activity) {
		Intent intent = new Intent(activity, MainActivity.class);
		activity.startActivity(intent);
	}

	public static void navigateToLogin(Activity activity) {
		Intent intent = new Intent(activity, LoginActivity.class);
		activity.startActivity(intent);
	}
}
