package hu.bme.xj4vjg.petshop;

import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowLog;

public class TestHelper {

	public static void setTestInjector() {
		ShadowLog.stream = System.out;
		PetShopApplication application = (PetShopApplication) RuntimeEnvironment.application;
		PetShopComponent injector = DaggerTestComponent.builder().testModule(new TestModule(application.getApplicationContext())).build();
		application.setInjector(injector);
	}
}