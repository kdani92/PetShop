package hu.bme.xj4vjg.petshop.mock.interceptors;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import hu.bme.xj4vjg.petshop.util.GsonHelper;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.Okio;

public class MockHelper {
	public static final int ERROR_RESPONSE_CODE = 503;

	public static Response makeResponse(Request request, Headers headers, int code, final String content) {

		return new Response.Builder().protocol(Protocol.HTTP_2).code(code).request(request).headers(headers).body(new ResponseBody() {
			@Override
			public MediaType contentType() {
				return MediaType.parse("application/json");
			}

			@Override
			public long contentLength() {
				return content.getBytes().length;
			}

			@Override
			public BufferedSource source() {
				return Okio.buffer(Okio.source(new ByteArrayInputStream(content.getBytes())));
			}
		}).build();
	}

	public static <T> T getBody(Request request, Class<T> clazz) {
		try {
			RequestBody requestBody = request.newBuilder().build().body();
			Buffer buffer = new Buffer();
			requestBody.writeTo(buffer);
			return GsonHelper.getGson().fromJson(buffer.readUtf8(), clazz);
		} catch (Exception e) {
			return null;
		}
	}
}