package com.github.fnunezkanut.controller

import groovy.json.JsonSlurper
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody


@Controller
@EnableAutoConfiguration
class Info {


	@RequestMapping(
		value = "/info",
		method = RequestMethod.GET,
		produces = "application/json"
	)
	@ResponseBody
	HashMap<String, String> info() {

		final Logger logger = LoggerFactory.getLogger(Info.class)
		final String url = 'http://10.0.0.13:8080/info'


		final CloseableHttpClient client = HttpClients.createDefault()
		final CloseableHttpResponse response
		String body = '{}'
		try {

			final HttpGet request = new HttpGet(url)
			response = client.execute( request )

			//logger.info("Response: " + response.getStatusLine())

			//get content body as string
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))
			StringBuffer result = new StringBuffer()
			String line = ""
			while ((line = rd.readLine()) != null) {
				result.append(line)
			}
			body = result.toString()

			//logger.info("body: " + body )

		} finally {

			if( response != null ){
				response.close()
			}
			client.close()
		}

		final HashMap<String, String> message = new JsonSlurper().parseText( body ) as HashMap<String,String>
		//final HashMap<String, String> message = [:]

		return message
	}


	//trying non closable version
	@RequestMapping(
		value = "/info2",
		method = RequestMethod.GET,
		produces = "application/json"
	)
	@ResponseBody
	HashMap<String, String> info2() {

		final Logger logger = LoggerFactory.getLogger(Info.class)
		final String url = 'http://10.0.0.13:8080/info'


		final HttpClient client = HttpClients.createDefault()
		final HttpResponse response
		String body = '{}'
		try {

			final HttpGet request = new HttpGet(url)
			response = client.execute( request )

			//logger.info("Response: " + response.getStatusLine())

			//get content body as string
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))
			StringBuffer result = new StringBuffer()
			String line = ""
			while ((line = rd.readLine()) != null) {
				result.append(line)
			}
			body = result.toString()

			//logger.info("body: " + body )

		} finally {

			if( response != null ){
			//	response.close()
			}
			//client.close()
		}

		final HashMap<String, String> message = new JsonSlurper().parseText( body ) as HashMap<String,String>

		return message
	}

}
