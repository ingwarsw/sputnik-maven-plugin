/*
 * Copyright 2014 Ingwar & co..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.eu.ingwar.maven.sputnik;

import java.util.Properties;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecution;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import pl.touk.sputnik.configuration.Configuration;
import pl.touk.sputnik.review.Engine;

/**
 *
 * @author Karol Lassak 'Ingwar'
 */
@Mojo(name = "sputnik",
        defaultPhase = LifecyclePhase.POST_INTEGRATION_TEST,
        threadSafe = true,
        aggregator = true
        )
public class SputnikMojo extends AbstractMojo {

    @Parameter(property = "sputnik.connector", defaultValue = "stash", required = true)
    private String connector;

    @Parameter(property = "sputnik.pullRequestId", required = true)
    private String pullRequestId;
    
    @Parameter(property = "sputnik.checkstyle.enabled", defaultValue = "true")
    private String checkstyleEnabled;
    
    @Parameter(property = "sputnik.checkstyle.configurationFile")
    private String checkstyleConfigurationFile;
    
    
    @Parameter(property = "sputnik.stash.host")
    private String stashHost;
    
    @Parameter(property = "sputnik.stash.port", defaultValue = "80")
    private String stashPort;
    
    @Parameter(property = "sputnik.stash.useHttps", defaultValue = "false")
    private String stashUseHttps;
    
    @Parameter(property = "sputnik.stash.username")
    private String stashUsername;

    @Parameter(property = "sputnik.stash.password")
    private String stashPassword;

    @Parameter(property = "sputnik.stash.projectKey")
    private String stashProjectKey;

    @Parameter(property = "sputnik.stash.repositorySlug")
    private String stashRepositorySlug;


    @Parameter(property = "project", readonly = true)
    private MavenProject mavenProject;
    
    @Parameter(property = "mojoExecution", readonly = true)
    private MojoExecution execution;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        Configuration conf = Configuration.instance();
        Properties prop = new Properties();
        
        prop.setProperty("cli.connector", connector);
        prop.setProperty("cli.pullRequestId", pullRequestId);
        
        prop.setProperty("checkstyle.enabled", checkstyleEnabled);
        prop.setProperty("checkstyle.configurationFile", checkstyleConfigurationFile);
        
        prop.setProperty("stash.host", stashHost);
        prop.setProperty("stash.port", stashPort);
        prop.setProperty("stash.useHttps", stashUseHttps);
        prop.setProperty("stash.username", stashUsername);
        prop.setProperty("stash.password", stashPassword);
        prop.setProperty("stash.projectKey", stashProjectKey);
        prop.setProperty("stash.repositorySlug", stashRepositorySlug);
        
        conf.setProperties(prop);
        new Engine().run();
    }
}
