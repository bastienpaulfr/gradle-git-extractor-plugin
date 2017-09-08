package fr.coppernic.gradle.git.extractor.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction;

/**
 * <p>Created on 07/09/17
 *
 * @author bastien
 */
public class ExtractSourcesFromGitInternalTask extends DefaultTask {

    static final boolean DEBUG = true

    File outputDir
    File gitDir

    @OutputDirectory
    public File getOutputDir(){
        return this.outputDir
    }

    @Input
    public File getGitDir(){
        return this.gitDir
    }

    @TaskAction
    public void action() {
        if(gitDir == null){
            gitDir = project.projectDir
        }

        if(executeOnShell("git checkout-index -a -f --prefix=$outputDir/", gitDir)){
            throw new GradleException("Git checkout index failed")
        }
    }

    private static def executeOnShell(String command, File workingDir) {
        println command
        def process = new ProcessBuilder(addShellPrefix(command))
                .directory(workingDir)
                .redirectErrorStream(true)
                .start()
        if(DEBUG) {
            process.inputStream.eachLine { println it }
        }
        process.waitFor();
        return process.exitValue()
    }

    private static def addShellPrefix(String command) {
        def commandArray = new String[3]
        commandArray[0] = "sh"
        commandArray[1] = "-c"
        commandArray[2] = command
        return commandArray
    }
}

