#!/bin/bash
#/etc/init.d/kafka
DAEMON_PATH=/home/ubuntu/kafka/bin
DAEMON_NAME=kafka
# Check that networking is up.
#[ ${NETWORKING} = "no" ] && exit 0

PATH=$PATH:$DAEMON_PATH

# See how we were called.
case "$1" in
  start)
        # Start daemon.
        pid=`ps ax | grep -i 'kafka.Kafka' | grep -v grep | awk '{print $1}'`
        if [ -n "$pid" ]
          then
            echo "Kafka is already running"
        else
          echo "Starting $DAEMON_NAME"
          $DAEMON_PATH/kafka-server-start.sh -daemon /home/ubuntu/kafka/config/server.properties
        fi
        ;;
  stop)
        echo "Shutting down $DAEMON_NAME"
        $DAEMON_PATH/kafka-server-stop.sh
        ;;
  restart)
        $0 stop
        sleep 2
        $0 start
        ;;
  status)
        pid=`ps ax | grep -i 'kafka.Kafka' | grep -v grep | awk '{print $1}'`
        if [ -n "$pid" ]
          then
          echo "Kafka is Running as PID: $pid"
        else
          echo "Kafka is not Running"
        fi
        ;;
  *)
        echo "Usage: $0 {start|stop|restart|status}"
        exit 1
esac

exit 0
