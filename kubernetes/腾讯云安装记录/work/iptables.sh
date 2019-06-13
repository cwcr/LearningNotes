iptables -A INPUT -p tcp --dport 6443 -j ACCEPT      # 开放6443端口
iptables -A INPUT -p tcp --dport 2379:2380 -j ACCEPT
iptables -A INPUT -p tcp --dport 10250:10255 -j ACCEPT
iptables -A INPUT -p tcp --dport 30000:32767 -j ACCEPT
