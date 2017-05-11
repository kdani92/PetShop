package hu.bme.xj4vjg.petshop.ui;

import android.content.Context;
import android.content.Intent;

import hu.bme.xj4vjg.petshop.ui.petlist.PetListActivity;

public class Navigator {
	public static void navigateToPetList(Context context) {
		Intent intent = new Intent(context, PetListActivity.class);
		context.startActivity(intent);
	}
}
