/*
 * Copyright 2014-2015 by Cloudsoft Corporation Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package brooklyn.clocker.example;

import org.apache.brooklyn.api.catalog.Catalog;
import org.apache.brooklyn.api.catalog.CatalogConfig;
import org.apache.brooklyn.api.entity.proxying.EntitySpec;

import brooklyn.config.ConfigKey;
import brooklyn.entity.basic.AbstractApplication;
import brooklyn.entity.basic.ConfigKeys;
import brooklyn.entity.container.docker.application.VanillaDockerApplication;

/**
 * Brooklyn managed {@link VanillaDockerApplication}.
 */
@Catalog(name = "Image Micro-Service",
        description = "A container micro-service defined by a Docker image",
        iconUrl = "classpath://container.png")
public class MicroServiceImage extends AbstractApplication {

    @CatalogConfig(label = "Container Name", priority = 90)
    public static final ConfigKey<String> CONTAINER_NAME = ConfigKeys.newStringConfigKey("docker.containerName", "Container name", "service");

    @CatalogConfig(label = "Image Name", priority = 80)
    public static final ConfigKey<String> IMAGE_NAME = VanillaDockerApplication.IMAGE_NAME;

    @CatalogConfig(label = "Image Tag", priority = 80)
    public static final ConfigKey<String> IMAGE_TAG = VanillaDockerApplication.IMAGE_TAG;

    @CatalogConfig(label = "Open Ports", priority = 70)
    public static final ConfigKey<String> OPEN_PORTS = ConfigKeys.newStringConfigKey("docker.openPorts", "Comma separated list of ports the application uses");

    @CatalogConfig(label = "Direct Ports", priority = 70)
    public static final ConfigKey<String> DIRECT_PORTS = ConfigKeys.newStringConfigKey("docker.directPorts", "Comma separated list of ports to open directly on the host");

    @Override
    public void initApp() {
        addChild(EntitySpec.create(VanillaDockerApplication.class)
                .configure("containerName", config().get(CONTAINER_NAME))
                .configure("imageName", config().get(IMAGE_NAME))
                .configure("imageTag", config().get(IMAGE_TAG))
                .configure("openPorts", config().get(OPEN_PORTS))
                .configure("directPorts", config().get(DIRECT_PORTS)));
    }

}
