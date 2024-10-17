# OEXFoundation Integration

**OEXFoundation** is a core library providing essential dependencies, extensions, utilities, and interfaces for
building plugins for the OpenEdX ecosystem. This library allows developers to extend the functionality of OpenEdX by
creating plugins that integrate seamlessly into the OpenEdX platform.

### Step 1: Installation

To add OEXFoundation to your project, include the following dependency in your `build.gradle` file:

```groovy
implementation("org.openedx:foundation:1.0")
```

### Step 2: Implementing Interfaces from OEXFoundation

```kotlin
class YourPlugin : OneOfOEXFoundationInterfaces()
```

### Step 3: Initialize in OpenEdXApp

Next, update the `initPlugins` function in your `OpenEdXApp` class to include your plugin class. Here’s how to do
it:

```kotlin
private fun initPlugins() {
    pluginManager.addPlugin(YourPlugin())
}
```

Now, PluginManager will handle the integration of your plugin.
