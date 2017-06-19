package br.com.maiconhellmann.dribbble.data.remote;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;

import br.com.maiconhellmann.dribbble.data.model.Pull;
import br.com.maiconhellmann.dribbble.data.model.Repository;
import br.com.maiconhellmann.dribbble.data.model.Shot;
import br.com.maiconhellmann.dribbble.data.remote.parse.RepositoryParser;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface DribbbleService {

    String ENDPOINT = "https://api.dribbble.com/v1/";

    @GET("shots?sort=views&page={page}&per_page=10")
    Observable<List<Shot>> getShots(@Path("page") Integer page);

    @GET("/repos/{owner}/{repository}/pulls")
    Observable<List<Pull>> getPulls(@Path("owner") String owner,@Path("repository") String repository);

    class Creator {
        public static DribbbleService newDribbbleService() {
            Type listType = new TypeToken<List<Repository>>(){}.getType();

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .registerTypeAdapter(listType, new RepositoryParser())
                    .create();

            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(10, TimeUnit.SECONDS);
            builder.connectTimeout(5, TimeUnit.SECONDS);

            builder.addInterceptor(new Interceptor() {
                @Override public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request().newBuilder().addHeader("Authorization", "Bearer 14fcc58bf3121d1a043992e19e3fbc945c05e1787936b36b1f3c72c7ae8584d6").build();
                    return chain.proceed(request);
                }
            });

            OkHttpClient client = builder.build();

            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(DribbbleService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(DribbbleService.class);
        }
    }
}
