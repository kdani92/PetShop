package hu.bme.xj4vjg.petshop.ui;

import android.content.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;
import hu.bme.xj4vjg.petshop.ui.addpet.AddPetPresenter;
import hu.bme.xj4vjg.petshop.ui.login.LoginPresenter;
import hu.bme.xj4vjg.petshop.ui.petdetail.PetDetailPresenter;
import hu.bme.xj4vjg.petshop.ui.petlist.PetListPresenter;
import hu.bme.xj4vjg.petshop.util.di.Network;
import hu.bme.xj4vjg.petshop.util.di.Repository;

@Module
public class UIModule {
	private Context context;

	public UIModule(Context context) {
		this.context = context;
	}

	@Provides
	@Singleton
	public EventBus provideEventBus() {
		return EventBus.getDefault();
	}

	@Provides
	@Singleton
	@Repository
	public Executor provideRepositoryExecutor() {
		return Executors.newFixedThreadPool(1);
	}

	@Provides
	@Singleton
	@Network
	public Executor provideNetworkExecutor() {
		return Executors.newFixedThreadPool(1);
	}

	@Provides
	public Context provideContext() {
		return context;
	}

	@Provides
	@Singleton
	public LoginPresenter provideLoginPresenter() {
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