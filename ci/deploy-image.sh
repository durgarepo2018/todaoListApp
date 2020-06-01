#!/bin/bash



set -e -u -x

mvn clean package -Dmaven.repo.local=.m2
