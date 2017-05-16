package hu.bme.xj4vjg.petshop.ui.petlist.dialog;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import hu.bme.xj4vjg.petshop.R;
import hu.bme.xj4vjg.petshop.model.Species;

public class SpeciesFilterDialog {
	private Context context;
	private MaterialDialog dialog;
	private List<Species> speciesList = new ArrayList<>();
	private List<Species> selectedSpeciesList = new ArrayList<>();
	private OnSpeciesSelectedListener listener;

	public SpeciesFilterDialog(Context context, List<Species> speciesList, List<Species> selectedSpeciesList) {
		this.context = context;
		this.speciesList = speciesList;
		this.selectedSpeciesList = selectedSpeciesList;
		buildDialog();
	}

	public SpeciesFilterDialog(Context context, List<Species> speciesList) {
		this(context, speciesList, null);
	}

	public void setListener(OnSpeciesSelectedListener listener) {
		this.listener = listener;
	}

	private void buildDialog() {
		List<String> speciesNames = new ArrayList<>();
		for (Species species : speciesList) {
			speciesNames.add(species.getName());
		}

		dialog = new MaterialDialog.Builder(context)
				.title(R.string.dialog_pet_filter_title)
				.items(speciesNames)
				.itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
					@Override
					public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
						if (listener != null) {
							List<Species> selectedSpeciesList = new ArrayList<Species>();
							for (Integer index : which) {
								selectedSpeciesList.add(speciesList.get(index));
							}

							if (listener != null) {
								listener.onSpeciesSelected(selectedSpeciesList);
							}
						}
						return true;
					}
				})
				.backgroundColor(ContextCompat.getColor(context, R.color.white))
				.titleColor(ContextCompat.getColor(context, R.color.colorTextSecondary))
				.itemsColor(ContextCompat.getColor(context, R.color.colorTextSecondary))
				.positiveText(R.string.dialog_pet_filter_choose)
				.negativeText(R.string.dialog_pet_filter_cancel)
				.build();

		selectSelectedSpecies();
	}

	private void selectSelectedSpecies() {
		if (selectedSpeciesList != null && !selectedSpeciesList.isEmpty()) {
			List<Integer> selectedIndexesList = new ArrayList<>();
			for (Species selectedSpecies : selectedSpeciesList) {
				for (Species species : speciesList) {
					if (species.getSpeciesId().equals(selectedSpecies.getSpeciesId())) {
						selectedIndexesList.add(speciesList.indexOf(species));
					}
				}
			}

			Integer[] selectedIndexesArray = new Integer[selectedIndexesList.size()];
			selectedIndexesList.toArray(selectedIndexesArray);
			dialog.setSelectedIndices(selectedIndexesArray);
		}
	}

	public void show() {
		dialog.show();
	}

	public interface OnSpeciesSelectedListener {
		void onSpeciesSelected(List<Species> speciesList);
	}
}
