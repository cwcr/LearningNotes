#!/bin/bash
service iptables status 1>/dev/null 2>&1
if [ $? -eq 0 ]; then
iptables -A INPUT -p tcp --dport 6443 -j ACCEPT      # 开放6443端口
iptables -A INPUT -p tcp --dport 2379:2380 -j ACCEPT # 开放2379-2380端口
iptables -A INPUT -p tcp --dport 10250:10255 -j ACCEPT #开放10250-10255端口
iptables -A INPUT -p tcp --dport 30000:32767 -j ACCEPT
echo "open port by iptables"
fi

service firewalld status 1>/dev/null 2>&1
if [ $? -eq 0 ]; then
firewall-cmd --permanent --zone=public --add-port=6443/tcp --permanent
firewall-cmd --permanent --zone=public --add-port=2379-2380/tcp --permanent
firewall-cmd --permanent --zone=public --add-port=10250-10255/tcp --permanent
firewall-cmd --permanent --zone=public --add-port=30000-32767/tcp --permanent
firewall-cmd --reload
echo "open port by firewalld"
fi