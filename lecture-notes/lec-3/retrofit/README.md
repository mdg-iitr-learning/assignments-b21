# Retrofit in Android
This lecture introduces you to Retrofit, a REST Client for Android and Java by Square. It makes it relatively easy to retrieve and upload JSON (or other structured data) via a REST based webservice.

In Retrofit you configure which converter is used for the data serialization. Typically for JSON you use GSon, but you can add custom converters to process XML, or other protocols like Protocol Buffers. Retrofit uses the OkHttp library for HTTP requests.


## Setup
In order to use retrofit in android, add the following dependency in your `gradle.build` file:

`compile 'com.squareup.retrofit2:retrofit:2.1.0'`

Also, add the following permission in your `AndroidManifest.xml` file:

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

## Using Retrofit in your Application

There are three classes that need to be defined in your application:

1. Interfaces which define the possible HTTP operations

2. Model class which is used to map the JSON data to

3. `Retrofit.Builder` class - Instance which uses the interface and the Builder API which allows defining the URL end point for the HTTP operation.

## Interfaces

Every method of an interface represents one possible API call. It must have a HTTP annotation (`GET`, `POST`, etc.) to specify the request type and the relative URL. The return value wraps the response in a *Call* object with the type of the expected result.

Two commonly used methods for a request-response between a client and server are:

* `GET` - Requests data from a specified resource
* `POST` - Submits data to be processed to a specified resource

The parameter passed in the HTTP method annotation is the extension added to the base URL.

```java
@GET("users")
Call<List<User>> getUsers()
```

You can use replacement blocks and query parameters to adjust the URL. A replacement block is added to the relative URL with `{}`. With the help of the `@Path` annotation in the method parameter, the value of that parameter is bound to the specific replacement block.

Query parameters are added with the `@Query` annotation in a method parameter. They are automatically added at the end of the URL.

```java
@GET("tv/19885/season/{id}")
Call<SeasonDetails> getSeasonDetails(@Path("id") int id, @Query("api_key") String apikey);
```

The `@Body` annotation on a method parameter tells Retrofit to use the object as the request body for the call.

```java
@POST("users")
Call<User> postUser(@Body User user)
```

In short, each endpoint specifies an annotation of the HTTP method (GET, POST, etc.) and method that will be used to dispatch the network call. The parameters of this method can also have special annotations:

| Annotation | Description |
| --- | :---: |
| `@Path` | variable substitution for the API endpoint (i.e. username will be swapped for `{username}` in the URL endpoint) |
| `@Query` | specifies the query key name with the value of the annotated parameter |
| `@Body` | payload for the POST call (serialized from a Java object to a JSON string) |
| `@Header` | specifies the header with the value of the annotated parameter |

## Model Class

You need to create `model` classes that are based on the JSON response received from an API call.

JSON stands for **J**ava**S**cript **O** bject **N** otation.  It is a syntax for storing and exchanging data.

Google's `Gson` library provides a powerful framework for converting between JSON strings and Java objects. This library helps to avoid needing to write boilerplate code to parse JSON responses yourself. It can be used with any networking library, including the Android Async HTTP Client and OkHttp.

To use it, add the following dependency in your `gradle.build` file:

`compile 'com.google.code.gson:gson:2.8.0'`

An example JSON response is as given below:

```javascript
{
  id: "771357161",
  title: "Mission: Impossible Rogue Nation",
  production: {
    director: "Christopher McQuarrie",
    screenplay: "Christopher McQuarrie"
  },
  year: 2015
}
```

The corresponding `model` classes are as follows:

```java
class Movie {
    String id;
    String title;
    int year;
    Production production;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public Production getProduction() {
        return production;
    }
}
```

```java
class Production {
    String director;
    String screenplay;

    public String getDirector() {
        return director;
    }

    public String getScreenplay() {
        return screenplay;
    }
}
```

Add the setter and constructors in the above classes accordingly.

By default, the Gson library will map the fields defined in the class to the JSON keys defined in the response. For instance, the fields `id`, `title`, and `year` will be mapped automatically. We do not need any special annotations unless the field names and JSON keys are different.

In this specific case, the Movie class will correspond to each individual movie element and the Production class corresponds to the nested JSON object under movie's production element.

#### Matching variable names to JSON keys

For instance, if our property name matches that of the JSON key, then we do not need to annotate the attributes. However, if we have a different name we wish to use, we can simply annotate the declaration with `@SerializedName`:

```java
public class SeasonDetails {

    @SerializedName("_id")
    private String _id;
  }
```

You can find more information about making model classes manually at this [guide](https://github.com/codepath/android_guides/wiki/Leveraging-the-Gson-Library).

## Retrofit.Builder Class

### Retrofit Converters

Retrofit can be configured to use a specific converter. This converter handles the data (de)serialization. Several converters are already available for various serialization formats:

* To convert to and from JSON:

  1. Gson: `com.squareup.retrofit:converter-gson`

  2. Jackson: `com.squareup.retrofit:converter-jackson`

  3. Moshi: `com.squareup.retrofit:converter-moshi`

* To convert to and from Protocol Buffers:

  1. Protobuf: `com.squareup.retrofit:converter-protobuf`

  2. Wire: `com.squareup.retrofit:converter-wire`

* To convert to and from XML:

  1. Simple XML: `com.squareup.retrofit:converter-simplexml`

Besides the listed converters, you can also create custom converters to process other protocols by subclassing the *Converter.Factory* class.

### Creating the Retrofit Instance

To send out network requests to an API, we need to use the
`Retrofit.Builder`
class and specify the base URL for the service.

```java
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        }
        return retrofit;
    }
}
```

> `BASE_URL` should contain the trailing `/` in the end!

## Making API Calls, Finally!

After creating the three necessary classes, initiate the REST API Client and make the API call wherever you need in the project.  An example usage is shown below:

```java
ApiInterface apiServiceSeason0 = RetrofitClient.getClient().create(ApiInterface.class);
Call<SeasonDetails> callSeason0 = apiServiceSeason0.getSeasonDetails(0, API_KEY);
callSeason0.enqueue(new Callback<SeasonDetails>() {
    @Override
    public void onResponse(Call<SeasonDetails> call, Response<SeasonDetails> response) {
        //do something when JSON response is recieved
    }

    @Override
    public void onFailure(Call<SeasonDetails> call, Throwable t) {
        //do something when JSON response is not recieved
    }
});
```

You are now ready to retrieve JSON from API calls!  However, retrofit helps not only in uploading and retrieving JSON, XML or Protobuf, but also in Authentication.  You can find more information about it at this [guide](https://github.com/codepath/android_guides/wiki/Consuming-APIs-with-Retrofit#retrofit-and-authentication).

Its pretty easy to use Retrofit once you get the hang of it and makes it a hell lot easier to do networking in your android applications! 

## Sources and More Guides

* [Vogella Tutorial](http://www.vogella.com/tutorials/Retrofit/article.html)   
* [A Nice Guide by CodePath](https://github.com/codepath/android_guides/wiki/Consuming-APIs-with-Retrofit)
* [AndroidHive Tutorial for using TMDB API](https://www.androidhive.info/2016/05/android-working-with-retrofit-http-library/)
