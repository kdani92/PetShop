package hu.bme.xj4vjg.petshop.ui.petlist.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import hu.bme.xj4vjg.petshop.R;
import hu.bme.xj4vjg.petshop.model.Pet;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> {
	private Context context;
	private List<Pet> pets;
	private OnPetClickedListener listener;

	public class PetViewHolder extends RecyclerView.ViewHolder {
		public CardView petCardView;
		public ImageView petImageView;
		public TextView petSpeciesTextView;
		public TextView petPriceTextView;

		public PetViewHolder(View itemView) {
			super(itemView);
			petCardView = (CardView) itemView.findViewById(R.id.pet_card_view);
			petImageView = (ImageView) itemView.findViewById(R.id.pet_image_view);
			petSpeciesTextView = (TextView) itemView.findViewById(R.id.pet_species_text_view);
			petPriceTextView = (TextView) itemView.findViewById(R.id.pet_price_text_view);
			petCardView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					PetAdapter.this.notifyListener(pets.get(getAdapterPosition()));
				}
			});
		}
	}

	public PetAdapter(Context context, List<Pet> pets) {
		this.context = context;
		this.pets = pets;
	}

	public void setOnPetClickedListener(OnPetClickedListener listener) {
		this.listener = listener;
	}

	@Override
	public PetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_row, parent, false);
		return new PetViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(PetViewHolder holder, int position) {
		Pet pet = pets.get(position);

		Glide
				.with(context)
				.load(pet.getImageUrl())
				.error(R.drawable.app_logo_black_small)
				.into(holder.petImageView);
		holder.petSpeciesTextView.setText(pet.getSpecies());
		holder.petPriceTextView.setText(pet.getPriceFormatted());
	}

	@Override
	public int getItemCount() {
		return pets.size();
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

	public void notifyListener(Pet pet) {
		if (listener != null) {
			listener.onPetClicked(pet);
		}
	}

	public interface OnPetClickedListener {
		void onPetClicked(Pet pet);
	}
}
