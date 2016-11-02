#!/usr/bin/env bash

# create Docker container and create database inside container

BASE_DIR=$(dirname $0)
USER_API=user-api
CONTAINER=assembly_mysql_1


function initMysql(){
  echo "the password of root:"
  sql=$(cat $BASE_DIR/mysql-init.sql)
  read -s password
  docker exec -it $CONTAINER mysql -u root -p$password -e "$sql"
}


initMysql