package hu.bme.xj4vjg.petshop.repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
	@Provides
	@Singleton
	public Repository provideRepository() {
		return new SugarOrmRepository();
	}
}
