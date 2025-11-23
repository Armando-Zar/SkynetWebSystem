# 1. Imagen base con GlassFish 5 (usaremos una imagen basada en Payara)
FROM payara/server-full:5.2022.5-jdk11

# 2. Establecer variables de entorno para la configuración
ENV GLASSFISH_HOME /opt/payara

# 3. Exponer el puerto por defecto
EXPOSE 8080

# 4. Copiar el archivo WAR construido al directorio de despliegue
COPY dist/SkynetWebSystem.war $GLASSFISH_HOME/deployments/

# 5. Configuración de GlassFish (puedes eliminar la línea 5 anterior si no la necesitas)
# Por ejemplo: RUN /opt/payara/bin/asadmin set configs.config.server-config.network-config.network-listeners.network-listener.http-listener-1.port=8080

# 6. Comando para iniciar Payara (INTENTO FINAL DE CORRECCIÓN)
CMD ["/opt/payara/bin/asadmin", "start-domain", "--verbose"]