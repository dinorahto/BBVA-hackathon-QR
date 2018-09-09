# BBVA Hackathon  | Android #

The goal of iQR is to make payments inside small stores with a more concise, pleasant, and simple way to pay.

This project has the primary implementation of the source code for the iQR Android, for more information about the backend implementation please send an email to: jose.guzman@minttcode.com
The project has being writed in Kotlin, for more information about Kotlin, please check the link below: https://kotlinlang.org
This repository is no longer accepting pull requests from outsiders to this project.

Development Environment 
==========

The app is written entirely in Kotlin and uses the Gradle build system.
To build the app, use the gradlew build command or use "Import Project" in Android Studio. A stable version >= 3.1 of Android Studio is required.

Minimal and Basic knowledge Requirements
=======

* Android SDK Minimal Version 18
* Implementing Retrofit, Dagger, Mobile Vision from Google, Realm, Kotlin and Kotlin Extensions, Architecture Components and Android KTX 
* Kotlin: https://github.com/JetBrains/kotlin
* Permissions required:

```
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

Basic Knowledge
=======

This is the basic knowledge need it for this project

| Libraries                 | Info                                                                          | URL                                                          |
|---------------------------|-------------------------------------------------------------------------------|--------------------------------------------------------------|
|       Base de datos       |                                                                               |                                                              |
| Realm                     | Database for local changes                                                    | https://realm.io/docs/java/latest                            |
|             UI            |                                                                               |                                                              |
| Picasso                   | A image downloading and caching library                                       | http://square.github.io/picasso/                             |
| RxJava                    | Reactive Extensions for Java                                                  | https://github.com/ReactiveX/RxJava                          |
| Dagger 2                  | A fast dependency injector for Android                                        | http://square.github.io/dagger/                              |
|         Networking        |                                                                               |                                                              |
| Gson y OkHttp             | Networking library for HTTP requests                                          | https://github.com/codepath/android_guides/wiki/Using-OkHttp |
| Retrofit                  | Rest type-safe client for Android                                             | https://github.com/square/retrofit                           |
|           Kotlin          |                                                                               |                                                              |
| Android KTX               | Extensions for Android development                                            | https://github.com/android/android-ktx                       |
| Architectural Componentes | Components to manage the lifecycle events of the activities and fragments     | https://github.com/googlesamples/android-architecture        |

Architecture
=======

The architecture inside this application has two parts, the presentation layer, the one that handles the views, the listener for the clicks, scrolls views and all the interactions with the user, this part can be implement over MVP (Model View Presenter). Soon to be MVVM (Model View ViewModel) for the last one, a personal recomendation is to check out the Architectural Components that Google has provided, you can found it here: https://developer.android.com/topic/libraries/architecture/index.html (now stable), we are moving all the architecture to MVVM as Google recomendation in the next week, so please check this project to get a enormous view of the development we are hedding https://github.com/google/iosched
For MVP (Model View Presenter) We are making a simple recomendation that every Android Developer must follow, don't make implementation of the business logic inside the Activities and Fragments, do it in the Presenter and make the connection with a Interface to speak to the Models. If you are using MVVM you can archive the Presenter-Interface model using a LiveData and ViewModels object from the Architectural components.

![Image of Presentation Layer](https://raw.githubusercontent.com/MinttcodeCo/GetStartedWithKotlinDagger/master/Images/presentationlayer.jpg)

The last element of the architecture is the Data Layer part, is in charge of the web server connection and the local databases, everything in this part is implemented as a secondary module from the project, making it simple for testing, the Data Layer is divided in two, after the repository, which mean we have a Local repository and a Remote Repository. 
Local Repository: handle the writting and reading of the databases.
Remote Repository: web services connections and parsing objects of the response. We recommend the use of Observables for this part
Take in consideration that you can have as many modules as posible if you need it, so you can separate the Login Repository form the Checkout Repository.

![Image of DataLayer](https://raw.githubusercontent.com/MinttcodeCo/GetStartedWithKotlinDagger/master/Images/datalayer.jpg)

This application is using LifecycleObserver as part of the management for the lifecycle of the activities and Lifecycle.Event to show special views over the lifecycle, so please make sure you understand that part before getting started https://developer.android.com/reference/android/arch/lifecycle/LifecycleObserver
We are making the the network monitoring with ConnectivityLiveData.

How to Contribute
=======
We welcome your contributions to this project. There are various ways to contribute:

**Reporting issues**

Help improve the project by reporting issues that you find by filing a new issue here in this repository

**Features suggestions**

You can also add feature suggestions by filing a new issue here

**Documentation**

You can help by adding or improving existing documentation. Simply send us a pull request for us to consider your proposed changes. 
If you are writing or changing any functions please make sure the documentatios is aligned

**Bug fixes**

Pull requests are welcome for minor bug fixes that do not involve any changes to existing API. These changes should ideally be accompanied by a test case that would have otherwise failed without the fix.

**New API or API changes**

Pull requests for new APIs or changes to existing APIs are welcome, but may require a bit of
discussion. Consider creating an issue to discuss any changes before you implement the change. Please make sure you have all your build tools and SDK downloaded, right now our target is from API 18 (JellyBean 4.3) to API 28 (Android P)

**Android Pie and backwards compatibility**

Make sure every code you wrote is works in versions 18 equaly as API 28. 


Before submitting
=======

* Check that lint, unit tests, and code style enforcement all pass by running `./gradlew check`. Use Lint to check the warning and errors you code can have, clean code is a happy code, Android studio counts with a Lint processor and analyzer, so please refer yourself to this link: (https://developer.android.com/studio/write/lint.html)
* Check that instrumentation tests pass by starting an API 26 or newer emulator and running
`./gradlew connectedCheck` 
* Make comments in every function that you are implementing
```
/**
* Created by Dinorah Tovar on 11/29/17.
* Application class that initializes Fabric, Realm and Dagger
*/

