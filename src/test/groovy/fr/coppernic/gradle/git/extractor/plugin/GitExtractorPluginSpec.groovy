package fr.coppernic.gradle.git.extractor.plugin

import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

/**
 * <p>Created on 07/09/17
 *
 * @author bastien
 */
public class GitExtractorPluginSpec extends Specification {

	def "apply() should load the plugin"() {
		given:
		def project = ProjectBuilder.builder().build()

		when:
		project.with {
			apply plugin: 'fr.coppernic.git.extractor'
		}

		then:
		project.plugins.hasPlugin(GitExtractorPlugin)
	}

}