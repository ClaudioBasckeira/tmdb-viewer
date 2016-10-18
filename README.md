## TMDB Viewer

A small Android app that allows viewing the list of upcoming movies from TMDb, as well as searching for movies and seeing the details of a specific movie.

## Third-party libraries used

### OrmLite Dao: http://ormlite.com/sqlite_java_android_orm.shtml
Used to simplify dealing with the database. Simple and lightweight enough for the needs of the app, since a database is used to store the list of genres.

### Retrofit: http://square.github.io/retrofit/
The HTTP client used to implement the calls to TMDb's API. Streamlines the implementation in a very clean, flexible and easy to use way.

### OkHttp: http://square.github.io/okhttp/
HTTP client used by retrofit to actually make the calls. Allows setting interceptors and logging.

### greenrobot EventBus: http://greenrobot.org/eventbus/
Used for asynchronous communication between the activities and the service layer, allowing them to be very decoupled. Very clean and concise usage

### Gson: https://github.com/google/gson
Json serializer/deserializer used to easily convert the TMDb API responses into objects.

### Joda-Time: http://www.joda.org/joda-time/
Robust date library that offers more reliability and flexibility than Java's standard library. Used for the date calculations in the app.

### Gson Jodatime Serialisers: https://github.com/gkopff/gson-jodatime-serialisers
Adds to Gson the ability to serlialize/deserialize the LocalDate objects used in the app.

### Glide: https://github.com/bumptech/glide
Used to download, optimize show and cache images. Recommended by google and indeed very simple to use.

### Android Annotations: http://androidannotations.org/
Adds dependency injection and automates lots of plumbing and boilerplate related to activities and other elements of the Android framework, allowing for much cleaner and more concise code to be written all around. Has integration with OrmLite and Parceler.

### Parceler: http://parceler.org/
Eliminates the need to write boilerplate code to implement parcelables.

## Credits

<div>Icons made by <a href="http://www.flaticon.com/authors/simpleicon" title="SimpleIcon">SimpleIcon</a> from <a href="http://www.flaticon.com" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>