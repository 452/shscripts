#!/bin/sh

export PATH=/usr/lib/jvm/jdk1.7.0_75/bin:$PATH
export JAVA_HOME=/usr/lib/jvm/jdk1.7.0_75

echo "************************************************************"
echo $(date)
export MAVEN_OPTS="-Xmx7799m -XX:MaxPermSize=1024m"
echo "MAVEN_OPT: $MAVEN_OPTS"
echo "JAVA_HOME: $JAVA_HOME"
java -version
echo "************************************************************"

# Check root or user
: 'if (( EUID == 0 )); then
	echo -e "\n- - - - - - - - - \n"
	echo "You are too root for this ! Recheck README.md file." 1>&2
	echo -e "\n- - - - - - - - - \n"
	exit
fi'

sudo rm -rf ~/.m2/repository/com/strikersoft
sudo rm -rf /root/.m2/repository/com/strikersoft
cd /work/git/tetra-integration/
MVN_ACTION=deploy
MVN_PROFILES="-Pdev-profitsoft -DskipTests=true"
mvn clean $MVN_ACTION $MVN_PROFILES
#cd /work/git/spe-backend/
cd /work/git/tetra-backend
#mvn clean deploy -Pdev -Plocal-fs -Punix -Dmaven.compiler.target=1.7 -Dmaven.compiler.source=1.7 -DskipTests=true
mvn clean $MVN_ACTION $MVN_PROFILES
notificationMessage="Build done! My lord"
espeak $notificationMessage
echo $notificationMessage
#sudo rsync -rcaP ~/.m2/repository/* /root/.m2/repository/
sudo rsync -ar ~/.m2/repository/* /root/.m2/repository/
#notify-send -u normal "Build" "$notificationMessage"
