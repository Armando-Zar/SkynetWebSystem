# 1. Imagen base con GlassFish 5 (generalmente basada en Java EE SDK)
FROM glassfish/glassfish:5.0-jdk8

# 2. Configurar la variable HOME de GlassFish
ENV GLASSFISH_HOME /usr/local/glassfish5

# 3. Exponer el puerto por defecto de GlassFish.
EXPOSE 8080

# 4. Copiar el archivo WAR desde la carpeta 'dist'
# *** ¡LÍNEA MODIFICADA! ***
COPY dist/SkynetWebSystem.war $GLASSFISH_HOME/glassfish/domains/domain1/autodeploy/

# 5. Configurar GlassFish para que escuche en el puerto 8080
RUN /usr/local/glassfish5/bin/asadmin --user admin --passwordfile /dev/null \
    set configs.config.server-config.network-config.network-listeners.network-listener.http-listener-1.port=8080

# 6. Comando para iniciar GlassFish
CMD ["/usr/local/glassfish5/bin/asadmin", "start-domain", "-v"]