package hu.bme.xj4vjg.petshop.ui.main;

import hu.bme.xj4vjg.petshop.ui.Presenter;

public class MainPresenter extends Presenter<MainScreen> {
	private static MainPresenter instance = null;

	private MainPresenter() {
	}

	public static MainPresenter getInstance() {
		if (instance == null) {
			instance = new MainPresenter();
		}
		return instance;
	}

	@Override
	public void attachScreen(MainScreen screen) {
		super.attachScreen(screen);
	}

	@Override
	public void detachScreen() {
		super.detachScreen();
	}
}