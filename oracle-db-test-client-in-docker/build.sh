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

set -e

image_version=1.0.0-SNAPSHOT
prgdir=`dirname "$0"`
script_path=`cd "$prgdir"; pwd`
client_zip=oracle-db-test-1.0.0-SNAPSHOT.zip
oracle_db_client_path=`cd ${script_path}/../oracle-db-test-client; pwd`


echo "----------------------------------"
echo "Building Oracle DB Client"
echo "----------------------------------"
pushd ${oracle_db_client_path}
mvn clean install
cp -v target/${client_zip} ${script_path}/packages/
popd


echo "----------------------------------"
echo "Building Docker Image"
echo "----------------------------------"
docker build -t pubudu/oracle-db-client:${image_version} .
echo "Base docker image built successfully"