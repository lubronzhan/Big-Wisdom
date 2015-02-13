$(function () {
    $.getJSON('http://www.highcharts.com/samples/data/jsonp.php?filename=range.json&callback=?', function (data) {

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
    });

});