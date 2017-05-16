package hu.bme.xj4vjg.petshop;

import javax.inject.Singleton;

import dagger.Component;
import hu.bme.xj4vjg.petshop.interactor.InteractorModule;
import hu.bme.xj4vjg.petshop.mock.MockNetworkModule;
import hu.bme.xj4vjg.petshop.repository.TestRepositoryModule;

@Singleton
@Component(modules = {MockNetworkModule.class, TestModule.class, InteractorModule.class, TestRepositoryModule.class})
public interface TestComponent extends PetShopComponent {
}