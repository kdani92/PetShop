package hu.bme.xj4vjg.petshop;

import javax.inject.Singleton;

import dagger.Component;
import hu.bme.xj4vjg.petshop.interactor.InteractorModule;
import hu.bme.xj4vjg.petshop.interactor.auth.AuthInteractor;
import hu.bme.xj4vjg.petshop.interactor.pet.network.PetNetworkInteractor;
import hu.bme.xj4vjg.petshop.interactor.pet.repository.PetRepositoryInteractor;
import hu.bme.xj4vjg.petshop.mock.MockAuthInteractor;
import hu.bme.xj4vjg.petshop.mock.MockModule;
import hu.bme.xj4vjg.petshop.mock.MockPetNetworkInteractor;
import hu.bme.xj4vjg.petshop.repository.RepositoryModule;
import hu.bme.xj4vjg.petshop.ui.UIModule;
import hu.bme.xj4vjg.petshop.ui.addpet.AddPetActivity;
import hu.bme.xj4vjg.petshop.ui.addpet.AddPetPresenter;
import hu.bme.xj4vjg.petshop.ui.login.LoginActivity;
import hu.bme.xj4vjg.petshop.ui.login.LoginPresenter;
import hu.bme.xj4vjg.petshop.ui.petdetail.PetDetailActivity;
import hu.bme.xj4vjg.petshop.ui.petdetail.PetDetailPresenter;
import hu.bme.xj4vjg.petshop.ui.petlist.PetListActivity;

@Singleton
@Component(modules = {RepositoryModule.class, InteractorModule.class, UIModule.class, MockModule.class})
public interface PetShopComponent {
	void inject(PetShopApplication petShopApplication);

	void inject(AuthInteractor authInteractor);

	void inject(PetRepositoryInteractor petRepositoryInteractor);

	void inject(PetNetworkInteractor petNetworkInteractor);

	void inject(MockAuthInteractor mockAuthInteractor);

	void inject(MockPetNetworkInteractor mockPetNetworkInteractor);

	void inject(LoginActivity loginActivity);

	void inject(LoginPresenter loginPresenter);

	void inject(PetListActivity petListActivity);

	void inject(PetDetailActivity petDetailActivity);

	void inject(PetDetailPresenter petDetailPresenter);

	void inject(AddPetActivity addPetActivity);

	void inject(AddPetPresenter addPetPresenter);
}