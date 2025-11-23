# 1. Imagen base con GlassFish 5 (usaremos Payara)
FROM payara/server-full:5.2022.5-jdk11

# 2. Establecer variables de entorno para la configuración
ENV GLASSFISH_HOME /opt/payara

# 3. Exponer el puerto por defecto
EXPOSE 8080

# 4. Copiar el archivo WAR construido al directorio de despliegue
COPY dist/SkynetWebSystem.war $GLASSFISH_HOME/deployments/

# 6. Comando para iniciar Payara (ELIMINADO: Dejamos que el ENTRYPOINT de la imagen base se encargue)
# CMD ["/opt/payara/bin/start-domain"] <--- ELIMINA O COMENTA ESTA LÍNEA

# O si quieres dejarlo en blanco:
# CMD []