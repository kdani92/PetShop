package hu.bme.xj4vjg.petshop.ui.addpet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import hu.bme.xj4vjg.petshop.R;

import static hu.bme.xj4vjg.petshop.PetShopApplication.injector;

public class AddPetActivity extends AppCompatActivity implements AddPetScreen {
	@Inject
	AddPetPresenter addPetPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_pet);
		injector.inject(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		addPetPresenter.attachScreen(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		addPetPresenter.detachScreen();
	}

	@Override
	public void showUnknownNetworkErrorMessage() {
		
	}

	@Override
	public void showUnknownUserMessage() {

	}

	@Override
	public void showWrongPetDetailMessage() {

	}

	@Override
	public void showNotAdminUserMessage() {

	}

	@Override
	public void showPetSavedSuccessfullyMessage() {

	}

	@Override
	public void navigateToPetList() {

	}
}