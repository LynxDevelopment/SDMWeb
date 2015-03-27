function stripeTables(applyToClass,evenClass,oddClass){

	var tables=$$('table.'+applyToClass).each(function stripeTable(e){

		var bodies=e.tBodies;
		for(var j=0;j<bodies.length;j++){
			var rows=bodies[j].rows;
			for(var i=0;i<rows.length;i++){
				var row=$(rows[i]);
				var rowClass;
				if(i%2==1){
					row.addClass(oddClass);
					row.removeClass(evenClass);
				}else{
					row.addClass(evenClass);
					row.removeClass(oddClass);
				}
			}
		}
	});
}

function addToBreadcrumbs(label){

	addToBreadcrumbs(label,null);
}

function addToBreadcrumbs(label, url){

	var ul=$('breadcrumbs');

	if(!ul) return;

	var li=document.createElement('LI');
	var a=document.createElement('A');
	li.appendChild(a);
	if(url!=null)
		a.href=url;
	a.innerHTML=label;

	if(ul.children.length>0){
		var separator=document.createElement('LI');
		separator.innerHTML='>';
		ul.appendChild(separator);
	}

	ul.appendChild(li);

	for(var ic1=0;ic1<ul.children.length;ic1++){
		if(ic1==(ul.children.length-1)){
			ul.children[ic1].className='here';
		}else{
			ul.children[ic1].className='';
		}
	}
}