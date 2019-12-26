FROM registry.cn-hangzhou.aliyuncs.com/choerodon-tools/nginx:1.11.4-alpine

RUN echo "Asia/shanghai" > /etc/timezone;
# RUN sed -i 's/\#gzip/gzip/g' /etc/nginx/nginx.conf;
ADD ./public /usr/share/nginx/html
# COPY --from=0 ./public /usr/share/nginx/html
# ADD ./default.conf /etc/nginx/conf.d/
# CMD ["nginx", "-g", "daemon off;"]

# EXPOSE 80
