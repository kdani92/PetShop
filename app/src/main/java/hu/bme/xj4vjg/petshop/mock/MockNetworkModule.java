package hu.bme.xj4vjg.petshop.mock;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.bme.xj4vjg.petshop.network.NetworkModule;
import hu.bme.xj4vjg.petshop.network.auth.AuthApi;
import hu.bme.xj4vjg.petshop.network.pet.PetApi;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

@Module
public class MockNetworkModule {
	private NetworkModule networkModule = new NetworkModule();

	@Provides
	@Singleton
	public OkHttpClient.Builder provideOkHttpClientBuilder() {
		return networkModule.provideOkHttpClientBuilder();
	}

	@Provides
	@Singleton
	public OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {
		builder.interceptors().add(new Interceptor() {
			@Override
			public Response intercept(Chain chain) throws IOException {
				Request request = chain.request();
				return MockHttpServer.call(request);
			}
		});

		return builder.build();
	}

	@Provides
	@Singleton
	public Retrofit provideRetrofit(OkHttpClient client) {
		return networkModule.provideRetrofit(client);
	}

	@Provides
	@Singleton
	public AuthApi provideAuthApi(Retrofit retrofit) {
		return networkModule.provideAuthApi(retrofit);
	}

	@Provides
	@Singleton
	public PetApi providePetApi(Retrofit retrofit) {
		return networkModule.providePetApi(retrofit);
	}
}