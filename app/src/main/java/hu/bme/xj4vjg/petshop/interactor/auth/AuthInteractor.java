package hu.bme.xj4vjg.petshop.interactor.auth;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

import static hu.bme.xj4vjg.petshop.PetShopApplication.injector;

public class AuthInteractor {
	@Inject
	EventBus bus;

	public AuthInteractor() {
		injector.inject(this);
	}

	public void register(String username, String password) {
		// TODO : Call Auth API for register
	}

	public void login(String username, String password) {
		// TODO : Call Auth API for login
	}
}
