sudo yum install epel-release -y 
sudo yum install git python36 sshpass -y
sudo python3.6 -m ensurepip
sudo /usr/local/bin/pip3 install --no-cache-dir ansible==2.7.5 netaddr -i https://mirrors.aliyun.com/pypi/simple/