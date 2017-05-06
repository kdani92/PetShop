package hu.bme.xj4vjg.petshop.ui;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;
import hu.bme.xj4vjg.petshop.ui.addpet.AddPetPresenter;
import hu.bme.xj4vjg.petshop.ui.login.LoginPresenter;
import hu.bme.xj4vjg.petshop.ui.petdetail.PetDetailPresenter;
import hu.bme.xj4vjg.petshop.ui.petlist.PetListPresenter;

@Module
public class UIModule {
	private Context context;

	public UIModule(Context context) {
		this.context = context;
	}

	@Provides
	public EventBus provideEventBus() {
		return EventBus.getDefault();
	}

	@Provides
	public Context provideContext() {
		return context;
	}

	@Provides
	@Singleton
	public LoginPresenter provideMainPresenter() {
		return new LoginPresenter();
	}

	@Provides
	@Singleton
	public PetListPresenter providePetListPresenter() {
		return new PetListPresenter();
	}

	@Provides
	@Singleton
	public PetDetailPresenter providePetDetailPresenter() {
		return new PetDetailPresenter();
	}

	@Provides
	@Singleton
	public AddPetPresenter provideAddPetPresenter() {
		return new AddPetPresenter();
	}
}