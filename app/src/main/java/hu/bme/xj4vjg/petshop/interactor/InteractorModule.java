package hu.bme.xj4vjg.petshop.interactor;

import dagger.Module;
import dagger.Provides;
import hu.bme.xj4vjg.petshop.interactor.pet.repository.PetRepositoryInteractor;

@Module
public class InteractorModule {
	@Provides
	public PetRepositoryInteractor providePetRespositoryInteractor() {
		return new PetRepositoryInteractor();
	}
}
