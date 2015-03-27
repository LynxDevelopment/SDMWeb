var isExtended = 0;
var height = 250;
var width = 200;
var slideDuration = 500;
var opacityDuration = 800;

function extendContract(){
	
	if(isExtended == 0){
		
		sideBarSlide(0, height, 0, width);
		
		sideBarOpacity(0, 1);
	
		isExtended = 1;
		
		// make expand tab arrow image face left (inwards)
		$('sideBarTab').childNodes[0].src = $('sideBarTab').childNodes[0].src.replace(/(\.[^.]+)$/, '-active$1');
		
	}
	else{
		
		sideBarSlide(height, 0, width, 0);
		
		sideBarOpacity(1, 0);
		
		isExtended = 0;
		
		// make expand tab arrow image face right (outwards)
		
		$('sideBarTab').childNodes[0].src = $('sideBarTab').childNodes[0].src.replace(/-active(\.[^.]+)$/, '$1');
	}

}

function onSideBarDrop() {};

function sideBarSlide(fromHeight, toHeight, fromWidth, toWidth){
		var myEffects = new Fx.Styles('sideBarContents', {duration: slideDuration, transition: Fx.Transitions.Circ.easeOut});
		myEffects.custom({
			 'height': [fromHeight, toHeight],
			 'width': [fromWidth, toWidth]
		});
}

function sideBarOpacity(from, to){
		var myEffects = new Fx.Styles('sideBarContents', {duration: opacityDuration, transition: Fx.Transitions.Circ.easeOut});
		myEffects.custom({
			 'opacity': [from, to]
		});
}

function getScrollXY() {
	var scrOfX = 0, scrOfY = 0;
	if( typeof( window.pageYOffset ) == 'number' ) {
		//Netscape compliant
		scrOfY = window.pageYOffset;
		scrOfX = window.pageXOffset;
	} else if( document.body && ( document.body.scrollLeft || document.body.scrollTop ) ) {
    //DOM compliant
    	scrOfY = document.body.scrollTop;
    	scrOfX = document.body.scrollLeft;
  	} else if( document.documentElement && ( document.documentElement.scrollLeft || document.documentElement.scrollTop ) ) {
    	//IE6 standards compliant mode
    	scrOfY = document.documentElement.scrollTop;
    	scrOfX = document.documentElement.scrollLeft;
  	}
  	return [ scrOfX, scrOfY ];
}

function init(){
	
	var flgDragging = false;
	var flgDragged = false;
	var startY;
	var startBarY;
	$('sideBarTab').addEvent('click', function(){if(flgDragged == false) extendContract();});
	$('sideBar').addEvent('mousedown', function(event){
		if(flgDragging == false){
			flgDragging = true;
			startBarY = $('sideBarTab').getTop();
			startY = new Event(event).page.y;
			new Event(event).stop();
			flgDragged = false;
		}
	});
	$('sideBar').addEvent('mouseup', function(event){
		flgDragging = false;
		if(flgDragged == true){
			new Event(event).stop();
			onSideBarDrop();
		}
	});
	$('sideBar').addEvent('mouseleave', function(event){
		flgDragging = false;
		if(flgDragged == true){
			new Event(event).stop();
			onSideBarDrop();
		}
	});
	$('sideBar').addEvent('mousemove', function(event){
		if(flgDragging == true){
			var delta = new Event(event).page.y - startY;
			if(Math.abs(delta) > 2){
				newPos = startBarY + delta;
				newPos = Math.max(newPos, 0);
				$('sideBar').setStyle('top', newPos);
				new Event(event).stop();
				flgDragged = true;
			}
		}
	});  	
	var scrollDim = 0;
	window.addEvent('scroll',function(event){
		if(scrollDim != getScrollXY()[0])
		{	
			if(scrollDim < getScrollXY()[0])
				var newRight = parseInt($('sideBar').getStyle('right')) - (getScrollXY()[0] - scrollDim);
			else
				var newRight = parseInt($('sideBar').getStyle('right')) + (scrollDim - getScrollXY()[0]);
			$('sideBar').setStyle('right', newRight);
			scrollDim = getScrollXY()[0];
		}
	});
	
}

window.addEvent('load', function(){init()});