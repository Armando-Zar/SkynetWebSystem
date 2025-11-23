# 1. Usamos una imagen que tiene el comando de inicio preconfigurado.
# La imagen Payara Micro es más ligera y flexible, pero usaremos la Full.
# Esta imagen suele tener el punto de entrada (ENTRYPOINT) correcto.
FROM payara/server-full:5.2022.5-jdk11

# 2. Establecer variables de entorno para la configuración
ENV GLASSFISH_HOME /opt/payara

# 3. Exponer el puerto por defecto
EXPOSE 8080

# 4. Copiar el archivo WAR construido al directorio de despliegue
# *** VERIFICA el nombre del WAR. Úsalo sin los corchetes. ***
COPY dist/SkynetWebSystem.war $GLASSFISH_HOME/deployments/

# 5. El CMD se cambia al ENTRYPOINT de la imagen para que inicie el dominio
# La mayoría de las imágenes oficiales simplemente ejecutan el servidor.
# No necesitamos el comando 'asadmin start-domain' explícito.
CMD ["/opt/payara/bin/start-server"]