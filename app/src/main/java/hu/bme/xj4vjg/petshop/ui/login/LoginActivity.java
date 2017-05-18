package hu.bme.xj4vjg.petshop.ui.login;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.bme.xj4vjg.petshop.R;
import hu.bme.xj4vjg.petshop.ui.BaseActivity;
import hu.bme.xj4vjg.petshop.ui.Navigator;

import static hu.bme.xj4vjg.petshop.PetShopApplication.injector;

public class LoginActivity extends BaseActivity implements LoginScreen {
	public static final String TAG = "LoginActivity";

	@Inject
	LoginPresenter loginPresenter;

	@Bind(R.id.toolbar)
	Toolbar toolbar;
	@Bind(R.id.name_edit_text)
	TextInputEditText nameEditText;
	@Bind(R.id.password_edit_text)
	TextInputEditText passwordEditText;

	private Tracker tracker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		injector.inject(this);
		tracker = getTracker();
		ButterKnife.bind(this);

		setToolbar(toolbar, R.string.activity_login_title);
	}

	@Override
	protected void onStart() {
		super.onStart();
		loginPresenter.attachScreen(this);

		tracker.setScreenName(TAG);
		tracker.send(new HitBuilders.ScreenViewBuilder().build());
	}

	@Override
	protected void onStop() {
		loginPresenter.detachScreen();
		super.onStop();
	}

	@Override
	public void showEmptyFieldsMessage() {
		showMessage(R.string.activity_login_empty_fields_error);
	}

	@Override
	public void showWrongCredentialsMessage() {
		tracker.send(new HitBuilders.EventBuilder()
				.setCategory("Action")
				.setAction("Wrong credentials during authentication")
				.build());
		showMessage(R.string.activity_login_wrong_credentials_error);
	}

	@Override
	public void showUsernameExistsMessage() {
		tracker.send(new HitBuilders.EventBuilder()
				.setCategory("Action")
				.setAction("Existing username during registration")
				.build());
		showMessage(R.string.activity_login_username_exists_error);
	}

	@Override
	public void showNetworkErrorMessage() {
		showMessage(R.string.network_error);
	}

	@Override
	public void naviageToPetList() {
		tracker.send(new HitBuilders.EventBuilder()
				.setCategory("Action")
				.setAction("Successful authentication")
				.build());
		Navigator.navigateToMain(this);
	}

	@OnClick(R.id.button_register)
	public void onRegisterButtonClick() {
		loginPresenter.register(nameEditText.getText().toString(), passwordEditText.getText().toString());
	}

	@OnClick(R.id.button_login)
	public void onLoginButtonClick() {
		loginPresenter.login(nameEditText.getText().toString(), passwordEditText.getText().toString());
	}
}