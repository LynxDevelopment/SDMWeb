var JsDragDropIt = 
{
	/*****************
	 * drag and drop *
	 *****************/
	
	isdrag : false,						// this flag indicates that the mouse movement is actually a drag.
	mouseStart: [0, 0],					// mouse position when drag starts
	elementStart: [0, 0],				// element position (absolute or relative) when drag starts
	elementOffset: [0, 0],	// element position offset (absolute wrt document) when drag starts
	
	/**
	 * the html element being dragged.
	 */
	elementToMove: null,
	
	/**
	 * this variable stores the orginal z-index of the object being dragged in order
	 * to restore it upon drop.
	 */ 
	originalZIndex: 0,
	
	/**
	 * an array containing bounds to be respected while dragging elements,
	 * these bounds are left, top, left + width, top + height of the parent element.
	 */
	bounds: {},
	
	mousemove: function(e)
	{
		if (JsDragDropIt.isdrag)
		{
			var mouseDeltaX = Event.pointerX(e) - JsDragDropIt.mouseStart[0];
			var mouseDeltaY = Event.pointerY(e) - JsDragDropIt.mouseStart[1];
			
			
			var newElementX = JsDragDropIt.elementOffset[0] + mouseDeltaX;
			var newElementY = JsDragDropIt.elementOffset[1] + mouseDeltaY;
			
			var elementDimensions = JsDragDropIt.elementToMove.getDimensions();
			
			// check bounds
			// note: the "-1" and "+1" is to avoid borders overlap
			if(JsDragDropIt.bounds.left && newElementX < JsDragDropIt.bounds.left)
				newElementX = JsDragDropIt.bounds.left + 1;
			if(JsDragDropIt.bounds.right && newElementX + elementDimensions.width > JsDragDropIt.bounds.right)
				newElementX = JsDragDropIt.bounds.right - elementDimensions.width - 1;
			if(JsDragDropIt.bounds.top && newElementY < JsDragDropIt.bounds.top)
				newElementY = JsDragDropIt.bounds.top + 1;
			if(JsDragDropIt.bounds.bottom && newElementY + elementDimensions.height > JsDragDropIt.bounds.bottom)
				newElementY = JsDragDropIt.bounds.bottom - elementDimensions.height - 1;


			// move element
			var newElementLeft = JsDragDropIt.elementStart[0] + newElementX - JsDragDropIt.elementOffset[0];
			var newElementTop = JsDragDropIt.elementStart[1] + newElementY - JsDragDropIt.elementOffset[1];
			JsDragDropIt.elementToMove.setStyle({left : newElementLeft + 'px', top : newElementTop + 'px'});

			// invoke callback on element			
			if(JsDragDropIt.elementToMove.onDrag)
				JsDragDropIt.elementToMove.onDrag();
			else
			{
				var onDragAttribute = JsDragDropIt.elementToMove.getAttribute("onDrag");
				if(onDragAttribute)
				{
					JsDragDropIt.elementToMove.onDrag = eval('function() {' + onDragAttribute + '}');
					JsDragDropIt.elementToMove.onDrag();
				}
			}
			
			// invoke global callback
			if(JsDragDropIt.onDrag)
				JsDragDropIt.onDrag(JsDragDropIt.elementToMove);
		}
	},
	
	/**
	 * finds the innermost draggable element starting from the one that generated the event "e"
	 * (i.e.: the html element under mouse pointer), then setup the document's onmousemove function to
	 * move the element around.
	 */
	startDrag: function(e) 
	{
		var eventSource = $(Event.element(e));
		if(eventSource.tagName == 'HTML')
			return;
	
		while (eventSource != document.body && !eventSource.hasClassName("draggable"))
		{  	
			eventSource = $(eventSource.parentNode);
		}
		// if a draggable element was found, calculate its actual position
		if (eventSource.hasClassName("draggable"))
		{
			JsDragDropIt.isdrag = true;
			JsDragDropIt.elementToMove = $(eventSource);
			var parentNode = $(JsDragDropIt.elementToMove.parentNode);
			
			// set relative positioning on dragged element
			JsDragDropIt.elementToMove.makePositioned();
			
			// calculate start point
			if(JsDragDropIt.elementToMove.getStyle('position') == 'absolute')
				JsDragDropIt.elementStart = Position.positionedOffset(JsDragDropIt.elementToMove);
			else
				JsDragDropIt.elementStart = [parseFloat(JsDragDropIt.elementToMove.style.left || 0),
										 parseFloat(JsDragDropIt.elementToMove.style.top || 0)];
			
			JsDragDropIt.elementOffset = Position.cumulativeOffset(JsDragDropIt.elementToMove);
			// calculate mouse start point
			JsDragDropIt.mouseStart[0] = Event.pointerX(e);
			JsDragDropIt.mouseStart[1] = Event.pointerY(e);
			
			JsDragDropIt.bounds = {};
			
			if(parentNode != document.body)
			{
				// calculate bounds as left, top, left + width, top + height of the parent element
				var parentPosition = Position.cumulativeOffset(parentNode);
				JsDragDropIt.bounds.left = parentPosition[0];
				JsDragDropIt.bounds.top = parentPosition[1];
				
				var parentDimensions = parentNode.getDimensions();
				JsDragDropIt.bounds.right = JsDragDropIt.bounds.left + parentDimensions.width;
				JsDragDropIt.bounds.bottom = JsDragDropIt.bounds.top + parentDimensions.height;		
			}
			
			JsDragDropIt.originalZIndex = JsDragDropIt.elementToMove.getStyle('z-index');
			
			JsDragDropIt.elementToMove.setStyle({zIndex : 30});
			
			// ghosting
			Element.setStyle(JsDragDropIt.elementToMove, {opacity: 0.6});

			// invoke callback on element
			if(JsDragDropIt.elementToMove.onDragStart)
				JsDragDropIt.elementToMove.onDragStart();
			else
			{
				var onDragStartAttribute = JsDragDropIt.elementToMove.getAttribute("onDragStart");
				if(onDragStartAttribute)
				{
					JsDragDropIt.elementToMove.onDragStart = eval('function() {' + onDragStartAttribute + '}');
					JsDragDropIt.elementToMove.onDragStart();
				}
			}
			
			// invoke global callback
			if(JsDragDropIt.onDragStart)
				JsDragDropIt.onDragStart(JsDragDropIt.elementToMove);
			
			Event.observe(document, "mousemove", JsDragDropIt.mousemove);
			return false;
		}
	},
	
	stopDrag: function(e)
	{
		JsDragDropIt.isdrag=false;
		if(JsDragDropIt.elementToMove)
		{
			JsDragDropIt.elementToMove.style.zIndex=JsDragDropIt.originalZIndex;
			// restore opacity from ghosting
			Element.setStyle(JsDragDropIt.elementToMove, {opacity: 1});
			Event.stopObserving(document, "mousemove", JsDragDropIt.mousemove);
	
			// invoke callback on element
			if(JsDragDropIt.elementToMove.onDrop)
				JsDragDropIt.elementToMove.onDrop();
			else
			{
				var onDropAttribute = JsDragDropIt.elementToMove.getAttribute("onDrop");
				if(onDropAttribute)
				{
					JsDragDropIt.elementToMove.onDrop = eval('function() {' + onDropAttribute + '}');
					JsDragDropIt.elementToMove.onDrop();
				}
			}
			
			// invoke global callback
			if(JsDragDropIt.onDrop)
				JsDragDropIt.onDrop(JsDragDropIt.elementToMove);
		}
	},
	
	init: function()
	{
		// check prototype presence
	    if((typeof Prototype == 'undefined') || 
	       (typeof Element == 'undefined') || 
	       (typeof Element.Methods == 'undefined') ||
	       parseFloat(Prototype.Version.split(".")[0] + "." +
	                  Prototype.Version.split(".")[1]) < 1.5)
	       throw("js-d&d.it requires the Prototype JavaScript framework >= 1.5.0");
		
		Event.observe(document, "mousedown", JsDragDropIt.startDrag);
		Event.observe(document, "mouseup", JsDragDropIt.stopDrag);
	}
}

JsDragDropIt.init();