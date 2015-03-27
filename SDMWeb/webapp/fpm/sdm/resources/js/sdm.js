function updateSubtable(_url,_id,_totalCells,_offset,_cells,_additionalAttributes){
	
	var emptyCell=null;
	var newRow=null;
	var newContent=null;
	var endOffset=(_totalCells-_offset)-_cells;
	var urlToCall='';
	
	if($('row'+_id+'Icon').hasClass('rowNotExpandedClass')){
		$('row'+_id+'Icon').removeClass('rowNotExpandedClass');
		$('row'+_id+'Icon').addClass('rowExpandedClass');
		newRow=new Element('tr',{'id':'detail'+_id,'style':'border:1px solid white'});
		newRow.injectAfter('row'+_id);
		newContent=new Element('td',{'id':_id+'DetailData','colspan':_cells,'class':'ajaxClass'});
		newContent.injectInside(newRow);
		for(var ic1=0;ic1<_offset;ic1++){
			emptyCell=new Element('td',{'class':'dataEvenRow'});
			emptyCell.injectBefore(newContent);
		}
		newContent.setHTML('loading contents...');
		for(var ic1=0;ic1<endOffset;ic1++){
			emptyCell=new Element('td',{'class':'dataEvenRow'});
			emptyCell.injectAfter(newContent);
		}
		urlToCall=_url+'?timestamp='+(new Date()).getTime()+'&id='+_id;
		if(_additionalAttributes!=null)
			urlToCall+='&'+_additionalAttributes
		ajaxRequest(urlToCall,_id+'DetailData');
	}else{
		$('row'+_id+'Icon').removeClass('rowExpandedClass');
		$('row'+_id+'Icon').addClass('rowNotExpandedClass');
		$('detail'+_id).remove();
	}
}

var modal_centered_popup = null;
var gotPopup = false;

function openModalCenteredWindow(url,_name,width,height,features){

 	if ( width == null ) width = 700;
 	if ( height == null ) height = 400;
 	if ( features == null ) features = "scrollbars=yes,resizable=yes";

	var left = (screen.availWidth-width)/2;
	var top = (screen.availHeight-height)/2;

	if(!gotPopup){
		gotPopup = true;
		modal_centered_popup = window.open(url, _name, features+",width="+width+",height="+height+",left="+left+",top="+top);

		window.onfocus = function(){
			if(gotPopup && modal_centered_popup != null){
				modal_centered_popup.focus();
			}
		}
	}
	
	modal_centered_popup.focus();
	gotPopup = false;
}