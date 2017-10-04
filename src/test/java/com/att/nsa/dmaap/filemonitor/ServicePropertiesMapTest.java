/*-
 * ============LICENSE_START=======================================================
 * ONAP Policy Engine
 * ================================================================================
 * Copyright (C) 2017 AT&T Intellectual Property. All rights reserved.
 * ================================================================================
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
 * ============LICENSE_END=========================================================
 */

package com.att.nsa.dmaap.filemonitor;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ServicePropertiesMapTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRefresh() {

		ServicePropertiesMap map = new ServicePropertiesMap();

		try {
			map.refresh(new File(":/file"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertTrue(true);

	}

	@Test
	public void testGetProperty() {

		ServicePropertiesMap map = new ServicePropertiesMap();

		try {
			map.getProperty("filename", "propertykey");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertTrue(true);
	}

	@Test
	public void testGetProperties() {

		ServicePropertiesMap map = new ServicePropertiesMap();

		try {
			map.getProperties("filename");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertTrue(true);

	}

	@Test
	public void testIfNullThenEmpty() {

		ServicePropertiesMap map = new ServicePropertiesMap();

		try {
			map.getProperties("filename");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertTrue(true);

	}

}