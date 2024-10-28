# OEXFoundation Integration

**OEXFoundation** is a core library providing essential dependencies, extensions, utilities, and interfaces for
building plugins for the OpenEdX ecosystem. This library allows developers to extend the functionality of OpenEdX by
creating plugins that integrate seamlessly into the OpenEdX platform.

### Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2: Installation

To add OEXFoundation to your project, include the following dependency in your `build.gradle` file:

```groovy
implementation 'com.github.openedx:openedx-app-foundation-android:1.0.0'
```

### Step 3: Provide your implementation for OEXFoundation abstraction

```kotlin
class YourPlugin : OneOfOEXFoundationInterfaces()
```

### Step 4: Plugin publishing

You can build and publish your plugin to any repository that suits your needs, such as mavenLocal, mavenCentral,
JitPack, or Nexus.

### Step 5: Initialize in OpenEdXApp

Next, include your plugin dependency in app module `build.gradle` file and update the `initPlugins` function in
your `OpenEdXApp` class to include your plugin class. Here’s how to do it:

```kotlin
private fun initPlugins() {
    pluginManager.addPlugin(YourPlugin())
}
```

Now, PluginManager will handle the integration of your plugin.
