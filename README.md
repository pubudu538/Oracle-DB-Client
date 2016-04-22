 Oracle DB Connection Testing
======================

This client can be used for Oracle db connection testing.

Instructions!!!

1. Update following values in oracle-db-test-1.0.0-SNAPSHOT/conf/config.properties file.

host=<DB Host Machine>
port=<DB Port>
db_name=<DB Name>
username=<Username>
password=<Password>
table_name=<Any table name that is in the mentioned DB>
number_of_connections=<Number of connections to connect to the db>
column_name=<Any valid column name of the mentioned table>
sleep_time=<Sleep time for querying data> (Specify in milli seconds)

2. Go to oracle-db-test-1.0.0-SNAPSHOT/bin folder and execute run.sh bash file.

3. To run in nohup mode replace last line in run.sh with following.

nohup java -cp "${class_path}" ${properties} ${debug} db.Main $* > /dev/null 2>&1 & echo $! > run.pid &

Note : Log and pid can be found in run.log and run.pid files. (This is when running in nohup mode)


