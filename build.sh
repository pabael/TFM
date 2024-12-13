#!/bin/bash

# Instalar Maven
apt-get update
apt-get install -y maven

# Ejecutar el comando Maven para construir la aplicación
mvn clean package
