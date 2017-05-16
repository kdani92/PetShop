package hu.bme.xj4vjg.petshop.ui;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import hu.bme.xj4vjg.petshop.ui.main.MainActivity;

public class BaseFragment extends Fragment {
	protected void showMessage(String message) {
		Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
	}

	protected void showMessage(int resId) {
		Toast.makeText(getContext(), getString(resId), Toast.LENGTH_SHORT).show();
	}

	protected void updateTitle(String title) {
		Activity activity = getActivity();
		if (activity != null && activity instanceof MainActivity) {
			((MainActivity) activity).updateTitle(title);
		}
	}
}
