@Grapes([
	@Grab('org.codehaus.groovy.modules.http-builder:http-builder:0.7.+'),
	@Grab(group='oauth.signpost', module='signpost-core', version='1.+'),
	@Grab(group='oauth.signpost', module='signpost-commonshttp4', version='1.+'),
	@Grab('org.apache.httpcomponents:httpcore:4.4.1'),
	@Grab('org.apache.httpcomponents:httpmime:4.3'),
	@Grab('commons-io:commons-io:2.4')
])
import static groovyx.net.http.ContentType.*
import groovyx.net.http.*
import static groovyx.net.http.Method.*
import groovy.xml.*
import org.apache.http.HttpEntity;
import org.apache.http.entity.*
import org.apache.http.entity.mime.*
import org.apache.http.entity.mime.content.*
import org.apache.commons.io.FileUtils;

class Download {

	Download(assessmentId, sessionId, deviceId, tokenId, endPoint) {
		HTTPBuilder http = new HTTPBuilder(endPoint + '/' + assessmentId)
		try {
			http.request(GET){ req ->
				req.addHeader("Connection", 'keep-alive');
				req.addHeader("SessionId", sessionId.toString());
				req.addHeader("DeviceId", deviceId.toString());
				req.addHeader("tokenId", tokenId.toString());
				req.addHeader("Content-Type", 'image/jpeg');
				response.success = { resp, body ->
					println "Download: GET response status ${resp.statusLine}"
					println "Download: Query response ${body.status} : ${body.reports.class}"
					
				}
				response.failure = { resp -> println "Download response statusline: ${resp.statusLine}" }
			}
		}
		catch (Exception e) {
			throw new RuntimeException("Download error: " + e)
		}
	}

	private String download() {
		def url = 'http://www.google.com/images/logo.gif'
		def file = new File('google_logo.gif').newOutputStream()
		file << new URL(url).openStream()
		file.close()
	}
}