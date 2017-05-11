package hu.bme.xj4vjg.petshop.ui.addpet;

public interface AddPetScreen {
	void showUnknownNetworkErrorMessage();

	void showUnknownUserMessage();

	void showWrongPetDetailMessage();

	void showNotAdminUserMessage();

	void showPetSavedSuccessfullyMessage();

	void navigateToPetList();
}
