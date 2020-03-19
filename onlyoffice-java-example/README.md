## Build instruction

At first, You need to install `oracle-java8-installer`

```
sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update
sudo apt-get install oracle-java8-installer
```

Edit the **settings.properties** configuration file. Specify the name of your local server with the ONLYOFFICE Document Server installed

```
nano src/main/resources/settings.properties
```

Edit the following lines:

```
files.docservice.url.converter=https://documentserver/ConvertService.ashx
files.docservice.url.tempstorage=https://documentserver/ResourceService.ashx
files.docservice.url.api=https://documentserver/web-apps/apps/api/documents/api.js
files.docservice.url.preloader=https://documentserver/web-apps/apps/api/documents/cache-scripts.html
```

Install Maven:

```
apt-get install maven
```

And build:

```
mvn package
```

After it, all bin files will be passed to `./target` folder

## Build from docker
Edit the **settings.properties** configuration file. Specify the name of your local server with the ONLYOFFICE Document Server installed

```
nano src/main/resources/settings.properties
```

Edit the following lines. You need to change `documentserver` to your documentserver:

```
files.docservice.url.converter=https://documentserver/ConvertService.ashx
files.docservice.url.tempstorage=https://documentserver/ResourceService.ashx
files.docservice.url.api=https://documentserver/web-apps/apps/api/documents/api.js
files.docservice.url.preloader=https://documentserver/web-apps/apps/api/documents/cache-scripts.html
```

Run next command in java example directory:
```
docker-compose up
```
After it, all bin files will be passed to `./target` folder
