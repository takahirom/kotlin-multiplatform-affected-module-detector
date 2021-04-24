package com.github.takahirom.affectedmoduledetector

import com.dropbox.affectedmoduledetector.AffectedModuleConfiguration
import com.dropbox.affectedmoduledetector.AffectedModuleDetector
import com.dropbox.affectedmoduledetector.AffectedTestConfiguration
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import java.util.*

open class AffectedTasks {
    val testTasks = listOf(
        "test",
    )
    val buildTasks = listOf(
        "assembleRelease",
        "assembleDebug",
        "linkDebugTestIos",
        "linkIos",
        "linkIosX64",
        "linkIosArm64",
        "linkReleaseFrameworkIosArm64",
        "linkDebugFrameworkIosArm64",
        "linkReleaseFrameworkIosX64",
        "linkDebugFrameworkIosX64",
    )

    companion object {
        var name = "affectedTasks"
    }
}

class AffectedTasksPlugin : Plugin<Project> {
    lateinit var affectedTasks: AffectedTasks
    override fun apply(project: Project) {
        project.extensions.add(
            AffectedModuleConfiguration.name,
            AffectedModuleConfiguration()
        )
        project.extensions.add(AffectedTasks.name, AffectedTasks())
        affectedTasks = requireNotNull(
            project.extensions.findByName(AffectedTasks.name)
        ) as AffectedTasks

        project.subprojects { subproject ->
            subproject.extensions.add(
                AffectedTestConfiguration.name,
                AffectedTestConfiguration()
            )
            configureTaskGuard(subproject, affectedTasks.buildTasks)
            configureTaskGuard(subproject, affectedTasks.testTasks)
        }

        project.afterEvaluate {
            val rootProject = project.rootProject
            AffectedModuleDetector.configure(project.gradle, project)

            registerAffectedTask(
                "runAffectedUnitTests",
                affectedTasks.testTasks,
                rootProject,
            )

            registerAffectedTask(
                "runAffectedBuilds",
                affectedTasks.buildTasks,
                rootProject,
            )
        }
    }

    private fun registerAffectedTask(
        taskName: String,
        taskNamesToRun: List<String>,
        rootProject: Project,
    ): Task {
        val task = rootProject.tasks.register(taskName) { task ->
            val paths = taskNamesToRun.flatMap {
                getAffectedPaths(it, rootProject)
            }
            paths.forEach { path ->
                task.dependsOn(path)
            }
            task.enabled = paths.isNotEmpty()
            task.onlyIf { paths.isNotEmpty() }
        }
        return task.get()
    }

    private fun getAffectedPaths(
        task: String,
        rootProject: Project
    ):
            Set<String> {
        val paths = LinkedHashSet<String>()
        rootProject.subprojects { subproject ->
            val pathName = "${subproject.path}:$task"
            if (AffectedModuleDetector.isProjectProvided(subproject)) {
                if (subproject.tasks.findByPath(pathName) != null) {
                    paths.add(pathName)
                }
            }
        }
        return paths
    }

    private fun configureTaskGuard(project: Project, tasks: List<String>) {
        project.tasks.all { task ->
            val foundTaskFilter = tasks.firstOrNull {
                task.name.contains(
                    it,
                    true
                )
            }
            if (foundTaskFilter != null) {
                task.onlyIf {
                    val projectAffected = AffectedModuleDetector.isProjectAffected(task.project)

                    println(
                        "[AffectedTasksPlugin] " +
                                "configure: ${task.project} " +
                                "task: ${task.name} " +
                                "filter:$foundTaskFilter " +
                                "affected:$projectAffected"
                    )
                    projectAffected
                }
            }
        }
    }
}