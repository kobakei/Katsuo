# Katsuo

This is a sample that adopted the latest trend in Android development.

This app is supposed to be the official application of the news site. This app holds timeline screen, article screen, and author's screen.

## Architecture

The architecture of this application mainly follows the following three items.

- Multi modules
- MVVM
- Layered architecture

This application does not adopt Flux or Redux. In applications where state management is not complicated, I think they are over-engineering.

### Modules

- app
  - Module with Application class or DI related class
- ui/timeline, ui/author, ui/detail
  - Module of each feature
- ui/router
  - Module of router interfaces. The implementations of routers are in each feature module.
- ui/common
  - Module of classes and extension functions commonly used by other UI modules
- data/repository
  - Module of repository classes
- data/api
  - Module of API client
- data/database
  - Module of database
- data/entity
  - Module of entity classes

## Libraries

I am adopting libraries that are standard in Android development and widely used as much as possible.
In addition, I try to use what Kotlin's language function provides as much as possible.

- Kotlin
  - Coroutines
  - Serialization
  - Parcelize
- Android Jetpack
  - Data binding
  - Lifecycle & ViewModel
  - LiveData
  - KTX
  - Room
    - Coroutines support
- Koin
- OkHttp
- Retrofit
  - Kotlin coroutines adapter
  - KotlinX serialization converter
- Glide
- Timber
- Stetho
  
## License

```
Copyright 2019-present Keisuke Kobayashi

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
