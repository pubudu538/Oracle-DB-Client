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


sed -i 's/^\(host=\).*/\1'${host}'/' ${DB_CLIENT_HOME}/conf/config.properties
sed -i 's/^\(port=\).*/\1'${port}'/' ${DB_CLIENT_HOME}/conf/config.properties
sed -i 's/^\(db_name=\).*/\1'${db_name}'/' ${DB_CLIENT_HOME}/conf/config.properties
sed -i 's/^\(username=\).*/\1'${username}'/' ${DB_CLIENT_HOME}/conf/config.properties
sed -i 's/^\(password=\).*/\1'${password}'/' ${DB_CLIENT_HOME}/conf/config.properties
sed -i 's/^\(table_name=\).*/\1'${table_name}'/' ${DB_CLIENT_HOME}/conf/config.properties
sed -i 's/^\(number_of_connections=\).*/\1'${number_of_connections}'/' ${DB_CLIENT_HOME}/conf/config.properties
sed -i 's/^\(column_name=\).*/\1'${column_name}'/' ${DB_CLIENT_HOME}/conf/config.properties


echo "Starting Oracle DB Client.."

pushd ${DB_CLIENT_HOME}/bin
    bash run.sh
popd
