package hu.bme.xj4vjg.petshop.ui.petdetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import hu.bme.xj4vjg.petshop.R;
import hu.bme.xj4vjg.petshop.model.Pet;
import hu.bme.xj4vjg.petshop.ui.BaseFragment;

import static hu.bme.xj4vjg.petshop.PetShopApplication.injector;

public class PetDetailFragment extends BaseFragment implements PetDetailScreen {
	public static final String TAG = "PetDetailFragment";
	public static final String PET_ID_ARG = "PetId";

	@Inject
	PetDetailPresenter petDetailPresenter;

	@Bind(R.id.pet_image_view)
	ImageView petImageView;
	@Bind(R.id.species_text_view)
	TextView speciesTextView;
	@Bind(R.id.color_text_view)
	TextView colorTextView;
	@Bind(R.id.age_text_view)
	TextView ageTextView;
	@Bind(R.id.price_text_view)
	TextView priceTextView;

	private Tracker tracker;

	public PetDetailFragment() {

	}

	public static PetDetailFragment newInstance(String petId) {
		PetDetailFragment fragment = new PetDetailFragment();
		Bundle args = new Bundle();
		args.putString(PET_ID_ARG, petId);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		injector.inject(this);

		Bundle args = getArguments();
		String petIdArg;
		if (args != null) {
			petIdArg = args.getString(PET_ID_ARG, "");
		} else {
			petIdArg = "";
		}
		petDetailPresenter.setPetId(petIdArg);

		tracker = getTracker();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_pet_detail, container, false);
		ButterKnife.bind(this, root);

		return root;
	}

	@Override
	public void onStart() {
		super.onStart();
		updateTitle(getString(R.string.fragment_pet_detail_title));
		petDetailPresenter.attachScreen(this);

		petDetailPresenter.updatePetDetail();

		tracker.setScreenName(TAG);
		tracker.send(new HitBuilders.ScreenViewBuilder().build());
	}

	@Override
	public void onStop() {
		petDetailPresenter.detachScreen();
		super.onStop();
	}

	@Override
	public void showUnknownUserMessage() {
		showMessage(R.string.unknown_user_error);
	}

	@Override
	public void showUnknownPetMessage() {
		showMessage(R.string.unknown_pet_error);
	}

	@Override
	public void showUnknownServerErrorMessage() {
		showMessage(R.string.network_error);
	}

	@Override
	public void showOfflineRepositoryErrorMessage() {
		showMessage(R.string.offline_with_repository_error);
	}

	@Override
	public void showOfflineUnknownPetMessage() {
		showMessage(R.string.offline_and_pet_detail_not_found_from_repo_error);

		tracker.send(new HitBuilders.EventBuilder()
				.setCategory("Action")
				.setAction("Pet detail shown with offline mode")
				.build());
	}

	@Override
	public void showOfflinePetFoundMessage() {
		showMessage(R.string.offline_but_pet_detail_found_from_repo);
	}

	@Override
	public void refreshPetDetail() {
		Pet pet = petDetailPresenter.getPet();
		updateTitle(pet.getSpecies() + " " + getString(R.string.fragment_pet_detail_detail));
		Glide
				.with(getContext())
				.load(pet.getImageUrl())
				.error(R.drawable.app_logo_black_big)
				.into(petImageView);
		speciesTextView.setText(pet.getSpecies());
		colorTextView.setText(pet.getColor());
		ageTextView.setText(pet.getAge());
		priceTextView.setText(pet.getPriceFormatted());

		tracker.send(new HitBuilders.EventBuilder()
				.setCategory("Action")
				.setAction("Pet detail refreshed")
				.build());
	}
}
