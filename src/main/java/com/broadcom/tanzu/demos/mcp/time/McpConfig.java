/*
 * Copyright (c) 2025 Broadcom, Inc. or its affiliates
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

package com.broadcom.tanzu.demos.mcp.time;

import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

@Configuration(proxyBeanMethods = false)
class McpConfig {
    @Bean
    public List<McpServerFeatures.SyncPromptRegistration> prompts() {
        return List.of(
                new McpServerFeatures.SyncPromptRegistration(new McpSchema.Prompt(
                        "time",
                        "A prompt to get the current time",
                        Collections.emptyList()), req -> {
                    final var m = new McpSchema.PromptMessage(McpSchema.Role.USER, new McpSchema.TextContent("What time is it?"));
                    return new McpSchema.GetPromptResult("A message to get the current time using the host time zone", List.of(m));
                }),
                new McpServerFeatures.SyncPromptRegistration(new McpSchema.Prompt(
                        "time-in-city",
                        "A prompt to get the current time in a given city",
                        List.of(new McpSchema.PromptArgument("city", "The city name", true))), req -> {
                    final var city = (String) req.arguments().get("city");
                    final var m = new McpSchema.PromptMessage(McpSchema.Role.USER, new McpSchema.TextContent("What time is it in " + city + "?"));
                    return new McpSchema.GetPromptResult("A message to get the current time in " + city, List.of(m));
                })
        );
    }
}
