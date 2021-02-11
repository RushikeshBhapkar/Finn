# Finn Marketplace App
<table>
<tr>
<td>
This is the android application which shows the advertisements from remote source. Application have following features
1. Display advertisements
2. Bookmark advertisements
3. Display advertisement in offline mode 
</td>
</tr>
</table>

## Application Architecture
![](https://raw.githubusercontent.com/RushikeshBhapkar/Finn/master/screenshots/Final_Architecture.png)

In this project I have used some of the best practices in Android Development suggested by Google

- ViewModel
- LiveData : Observable data holder
- Hilt (for dependency injection)
- Kotlin Coroutines : For background operations
- Retrofit : 
- Room : Local data base
- Navigation

## Project Structure
![](https://raw.githubusercontent.com/RushikeshBhapkar/Finn/master/screenshots/Project_Structure.png)

## Application performance

![](https://raw.githubusercontent.com/RushikeshBhapkar/Finn/master/screenshots/Application_Performence.png)

## Demo
Here is a recorded demo :  https://github.com/RushikeshBhapkar/Finn/blob/master/App_Performance/Application_Performamce.mp4

# Application screenshots
### Splash Screen
![](https://raw.githubusercontent.com/RushikeshBhapkar/Finn/master/screenshots/Splash_Screen.png)

### Home Screen
![](https://raw.githubusercontent.com/RushikeshBhapkar/Finn/master/screenshots/Landing_Screen.png)

### Bookmark And Offline support
![](https://raw.githubusercontent.com/RushikeshBhapkar/Finn/master/screenshots/Offline_Support.png)

## To-do
- Details screen for advertisement
- Test-cases
- Service and database query optimisation when integrate it with actual services
- Pagination 

## References 

- [Android Developer Guide to app architecture](https://developer.android.com/jetpack/guide) - Best pracices and recommended architecture for building robust, production-quality applications
- [Using Dagger in Android apps](https://developer.android.com/training/dependency-injection/dagger-android )
- [Save data in a local database using Room](https://developer.android.com/training/data-storage/room )
