package hu.bme.xj4vjg.petshop.interactor;

import dagger.Module;
import dagger.Provides;
import hu.bme.xj4vjg.petshop.interactor.auth.AuthInteractor;
import hu.bme.xj4vjg.petshop.interactor.pet.network.PetNetworkInteractor;
import hu.bme.xj4vjg.petshop.interactor.pet.repository.PetRepositoryInteractor;

@Module
public class InteractorModule {
	@Provides
	public AuthInteractor provideAuthInteractor() {
		return new AuthInteractor();
	}

	@Provides
	public PetRepositoryInteractor providePetRespositoryInteractor() {
		return new PetRepositoryInteractor();
	}

	@Provides
	public PetNetworkInteractor providePetNetworkInteractor() {
		return new PetNetworkInteractor();
	}
}
