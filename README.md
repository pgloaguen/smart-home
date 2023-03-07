# Smart Home
<table>
  <tr>
    <th>MinSdk</th>
    <td>21</td>
  </tr>
  <tr>
    <th>TargetSdk</th>
    <td>33</td>
  </tr>
   <tr>
    <th>AGP</th>
    <td>7.4.2</td>
  </tr>
   <tr>
    <th>Kotlin version</th>
    <td>1.8.0</td>
  </tr>
</table>


## Architecture
I decided to use clean architecture to have a clear separation of concerns and to have a scalable project.

The project is divided in 3 modules:
* **app**: The APP module, is the main module of the project, it contains the views, the view models and the navigation.
* **domain**: The domain module, contains the business logic of the project, the use cases and define the repositories interface.
* **data**: The data module, contains the implementation of the repositories interfaces. It is responsible of data retrieval and data storage.

## Libraries
* **Koin**: for dependency injection. I decided to use this library over hilt because it is more lightweight for a sample project and to use a fully kotlin solution.
* **kotlinx.coroutines**: for asynchronous programming.
* **appcompat**: for compatibility with older versions of Android.
* **material**: for using material design components.
* **constraintlayout**: for building my layouts.
* **recyclerview**: for displaying lists of data.
* **lifecycle**: for helping to manage lifecycle events.
* **navigation**: for simplifying navigation between fragments.
* **viewmodel**: for managing UI-related data in a lifecycle conscious way.
* **ktor**: for the api call, in this sample I decided to use this library to handle the network requests to have a fully kotlin solution.
* **kotlinx.serialization**: for serializing my DTO, I used this library because it is the official serialization library for Kotlin.

## Features
- [x] Initialize project
- [x] Introduce the list of devices screen
- [x] Filter the list of devices
- [x] Delete device with a swipe
- [x] Introduce the device steering screen
- [ ] Introduce the account fragment


