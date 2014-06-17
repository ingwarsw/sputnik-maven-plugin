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
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;
import pl.touk.sputnik.Connectors;
import pl.touk.sputnik.configuration.CliOption;
import pl.touk.sputnik.configuration.ConfigurationHolder;
import pl.touk.sputnik.configuration.ConfigurationOption;
import pl.touk.sputnik.connector.ConnectorFacade;
import pl.touk.sputnik.connector.ConnectorFacadeFactory;
import pl.touk.sputnik.review.Engine;

/**
 * Abstract Mojo for all sputnik connectors.
 * 
 * @author Karol Lassak 'Ingwar'
 */
public abstract class SputnikAbstractMojo extends AbstractMojo {

    @Parameter(property = "sputnik.pullRequestId", required = true)
    private String pullRequestId;
    
    @Parameter(property = "sputnik.checkstyle.enabled", defaultValue = "true")
    private String checkstyleEnabled;
    
    @Parameter(property = "sputnik.checkstyle.configurationFile")
    private String checkstyleConfigurationFile;
    
    private Properties sputnikProperties = new Properties();

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        setConnectorProperty(CliOption.CONNECTOR, getConnector().name());
        setConnectorProperty(CliOption.PULL_REQUEST_ID, pullRequestId);
        
        sputnikProperties.setProperty("checkstyle.enabled", checkstyleEnabled);
        sputnikProperties.setProperty("checkstyle.configurationFile", checkstyleConfigurationFile);

        setConnectorProperties();
        
        ConfigurationHolder.initFromProperties(sputnikProperties);
        ConnectorFacade facade = ConnectorFacadeFactory.INSTANCE.build(ConfigurationHolder.instance().getProperty(CliOption.CONNECTOR));
        new Engine(facade).run();

    }
    
    protected void setConnectorProperty(ConfigurationOption opt, String value) {
        sputnikProperties.setProperty(opt.getKey(), value);
    }

    protected abstract Connectors getConnector();
    protected abstract void setConnectorProperties();
}
