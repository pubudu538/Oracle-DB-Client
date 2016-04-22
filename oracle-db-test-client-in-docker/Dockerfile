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

FROM ubuntu:15.10
MAINTAINER pubudu538@gmail.com

ENV DEBIAN_FRONTEND noninteractive


# JDK
ENV JDK_VERSION 1.7.0_80
ENV JDK_TAR_FILE jdk-7u80-linux-x64.tar.gz
ENV JAVA_HOME /opt/jdk${JDK_VERSION}

ENV DB_CLIENT oracle-db-test-1.0.0-SNAPSHOT
ENV DB_CLIENT_FILE ${DB_CLIENT}.zip
ENV DB_CLIENT_HOME /opt/${DB_CLIENT}

# Docker Issue #9299 - for demo purposes
ENV TERM xterm

WORKDIR /opt/

RUN apt-get install -y \
        zip 


RUN apt-get update
RUN apt-get install software-properties-common -y
RUN add-apt-repository ppa:webupd8team/java -y
RUN apt-get update
RUN echo debconf shared/accepted-oracle-license-v1-1 select true | debconf-set-selections
RUN apt-get install oracle-java8-installer -y
RUN apt-get install oracle-java8-set-default


# ------------------------------
# Install JDK, Oracle DB client
# ------------------------------
ADD  packages/${JDK_TAR_FILE} /opt/
ADD  packages/${DB_CLIENT_FILE} /opt/
COPY scripts/init.sh /opt/

RUN unzip -q /opt/${DB_CLIENT_FILE} 
RUN chmod +x /opt/init.sh
RUN rm /opt/${DB_CLIENT_FILE}


ENTRYPOINT /opt/init.sh
