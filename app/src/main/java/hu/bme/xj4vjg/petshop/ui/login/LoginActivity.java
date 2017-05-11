package hu.bme.xj4vjg.petshop.ui.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import hu.bme.xj4vjg.petshop.R;

import static hu.bme.xj4vjg.petshop.PetShopApplication.injector;

public class LoginActivity extends AppCompatActivity implements LoginScreen {
	@Inject
	LoginPresenter loginPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		injector.inject(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		loginPresenter.attachScreen(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		loginPresenter.detachScreen();
	}


	@Override
	public void showWrongCredentialsMessage() {
		// TODO
	}

	@Override
	public void showUsernameExistsMessage() {
		// TODO
	}

	@Override
	public void showNetworkErrorMessage() {
		// TODO
	}

	@Override
	public void naviageToPetList() {
		// TODO
	}
}