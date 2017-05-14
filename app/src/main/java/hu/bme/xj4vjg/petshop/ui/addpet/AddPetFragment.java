package hu.bme.xj4vjg.petshop.ui.addpet;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import hu.bme.xj4vjg.petshop.R;

import static hu.bme.xj4vjg.petshop.PetShopApplication.injector;

public class AddPetFragment extends Fragment implements AddPetScreen {
	public static final String TAG = "AddPetFragment";

	@Inject
	AddPetPresenter addPetPresenter;

	private AddPetFragment.OnAddPetFragmentInteractionListener listener;

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
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_add_pet, container, false);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof OnAddPetFragmentInteractionListener) {
			listener = (OnAddPetFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString() + " must implement OnAddPetFragmentInteractionListener");
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
		addPetPresenter.attachScreen(this);
	}

	@Override
	public void onStop() {
		addPetPresenter.detachScreen();
		super.onStop();
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

	public interface OnAddPetFragmentInteractionListener {
		void onAddPetFinished();
	}
}