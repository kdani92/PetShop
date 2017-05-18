package hu.bme.xj4vjg.petshop.ui.addpet;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.bme.xj4vjg.petshop.R;
import hu.bme.xj4vjg.petshop.model.Pet;
import hu.bme.xj4vjg.petshop.ui.BaseFragment;

import static hu.bme.xj4vjg.petshop.PetShopApplication.injector;

public class AddPetFragment extends BaseFragment implements AddPetScreen {
	public static final String TAG = "AddPetFragment";

	@Inject
	AddPetPresenter addPetPresenter;

	@Bind(R.id.pet_image_view)
	ImageView petImageView;
	@Bind(R.id.species_edit_text)
	TextInputEditText speciesEditText;
	@Bind(R.id.color_edit_text)
	TextInputEditText colorEditText;
	@Bind(R.id.age_edit_text)
	TextInputEditText ageEditText;
	@Bind(R.id.price_edit_text)
	TextInputEditText priceEditText;

	private Tracker tracker;

	public AddPetFragment() {

	}

	public static AddPetFragment newInstance() {
		AddPetFragment fragment = new AddPetFragment();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		injector.inject(this);
		tracker = getTracker();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_add_pet, container, false);
		ButterKnife.bind(this, root);

		return root;
	}

	@Override
	public void onStart() {
		super.onStart();
		updateTitle(getString(R.string.fragment_add_pet_title));
		addPetPresenter.attachScreen(this);

		tracker.setScreenName(TAG);
		tracker.send(new HitBuilders.ScreenViewBuilder().build());
	}

	@Override
	public void onStop() {
		addPetPresenter.detachScreen();
		super.onStop();
	}

	@OnClick(R.id.add_pet_button)
	public void onAddPetButtonClick() {
		String species = speciesEditText.getText().toString();
		String color = colorEditText.getText().toString();
		String age = ageEditText.getText().toString();
		String price = priceEditText.getText().toString();

		if (validatePetDetail(species, color, age, price)) {
			addPetPresenter.addPet(
					species,
					color,
					age.isEmpty() ? 0 : Pet.getTimeOfBirthFromAgeInMonths(Integer.parseInt(age)),
					price.isEmpty() ? 0 : Integer.parseInt(price),
					null);
		} else {
			showWrongPetDetailMessage();
		}
	}

	private boolean validatePetDetail(String species, String color, String age, String price) {
		if (species.isEmpty()) {
			return false;
		}

		try {
			if (!age.isEmpty()) {
				int ageInt = Integer.parseInt(age);
				if (ageInt < 0) {
					return false;
				}
			}
			if (!price.isEmpty()) {
				int priceInt = Integer.parseInt(price);
				if (priceInt < 0) {
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	public void showUnknownNetworkErrorMessage() {
		showMessage(R.string.network_error);
	}

	@Override
	public void showUnknownUserMessage() {
		showMessage(R.string.unknown_user_error);
	}

	@Override
	public void showWrongPetDetailMessage() {
		showMessage(R.string.fragment_pet_detail_wrong_detail);

		tracker.send(new HitBuilders.EventBuilder()
				.setCategory("Action")
				.setAction("Wrong pet detail during add pet")
				.build());
	}

	@Override
	public void showNotAdminUserMessage() {
		showMessage(R.string.not_admin_user_erre);
	}

	@Override
	public void navigateToPetList() {
		tracker.send(new HitBuilders.EventBuilder()
				.setCategory("Action")
				.setAction("Pet added")
				.build());

		Activity activity = getActivity();
		if (activity != null) {
			activity.onBackPressed();
		}
	}
}