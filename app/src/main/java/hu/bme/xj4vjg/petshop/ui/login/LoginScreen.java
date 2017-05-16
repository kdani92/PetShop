package hu.bme.xj4vjg.petshop.ui.login;

public interface LoginScreen {
	void showEmptyFieldsMessage();

	void showWrongCredentialsMessage();

	void showUsernameExistsMessage();

	void showNetworkErrorMessage();

	void naviageToPetList();
}