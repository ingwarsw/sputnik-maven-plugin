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
import pl.touk.sputnik.configuration.GeneralOption;

/**
 * Mojo class for connector connector.
 *
 * @author Karol Lassak 'Ingwar'
 */
@Mojo(name = "stash",
        defaultPhase = LifecyclePhase.POST_INTEGRATION_TEST,
        threadSafe = true,
        aggregator = true)
public class StashConnectorMojo extends SputnikAbstractMojo {
    @Parameter(property = "sputnik.pullRequestId", required = true)
    private String pullRequestId;

    @Parameter(property = "sputnik.connector.projectKey", required = true)
    private String connectorProjectKey;

    @Parameter(property = "sputnik.connector.repositorySlug", required = true)
    private String connectorRepositorySlug;

    @Override
    protected Connectors getConnector() {
        return Connectors.STASH;
    }

    @Override
    protected void setConnectorProperties() {
        setConnectorProperty(CliOption.PULL_REQUEST_ID, pullRequestId);

        setConnectorProperty(GeneralOption.PROJECT_KEY, connectorProjectKey);
        setConnectorProperty(GeneralOption.REPOSITORY_SLUG, connectorRepositorySlug);
    }
}
