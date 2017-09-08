import org.gradle.testkit.runner.GradleRunner
import spock.lang.Specification
import spock.lang.Unroll

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

class AcceptanceSpec extends Specification {

    @Unroll
    def 'acceptance test should pass on Gradle #gradleVersion'() {
        given:
        def runner = GradleRunner.create()
            .withProjectDir(new File('fixture'))
            .withArguments('test')
            .withPluginClasspath()
            .withGradleVersion(gradleVersion)

        when:
        def result = runner.build()

        then:
        noExceptionThrown()
        result.task(":test").outcome == SUCCESS

        where:
        gradleVersion << [System.getProperty('current.gradle.version'), '3.5']
    }


    def 'extract tasks'() {
        given:
        def runner = GradleRunner.create()
                .withProjectDir(new File('fixture'))
                .withArguments('clean','extract', '--stacktrace')
                .withPluginClasspath()
                .withGradleVersion(gradleVersion)

        when:
        def result = runner.build()

        then:
        noExceptionThrown()
        println result.output
        result.task(":extract").outcome == SUCCESS
        assert new File(runner.projectDir, "build/coppernic/test/acceptance-test/fixture/build.gradle").exists()
        assert new File(runner.projectDir, "build/coppernic/test/acceptance-test/fixture/filetoignore.txt").exists()
        assert new File(runner.projectDir, "build/coppernic/test/acceptance-test/fixture/filetoinclude.txt").exists()

        where:
        gradleVersion << [System.getProperty('current.gradle.version'), '3.5']
    }

    def 'copy extracted files'() {
        given:
        def runner = GradleRunner.create()
                .withProjectDir(new File('fixture'))
                .withArguments('clean','copy', '--stacktrace')
                .withPluginClasspath()
                .withGradleVersion(gradleVersion)

        when:
        def result = runner.build()

        then:
        noExceptionThrown()
        println result.output
        result.task(":copy").outcome == SUCCESS
        assert new File(runner.projectDir, "build/coppernic/sources/filtered/acceptance-test/fixture/build.gradle").exists()
        assert !new File(runner.projectDir, "build/coppernic/sources/filtered/acceptance-test/fixture/filetoignore.txt").exists()
        assert new File(runner.projectDir, "build/coppernic/sources/filtered/acceptance-test/fixture/filetoinclude.txt").exists()

        where:
        gradleVersion << [System.getProperty('current.gradle.version'), '3.5']
    }

}
