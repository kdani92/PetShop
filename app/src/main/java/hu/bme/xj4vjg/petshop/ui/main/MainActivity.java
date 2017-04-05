package hu.bme.xj4vjg.petshop.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import hu.bme.xj4vjg.petshop.R;

public class MainActivity extends AppCompatActivity implements MainScreen {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onStart() {
		super.onStart();
		MainPresenter.getInstance().attachScreen(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		MainPresenter.getInstance().detachScreen();
	}

	@Override
	public void showMessage(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}
}
