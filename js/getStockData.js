function get_stock_data(){
	company_name = $("#artist_search").val().toUpperCase();


	$("#company-title").text(company_name);





	$.ajax({
		url: "php/get_stock_data.php",  
		type: "POST",
		data: {company_name:company_name},
		//dataType: "json",
		error: function(data){ 
			alert('Error loading document'); 
			alert(data);
		},  
		success: function(data){
			console.log(data);
			$(function () {

	dataArr = processData(data);
    $.getJSON('http://www.highcharts.com/samples/data/jsonp.php?filename=aapl-c.json&callback=?', function (data) {
        // Create the chart
        $('#stock-graph').highcharts('StockChart', {


            rangeSelector : {
                selected : 1
            },

            title : {
                text : company_name + ' Stock Price'
            }

            /*series : [{
                name : 'Stock Prince',
                data : jsonData,
                tooltip: {
                    valueDecimals: 2
                }
            }]*/
        });

        var chart = $('#stock-graph').highcharts();
        chart.renderer.path(['M', 875, 80, 'L',875, 600])
            .attr({
                'stroke-width': 1,
                stroke: 'white',
                dashStyle: 'dash'
            })
            .add();
        var nameArr = ['Original Data', 'MA3', 'MA5']
		for(var i=0; i<dataArr.length; i++)
		{
			var series = chart.addSeries({                        
				name: nameArr[i],
				data: dataArr[i]
			}, false);
		}
		chart.redraw();	//redraw chart after all data loaded

    });

});

		}

	});



}

function processData(data){

	var myDate = new Date();
	var h = myDate.getFullYear();
	var m = myDate.getMonth() + 1;
	var d = myDate.getDate();
	$doc_arr = data.split("\n");

	//console.log(data);
	jsonArr = [];
	jsonStr = "[";
	originIndex = ($doc_arr.length-1) / 3;
	index = originIndex;
	for(i = 0; i < $doc_arr.length-2; i++){
		var num = $doc_arr[i].toString();
		var avg_price;
		if(num === ""){
			avg_price = 'null';
			time_stamp = get_today_timestamp(h,m,d,index--);
			line_result = "[" +  time_stamp + "," + avg_price + "],";
			jsonStr += line_result;
			continue;
		}
		else avg_price = parseFloat(num);

		if(num === undefined) {
			continue;
		}
		if(num === "*************************"){ //next curve
			jsonStr = jsonStr.substring(0, jsonStr.length - 1);
			jsonStr += "]";
			jsonArr.push(JSON.parse(jsonStr));
			jsonStr = "[";
			index = originIndex;
			continue;
		}

		if(isNaN(num) || isNaN(num) ) continue;

		time_stamp = get_today_timestamp(h,m,d,index--);
		line_result = "[" +  time_stamp + "," + avg_price + "],";
		jsonStr += line_result;
	}
	console.log($doc_arr[$doc_arr.length-1]);
	jsonStr = jsonStr.substring(0, jsonStr.length - 1);
	jsonStr += "]";
	jsonArr.push(JSON.parse(jsonStr));
	//console.log(jsonArr);
	var star = "";
	var level = $doc_arr[$doc_arr.length-2];
	console.log("aaa" + level);
	var emp = 5 - level;
	for(i = 0; i < level; i++){
		star += "<span class='glyphicon glyphicon-star' ></span>";
	}

	for(i = level; i < 5; i++){
		star += "<span class='glyphicon glyphicon-star-empty' ></span>";
	}
	$("#star-panel").empty();
	$("#star-panel").append(star);
	return jsonArr;
}


function get_today_timestamp(local_year, local_month, local_day, offset){
	
	 $tmp = ( local_year - 1973 - ( local_year -1973 ) % 4 ) / 4 * 126230400 + (local_year - 1973) % 4 * 31536000  + 94608000;
     monthday = [ 0, 2678400, 5097600, 7776000, 10368000, 13046400, 15638400, 18316800, 20995200, 23587200, 26265600, 28857600 ];
	 if ( local_year % 4 == 0 && local_month > 2 ) {
      	$tmp += monthday[local_month-1] + 86400 + local_day * 86400 ;
      	return ($tmp - offset * 86400) * 1000;
     } else {
     	$tmp += monthday[local_month-1] + local_day * 86400 ;
     	return ($tmp - offset * 86400) * 1000;
 	 }
}






