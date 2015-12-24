import static Info.info
import Auth
import Assessment
import Image

info "Start Testing"

Properties properties = new Properties()
new File('../properties/staging.properties').withInputStream { properties.load(it) }

println properties.authLogin
def auth = (new Auth(properties.authLogin, properties.authPassword, properties.authEndpoint, '../xml/auth.xml')).xpath()

info auth.authToken.tokenId
assert auth.statusCode.equals(200) : "Server Response Code must be 200"

def assessmentId = 4

//def assessment = (new Assessment(assessmentId, 'session-01', 'soap_ui', auth.authToken.tokenId, properties.assessmentEndpoint, '../xml/formIn.xml')).xpath()

//new Image(assessmentId, 'session-01', 'soap_ui', auth.authToken.tokenId, properties.imagesEndpoint, properties.imagesDirectory, '../xml/formIn.xml')

new Download(assessmentId, 'session-01', 'soap_ui', auth.authToken.tokenId, properties.assessmentEndpoint)

//info assessment
info "Testing Done"
//assert auth.statusCode.equals(200)