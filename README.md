For this projects I have used following Architecture Components :

UI Controllers
are activities or fragments. The only job of UI controllers is to know how to display data and pass on UI events, such as the user pressing a button. UI Controllers neither contain the UI data, nor directly manipulate data.

ViewModels and LiveData
These classes represent all of the data needed for the UI to display. 

Repository
This class is the single source of truth for all of our app's data and acts as a clean API for the UI to communicate with. ViewModels simply request data from the repository. They do not need to worry about whether the repository should load from the database or network, or how or when to persist the data. The repository manages all of this. As part of this responsibility, the repository is a mediator between the different data sources.

Model - Manages local data stored in the database.

Used libraries:
Live data, 
RxAndroid, 
Room, 
Retrofit2, 
Android architecture, components, 
Room, 
Junit.
