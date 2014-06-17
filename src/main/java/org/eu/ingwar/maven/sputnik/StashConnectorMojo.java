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
import pl.touk.sputnik.connector.stash.StashOption;

/**
 * Mojo class for stash connector.
 * 
 * @author Karol Lassak 'Ingwar'
 */
@Mojo(name = "stash",
        defaultPhase = LifecyclePhase.POST_INTEGRATION_TEST,
        threadSafe = true,
        aggregator = true
        )
public class StashConnectorMojo extends SputnikAbstractMojo {

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

    @Override
    protected Connectors getConnector() {
        return Connectors.STASH;
    }
    @Override
    protected void setConnectorProperties() {
        setConnectorProperty(StashOption.HOST, stashHost);
        setConnectorProperty(StashOption.PORT, stashPort);
        setConnectorProperty(StashOption.USE_HTTPS, stashUseHttps);
        setConnectorProperty(StashOption.USERNAME, stashUsername);
        setConnectorProperty(StashOption.PASSWORD, stashPassword);
        setConnectorProperty(StashOption.PROJECT_KEY, stashProjectKey);
        setConnectorProperty(StashOption.REPOSITORY_SLUG, stashRepositorySlug);
    }
}
