package hu.bme.xj4vjg.petshop.ui.addpet;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hu.bme.xj4vjg.petshop.interactor.pet.network.PetNetworkInteractor;
import hu.bme.xj4vjg.petshop.interactor.pet.network.event.AddPetNetworkEvent;
import hu.bme.xj4vjg.petshop.interactor.pet.repository.PetRepositoryInteractor;
import hu.bme.xj4vjg.petshop.interactor.pet.repository.event.SavePetRepositoryEvent;
import hu.bme.xj4vjg.petshop.model.Pet;
import hu.bme.xj4vjg.petshop.ui.Presenter;
import hu.bme.xj4vjg.petshop.util.di.Network;
import hu.bme.xj4vjg.petshop.util.di.Repository;

import static hu.bme.xj4vjg.petshop.PetShopApplication.injector;

public class AddPetPresenter extends Presenter<AddPetScreen> {
	@Inject
	@Repository
	Executor repositoryExecutor;
	@Inject
	@Network
	Executor networkExecutor;

	@Inject
	PetRepositoryInteractor petRepositoryInteractor;
	@Inject
	PetNetworkInteractor petNetworkInteractor;

	@Inject
	EventBus bus;

	private Pet newPet;

	public AddPetPresenter() {
		injector.inject(this);
	}

	@Override
	public void attachScreen(AddPetScreen screen) {
		super.attachScreen(screen);
		bus.register(this);
	}

	@Override
	public void detachScreen() {
		bus.unregister(this);
		super.detachScreen();
	}

	public void addPet(final String species, final String color, final long timeOfBirth, final int price, final String imageUrl) {
		networkExecutor.execute(new Runnable() {
			@Override
			public void run() {
				newPet = new Pet(species, color, timeOfBirth, price, imageUrl);
				petNetworkInteractor.addPet(species, color, timeOfBirth, price, imageUrl);
			}
		});
	}

	public void onEventMainThread(final AddPetNetworkEvent event) {
		if (event.getThrowable() != null) {
			event.getThrowable().printStackTrace();
			if (screen != null) {
				screen.showUnknownNetworkErrorMessage();
			}
		} else if (event.getCode() == 401) {
			if (screen != null) {
				screen.showUnknownUserMessage();
			}
		} else if (event.getCode() == 410) {
			if (screen != null) {
				screen.showWrongPetDetailMessage();
			}
		} else if (event.getCode() == 420) {
			if (screen != null) {
				screen.showNotAdminUserMessage();
			}
		} else if (event.getCode() != 200) {
			if (screen != null) {
				screen.showUnknownNetworkErrorMessage();
			}
		} else {
			newPet.setPetId(event.getContent());
			repositoryExecutor.execute(new Runnable() {
				@Override
				public void run() {
					petRepositoryInteractor.savePet(newPet);
				}
			});
			if (screen != null) {
				screen.showPetSavedSuccessfullyMessage();
				screen.navigateToPetList();
			}
		}
	}

	public void onEventMainThread(SavePetRepositoryEvent event) {
		if (event.getThrowable() != null) {
			event.getThrowable().printStackTrace();
		}
	}
}