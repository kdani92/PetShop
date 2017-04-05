package hu.bme.xj4vjg.petshop;

import android.app.Application;

import hu.bme.xj4vjg.petshop.ui.UIModule;

public class PetShopApplication extends Application {
	public static PetShopComponent injector;

	@Override
	public void onCreate() {
		super.onCreate();

		injector =
				DaggerPetShopComponent.builder().
						uIModule(
								new UIModule(this)
						).build();
	}
}