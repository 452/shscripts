#!/bin/sh
if [ -z "$FUSE_HOME" ]; then
  export FUSE_HOME=/opt/fuse;
fi
sudo rm -rf $FUSE_HOME/data
sudo rm -rf /opt/jboss-fuse-6.2.0.redhat-133/data
[ -f $FUSE_HOME/lock ] && sudo rm $FUSE_HOME/lock
sudo rm -rf /tmp/fakerepo
sudo rm -rf /tmp/hsperfdata_root
sudo rm -rf /tmp/hsperfdata_$USER
sudo rm -rf /opt/tetra/fs-repo/hs/assessment/dev
sudo rm -rf /opt/tetra/fs-repo/water/assessment/dev
sudo rm -rf $HOME/.karaf
sudo rm -rf $HOME/.m2/repository/com/strikersoft
sudo rm -rf /root/.m2/repository/com/strikersoft
if [ -d $FUSE_HOME/data ] ; then
  echo "Clean problem"
else #if needed #also: elif [new condition] 
  echo "JBoss Fuse & Maven repo is cleaned"
fi
