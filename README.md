# kotlin-multiplatform-affected-module-detector-sample

Since the [dropbox/AffectedModuleDetector](https://github.com/dropbox/AffectedModuleDetector) plugin is specialized for Android, it cannot support Kotlin Multiplaform, so it is a sample to modify the plugin and use it on Kotlin Multiplatform.

module-a -> module-b -> module-c

| module-a | module-b | module-c |
|---|---|---|
| `implementation(project(":module-b"))` | `implementation(project(":module-c"))` | |

When I change module-b we can skip build like `module-c:assembleRelease` , `module-c:linkDebugIos`.

You can check that log.

<summary>
<details>

```
$ ./gradlew runAffectedBuild -Paffected_module_detector.enable
> Task :module-a:preBuild UP-TO-DATE
> Task :module-a:preDebugBuild UP-TO-DATE
> Task :module-b:preBuild UP-TO-DATE
> Task :module-b:preDebugBuild UP-TO-DATE
> Task :module-c:preBuild UP-TO-DATE
> Task :module-c:preDebugBuild UP-TO-DATE
> Task :module-c:compileDebugAidl NO-SOURCE
> Task :module-b:compileDebugAidl NO-SOURCE
> Task :module-a:compileDebugAidl NO-SOURCE
> Task :module-a:mergeDebugJniLibFolders UP-TO-DATE
> Task :module-a:mergeDebugNativeLibs NO-SOURCE
> Task :module-a:stripDebugDebugSymbols NO-SOURCE
> Task :module-a:copyDebugJniLibsProjectAndLocalJars UP-TO-DATE
> Task :module-b:packageDebugRenderscript NO-SOURCE
> Task :module-a:compileDebugRenderscript NO-SOURCE
> Task :module-a:generateDebugBuildConfig UP-TO-DATE
> Task :module-a:generateDebugResValues UP-TO-DATE
> Task :module-a:generateDebugResources UP-TO-DATE
> Task :module-a:packageDebugResources UP-TO-DATE
> Task :module-a:parseDebugLocalResources UP-TO-DATE
> Task :module-a:processDebugManifest UP-TO-DATE
> Task :module-c:packageDebugRenderscript NO-SOURCE
> Task :module-b:compileDebugRenderscript NO-SOURCE
> Task :module-b:generateDebugResValues UP-TO-DATE
> Task :module-b:generateDebugResources UP-TO-DATE
> Task :module-b:packageDebugResources UP-TO-DATE
> Task :module-b:parseDebugLocalResources UP-TO-DATE
> Task :module-b:processDebugManifest UP-TO-DATE
> Task :module-c:compileDebugRenderscript NO-SOURCE
> Task :module-c:generateDebugResValues UP-TO-DATE
> Task :module-c:generateDebugResources UP-TO-DATE
> Task :module-c:packageDebugResources UP-TO-DATE
> Task :module-c:parseDebugLocalResources UP-TO-DATE
> Task :module-c:processDebugManifest UP-TO-DATE
> Task :module-c:generateDebugRFile UP-TO-DATE
> Task :module-b:generateDebugRFile UP-TO-DATE
> Task :module-a:generateDebugRFile UP-TO-DATE
> Task :module-b:generateDebugBuildConfig UP-TO-DATE
> Task :module-c:generateDebugBuildConfig UP-TO-DATE
> Task :module-c:compileDebugKotlinAndroid UP-TO-DATE
> Task :module-c:javaPreCompileDebug UP-TO-DATE
> Task :module-c:compileDebugJavaWithJavac UP-TO-DATE
> Task :module-c:bundleLibCompileToJarDebug UP-TO-DATE
> Task :module-b:compileDebugKotlinAndroid UP-TO-DATE
> Task :module-b:javaPreCompileDebug UP-TO-DATE
> Task :module-b:compileDebugJavaWithJavac UP-TO-DATE
> Task :module-b:bundleLibCompileToJarDebug UP-TO-DATE
> Task :module-a:compileDebugKotlinAndroid UP-TO-DATE
> Task :module-a:javaPreCompileDebug UP-TO-DATE
> Task :module-a:compileDebugJavaWithJavac UP-TO-DATE
> Task :module-a:extractDebugAnnotations UP-TO-DATE
> Task :module-a:mergeDebugGeneratedProguardFiles UP-TO-DATE
> Task :module-a:mergeDebugConsumerProguardFiles UP-TO-DATE
> Task :module-a:mergeDebugShaders UP-TO-DATE
> Task :module-a:compileDebugShaders NO-SOURCE
> Task :module-a:generateDebugAssets UP-TO-DATE
> Task :module-a:packageDebugAssets UP-TO-DATE
> Task :module-a:packageDebugRenderscript NO-SOURCE
> Task :module-a:prepareLintJarForPublish UP-TO-DATE
> Task :module-a:processDebugJavaRes NO-SOURCE
> Task :module-a:mergeDebugJavaResource UP-TO-DATE
> Task :module-a:syncDebugLibJars UP-TO-DATE
> Task :module-a:writeDebugAarMetadata UP-TO-DATE
> Task :module-a:bundleDebugAar UP-TO-DATE
> Task :module-a:compileDebugSources UP-TO-DATE

> Task :module-a:assembleDebug UP-TO-DATE
[AffectedTasksPlugin] configure: project ':module-a' task: assembleDebug filter:assembleDebug affected:true

> Task :module-a:preReleaseBuild UP-TO-DATE
> Task :module-b:preReleaseBuild UP-TO-DATE
> Task :module-c:preReleaseBuild UP-TO-DATE
> Task :module-c:compileReleaseAidl NO-SOURCE
> Task :module-b:compileReleaseAidl NO-SOURCE
> Task :module-a:compileReleaseAidl NO-SOURCE
> Task :module-a:mergeReleaseJniLibFolders UP-TO-DATE
> Task :module-a:mergeReleaseNativeLibs NO-SOURCE
> Task :module-a:stripReleaseDebugSymbols NO-SOURCE
> Task :module-a:copyReleaseJniLibsProjectAndLocalJars UP-TO-DATE
> Task :module-b:packageReleaseRenderscript NO-SOURCE
> Task :module-a:compileReleaseRenderscript NO-SOURCE
> Task :module-a:generateReleaseBuildConfig UP-TO-DATE
> Task :module-a:generateReleaseResValues UP-TO-DATE
> Task :module-a:generateReleaseResources UP-TO-DATE
> Task :module-a:packageReleaseResources UP-TO-DATE
> Task :module-a:parseReleaseLocalResources UP-TO-DATE
> Task :module-a:processReleaseManifest UP-TO-DATE
> Task :module-c:packageReleaseRenderscript NO-SOURCE
> Task :module-b:compileReleaseRenderscript NO-SOURCE
> Task :module-b:generateReleaseResValues UP-TO-DATE
> Task :module-b:generateReleaseResources UP-TO-DATE
> Task :module-b:packageReleaseResources UP-TO-DATE
> Task :module-b:parseReleaseLocalResources UP-TO-DATE
> Task :module-b:processReleaseManifest UP-TO-DATE
> Task :module-c:compileReleaseRenderscript NO-SOURCE
> Task :module-c:generateReleaseResValues UP-TO-DATE
> Task :module-c:generateReleaseResources UP-TO-DATE
> Task :module-c:packageReleaseResources UP-TO-DATE
> Task :module-c:parseReleaseLocalResources UP-TO-DATE
> Task :module-c:processReleaseManifest UP-TO-DATE
> Task :module-c:generateReleaseRFile UP-TO-DATE
> Task :module-b:generateReleaseRFile UP-TO-DATE
> Task :module-a:generateReleaseRFile UP-TO-DATE
> Task :module-b:generateReleaseBuildConfig UP-TO-DATE
> Task :module-c:generateReleaseBuildConfig UP-TO-DATE
> Task :module-c:compileReleaseKotlinAndroid UP-TO-DATE
> Task :module-c:javaPreCompileRelease UP-TO-DATE
> Task :module-c:compileReleaseJavaWithJavac UP-TO-DATE
> Task :module-c:bundleLibCompileToJarRelease UP-TO-DATE
> Task :module-b:compileReleaseKotlinAndroid UP-TO-DATE
> Task :module-b:javaPreCompileRelease UP-TO-DATE
> Task :module-b:compileReleaseJavaWithJavac UP-TO-DATE
> Task :module-b:bundleLibCompileToJarRelease UP-TO-DATE
> Task :module-a:compileReleaseKotlinAndroid UP-TO-DATE
> Task :module-a:javaPreCompileRelease UP-TO-DATE
> Task :module-a:compileReleaseJavaWithJavac UP-TO-DATE
> Task :module-a:extractReleaseAnnotations UP-TO-DATE
> Task :module-a:mergeReleaseGeneratedProguardFiles UP-TO-DATE
> Task :module-a:mergeReleaseConsumerProguardFiles UP-TO-DATE
> Task :module-a:mergeReleaseShaders UP-TO-DATE
> Task :module-a:compileReleaseShaders NO-SOURCE
> Task :module-a:generateReleaseAssets UP-TO-DATE
> Task :module-a:packageReleaseAssets UP-TO-DATE
> Task :module-a:packageReleaseRenderscript NO-SOURCE
> Task :module-a:processReleaseJavaRes NO-SOURCE
> Task :module-a:mergeReleaseJavaResource UP-TO-DATE
> Task :module-a:syncReleaseLibJars UP-TO-DATE
> Task :module-a:writeReleaseAarMetadata UP-TO-DATE
> Task :module-a:bundleReleaseAar UP-TO-DATE
> Task :module-a:compileReleaseSources UP-TO-DATE
> Task :module-a:mergeReleaseResources UP-TO-DATE
> Task :module-b:compileReleaseLibraryResources UP-TO-DATE
> Task :module-c:compileReleaseLibraryResources UP-TO-DATE
> Task :module-a:verifyReleaseResources UP-TO-DATE

> Task :module-a:assembleRelease UP-TO-DATE
[AffectedTasksPlugin] configure: project ':module-a' task: assembleRelease filter:assembleRelease affected:true

> Task :module-c:compileKotlinIosArm64 NO-SOURCE
> Task :module-b:compileKotlinIosArm64 NO-SOURCE
> Task :module-a:compileKotlinIosArm64 NO-SOURCE
> Task :module-a:iosArm64ProcessResources NO-SOURCE
> Task :module-a:iosArm64MainKlibrary UP-TO-DATE

> Task :module-a:compileTestKotlinIosArm64 NO-SOURCE
[AffectedTasksPlugin] configure: project ':module-a' task: compileTestKotlinIosArm64 filter:test affected:true

> Task :module-a:linkDebugTestIosArm64 NO-SOURCE
[AffectedTasksPlugin] configure: project ':module-a' task: linkDebugTestIosArm64 filter:linkDebugTestIos affected:true
[AffectedTasksPlugin] configure: project ':module-a' task: linkDebugTestIosArm64 filter:test affected:true

> Task :module-a:linkIosArm64 UP-TO-DATE
[AffectedTasksPlugin] configure: project ':module-a' task: linkIosArm64 filter:linkIos affected:true

> Task :module-c:compileKotlinIosX64 NO-SOURCE
> Task :module-b:compileKotlinIosX64 NO-SOURCE
> Task :module-a:compileKotlinIosX64 NO-SOURCE
> Task :module-a:iosX64ProcessResources NO-SOURCE
> Task :module-a:iosX64MainKlibrary UP-TO-DATE

> Task :module-a:compileTestKotlinIosX64 NO-SOURCE
[AffectedTasksPlugin] configure: project ':module-a' task: compileTestKotlinIosX64 filter:test affected:true

> Task :module-a:linkDebugTestIosX64 NO-SOURCE
[AffectedTasksPlugin] configure: project ':module-a' task: linkDebugTestIosX64 filter:linkDebugTestIos affected:true
[AffectedTasksPlugin] configure: project ':module-a' task: linkDebugTestIosX64 filter:test affected:true

> Task :module-a:linkIosX64 UP-TO-DATE
[AffectedTasksPlugin] configure: project ':module-a' task: linkIosX64 filter:linkIos affected:true

> Task :module-b:mergeDebugJniLibFolders UP-TO-DATE
> Task :module-b:mergeDebugNativeLibs NO-SOURCE
> Task :module-b:stripDebugDebugSymbols NO-SOURCE
> Task :module-b:copyDebugJniLibsProjectAndLocalJars UP-TO-DATE
> Task :module-b:extractDebugAnnotations UP-TO-DATE
> Task :module-b:mergeDebugGeneratedProguardFiles UP-TO-DATE
> Task :module-b:mergeDebugConsumerProguardFiles UP-TO-DATE
> Task :module-b:mergeDebugShaders UP-TO-DATE
> Task :module-b:compileDebugShaders NO-SOURCE
> Task :module-b:generateDebugAssets UP-TO-DATE
> Task :module-b:packageDebugAssets UP-TO-DATE
> Task :module-b:prepareLintJarForPublish UP-TO-DATE
> Task :module-b:processDebugJavaRes NO-SOURCE
> Task :module-b:mergeDebugJavaResource UP-TO-DATE
> Task :module-b:syncDebugLibJars UP-TO-DATE
> Task :module-b:writeDebugAarMetadata UP-TO-DATE
> Task :module-b:bundleDebugAar UP-TO-DATE
> Task :module-b:compileDebugSources UP-TO-DATE

> Task :module-b:assembleDebug UP-TO-DATE
[AffectedTasksPlugin] configure: project ':module-b' task: assembleDebug filter:assembleDebug affected:true

> Task :module-b:mergeReleaseJniLibFolders UP-TO-DATE
> Task :module-b:mergeReleaseNativeLibs NO-SOURCE
> Task :module-b:stripReleaseDebugSymbols NO-SOURCE
> Task :module-b:copyReleaseJniLibsProjectAndLocalJars UP-TO-DATE
> Task :module-b:extractReleaseAnnotations UP-TO-DATE
> Task :module-b:mergeReleaseGeneratedProguardFiles UP-TO-DATE
> Task :module-b:mergeReleaseConsumerProguardFiles UP-TO-DATE
> Task :module-b:mergeReleaseShaders UP-TO-DATE
> Task :module-b:compileReleaseShaders NO-SOURCE
> Task :module-b:generateReleaseAssets UP-TO-DATE
> Task :module-b:packageReleaseAssets UP-TO-DATE
> Task :module-b:processReleaseJavaRes NO-SOURCE
> Task :module-b:mergeReleaseJavaResource UP-TO-DATE
> Task :module-b:syncReleaseLibJars UP-TO-DATE
> Task :module-b:writeReleaseAarMetadata UP-TO-DATE
> Task :module-b:bundleReleaseAar UP-TO-DATE
> Task :module-b:compileReleaseSources UP-TO-DATE
> Task :module-b:mergeReleaseResources UP-TO-DATE
> Task :module-b:verifyReleaseResources UP-TO-DATE

> Task :module-b:assembleRelease UP-TO-DATE
[AffectedTasksPlugin] configure: project ':module-b' task: assembleRelease filter:assembleRelease affected:true

> Task :module-b:iosArm64ProcessResources NO-SOURCE
> Task :module-b:iosArm64MainKlibrary UP-TO-DATE

> Task :module-b:compileTestKotlinIosArm64 NO-SOURCE
[AffectedTasksPlugin] configure: project ':module-b' task: compileTestKotlinIosArm64 filter:test affected:true

> Task :module-b:linkDebugTestIosArm64 NO-SOURCE
[AffectedTasksPlugin] configure: project ':module-b' task: linkDebugTestIosArm64 filter:linkDebugTestIos affected:true
[AffectedTasksPlugin] configure: project ':module-b' task: linkDebugTestIosArm64 filter:test affected:true

> Task :module-b:linkIosArm64 UP-TO-DATE
[AffectedTasksPlugin] configure: project ':module-b' task: linkIosArm64 filter:linkIos affected:true

> Task :module-b:iosX64ProcessResources NO-SOURCE
> Task :module-b:iosX64MainKlibrary UP-TO-DATE

> Task :module-b:compileTestKotlinIosX64 NO-SOURCE
[AffectedTasksPlugin] configure: project ':module-b' task: compileTestKotlinIosX64 filter:test affected:true

> Task :module-b:linkDebugTestIosX64 NO-SOURCE
[AffectedTasksPlugin] configure: project ':module-b' task: linkDebugTestIosX64 filter:linkDebugTestIos affected:true
[AffectedTasksPlugin] configure: project ':module-b' task: linkDebugTestIosX64 filter:test affected:true

> Task :module-b:linkIosX64 UP-TO-DATE
[AffectedTasksPlugin] configure: project ':module-b' task: linkIosX64 filter:linkIos affected:true

> Task :module-c:mergeDebugJniLibFolders UP-TO-DATE
> Task :module-c:mergeDebugNativeLibs NO-SOURCE
> Task :module-c:stripDebugDebugSymbols NO-SOURCE
> Task :module-c:copyDebugJniLibsProjectAndLocalJars UP-TO-DATE
> Task :module-c:extractDebugAnnotations UP-TO-DATE
> Task :module-c:mergeDebugGeneratedProguardFiles UP-TO-DATE
> Task :module-c:mergeDebugConsumerProguardFiles UP-TO-DATE
> Task :module-c:mergeDebugShaders UP-TO-DATE
> Task :module-c:compileDebugShaders NO-SOURCE
> Task :module-c:generateDebugAssets UP-TO-DATE
> Task :module-c:packageDebugAssets UP-TO-DATE
> Task :module-c:prepareLintJarForPublish UP-TO-DATE
> Task :module-c:processDebugJavaRes NO-SOURCE
> Task :module-c:mergeDebugJavaResource UP-TO-DATE
> Task :module-c:syncDebugLibJars UP-TO-DATE
> Task :module-c:writeDebugAarMetadata UP-TO-DATE
> Task :module-c:bundleDebugAar UP-TO-DATE
> Task :module-c:compileDebugSources UP-TO-DATE

> Task :module-c:assembleDebug SKIPPED
[AffectedTasksPlugin] configure: project ':module-c' task: assembleDebug filter:assembleDebug affected:false

> Task :module-c:mergeReleaseJniLibFolders UP-TO-DATE
> Task :module-c:mergeReleaseNativeLibs NO-SOURCE
> Task :module-c:stripReleaseDebugSymbols NO-SOURCE
> Task :module-c:copyReleaseJniLibsProjectAndLocalJars UP-TO-DATE
> Task :module-c:extractReleaseAnnotations UP-TO-DATE
> Task :module-c:mergeReleaseGeneratedProguardFiles UP-TO-DATE
> Task :module-c:mergeReleaseConsumerProguardFiles UP-TO-DATE
> Task :module-c:mergeReleaseShaders UP-TO-DATE
> Task :module-c:compileReleaseShaders NO-SOURCE
> Task :module-c:generateReleaseAssets UP-TO-DATE
> Task :module-c:packageReleaseAssets UP-TO-DATE
> Task :module-c:processReleaseJavaRes NO-SOURCE
> Task :module-c:mergeReleaseJavaResource UP-TO-DATE
> Task :module-c:syncReleaseLibJars UP-TO-DATE
> Task :module-c:writeReleaseAarMetadata UP-TO-DATE
> Task :module-c:bundleReleaseAar UP-TO-DATE
> Task :module-c:compileReleaseSources UP-TO-DATE
> Task :module-c:mergeReleaseResources UP-TO-DATE
> Task :module-c:verifyReleaseResources UP-TO-DATE

> Task :module-c:assembleRelease SKIPPED
[AffectedTasksPlugin] configure: project ':module-c' task: assembleRelease filter:assembleRelease affected:false

> Task :module-c:iosArm64ProcessResources NO-SOURCE
> Task :module-c:iosArm64MainKlibrary UP-TO-DATE

> Task :module-c:compileTestKotlinIosArm64 SKIPPED
[AffectedTasksPlugin] configure: project ':module-c' task: compileTestKotlinIosArm64 filter:test affected:false

> Task :module-c:linkDebugTestIosArm64 SKIPPED
[AffectedTasksPlugin] configure: project ':module-c' task: linkDebugTestIosArm64 filter:linkDebugTestIos affected:false

> Task :module-c:linkIosArm64 SKIPPED
[AffectedTasksPlugin] configure: project ':module-c' task: linkIosArm64 filter:linkIos affected:false

> Task :module-c:iosX64ProcessResources NO-SOURCE
> Task :module-c:iosX64MainKlibrary UP-TO-DATE

> Task :module-c:compileTestKotlinIosX64 SKIPPED
[AffectedTasksPlugin] configure: project ':module-c' task: compileTestKotlinIosX64 filter:test affected:false

> Task :module-c:linkDebugTestIosX64 SKIPPED
[AffectedTasksPlugin] configure: project ':module-c' task: linkDebugTestIosX64 filter:linkDebugTestIos affected:false

> Task :module-c:linkIosX64 SKIPPED
[AffectedTasksPlugin] configure: project ':module-c' task: linkIosX64 filter:linkIos affected:false

> Task :runAffectedBuilds UP-TO-DATE

BUILD SUCCESSFUL in 1s
135 actionable tasks: 135 up-to-date
```
</details>
</summary>