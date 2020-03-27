FROM onlyoffice/documentserver:5.4.2.46
MAINTAINER wuyuefeng
# 删除没用的字体
RUN rm -rf /var/www/onlyoffice/documentserver/core-fonts/*
RUN rm -rf /usr/share/fonts/truetype/dejavu/*
# 拷贝字体文件
COPY winfont /usr/share/fonts/winfont
RUN fc-cache -f -v && /usr/bin/documentserver-generate-allfonts.sh
# 拷贝前端代码
COPY web-apps /var/www/onlyoffice/documentserver/web-apps
EXPOSE 80 443
VOLUME /etc/onlyoffice /var/log/onlyoffice /var/lib/onlyoffice /var/www/onlyoffice/Data /var/lib/postgresql /usr/share/fonts/truetype/custom /usr/share/fonts/winfont
CMD bash -C '/app/onlyoffice/run-document-server.sh';'bash'
