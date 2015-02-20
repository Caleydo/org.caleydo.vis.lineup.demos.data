//remove all garbage
$('span.tooltipspan').remove()
$('tfoot').remove()
$('p.pt5, a.preisvergleich-btn, div.gesamtwertungwrapper, a.tooltip, div.preis-leistungwrapper').remove()
$('br').remove()
$('tr.repated-header').remove()
$('th').attr('class',null)

$('#mainTable').appendTo('body')
$('body > :not(#mainTable)').remove()
$('th, th span').on('click', function() { return false; })

//make it plain
$('td').replaceWith(function() { return $('<td>').text($(this).text()); })

//copy the simple table to excel
//remove columsn and remove some text garbage: ab €, units, ...
//save as CSV tab separated with UTF-8 encoding
//open in text editor and regex replace: ,(\d) -> .$1