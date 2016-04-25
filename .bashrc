if [ "$TERM" == "xterm" ]; then
RED=`tput setaf 1`
GREEN=`tput setaf 2`
END=`tput sgr0`

if [ -z "$FUSE_HOME" ]; then
  export FUSE_HOME=/opt/fuse;
fi

if [ -z "$USER_NAME" ]; then
  export USER_NAME=ihor.lavryniuk;
fi

echo "**************************************************************"
echo "* Welcome ${GREEN}}RooT{${END} or $USER"
echo "* Host: $(hostname)"
echo "* Using FUSE_HOME: $FUSE_HOME"
echo "* Using JAVA_HOME: $JAVA_HOME"
echo "* Java Version: $(java -version 2>&1 | awk '/version/{print $NF}')"
echo "* Java Build: $(java -version 2>&1 | awk '/build/{print}')"
echo "* Using CATALINA_OPTS: $CATALINA_OPTS"
echo "* Using OS kernel: $(uname -mrs)"
echo "* OS: $(lsb_release -d | sed 's/Description://g')"
echo "* ${GREEN}fff${END} - stop fuse"
echo "* ${GREEN}ffa${END} - start fuse"
echo "* ${GREEN}ffc${END} - connect to fuse client console"
echo "* ${GREEN}ffd${END} - force clean fuse"
echo "* ${GREEN}log${END} - show tail -fn -1000 ...  fuse.log"
echo "**************************************************************"

function connectToFuse() {
 $FUSE_HOME/bin/status
 while :
 do
    $FUSE_HOME/bin/client
    ret=$?
    if test "$ret" != "0"; then
        echo "Wait";
    else
        echo "Done";
        break;
    fi
    sleep 5
 done
}

alias deploy="bash < <(curl -s  https://raw.githubusercontent.com/452/shscripts/master/deploy.sh)"
alias fss="sudo service karaf-service stop"
alias fst="sudo service karaf-service start"
alias fsr="sudo service karaf-service restart"
alias fff="$FUSE_HOME/bin/stop && echo 'JBoss Fuse has been successfully stopped'"
alias ffa="sudo $FUSE_HOME/bin/start && echo 'Please wait while JBoss Fuse is loading... in background'"
alias ffc="connectToFuse"
alias ffs="$FUSE_HOME/bin/status"
alias ffd="fff; sudo bash < <(curl -s  https://raw.githubusercontent.com/452/shscripts/master/fclean.sh)"
alias fuse-logs-list="ls -l $FUSE_HOME/data/log/"
alias d="cd /work/fuse/"
alias update-bash="source <(curl -s  https://raw.githubusercontent.com/452/shscripts/master/.bashrc)"
alias ls="ls -lh"
alias x="netstat -lntu"
alias g="ntsysv"
alias gl="git log --oneline"
alias log="tail -fn -1000 $FUSE_HOME/data/log/fuse.log | sed 's#WARN#\x1b[33m&#; s#ERROR#\x1b[31m&#; s#foo#\x1b[32m&#'"
alias clog="lnav $FUSE_HOME/data/log/fuse.log"
alias backup="sudo bash < <(curl -s  https://raw.githubusercontent.com/452/shscripts/master/backup.sh)"

fi
