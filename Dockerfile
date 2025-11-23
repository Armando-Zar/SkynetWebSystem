# 1. Imagen base con GlassFish 5 (usaremos una imagen basada en Payara para asegurar la disponibilidad)
FROM payara/server-full:5.2022.5-jdk11

# 2. Establecer variables de entorno para la configuración (ajustadas para Payara/GlassFish)
ENV GLASSFISH_HOME /opt/payara
# ENV GLASSFISH_HOME /usr/local/glassfish5   <-- ELIMINA esta línea si existe

# 3. Exponer el puerto por defecto
EXPOSE 8080

# 4. Copiar el archivo WAR construido al directorio de despliegue
# *** Asegúrate de mantener la ruta correcta de tu WAR ***
COPY dist/SkynetWebSystem.war $GLASSFISH_HOME/deployments/

# 5. Configurar GlassFish para que escuche en el puerto 8080 (Comando Payara/GlassFish)
# Este comando puede variar ligeramente, pero Payara suele escuchar por defecto.
# Si tu aplicación requiere configuraciones específicas de GlassFish, debes añadirlas aquí.

# 6. Comando para iniciar GlassFish (ajustado para Payara)
CMD ["/opt/payara/bin/asadmin", "start-domain"]