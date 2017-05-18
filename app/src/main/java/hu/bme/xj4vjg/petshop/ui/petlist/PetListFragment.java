package hu.bme.xj4vjg.petshop.ui.petlist;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import hu.bme.xj4vjg.petshop.R;
import hu.bme.xj4vjg.petshop.model.Pet;
import hu.bme.xj4vjg.petshop.model.Species;
import hu.bme.xj4vjg.petshop.ui.BaseFragment;
import hu.bme.xj4vjg.petshop.ui.petlist.adapter.PetAdapter;
import hu.bme.xj4vjg.petshop.ui.petlist.dialog.SpeciesFilterDialog;

import static hu.bme.xj4vjg.petshop.PetShopApplication.injector;

public class PetListFragment extends BaseFragment implements
		PetAdapter.OnPetClickedListener,
		SpeciesFilterDialog.OnSpeciesSelectedListener,
		PetListScreen {
	public static final String TAG = "PetListFragment";

	@Inject
	PetListPresenter petListPresenter;

	@Bind(R.id.pets_recycle_view)
	RecyclerView recyclerView;

	private Tracker tracker;
	private PetAdapter petAdapter;
	private OnPetListFragmentInteractionListener listener;

	public PetListFragment() {

	}

	public static PetListFragment newInstance() {
		PetListFragment fragment = new PetListFragment();
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
		View root = inflater.inflate(R.layout.fragment_pet_list, container, false);
		ButterKnife.bind(this, root);
		setPetRecycleView();

		return root;
	}

	private void setPetRecycleView() {
		petAdapter = new PetAdapter(getContext(), new ArrayList<Pet>());
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setAdapter(petAdapter);
		petAdapter.setOnPetClickedListener(this);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof OnPetListFragmentInteractionListener) {
			listener = (OnPetListFragmentInteractionListener) context;
			System.out.println("Activity: " + getActivity().getClass().toString());
		} else {
			throw new RuntimeException(context.toString() + " must implement OnPetListFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		listener = null;
	}

	@Override
	public void onStart() {
		super.onStart();
		updateTitle(getString(R.string.fragment_pet_list_title));
		petListPresenter.attachScreen(this);
		petListPresenter.applySpeciesFilters(new ArrayList<Species>());
		petListPresenter.updateSpecies();
		petListPresenter.updatePets();

		tracker.setScreenName(TAG);
		tracker.send(new HitBuilders.ScreenViewBuilder().build());
	}

	@Override
	public void onStop() {
		petListPresenter.detachScreen();
		super.onStop();
	}

	@Override
	public void onPetClicked(Pet pet) {
		if (listener != null) {
			listener.onPetSelected(pet.getPetId());
		}
	}

	public void onFilterClicked() {
		SpeciesFilterDialog speciesFilterDialog = new SpeciesFilterDialog(
				getContext(),
				new ArrayList<>(petListPresenter.getSpeciesList()),
				new ArrayList<>(petListPresenter.getActiveSpeciesFilters()));
		speciesFilterDialog.setListener(this);
		speciesFilterDialog.show();
	}

	@Override
	public void onSpeciesSelected(List<Species> speciesList) {
		petListPresenter.applySpeciesFilters(speciesList);

		tracker.send(new HitBuilders.EventBuilder()
				.setCategory("Action")
				.setAction("Pets filtered")
				.build());
	}

	@Override
	public void showUnknownUserMessage() {
		showMessage(R.string.unknown_user_error);
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
	public void showOfflinePetsFoundMessage() {
		showMessage(R.string.offline_but_pets_found_from_repo);

		tracker.send(new HitBuilders.EventBuilder()
				.setCategory("Action")
				.setAction("Pets showed with offline mode")
				.build());
	}

	@Override
	public void refreshPets() {
		petAdapter.setPets(petListPresenter.getFilteredPetList());
		petAdapter.notifyDataSetChanged();

		tracker.send(new HitBuilders.EventBuilder()
				.setCategory("Action")
				.setAction("Pets refreshed")
				.build());
	}

	public interface OnPetListFragmentInteractionListener {
		void onPetSelected(String petId);
	}
}
