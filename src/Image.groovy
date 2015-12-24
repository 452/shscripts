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

public class Image {

	def resp

	Image(assessmentId, sessionId, deviceId, tokenId, endPoint, imagesDirectory, formInPath) {
		def xmlIn = new XmlSlurper().parseText(new File(formInPath).text)
		for (imgRef in  xmlIn.resources.image){
			//info 'resources ' + imgRef['@reference']
			def img =  xmlIn.depthFirst().find { (it.name() == 'image' || it.name() == 'imageResource' )&& it."@inner-id" == imgRef['@reference']}
			new Image(assessmentId, sessionId, deviceId, tokenId, endPoint, imagesDirectory, img.@name, 123)
			//'image  id=  ' + img.@name  + ", url = " + properties.imagesDirectory + img.@name
		}
	}

	Image(assessmentId, sessionId, deviceId, tokenId, endPoint, imagesDirectory, resourceId, da) {
		//def binding = ["login": login, "password": password]
		File image = new File(imagesDirectory+resourceId)
		assert image.exists()
		//def engine = new groovy.text.SimpleTemplateEngine()
		//def assessmentBody = engine.createTemplate(assessmentXml).make(binding)
		endPoint = endPoint + '/'  + assessmentId + '/resource/' + resourceId
		HTTPBuilder http = new HTTPBuilder(endPoint)
		try {
			resp = http.request(PUT){ req ->
				req.addHeader("Connection", 'keep-alive');
				req.addHeader("SessionId", sessionId.toString());
				req.addHeader("DeviceId", deviceId.toString());
				req.addHeader("tokenId", tokenId.toString());
				req.addHeader("Content-Type", 'image/jpeg');
				req.setEntity(new FileEntity(image))
				response.success = { resp, body ->
					println "Image Upload: PUT response status ${resp.statusLine}"
					println "Image Upload: Query response ${body.status}"
				}
				response.failure = { resp -> println "PUT response statusline: ${resp.statusLine}" }
			}
		}
		catch (Exception e) {
			throw new RuntimeException("Image Uploading error: " + e)
		}
	}

	def xpath() {
		return resp
	}

}