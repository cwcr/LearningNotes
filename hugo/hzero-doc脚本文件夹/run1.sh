image='hcbm-doc'
image_tag=$(date +%Y%m%d-%H%M%S)
docker stop $image
docker rm $image
docker rmi $(docker images | grep $image | awk "{print $3}")
docker build --pull -t $image:$image_tag .
docker run --name $image -p 1313:80 -d $image:$image_tag

