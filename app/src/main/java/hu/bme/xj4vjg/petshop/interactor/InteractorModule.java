package hu.bme.xj4vjg.petshop.interactor;

import dagger.Module;
import hu.bme.xj4vjg.petshop.interactor.pet.repository.PetRepositoryInteractor;

@Module
public class InteractorModule {
	public PetRepositoryInteractor providePetRespositoryInteractor() {
		return new PetRepositoryInteractor();
	}
}
