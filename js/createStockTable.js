function createStockTable(){
	var tableStr = '<table class="table"><tr><th>Comapny Stock Code</th><th>Recommendation Score</th><th>Stars</th></tr>';
	tableStr += "<tr><td>GOOGL</td><td>0.89</td><td><span class='glyphicon glyphicon-star' aria-hidden='true'></span> </td></tr>"
	$(tableStr + '</table>').appendTo("#table-container");
	$("#table-container").show();
}