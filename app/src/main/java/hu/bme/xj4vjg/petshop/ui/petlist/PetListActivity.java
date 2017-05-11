package hu.bme.xj4vjg.petshop.ui.petlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import hu.bme.xj4vjg.petshop.R;

import static hu.bme.xj4vjg.petshop.PetShopApplication.injector;

public class PetListActivity extends AppCompatActivity implements PetListScreen {
	@Inject
	PetListPresenter petListPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pet_list);
		injector.inject(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		petListPresenter.attachScreen(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		petListPresenter.detachScreen();
	}

	@Override
	public void showUnknownUserMessage() {

	}

	@Override
	public void showUnknownServerErrorMessage() {

	}

	@Override
	public void showOfflineRepositoryErrorMessage() {

	}

	@Override
	public void showOfflinePetsFoundMessage() {

	}

	@Override
	public void refreshPets() {

	}
}
