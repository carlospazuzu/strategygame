#!/bin/bash

./gradlew assembleDebug
# ./gradlew installDebug

adb uninstall com.strategygame
adb -d install android/build/outputs/apk/debug/android-debug.apk

adb shell am start -n com.strategygame/com.strategygame.AndroidLauncher
