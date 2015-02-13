#echo $1

/Library/Frameworks/Python.framework/Versions/2.7/bin/python ./HistoricalDataRequest.py http-api.openbloomberg.com/ $1
cd java/bin
java HacksonMain $1
#java -jar run.jar