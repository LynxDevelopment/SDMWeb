/**
 * Common JS functions
 */

/**
 * Reset document value
 * @param id
 */
function resetValue(id){
    if( undefined !== document.getElementById(id)){
     document.getElementById(id).value = "";
     alert("Elem["+id+"]="+value+" === "+document.getElementById(id));
    }
}

/**
 * Set Field value
 * @param id
 * @param value
 */
function setValue(id,value){
	var elem = document.getElementById(id);
	if ( undefined !== elem ){
		elem.value=value;
		//alert("Elem["+id+"]="+value+" === "+document.getElementById(id));
	}
}

/**
 * Reset bunch of values
 * @param list
 */
function resetValues(list) {
    //alert("LIST["+list.length+"]->"+list);
    for(i=0;i<list.length;i++){
        var elem = document.getElementById(list[i]);
        if( undefined !== elem && null != elem){
            //alert("List["+i+"]="+elem.value);                 
            document.getElementById(list[i]).value = "";
        } else {
            //alert("List["+list[i]+"] is undefined");
        }
    }           
    return;
}

/***
 * Order combobox
 * @param selElem
 */
function sortSelect(selElem) {
    var tmpAry = new Array();
    
    // Copy into ARRAY and extract first element ( value = "" )
    
    var item=0;
    for (var i=0;i<selElem.options.length;i++) {
    	if (selElem.options[i].value==""){
			first = new Array();
			first[0] = selElem.options[i].text;
            first[1] = selElem.options[i].value;
		} else {
			tmpAry[item] = new Array();
            tmpAry[item][0] = selElem.options[item].text;
            tmpAry[item][1] = selElem.options[item].value;
            item++;
		}
    }
    
    // Sort Array and clear select
    tmpAry.sort();
    while (selElem.options.length > 0) {
        selElem.options[0] = null;
    }
    
    var index=0;
    
    if (first!=null){
    	selElem.options[0] = new Option(first[0],first[1]);
    	index=1;
    }
    
    for (var i=0;i<tmpAry.length;i++) {
            var op = new Option(tmpAry[i][0], tmpAry[i][1]);
            selElem.options[index+i] = op;
    }
    return;
}
