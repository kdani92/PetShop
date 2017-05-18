package hu.bme.xj4vjg.petshop.interactor.auth;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hu.bme.xj4vjg.petshop.interactor.auth.event.LoginEvent;
import hu.bme.xj4vjg.petshop.interactor.auth.event.RegisterEvent;
import hu.bme.xj4vjg.petshop.network.auth.AuthApi;
import hu.bme.xj4vjg.petshop.network.auth.model.UserDetail;
import retrofit2.Call;
import retrofit2.Response;

import static hu.bme.xj4vjg.petshop.PetShopApplication.injector;

public class AuthInteractor {
	@Inject
	AuthApi authApi;

	@Inject
	EventBus bus;

	public AuthInteractor() {
		injector.inject(this);
	}

	public void register(String username, String password) {
		RegisterEvent event = new RegisterEvent();
		Call call = authApi.register(new UserDetail(username, password));
		Response response;

		try {
			response = call.execute();

			event.setCode(response.code());
			bus.post(event);
		} catch (Exception e) {
			event.setThrowable(e);
			bus.post(event);
		}
	}

	public void login(String username, String password) {
		LoginEvent event = new LoginEvent();
		Call call = authApi.login(new UserDetail(username, password));
		Response response;

		try {
			response = call.execute();

			event.setCode(response.code());
			bus.post(event);
		} catch (Exception e) {
			event.setThrowable(e);
			bus.post(event);
		}
	}
}
