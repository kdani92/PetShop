package hu.bme.xj4vjg.petshop.ui;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.bme.xj4vjg.petshop.ui.main.MainPresenter;

@Module
public class UIModule {
	private Context context;

	public UIModule(Context context) {
		this.context = context;
	}

	@Provides
	public Context provideContext() {
		return context;
	}

	@Provides
	@Singleton
	public MainPresenter provideMainPresenter() {
		return new MainPresenter();
	}
}