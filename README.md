## TMDB Viewer

A small Android app that allows viewing the list of upcoming movies from TMDb, as well as searching for movies and seeing the details of a specific movie.

## Third-party libraries used

### [OrmLite Dao](http://ormlite.com/sqlite_java_android_orm.shtml)
Used to simplify dealing with the database. Simple and lightweight enough for the needs of the app, since a database is used to store the list of genres.

### [Retrofit](http://square.github.io/retrofit/)
The HTTP client used to implement the calls to TMDb's API. Streamlines the implementation in a very clean, flexible and easy to use way.

### [OkHttp](http://square.github.io/okhttp/)
HTTP client used by retrofit to actually make the calls. Allows setting interceptors and logging.

### [Greenrobot EventBus](http://greenrobot.org/eventbus/)
Used for asynchronous communication between the activities and the service layer, allowing them to be very decoupled. Very clean and concise usage

### [Gson](https://github.com/google/gson)
Json serializer/deserializer used to easily convert the TMDb API responses into objects.

### [Joda-Time](http://www.joda.org/joda-time/)
Robust date library that offers more reliability and flexibility than Java's standard library. Used for the date calculations in the app.

### [Gson Jodatime Serialisers](https://github.com/gkopff/gson-jodatime-serialisers)
Adds to Gson the ability to serlialize/deserialize the LocalDate objects used in the app.

### [Glide](https://github.com/bumptech/glide)
Used to download, optimize show and cache images. Recommended by google and indeed very simple to use.

### [Android Annotations](http://androidannotations.org/)
Adds dependency injection and automates lots of plumbing and boilerplate related to activities and other elements of the Android framework, allowing for much cleaner and more concise code to be written all around. Has integration with OrmLite and Parceler.

### [Parceler](http://parceler.org/)
Allows annotating classes as @Parcel, eliminating the need to write boilerplate code to implement parcelables.

## Architecture considerations

* Thinking about scalability I added a layer of abstraction in that the project has "entity" classes which are intended to hold the direct results of deserealization of the API responses, which are then mapped to "value" classes, meant to be parcelable pojos to be used within the app, specially by the presentation layer. Although the entities would currently mostly fit the needs of the project, it would be very risky to assume this would still be the case as the project scaled. This approach also allows the same response to be mapped to different things if the need arises. For example we could have lightweight movie objects intended to be just items in a list and full movie objects intended to hold complete data for the details activity.

* Instead of having the details activity for a movie receive just a movie id and request the details to the api, I just pass a movie object from the list to it containing all the details. I choose this approach because in the current state of the requirements, even the data to be shown in the details activity is very lightweight and the movie objects used in the list can hold it. If the project grows and the idea mentioned above about having a different object to hold full movie data (possibly acquired through another api call) needs to be implemented, I don't think this approach needs to be changed. The details activity can quickly show the basic information contained in the movie object it receives from the list while it loads the rest of the data from the api. This might even improve the user experience.

* My preferred approach to the *Upcoming* list would be an infinite scrolling list (which wouldn't be infinite in this case, stopping at 50 items), fetching new pages from TMDb as you scrolled down, since results returned by the API have a hard constraint of 20 items per page. In fact I have this implemented in another local branch. However, TMDb order its results by primary release date, but returns the local release date for each item. This means page 2 could have entries with earlier release dates than some in page 1 and it would be impossible to correctly order the entries if I picked page 2 after page 1 is already being shown. I had to change my approach and use the uglier method of getting 3 pages at once, creating the list of all their results, ordering it and then keeping the first 50 records.

## Credits

<div>Icons made by <a href="http://www.flaticon.com/authors/simpleicon" title="SimpleIcon">SimpleIcon</a> from <a href="http://www.flaticon.com" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>