package hu.bme.xj4vjg.petshop.ui.petdetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import hu.bme.xj4vjg.petshop.R;

import static hu.bme.xj4vjg.petshop.PetShopApplication.injector;

public class PetDetailActivity extends AppCompatActivity implements PetDetailScreen {
	@Inject
	PetDetailPresenter petDetailPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pet_detail);
		injector.inject(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		petDetailPresenter.attachScreen(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		petDetailPresenter.detachScreen();
	}

	@Override
	public void showUnknownUserMessage() {

	}

	@Override
	public void showUnknownPetMessage() {

	}

	@Override
	public void showUnknownServerErrorMessage() {

	}

	@Override
	public void showOfflineRepositoryErrorMessage() {

	}

	@Override
	public void showOfflineUnknownPetMessage() {

	}

	@Override
	public void showOfflinePetFoundMessage() {

	}

	@Override
	public void refreshPetDetail() {

	}
}
