### Catalog Movie

Adalah aplikasi pencari Movie yang terintegrasi dengan api.themoviedb.org

### Fitur

1. Search Movie
2. Upcoming Movie
3. Now Playing Movie
4. Favorite
5. Dayli Reminder
6. Upcoming Movie Reminder
7. widget Movie Favorit


### Screenshots

<img src="https://github.com/CF75/CatalogMovie/blob/master/Screenshots/1.png" width="216" height="384">
<img src="https://github.com/CF75/CatalogMovie/blob/master/Screenshots/2.png" width="216" height="384">
<img src="https://github.com/CF75/CatalogMovie/blob/master/Screenshots/3.png" width="216" height="384">
<img src="https://github.com/CF75/CatalogMovie/blob/master/Screenshots/4.png" width="216" height="384">
<br/>

### Getting started
1. Install android Studio, Download [disini](https://developer.android.com/sdk/index.html)
2. Clone project :
> git clone https://github.com/CF75/CatalogMovie.git
3. buka project dari android studio
4. Tunggu hingga android studio selesai building
5. lanjut ke proses konfigurasi

### Configuration

1. daftar [disni](https://www.themoviedb.org/documentation/api) untuk mendapatkan API KEY
2. Buka build.gradle
3. Ganti Your API Key dengan API KEY kamu

>
	buildTypes {
        	release {
        	    minifyEnabled false
        	    proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        	}
        	buildTypes.each {
        	    it.buildConfigField 'String', 'API_KEY', '"YOUR API KEY"'
        	    it.buildConfigField 'String', 'URL_POSTER', '"http://image.tmdb.org/t/p/w185"'
        	}
    	}
    

4. Run Aplikasi


### Url TheMoviedb

1. Search Movie

* api.themoviedb.org/3/search/movie?api_key="Your API Key"&language=en-US&query="Movie Name"

2. Upcoming Movie

* api.themoviedb.org/3/movie/upcoming?api_key="Your Api Key"&language=en-US

3. Now Playing Movie

* api.themoviedb.org/3/movie/now_playing?api_key="Your Api Key"&language=en-US

4. Detail Movie

* api.themoviedb.org/3/movie/"Movie ID"?api_key="Your API Key"&language=en-US

5. Poster Movie 

* image.tmdb.org/t/p/"Poster Size"/"Poster Path"
* Poster Size : w92, w154, w185, w342, w500, w780, and original


### library

* [retrofit](https://github.com/square/retrofit)

* [glide](https://github.com/bumptech/glide)

* [butterknife](https://github.com/JakeWharton/butterknife)

