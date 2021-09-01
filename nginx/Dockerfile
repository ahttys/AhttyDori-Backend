FROM nginx

RUN mkdir /app
WORKDIR /app
#RUN mkdir ./www
#ADD ./client/build ./www
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80