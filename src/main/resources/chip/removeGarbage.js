/**
 * How to use
 * 1. Open [Chip Smartphone Test](http://www.chip.de/bestenlisten/Bestenliste-Handys--index/detail/id/900/)
 * 2. Open the browser console
 * 3. Paste the JS code below and execute it
 * 4. Copy the simple table to excel
 * 5. Remove columns and remove some text garbage: ab €, units, ...
 * 6. Save as CSV tab separated with UTF-8 encoding
 * 7. Open in text editor and regex replace: ,(\d) -> .$1
 */ 

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
