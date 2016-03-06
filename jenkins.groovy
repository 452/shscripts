import hudson.model.*

def foo = build.buildVariableResolver.resolve("BACKEND_URL")
def buildNumber = build.getNumber()
/*
hudson.maven.MavenModuleSet
hudson.maven.MavenModuleSetBuild
getLastStableBuild, or getLastFailedBuild()
prinln j.getName() j.isBuilding() j.isInQueue()
println Jenkins.instance.items.size()
Jenkins.instance.items.each {
	println(it.getName())
}
*/
def tetraQAIntegrationBuild = Hudson.instance.getJob('tetra-qa-integration').getLastBuild() 

println tetraQAIntegrationBuild.getTime()
println tetraQAIntegrationBuild.getWorkspace()
println tetraQAIntegrationBuild.getNumber()

println "# ${buildNumber} ${foo}"
//println "# ${tetraQAIntegrationBuild.getBuilds()}"

def currentBuild = Thread.currentThread().executable

setBuildParameters([BACKEND_URL:'value28',HAAA:777])

//ParametersAction newParamAction = new hudson.model.ParametersAction(new hudson.model.StringParameterValue('BACKEND_URL','value28'));
//currentBuild.addAction(newParamAction);

currentBuild.actions.each {
  println '***************\n' + it
}

def PULL_REQUEST_URL = build.buildVariableResolver.resolve('BACKEND_URL')
def PULL_REQUEST_ID = build.buildVariableResolver.resolve('BACKEND_URL')
def description = "<a href='$PULL_REQUEST_URL'>PR #$PULL_REQUEST_ID</a>"
currentBuild.description = description
//currentBuild.setDescription(description)

def setBuildParameters(map) {
    def npl = new ArrayList<StringParameterValue>()
    for (e in map) {
        npl.add(new StringParameterValue(e.key.toString(), e.value.toString()))
    }
    def newPa = null
    def oldPa = build.getAction(ParametersAction.class)
    if (oldPa != null) {
        build.actions.remove(oldPa)
        newPa = oldPa.createUpdated(npl)
    } else {
        newPa = new ParametersAction(npl)
    }
    build.actions.add(newPa)
}

import hudson.model.*

log("123 456 789 #${manager.build.getNumber()} ")

void log(msg) {
  manager.listener.logger.println(msg)
}

/////////////////////////
def credentials_store = jenkins.model.Jenkins.instance.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')

println ">credentials_store: ${credentials_store}"
println ">Description: ${credentials_store.description}"
println ">Target: ${credentials_store.target}"
credentials_store.each { println "credentials_store.each: ${it}" }

credentials_store[0].credentials.each { it ->
    println "credentials: -> ${it}"
    if (it instanceof com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl) {
        println ">>>: username: ${it.username} password: ${it.password} description: ${it.description}"
    }
}
/////////////////////////
println getCredentialsByID('')

def getCredentialsByID(id) {
  jenkins.model.Jenkins.instance.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0].credentials.findResult { credential ->
      if (credential instanceof com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl) {
        if (credential.id == id)
        	[id:credential.id, username:credential.username, password:credential.password, description:credential.description]
      }
  }
}
