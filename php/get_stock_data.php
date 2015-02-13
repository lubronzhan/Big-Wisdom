<?php
  	# operator
#$command = '/Library/Frameworks/Python.framework/Versions/2.7/bin/python ./HistoricalDataRequest.py http-api.openbloomberg.com/';
#$command = "./test.sh";
#exec('python HistoricalDataRequest.py http-api.openbloomberg.com/', $output);
#$command = "python HistoricalDataRequest.py";
exec('unset DYLD_LIBRARY_PATH ;');
putenv('DYLD_LIBRARY_PATH');
putenv('DYLD_LIBRARY_PATH=/usr/bin');
$output = shell_exec('./test.sh ' . $_POST["company_name"]);
#system("id");
#echo $_POST["company_name"];
echo $output;
#echo "hello"
$json = json_decode($output);
#echo $json->{'data'};

#echo $output[1];
	#$command = escapeshellcmd('python ./HistoricalDataRequest.py http-api.openbloomberg.com/');
	#$output = shell_exec($command);
	#echo $output;
?>