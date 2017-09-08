Gradle Git Extractor Plugin [![Build Status](https://travis-ci.org/bastienpaulfr/gradle-git-extractor-plugin.svg?branch=master)](https://travis-ci.org/bastienpaulfr/gradle-git-extractor-plugin) [![Gradle Status](https://gradleupdate.appspot.com/int128/gradle-git-extractor-plugin/status.svg?branch=master)](https://gradleupdate.appspot.com/int128/gradle-git-extractor-plugin/status)
============================

This plugin for gradle is simply adding a task to extract files from a git repository

Features
--------

This contains following features:

  * Task `ExtractSourcesFromGitInternalTask`

This task is doing more or less the same than : `git checkout-index -a -f --prefix=$prefix`

Getting Started
---------------

**build.gradle**

```
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath 'fr.coppernic.gradle.plugin:git-extractor:0.0.2'
    }
}


import fr.coppernic.gradle.git.extractor.tasks.ExtractSourcesFromGitInternalTask


task extractSourcesFromGit(type: ExtractSourcesFromGitInternalTask) {
    gitDir = project.projectDir
    outputDir = new File(project.buildDir, "sources/git")
}

```

Then run task

```
./gradlew extractSourcesFromGit
```

Contributions
-------------

This is an open source software licensed under the Apache License Version 2.0.
Feel free to open issues or pull requests.
