package hu.bme.xj4vjg.petshop.ui.login;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hu.bme.xj4vjg.petshop.interactor.auth.event.LoginEvent;
import hu.bme.xj4vjg.petshop.interactor.auth.event.RegisterEvent;
import hu.bme.xj4vjg.petshop.mock.MockAuthInteractor;
import hu.bme.xj4vjg.petshop.model.Settings;
import hu.bme.xj4vjg.petshop.ui.Presenter;
import hu.bme.xj4vjg.petshop.util.di.Network;

import static hu.bme.xj4vjg.petshop.PetShopApplication.injector;

public class LoginPresenter extends Presenter<LoginScreen> {
	@Inject
	@Network
	Executor executor;

	@Inject
	MockAuthInteractor authInteractor;

	@Inject
	Settings settings;

	@Inject
	EventBus bus;

	private String username;

	public LoginPresenter() {
		injector.inject(this);
	}

	@Override
	public void attachScreen(LoginScreen screen) {
		super.attachScreen(screen);
		bus.register(this);
	}

	@Override
	public void detachScreen() {
		bus.unregister(this);
		super.detachScreen();
	}

	public void login(final String name, final String password) {
		this.username = name;
		executor.execute(new Runnable() {
			@Override
			public void run() {
				authInteractor.login(name, password);
			}
		});
	}

	public void onEventMainThread(LoginEvent event) {
		if (event.getThrowable() != null) {
			event.getThrowable().printStackTrace();
			if (screen != null) {
				screen.showNetworkErrorMessage();
			}
		} else if (event.getCode() == 410) {
			if (screen != null) {
				screen.showWrongCredentialsMessage();
			}
		} else if (event.getCode() != 200) {
			if (screen != null) {
				screen.showNetworkErrorMessage();
			}
		} else {
			settings.setUsername(username);
			if (screen != null) {
				screen.naviageToPetList();
			}
		}
	}

	public void register(final String name, final String password) {
		this.username = name;
		executor.execute(new Runnable() {
			@Override
			public void run() {
				authInteractor.register(name, password);
			}
		});
	}

	public void onEventMainThread(RegisterEvent event) {
		if (event.getThrowable() != null) {
			event.getThrowable().printStackTrace();
			if (screen != null) {
				screen.showNetworkErrorMessage();
			}
		} else if (event.getCode() == 410) {
			if (screen != null) {
				screen.showUsernameExistsMessage();
			}
		} else if (event.getCode() != 200) {
			if (screen != null) {
				screen.showNetworkErrorMessage();
			}
		} else {
			settings.setUsername(username);
			if (screen != null) {
				screen.naviageToPetList();
			}
		}
	}
}