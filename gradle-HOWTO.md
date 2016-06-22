
# Building with Gradle
This Project uses gradle for automatic testing, building and releasing. Current version used is gradle 2.14. Any tasks for the gradle binary to execute are defined in [TDD-Trainer/build.gradle](TDD-Trainer/build.gradle). Executing these tasks requires a gradle setup. Consider getting one [here](http://gradle.org/gradle-download/) or see [Gradle Wrapper](#gradle-wrapper)-Section below.

## Execution
Execution of the gradle routine is very simple. Just type `gradle build` and watch the console for output :wink: Make sure, your `pwd` is the `TDD-Trainer`-Folder.

## Gradle Wrapper
This project ships with a gradle wrapper, `gradlew`. `gradlew` is a standalone binary, that automatically provides you with a working gradle setup if you didn't install any yet. It'll download the necessary gradle files and libraries, and execute any gradle command like a normal gradle installation would. Simply type `gradlew` instead of `gradle`, for example `gradlew build`. Again, `cd TDD-Trainer` is neccessary before.

Tip (Linux only): If `gradlew` doesn't execute because of a permission error, make sure it is marked as executable in your file system. Type `chmod a+x gradlew` in the `TDD-Trainer`-folder to set this.
