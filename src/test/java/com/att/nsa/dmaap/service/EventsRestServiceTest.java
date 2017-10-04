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
package com.att.nsa.dmaap.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.att.ajsc.beans.PropertiesMapBean;
import com.att.nsa.cambria.CambriaApiException;
import com.att.nsa.cambria.backends.ConsumerFactory.UnavailableException;
import com.att.nsa.cambria.service.EventsService;
import com.att.nsa.configs.ConfigDbException;
import com.att.nsa.security.ReadWriteSecuredResource.AccessDeniedException;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import com.att.nsa.cambria.beans.DMaaPContext;
import com.att.nsa.cambria.exception.DMaaPAccessDeniedException;
import com.att.nsa.cambria.exception.ErrorResponse;
import com.att.nsa.cambria.metabroker.Broker.TopicExistsException;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ PropertiesMapBean.class })
public class EventsRestServiceTest {

	@InjectMocks
	EventsRestService eventsRestRestService;

	@Mock
	private EventsService eventsService;
	
	@Mock
	ErrorResponse errorResponse;
	
	@Mock
	DMaaPContext dmaapContext;

	@Mock
	InputStream iStream;

	@Mock
	ServletInputStream servletInputStream;

	@Mock
	HttpServletRequest httpServReq;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetEvents() throws CambriaApiException {

		eventsRestRestService.getEvents("topicName", "consumergroup", "consumerid");

	}

	@Test(expected = TopicExistsException.class)
	public void testGetEvents_TopicExistException() throws CambriaApiException, ConfigDbException, TopicExistsException,
			UnavailableException, IOException, AccessDeniedException {

		Mockito.doThrow(new TopicExistsException("topic exists")).when(eventsService).getEvents(dmaapContext,
				"topicName", "consumergroup", "consumerid");

		eventsService.getEvents(dmaapContext, "topicName", "consumergroup", "consumerid");

	}
	
	@Test(expected = DMaaPAccessDeniedException.class)
	public void testGetEvents_DMaaPAccessDeniedException() throws CambriaApiException, ConfigDbException, TopicExistsException,
			UnavailableException, IOException, AccessDeniedException {

		Mockito.doThrow(new DMaaPAccessDeniedException(errorResponse)).when(eventsService).getEvents(dmaapContext,
				"topicName", "consumergroup", "consumerid");

		eventsService.getEvents(dmaapContext, "topicName", "consumergroup", "consumerid");

	}
	
	/*@Test(expected = DMaaPAccessDeniedException.class)
	public void testGetEvents_DMaaPAccessDeniedException() throws CambriaApiException, ConfigDbException, TopicExistsException,
			UnavailableException, IOException, AccessDeniedException {

		Mockito.doThrow(new DMaaPAccessDeniedException(errorResponse)).when(eventsService).getEvents(dmaapContext,
				"topicName", "consumergroup", "consumerid");

		eventsService.getEvents(dmaapContext, "topicName", "consumergroup", "consumerid");

	}*/

	@Test
	public void testPushEvents() throws CambriaApiException {

		eventsRestRestService.pushEvents("topicName", iStream, "partitionKey");

	}
	

	@Test
	public void testPushEvents_TopicExistException() throws CambriaApiException {

		eventsRestRestService.pushEvents("topicName", iStream, "partitionKey");

	}

	@Test
	public void tesTPushEventsWithTransaction() throws CambriaApiException, IOException {
		// when(dmaapContext.getRequest()).th
		// thenReturn(httpServReq);
		when(httpServReq.getInputStream()).thenReturn(servletInputStream);
		eventsRestRestService.pushEventsWithTransaction("topicName", "partitionKey");

	}

}