package hu.bme.xj4vjg.petshop.mock;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hu.bme.xj4vjg.petshop.interactor.auth.event.LoginEvent;
import hu.bme.xj4vjg.petshop.interactor.auth.event.RegisterEvent;

import static hu.bme.xj4vjg.petshop.PetShopApplication.injector;

public class MockAuthInteractor {
	@Inject
	EventBus bus;

	private static Map<String, String> users;

	static {
		users = new HashMap<>();
		users.put("used1", "pass");
		users.put("used2", "pass");
		users.put("user", "pass");
		users.put("admin", "pass");
		users.put("a", "p");
		users.put("u", "p");
		users.put("name", "pass");
	}

	public MockAuthInteractor() {
		injector.inject(this);
	}

	public void register(String username, String password) {
		RegisterEvent registerEvent = new RegisterEvent();
		if (users.containsKey(username)) {
			registerEvent.setCode(410);
		} else {
			users.put(username, password);
			registerEvent.setCode(200);
		}
		bus.post(registerEvent);
	}

	public void login(String username, String password) {
		LoginEvent loginEvent = new LoginEvent();
		if (!users.containsKey(username) || !users.get(username).equals(password)) {
			loginEvent.setCode(410);
		} else {
			loginEvent.setCode(200);
		}
		bus.post(loginEvent);
	}

	public static boolean isUserAdmin(String name) {
		if (name.equals("admin") || name.equals("a")) {
			return true;
		}
		return false;
	}
}
