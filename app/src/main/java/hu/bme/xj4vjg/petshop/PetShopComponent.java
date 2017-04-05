package hu.bme.xj4vjg.petshop;

import javax.inject.Singleton;

import dagger.Component;
import hu.bme.xj4vjg.petshop.ui.UIModule;
import hu.bme.xj4vjg.petshop.ui.main.MainActivity;

@Singleton
@Component(modules = {UIModule.class})
public interface PetShopComponent {
	void inject(MainActivity mainActivity);

}