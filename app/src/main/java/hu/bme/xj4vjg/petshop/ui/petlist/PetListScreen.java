package hu.bme.xj4vjg.petshop.ui.petlist;

public interface PetListScreen {
	void showUnknownUserMessage();

	void showUnknownServerErrorMessage();

	void showOfflineRepositoryErrorMessage();

	void showOfflinePetsFoundMessage();

	void refreshPets();
}
