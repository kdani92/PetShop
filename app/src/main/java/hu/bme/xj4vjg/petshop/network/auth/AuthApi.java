package hu.bme.xj4vjg.petshop.network.auth;

import hu.bme.xj4vjg.petshop.network.auth.model.UserDetail;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {
	@POST("auth/register")
	Call<ResponseBody> register(@Body UserDetail userDetail);

	@POST("auth/login")
	Call<ResponseBody> login(@Body UserDetail userDetail);
}
