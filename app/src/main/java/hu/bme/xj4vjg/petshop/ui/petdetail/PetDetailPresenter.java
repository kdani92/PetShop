package hu.bme.xj4vjg.petshop.ui.petdetail;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hu.bme.xj4vjg.petshop.interactor.pet.network.PetNetworkInteractor;
import hu.bme.xj4vjg.petshop.interactor.pet.network.event.GetPetDetailNetworkEvent;
import hu.bme.xj4vjg.petshop.interactor.pet.repository.PetRepositoryInteractor;
import hu.bme.xj4vjg.petshop.interactor.pet.repository.event.GetPetRepositoryEvent;
import hu.bme.xj4vjg.petshop.interactor.pet.repository.event.SavePetRepositoryEvent;
import hu.bme.xj4vjg.petshop.model.Pet;
import hu.bme.xj4vjg.petshop.ui.Presenter;
import hu.bme.xj4vjg.petshop.util.di.Network;
import hu.bme.xj4vjg.petshop.util.di.Repository;

import static hu.bme.xj4vjg.petshop.PetShopApplication.injector;

public class PetDetailPresenter extends Presenter<PetDetailScreen> {
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

	private String petId;
	private Pet pet;

	public PetDetailPresenter() {
		injector.inject(this);
	}

	@Override
	public void attachScreen(PetDetailScreen screen) {
		super.attachScreen(screen);
		bus.register(this);
	}

	@Override
	public void detachScreen() {
		bus.unregister(this);
		super.detachScreen();
	}

	public void setPetId(String petId) {
		this.petId = petId;
	}

	public void updatePetDetail() {
		if (petId != null) {
			networkExecutor.execute(new Runnable() {
				@Override
				public void run() {
					petNetworkInteractor.getPetDetail(petId);
				}
			});
		} else {
			if (screen != null) {
				screen.showUnknownPetMessage();
			}
		}
	}

	public void onEventMainThread(GetPetDetailNetworkEvent event) {
		if (event.getThrowable() != null) {
			event.getThrowable().printStackTrace();
			repositoryExecutor.execute(new Runnable() {
				@Override
				public void run() {
					petRepositoryInteractor.getPet(petId);
				}
			});
		} else if (event.getCode() == 401) {
			if (screen != null) {
				screen.showUnknownUserMessage();
			}
		} else if (event.getCode() == 410) {
			if (screen != null) {
				screen.showUnknownPetMessage();
			}
		} else if (event.getCode() != 200) {
			if (screen != null) {
				screen.showUnknownServerErrorMessage();
			}
		} else {
			pet = event.getContent();
			repositoryExecutor.execute(new Runnable() {
				@Override
				public void run() {
					petRepositoryInteractor.savePet(pet);
				}
			});
			if (screen != null) {
				screen.refreshPetDetail();
			}
		}
	}

	public void onEventMainThread(GetPetRepositoryEvent event) {
		if (event.getThrowable() != null) {
			event.getThrowable().printStackTrace();
			if (screen != null) {
				screen.showOfflineRepositoryErrorMessage();
			}
		} else if (event.getContent() == null) {
			if (screen != null) {
				screen.showOfflineUnknownPetMessage();
			}
		} else {
			pet = event.getContent();
			if (screen != null) {
				screen.showOfflinePetFoundMessage();
				screen.refreshPetDetail();
			}
		}
	}

	public void onEventMainThread(SavePetRepositoryEvent event) {
		if (event.getThrowable() != null) {
			event.getThrowable().printStackTrace();
		}
	}

	public Pet getPet() {
		return pet;
	}
}