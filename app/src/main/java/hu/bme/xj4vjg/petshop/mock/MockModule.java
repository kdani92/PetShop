package hu.bme.xj4vjg.petshop.mock;

import dagger.Module;
import dagger.Provides;

@Module
public class MockModule {
	@Provides
	public MockAuthInteractor provideMockAuthInteractor() {
		return new MockAuthInteractor();
	}

	@Provides
	public MockPetNetworkInteractor provideMockPetNetworkInteractor() {
		return new MockPetNetworkInteractor();
	}
}