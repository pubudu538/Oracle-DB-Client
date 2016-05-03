-------- Building the Docker Image -----------

1. Create a folder call packages within the home folder.
2. Copy jdk-7u80-linux-x64.tar.gz pack to packages folder in Home location.
3. Run bash build.sh to build the docker Image


---------- Running the docker Container ----------

1. Update following values in the run.sh

host=(DB Host Machine) <br />
port=(DB Port) <br />
db_name=(DB Name) <br />
username=(Username) <br />
password=(Password) <br />
table_name=(Any table name that is in the mentioned DB) <br />
number_of_connections=(Number of connections to connect to the db) <br />
column_name=(Any valid column name of the mentioned table) <br />
sleep_time=(Sleep time for querying data) (Specify in milli seconds) <br />

2. Run bash run.sh to run the docker container in your machine.
