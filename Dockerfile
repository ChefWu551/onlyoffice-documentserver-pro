FROM onlyoffice/documentserver:latest
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
# 设置关闭后2秒内自动保存(默认10s)
ADD config/default.json /etc/onlyoffice/documentserver/
