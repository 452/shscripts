#!/bin/sh

FREE_WIFI_COUNT=$(iwlist wlan0 scan | grep 'Encryption key:off' -c)
PRIVATE_WIFI_COUNT=$(iwlist wlan0 scan | grep 'Encryption key:on' -c)
SCAN_MESSAGE="Private WIFI count: $PRIVATE_WIFI_COUNT\nFree WIFI count: $FREE_WIFI_COUNT"
[ "$FREE_WIFI_COUNT" -gt "0" ] && echo 777
date >> /home/ihor/date.txt
notify-send "WIFI scan" "$SCAN_MESSAGE" -i info
