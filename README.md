sputnik-maven-plugin
====================

[![Build Status](https://travis-ci.org/ingwarsw/sputnik-maven-plugin.png)](https://travis-ci.org/ingwarsw/sputnik-maven-plugin)
[![Coverage Status](https://coveralls.io/repos/ingwarsw/sputnik-maven-plugin/badge.png?branch=master)](https://coveralls.io/r/ingwarsw/sputnik-maven-plugin?branch=master)


Maven plugin for sputnik revieve engine:
https://github.com/TouK/sputnik


# Basic usage

## Stash

    <plugin>
        <groupId>org.eu.ingwar.maven</groupId>
        <artifactId>sputnik-maven-plugin</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        <executions>
            <execution>
                <id>sputnik-default</id>
                <goals>
                    <goal>stash</goal>
                </goals>
            </execution>
        </executions>
        <configuration>
            <!-- stash specific options -->
            <pullRequestId>123</pullRequestId>
            <connectorProjectKey>project</connectorProjectKey>
            <connectorRepositorySlug>slug</connectorRepositorySlug>
            
            <!-- global options -->
            <checkstyleEnabled>true</checkstyleEnabled>
            <checkstyleConfigurationFile>checkstyle_file.xml</checkstyleConfigurationFile>
                            
            <connectorHost>stash.server.addr</connectorHost>
            <connectorUsername>user</connectorUsername>
            <connectorPassword>pass</connectorPassword>
        </configuration>
    </plugin>

## Gerrit

    <plugin>
        <groupId>org.eu.ingwar.maven</groupId>
        <artifactId>sputnik-maven-plugin</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        <executions>
            <execution>
                <id>sputnik-default</id>
                <goals>
                    <goal>gerrit</goal>
                </goals>
            </execution>
        </executions>
        <configuration>
            <!-- gerrit specific options -->
            <changeId>123</changeId>
            <revisionId>project</revisionId>

            <!-- global options -->
            <checkstyleEnabled>true</checkstyleEnabled>
            <checkstyleConfigurationFile>checkstyle_file.xml</checkstyleConfigurationFile>
                            
            <connectorHost>stash.server.addr</connectorHost>
            <connectorUsername>user</connectorUsername>
            <connectorPassword>pass</connectorPassword>
        </configuration>
    </plugin>

# Extended usage

## Stash + Jenkins

        <profile>
            <id>sputnik</id>
            <build>
                <plugins>
                    <plugin>
                        <groupId>org.codehaus.mojo</groupId>
                        <artifactId>build-helper-maven-plugin</artifactId>
                        <executions>
                            <execution>
                                <id>regex-property-sputnik</id>
                                <phase>initialize</phase>
                                <goals>
                                    <goal>regex-property</goal>
                                </goals>
                                <configuration>
                                    <name>sputnik.pullRequestId</name>
                                    <value>${env.GIT_BRANCH2}</value>
                                    <regex>^origin/pr/([0-9]+)$</regex>
                                    <replacement>$1</replacement>
                                    <failIfNoMatch>true</failIfNoMatch>
                                </configuration>
                            </execution>
                        </executions>
                    </plugin>
                    <plugin>
                        <groupId>org.eu.ingwar.maven</groupId>
                        <artifactId>sputnik-maven-plugin</artifactId>
                        <version>0.0.1-SNAPSHOT</version>
                        <executions>
                            <execution>
                                <id>sputnik-default</id>
                                <goals>
                                    <goal>stash</goal>
                                </goals>
                            </execution>
                        </executions>
                        <configuration>
                            <!-- stash specific options -->
                            <connectorProjectKey>project</connectorProjectKey>
                            <connectorRepositorySlug>slug</connectorRepositorySlug>
                            
                            <!-- global options -->
                            <checkstyleEnabled>true</checkstyleEnabled>
                            <checkstyleConfigurationFile>checkstyle_file.xml</checkstyleConfigurationFile>
                                            
                            <connectorHost>stash.server.addr</connectorHost>
                            <connectorUsername>user</connectorUsername>
                            <connectorPassword>pass</connectorPassword>
                        </configuration>
                    </plugin>
                </plugins>
                
            </build>
        </profile>
