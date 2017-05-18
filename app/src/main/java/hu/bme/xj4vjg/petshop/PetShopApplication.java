package hu.bme.xj4vjg.petshop;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import hu.bme.xj4vjg.petshop.repository.Repository;
import hu.bme.xj4vjg.petshop.ui.UIModule;
import io.fabric.sdk.android.Fabric;

public class PetShopApplication extends Application {
	public static PetShopComponent injector;

	@Inject
	Repository repository;

	private Tracker tracker;

	@Override
	public void onCreate() {
		super.onCreate();

		Fabric.with(this, new Crashlytics());
		injector =
				DaggerPetShopComponent.builder().
						uIModule(
								new UIModule(getApplicationContext())
						).build();
		injector.inject(this);

		repository.open(this);
	}

	public void setInjector(PetShopComponent appComponent) {
		injector = appComponent;
		injector.inject(this);
		repository.open(getApplicationContext());
	}

	synchronized public Tracker getDefaultTracker() {
		if (tracker == null) {
			GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
			tracker = analytics.newTracker(R.xml.global_tracker);
		}
		return tracker;
	}
}