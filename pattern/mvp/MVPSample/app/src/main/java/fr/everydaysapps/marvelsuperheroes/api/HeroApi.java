package fr.everydaysapps.marvelsuperheroes.api;


import fr.everydaysapps.marvelsuperheroes.models.Heroes;
import retrofit2.http.GET;
import rx.Observable;

public interface HeroApi {

    @GET("test-mobile/iOS/json/test2.json")
    Observable<Heroes> getHeroes();


}
