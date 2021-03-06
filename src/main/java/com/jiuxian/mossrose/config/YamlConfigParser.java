/**
 * Copyright 2015-2020 jiuxian.com.
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
package com.jiuxian.mossrose.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class YamlConfigParser implements ConfigParser {

	private static final Logger LOGGER = LoggerFactory.getLogger(YamlConfigParser.class);

	@Override
	public MossroseConfig fromClasspathFile(String file) {
		final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		try (InputStream in = this.getClass().getClassLoader().getResourceAsStream(file)) {
			if (in == null) {
				throw new RuntimeException("Mossrose config file " + file + " cannot be found.");
			}
			return mapper.readValue(in, MossroseConfig.class);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
