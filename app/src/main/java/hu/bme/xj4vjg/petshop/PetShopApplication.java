package hu.bme.xj4vjg.petshop;

import android.app.Application;

import javax.inject.Inject;

import hu.bme.xj4vjg.petshop.repository.Repository;
import hu.bme.xj4vjg.petshop.ui.UIModule;

public class PetShopApplication extends Application {
	public static PetShopComponent injector;

	@Inject
	Repository repository;

	@Override
	public void onCreate() {
		super.onCreate();

		injector =
				DaggerPetShopComponent.builder().
						uIModule(
								new UIModule(this)
						).build();
		injector.inject(this);

		repository.open(this);
	}
}