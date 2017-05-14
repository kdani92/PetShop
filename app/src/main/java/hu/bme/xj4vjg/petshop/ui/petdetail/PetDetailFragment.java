package hu.bme.xj4vjg.petshop.ui.petdetail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import hu.bme.xj4vjg.petshop.R;

import static hu.bme.xj4vjg.petshop.PetShopApplication.injector;

public class PetDetailFragment extends Fragment implements PetDetailScreen {
	public static final String TAG = "PetDetailFragment";

	@Inject
	PetDetailPresenter petDetailPresenter;

	public PetDetailFragment() {

	}

	public static PetDetailFragment newInstance() {
		PetDetailFragment fragment = new PetDetailFragment();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		injector.inject(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_pet_detail, container, false);
	}

	@Override
	public void onStart() {
		super.onStart();
		petDetailPresenter.attachScreen(this);
	}

	@Override
	public void onStop() {
		petDetailPresenter.detachScreen();
		super.onStop();
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
