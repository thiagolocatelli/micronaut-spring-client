/*
 * Copyright 2017-2018 original authors
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

package io.micronaut.discovery.spring.config.client.response;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.discovery.config.ConfigurationClient;

import java.util.Map;

/**
 * A {@link ConfigurationClient} for Spring Cloud client.
 *
 *  @author Thiago Locatelli
 *  @since 1.0
 */
public class ConfigServerPropertySource {

    private String name;

    private Map<String, Object> source;

    @JsonCreator
    public ConfigServerPropertySource(@JsonProperty("name") String name,
                                      @JsonProperty("source") Map<String, Object> source) {
        this.name = name;
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public Map<String, Object> getSource() {
        return source;
    }

    @Override
    public String toString() {
        return "ConfigServerPropertySource [name=" + name + "]";
    }

}