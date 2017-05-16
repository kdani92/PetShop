package hu.bme.xj4vjg.petshop.ui.petlist;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hu.bme.xj4vjg.petshop.interactor.pet.network.PetNetworkInteractor;
import hu.bme.xj4vjg.petshop.interactor.pet.network.event.GetPetsNetworkEvent;
import hu.bme.xj4vjg.petshop.interactor.pet.network.event.GetSpeciesNetworkEvent;
import hu.bme.xj4vjg.petshop.interactor.pet.repository.PetRepositoryInteractor;
import hu.bme.xj4vjg.petshop.interactor.pet.repository.event.GetPetsRepositoryEvent;
import hu.bme.xj4vjg.petshop.interactor.pet.repository.event.GetSpeciesRepositoryEvent;
import hu.bme.xj4vjg.petshop.interactor.pet.repository.event.UpdatePetsRepositoryEvent;
import hu.bme.xj4vjg.petshop.interactor.pet.repository.event.UpdateSpeciesRepositoryEvent;
import hu.bme.xj4vjg.petshop.model.Pet;
import hu.bme.xj4vjg.petshop.model.Species;
import hu.bme.xj4vjg.petshop.ui.Presenter;
import hu.bme.xj4vjg.petshop.util.di.Network;
import hu.bme.xj4vjg.petshop.util.di.Repository;

import static hu.bme.xj4vjg.petshop.PetShopApplication.injector;

public class PetListPresenter extends Presenter<PetListScreen> {
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

	private List<Species> speciesList = new ArrayList<>();
	private List<Pet> petList = new ArrayList<>();
	private List<Species> activeSpeciesFilters = new ArrayList<>();
	private List<Pet> filteredPetList = new ArrayList<>();

	public PetListPresenter() {
		injector.inject(this);
	}

	@Override
	public void attachScreen(PetListScreen screen) {
		super.attachScreen(screen);
		bus.register(this);
	}

	@Override
	public void detachScreen() {
		bus.unregister(this);
		super.detachScreen();
	}

	public void updateSpecies() {
		networkExecutor.execute(new Runnable() {
			@Override
			public void run() {
				petNetworkInteractor.getSpecies();
			}
		});
	}

	public void onEventMainThread(final GetSpeciesNetworkEvent event) {
		if (event.getThrowable() != null) {
			event.getThrowable().printStackTrace();
			repositoryExecutor.execute(new Runnable() {
				@Override
				public void run() {
					petRepositoryInteractor.getSpecies();
				}
			});
		} else if (event.getCode() == 401) {
			if (screen != null) {
				screen.showUnknownUserMessage();
			}
		} else if (event.getCode() != 200) {
			if (screen != null) {
				screen.showUnknownServerErrorMessage();
			}
		} else {
			speciesList = event.getContent();
			repositoryExecutor.execute(new Runnable() {
				@Override
				public void run() {
					petRepositoryInteractor.updateSpecies(speciesList);
				}
			});
		}
	}

	public void onEventMainThread(GetSpeciesRepositoryEvent event) {
		if (event.getThrowable() != null) {
			event.getThrowable().printStackTrace();
			if (screen != null) {
				screen.showOfflineRepositoryErrorMessage();
			}
		} else if (event.getContent() == null) {
			if (screen != null) {
				screen.showOfflineRepositoryErrorMessage();
			}
		} else {
			speciesList = event.getContent();
		}
	}

	public void onEventMainThread(UpdateSpeciesRepositoryEvent event) {
		if (event.getThrowable() != null) {
			event.getThrowable().printStackTrace();
		}
	}

	public void updatePets() {
		networkExecutor.execute(new Runnable() {
			@Override
			public void run() {
				petNetworkInteractor.getPets();
			}
		});
	}

	public void onEventMainThread(GetPetsNetworkEvent event) {
		if (event.getThrowable() != null) {
			event.getThrowable().printStackTrace();
			repositoryExecutor.execute(new Runnable() {
				@Override
				public void run() {
					petRepositoryInteractor.getPets();
				}
			});
		} else if (event.getCode() == 401) {
			if (screen != null) {
				screen.showUnknownUserMessage();
			}
		} else if (event.getCode() != 200) {
			if (screen != null) {
				screen.showUnknownServerErrorMessage();
			}
		} else {
			petList = event.getContent();
			repositoryExecutor.execute(new Runnable() {
				@Override
				public void run() {
					petRepositoryInteractor.updatePets(petList);
				}
			});
			applySpeciesFilters(activeSpeciesFilters);
		}
	}

	public void onEventMainThread(GetPetsRepositoryEvent event) {
		if (event.getThrowable() != null) {
			event.getThrowable().printStackTrace();
			if (screen != null) {
				screen.showOfflineRepositoryErrorMessage();
			}
		} else if (event.getContent() == null) {
			if (screen != null) {
				screen.showOfflineRepositoryErrorMessage();
			}
		} else {
			petList = event.getContent();
			applySpeciesFilters(activeSpeciesFilters);
			if (screen != null) {
				screen.showOfflinePetsFoundMessage();
			}
		}
	}

	public void onEventMainThread(UpdatePetsRepositoryEvent event) {
		if (event.getThrowable() != null) {
			event.getThrowable().printStackTrace();
		}
	}

	public void applySpeciesFilters(List<Species> speciesFilters) {
		activeSpeciesFilters.clear();
		filteredPetList.clear();

		activeSpeciesFilters.addAll(speciesFilters);
		if (activeSpeciesFilters.size() > 0) {
			for (Pet pet : petList) {
				for (Species species : activeSpeciesFilters) {
					if (pet.getSpecies().equals(species.getName())) {
						filteredPetList.add(pet);
						break;
					}
				}
			}
		} else {
			filteredPetList.addAll(petList);
		}

		if (screen != null) {
			screen.refreshPets();
		}
	}

	public List<Species> getSpeciesList() {
		return speciesList;
	}

	public List<Pet> getPetList() {
		return petList;
	}

	public List<Species> getActiveSpeciesFilters() {
		return activeSpeciesFilters;
	}

	public List<Pet> getFilteredPetList() {
		return filteredPetList;
	}
}