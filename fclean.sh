#!/bin/sh
sudo rm -rf /opt/jboss-fuse-6.1.0.redhat-379/data
sudo rm -rf /opt/jboss-fuse-6.2.0.redhat-133/data
sudo rm /opt/jboss-fuse-6.1.0.redhat-379/lock
sudo rm -rf /tmp/fakerepo
sudo rm -rf /tmp/hsperfdata_root
sudo rm -rf /tmp/hsperfdata_$USER
sudo rm -rf /opt/tetra/fs-repo/hs/assessment/dev
sudo rm -rf /opt/tetra/fs-repo/water/assessment/dev
sudo rm -rf $HOME/.karaf
sudo rm -rf $HOME/.m2/repository/com/strikersoft
sudo rm -rf /root/.m2/repository/com/strikersoft
if [ -d /opt/jboss-fuse-6.1.0.redhat-379/data ] ; then
  echo "Clean problem"
else #if needed #also: elif [new condition] 
  echo "JBoss Fuse & Maven repo is cleaned"
fi
