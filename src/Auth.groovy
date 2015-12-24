@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7.1')
@Grab(group='oauth.signpost', module='signpost-core', version='1.2.1.2')
@Grab(group='oauth.signpost', module='signpost-commonshttp4', version='1.2.1.2')
import static groovyx.net.http.ContentType.*
import groovyx.net.http.*
import static groovyx.net.http.Method.*
import groovy.xml.*

public class Auth {

	def resp

	Auth(login, password, endPoint, xmlTemplateFile) {
		def binding = ["login": login, "password": password]
		String authXml = new File(xmlTemplateFile).text

		def engine = new groovy.text.SimpleTemplateEngine()
		def authBody = engine.createTemplate(authXml).make(binding)

		def http = new HTTPBuilder(endPoint)
		try {
			resp = http.request(PUT, XML){
				delegate.headers['Connection'] = 'keep-alive';
				delegate.body = authBody;
			}
		}
		catch (HttpResponseException e) {
			throw new RuntimeException("Please check your credentials: " + login)
		}
		catch (Exception e) {
			throw new RuntimeException("Backend is down or wrong server endpoint: " + endPoint)
		}
	}

	def xpath() {
		return resp
	}

}