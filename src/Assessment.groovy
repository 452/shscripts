@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7.1')
@Grab(group='oauth.signpost', module='signpost-core', version='1.2.1.2')
@Grab(group='oauth.signpost', module='signpost-commonshttp4', version='1.2.1.2')
import static groovyx.net.http.ContentType.*
import groovyx.net.http.*
import static groovyx.net.http.Method.*
import groovy.xml.*

public class Assessment {

	def resp
	def assessmentId
	def sessionId
	def deviceId
	def tokenId

	Assessment(assessmentId, sessionId, deviceId, tokenId, endPoint, formInFile) {
		this.assessmentId = assessmentId
		this.sessionId = sessionId
		this.deviceId = deviceId
		this.tokenId = tokenId
		//def binding = ["login": login, "password": password]
		String assessmentXml = new File(formInFile).text

		//def engine = new groovy.text.SimpleTemplateEngine()
		//def assessmentBody = engine.createTemplate(assessmentXml).make(binding)
		def http = new HTTPBuilder(endPoint + '/'  + assessmentId + '/form')
		try {
			resp = http.request(PUT, XML){
				delegate.headers['Connection'] = 'keep-alive';
				delegate.headers['SessionId'] = sessionId;
				delegate.headers['DeviceId'] = deviceId;
				delegate.headers['tokenId'] = tokenId;
				delegate.body = assessmentXml;
				response.success = { resp, body ->
					println "FormIn Upload: PUT response status ${resp.statusLine}"
					println "FormIn Upload: Query response ${body.status}"
				}
				response.failure = { resp -> println "PUT response statusline: ${resp.statusLine}" }
			}
		}
		catch (Exception e) {
			println e
			throw new RuntimeException("Backend is down or wrong server endpoint: " + endPoint)
		}
	}

	def getStatus(assessmentId) {
		def http = new HTTPBuilder(endPoint + assessmentId)
		def result;
		try {
			result = http.request(GET, XML){
				delegate.headers['Connection'] = 'keep-alive';
				delegate.headers['SessionId'] = sessionId;
				delegate.headers['DeviceId'] = deviceId;
				delegate.headers['tokenId'] = tokenId;
				delegate.body = assessmentXml;
				response.success = { resp, body ->
					println " response status ${resp.statusLine}"
					println "FormIn Upload: Query response ${body.status}"
				}
				response.failure = { resp -> println "PUT response statusline: ${resp.statusLine}" }
			}
		}
		catch (Exception e) {
			println e
			throw new RuntimeException("Backend is down or wrong server endpoint: " + endPoint)
		}
		return result
	}

	def xpath() {
		return resp
	}

}