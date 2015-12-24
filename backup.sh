#sudo rm -rf /opt/jboss-fuse-6.1.0.redhat-379/data
#sudo tar -xzvf /opt/backup/2015-12-22.17.52.42.data.tar.gz -C /
#psql -U postgres -f ~/Downloads/backup
filename=`date +%Y-%m-%d.%H.%M.%S`;
FUSE_HOME=/opt/jboss-fuse-6.1.0.redhat-379
BACKUP=/opt/backup
exclude="
--exclude=$FUSE_HOME/data/git
--exclude=$FUSE_HOME/data/log
--exclude=$FUSE_HOME/data/mavenIndexer
--exclude=$FUSE_HOME/data/tmp
--exclude=$FUSE_HOME/data/txlog
--exclude=$FUSE_HOME/data/karaf.out
"
if [ -d $FUSE_HOME ]; then
  [[ -z "$PGPASSWORD" ]] && echo "Please set password for db";
  echo "Backup process started:"
  if [ ! -d $BACKUP ]; then
    mkdir -p $BACKUP
  fi
  pg_dumpall -U postgres | gzip -c > "$BACKUP/$filename.backup.db.out.gz"
  tar cfz $BACKUP/$filename.data.tar.gz $FUSE_HOME/data $exclude
  echo -e "Backup done:\n$BACKUP/$filename.backup.db.out.gz\n$BACKUP/$filename.data.tar.gz"
fi
