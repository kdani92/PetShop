package hu.bme.xj4vjg.petshop.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.bme.xj4vjg.petshop.network.auth.AuthApi;
import hu.bme.xj4vjg.petshop.network.pet.PetApi;
import hu.bme.xj4vjg.petshop.util.GsonHelper;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

@Module
public class NetworkModule {
	@Provides
	@Singleton
	public OkHttpClient.Builder provideOkHttpClientBuilder() {
		return new OkHttpClient().newBuilder();
	}

	@Provides
	@Singleton
	public OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {
		return builder
				.retryOnConnectionFailure(true)
				.build();
	}

	@Provides
	@Singleton
	public Retrofit provideRetrofit(OkHttpClient client) {
		return new Retrofit.Builder()
				.baseUrl(NetworkConfig.SERVICE_ENDPOINT)
				.client(client)
				.addConverterFactory(GsonConverterFactory.create(GsonHelper.getGson()))
				.build();
	}

	@Provides
	@Singleton
	public AuthApi provideAuthApi(Retrofit retrofit) {
		return retrofit.create(AuthApi.class);
	}

	@Provides
	@Singleton
	public PetApi providePetApi(Retrofit retrofit) {
		return retrofit.create(PetApi.class);
	}
}
