package hu.bme.xj4vjg.petshop.ui.petdetail;

public interface PetDetailScreen {
	void showUnknownUserMessage();

	void showUnknownPetMessage();

	void showUnknownServerErrorMessage();

	void showOfflineRepositoryErrorMessage();

	void showOfflineUnknownPetMessage();

	void showOfflinePetFoundMessage();

	void refreshPetDetail();
}
