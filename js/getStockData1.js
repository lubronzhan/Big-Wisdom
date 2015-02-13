function get_stock_data(company_name){
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
			//console.log(data['data'][0]['securityData']['fieldData'][0]);
			//console.log(data);
			//strArr = data.split('\n');
			console.log(data);

			$('#stock-graph').highcharts('StockChart', {

            chart: {
                type: 'columnrange'
            },

            rangeSelector: {
                selected: 2
            },

            title: {
                text: 'Temperature variation by day'
            },  

            tooltip: {
                valueSuffix: '°C'
            },

            series: [{
                name: 'Temperatures',
                data: data
            }]

        });

		}
	});
}
