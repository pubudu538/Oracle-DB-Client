 Oracle DB Connection Testing
======================

This client can be used for Oracle db connection testing.

Instructions!!!

1). Update following values in oracle-db-test-1.0.0-SNAPSHOT/conf/config.properties file.

host=(DB Host Machine) <br />
port=(DB Port) <br />
db_name=(DB Name) <br />
username=(Username) <br />
password=(Password) <br />
table_name=(Any table name that is in the mentioned DB) <br />
number_of_connections=(Number of connections to connect to the db) <br />
column_name=(Any valid column name of the mentioned table) <br />
sleep_time=(Sleep time for querying data) (Specify in milli seconds) <br />

2). Go to oracle-db-test-1.0.0-SNAPSHOT/bin folder and execute run.sh bash file.

3). To run in nohup mode replace last line in run.sh with following.

nohup java -cp "${class_path}" ${properties} ${debug} db.Main $* > /dev/null 2>&1 & echo $! > run.pid &

Note : Log and pid can be found in run.log and run.pid files. (This is when running in nohup mode)


