package hu.bme.xj4vjg.petshop;

import javax.inject.Singleton;

import dagger.Component;
import hu.bme.xj4vjg.petshop.ui.UIModule;
import hu.bme.xj4vjg.petshop.ui.addpet.AddPetActivity;
import hu.bme.xj4vjg.petshop.ui.login.LoginActivity;
import hu.bme.xj4vjg.petshop.ui.petdetail.PetDetailActivity;
import hu.bme.xj4vjg.petshop.ui.petlist.PetListActivity;

@Singleton
@Component(modules = {UIModule.class})
public interface PetShopComponent {
	void inject(LoginActivity loginActivity);

	void inject(PetListActivity petListActivity);

	void inject(PetDetailActivity petDetailActivity);

	void inject(AddPetActivity addPetActivity);
}