#!/bin/bash
# remove all previous rules:

QBITTORENT_LISTEN_PORT=6881
iptables --flush

iptables -A INPUT -i lo -j ACCEPT
iptables -A INPUT -s 0.0.0.0/0 -d 0.0.0.0/0 -p tcp --dport 22 -j ACCEPT
iptables -A INPUT -p tcp -m tcp --dport 22 --tcp-flags FIN,SYN,RST,ACK SYN -j DROP
iptables -A INPUT -p icmp -j ACCEPT
#iptables -A INPUT -m conntrack --ctstate RELATED,ESTABLISHED -j ACCEPT
iptables -A INPUT -m state --state ESTABLISHED,RELATED -j ACCEPT
#Drop all other connections:
iptables -A INPUT -j DROP
iptables -P INPUT DROP
