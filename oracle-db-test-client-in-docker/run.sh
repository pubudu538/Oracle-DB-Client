#!/bin/bash
# ------------------------------------------------------------------------
#
# Copyright 2005-2015 WSO2, Inc. (http://wso2.com)
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License
#
# ------------------------------------------------------------------------

image_version=1.0.0-SNAPSHOT

host=192.168.54.97
port=1521
db_name=ora11g
username=IS510CARBONDB
password=IS510CARBONDB
table_name=DBUSER
number_of_connections=10
column_name=USERNAME

name="pubci-odc"
container_id=`docker run -d -P --name ${name} -e host=${host} -e port=${port} -e db_name=${db_name} -e username=${username} \
-e password=${password} -e table_name=${table_name} -e number_of_connections=${number_of_connections} -e column_name=${column_name} \
pubudu/oracle-db-client:${image_version}`

member_ip=`docker inspect --format '{{ .NetworkSettings.IPAddress }}' ${container_id}`

echo "Container started: [name] ${name} [ip] ${member_ip} [container-id] ${container_id}"
docker exec -it ${container_id} bash
	

