
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

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 *
 * @author Karol Lassak 'Ingwar'
 */
@Mojo(name = "sputnik",
        defaultPhase = LifecyclePhase.POST_INTEGRATION_TEST,
        threadSafe = true
        )
@Execute()
public class SputnikMojo extends AbstractMojo {
    @Parameter(name = "test", defaultValue = "acham", property = "ala.ma.test")
    private String test;
    
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
    }
    
}
