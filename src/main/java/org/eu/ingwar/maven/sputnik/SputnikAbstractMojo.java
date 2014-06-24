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
import pl.touk.sputnik.configuration.GeneralOption;
import pl.touk.sputnik.connector.ConnectorFacade;
import pl.touk.sputnik.connector.ConnectorFacadeFactory;
import pl.touk.sputnik.review.Engine;

/**
 * Abstract Mojo for all sputnik connectors.
 * 
 * @author Karol Lassak 'Ingwar'
 */
public abstract class SputnikAbstractMojo extends AbstractMojo {

    @Parameter(property = "sputnik.global.processTestFiles")
    private String processTestFiles;
    
    @Parameter(property = "sputnik.global.maxNumberOfComments")
    private String maxNumberOfComments;

    @Parameter(property = "sputnik.checkstyle.enabled")
    private String checkstyleEnabled;
    
    @Parameter(property = "sputnik.checkstyle.configurationFile")
    private String checkstyleConfigurationFile;
    
    @Parameter(property = "sputnik.pmd.enabled")
    private String pmdEnabled;

    @Parameter(property = "sputnik.pmd.pmdRulesets")
    private String pmdRulesets;

    @Parameter(property = "sputnik.findbugs.enabled")
    private String findbugsEnabled;

    @Parameter(property = "sputnik.findbugs.includeFilter")
    private String findbugsIncludeFilter;

    @Parameter(property = "sputnik.findbugs.excludeFilter")
    private String findbugsExcludeFilter;

    @Parameter(property = "sputnik.scalastyle.enabled")
    private String scalastyleEnabled;

    @Parameter(property = "sputnik.scalastyle.configurationFile")
    private String scalastyleConfigurationFile;


    private Properties sputnikProperties = new Properties();

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        setConnectorProperty(CliOption.CONNECTOR, getConnector().name().toLowerCase());
        
        setConnectorProperty(GeneralOption.PROCESS_TEST_FILES, processTestFiles);
        setConnectorProperty(GeneralOption.MAX_NUMBER_OF_COMMENTS, maxNumberOfComments);

        setConnectorProperty(GeneralOption.CHECKSTYLE_ENABLED, checkstyleEnabled);
        setConnectorProperty(GeneralOption.CHECKSTYLE_CONFIGURATION_FILE, checkstyleConfigurationFile);

        setConnectorProperty(GeneralOption.PMD_ENABLED, pmdEnabled);
        setConnectorProperty(GeneralOption.PMD_RULESETS, pmdRulesets);

        setConnectorProperty(GeneralOption.FINDBUGS_ENABLED, findbugsEnabled);
        setConnectorProperty(GeneralOption.FINDBUGS_EXCLUDE_FILTER, findbugsExcludeFilter);
        setConnectorProperty(GeneralOption.FINDBUGS_INCLUDE_FILTER, findbugsIncludeFilter);
        setConnectorProperties();
        setConnectorProperty(GeneralOption.SCALASTYLE_ENABLED, scalastyleEnabled);
        setConnectorProperty(GeneralOption.SCALASTYLE_CONFIGURATION_FILE, scalastyleConfigurationFile);
        
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
