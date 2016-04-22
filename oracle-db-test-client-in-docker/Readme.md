-------- Building the Docker Image -----------

1. Create a folder call packages within the home folder.
2. Copy jdk-7u80-linux-x64.tar.gz pack to packages folder in Home location.
3. Run bash build.sh to build the docker Image


---------- Running the docker Container ----------

1. Update following values in the run.sh

host=<DB Host Machine>
port=<DB Port>
db_name=<DB Name>
username=<Username>
password=<Password>
table_name=<Any table name that is in the mentioned DB>
number_of_connections=<Number of connections to connect to the db>
column_name=<Any valid column name of the mentioned table>

2. Run bash run.sh to run the docker container in your machine.