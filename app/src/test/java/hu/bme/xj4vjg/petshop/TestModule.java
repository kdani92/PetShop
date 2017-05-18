package hu.bme.xj4vjg.petshop;

import android.content.Context;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;
import hu.bme.xj4vjg.petshop.model.Settings;
import hu.bme.xj4vjg.petshop.ui.UIModule;
import hu.bme.xj4vjg.petshop.ui.addpet.AddPetPresenter;
import hu.bme.xj4vjg.petshop.ui.login.LoginPresenter;
import hu.bme.xj4vjg.petshop.ui.petdetail.PetDetailPresenter;
import hu.bme.xj4vjg.petshop.ui.petlist.PetListPresenter;
import hu.bme.xj4vjg.petshop.util.di.Network;
import hu.bme.xj4vjg.petshop.util.di.Repository;
import hu.bme.xj4vjg.petshop.utils.UiExecutor;

@Module
public class TestModule {
	private final UIModule uiModule;

	public TestModule(Context context) {
		this.uiModule = new UIModule(context);
	}

	@Provides
	public Context provideContext() {
		return uiModule.provideContext();
	}

	@Provides
	@Singleton
	public Settings provideSettings() {
		return uiModule.provideSettings();
	}

	@Provides
	@Singleton
	@Repository
	public Executor provideNetworkExecutor() {
		return new UiExecutor();
	}

	@Provides
	@Singleton
	@Network
	public Executor provideRepositoryExecutor() {
		return new UiExecutor();
	}

	@Provides
	public LoginPresenter provideLoginPresenter() {
		return uiModule.provideLoginPresenter();
	}

	@Provides
	public PetListPresenter providePetListPresenter() {
		return uiModule.providePetListPresenter();
	}

	@Provides
	public PetDetailPresenter providePetDetailPresenter() {
		return uiModule.providePetDetailPresenter();
	}

	@Provides
	public AddPetPresenter provideAddPetPresenter() {
		return uiModule.provideAddPetPresenter();
	}

	@Provides
	@Singleton
	public EventBus provideEventBus() {
		return EventBus.getDefault();
	}
}
