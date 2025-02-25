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

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
class TimeTools {
    @Tool(name = "time_get", description = """
            Get current date and time in a specified time zone.
            """)
    String now(@ToolParam(required = false, description = "Zone id") String zoneId) {
        final var zone = zoneId != null ? ZoneId.of(zoneId) : ZoneId.systemDefault();
        return LocalDateTime.now(zone).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