@Module
class MyApplication : Application {

private var mApplication: Application? = null
/**
* Companion Object with static objects
*/
companion object {
var mAppComponent: AppComponent? = null
}

/**
* Empty constructor
*/
constructor()

/**
* Constructor with Application
* @param mApplication the application
*/
constructor(mApplication: Application) {
this.mApplication = mApplication
}
```

* Every method that was implemented in the Presentation Layer, must be tested, at least the Presenter of every Activity and every Fragment, if they dont have it, the pull will be rejected.
* Please make sure you are covering the coverage of every class, testing is important for everyone. 
* Every method must be clean and sharpe, we don't need any boilerplate, so please make code readable
* Every developer should make merge to the Master branch, everyday if this changes does not going to be on testing
* Every pull request need to be reviewed by: gerardo.ramirez@bcgdvlabs.com francisco.bartilotti@bcgdvlabs.com or dinorah.tovar@bcgdvlabs.com
* The last stable QA version should be on Beta by Crashlytics, the only persons available to do this release are gerardo.ramirez@bcgdvlabs.com francisco.bartilotti@bcgdvlabs.com or dinorah.tovar@bcgdvlabs.com
* Production version are tagged, please don't keep branchs hanging out during this project 


Handle of this project
=======

In order to use this project, please follow the next steps for Kotlin development:

1. If you are using Android 3.0 or later, the project will be inserting by all ends the necesary plugins for Koltin
2. If not, you will require using the plugin of JetBrains, to solve Kotlin, you can found it inside Preferences -> Plugins and select Kotlin plugin
3. Then, you can simple import this project and you are ready to go
4. If you found a problem trying to run this, please make a "Sync Gradle with Gradle Files" or a "Invalidate Cache & Restart" build, could be a cache problem
5. If after this, you are still facing trouble please send an email to gerardo.ramirez@bcgdvlabs.com

Documentation
=======
You are almost there! All the code for these library projects is based on:

1. Android Extensions: https://kotlinlang.org/docs/tutorials/android-plugin.html
2. LifecycleObserver: https://developer.android.com/reference/android/arch/lifecycle/LifecycleObserver
3. Retrofit: http://square.github.io/retrofit/
4. Rx Java: https://github.com/ReactiveX/RxJava
5. Dagger: http://square.github.io/dagger/

Contact
=======
If you need help, We are always happy to help, you can found us here:
Email: dinorahtovar@minttcode.com

Reading list
=======
If you need more information please, I will invite you to read the next Medium Post
https://medium.com/knowing-android/recyclerview-with-two-holders-320fc83759c0
https://medium.com/knowing-android/getting-started-with-kotlin-dagger-b3f07a6e2a9f
https://medium.com/knowing-android/animaciones-y-transiciones-en-android-parte-1-3bd2d2e9625d

License
=======
Copyright 2018 Walmart Labs

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0




