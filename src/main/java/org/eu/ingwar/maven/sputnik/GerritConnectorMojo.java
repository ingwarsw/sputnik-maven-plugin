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

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import pl.touk.sputnik.Connectors;
import pl.touk.sputnik.configuration.CliOption;
import pl.touk.sputnik.connector.gerrit.GerritOption;

/**
 * Mojo class for gerrit connector.
 *
 * @author Karol Lassak 'Ingwar'
 */
@Mojo(name = "gerrit",
        defaultPhase = LifecyclePhase.POST_INTEGRATION_TEST,
        threadSafe = true,
        aggregator = true)
public class GerritConnectorMojo extends SputnikAbstractMojo {
    @Parameter(property = "sputnik.changeId", required = true)
    private String changeId;

    @Parameter(property = "sputnik.revisionId", required = true)
    private String revisionId;


    @Parameter(property = "sputnik.gerrit.host")
    private String gerritHost;

    @Parameter(property = "sputnik.gerrit.port")
    private String gerritPort;

    @Parameter(property = "sputnik.gerrit.useHttps")
    private String gerritUseHttps;

    @Parameter(property = "sputnik.gerrit.username")
    private String gerritUsername;

    @Parameter(property = "sputnik.gerrit.password")
    private String gerritPassword;

    @Override
    protected Connectors getConnector() {
        return Connectors.STASH;
    }

    @Override
    protected void setConnectorProperties() {
        setConnectorProperty(CliOption.CHANGE_ID, changeId);
        setConnectorProperty(CliOption.REVISION_ID, revisionId);

        setConnectorProperty(GerritOption.HOST, gerritHost);
        setConnectorProperty(GerritOption.PORT, gerritPort);
        setConnectorProperty(GerritOption.USE_HTTPS, gerritUseHttps);
        setConnectorProperty(GerritOption.USERNAME, gerritUsername);
        setConnectorProperty(GerritOption.PASSWORD, gerritPassword);
    }
}
