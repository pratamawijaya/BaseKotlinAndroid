
# Base Android Project with Kotlin
MVVM Base Android Kotlin Project

[![build](https://github.com/pratamawijaya/BaseKotlinAndroid/actions/workflows/build.yaml/badge.svg)](https://github.com/pratamawijaya/BaseKotlinAndroid/actions/workflows/build.yaml)

[![codecov](https://codecov.io/gh/pratamawijaya/BaseKotlinAndroid/branch/master/graph/badge.svg)](https://codecov.io/gh/pratamawijaya/BaseKotlinAndroid)

<img src="ss/ss1.png" width="250">

## Tech Stack
- Kotlin
- AndroidX
- Coroutine
- Retrofit / OkHttp
- Gson
- Groupie as Recyclerview lib
- sdp ssp
- Hilt as Dependency Injection lib
- Picasso as Image loader

## Extra
- `./gradlew detekt` static analysis with detekt
- `./gradlew testDebugUnitTestCoverage` for codeCoverage
- `./gradlew check` for running all

## How to use
- Register and create your api key here https://newsapi.org/register
- Copy secrets.properties.example to secrets.properties and put ur API KEY