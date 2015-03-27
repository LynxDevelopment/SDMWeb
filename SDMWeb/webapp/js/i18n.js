	var popupElement;

	function applyI18NTransformation(element)
	{
		// add listener for context menu
		$(element).addEvent('contextmenu', showI18NContextMenu);
	}
	
	function showI18NContextMenu(event)
	{
		popupElement = this;
		event = new Event(event);
		var menu = $('i18n_contextMenu');
		menu.setStyle('top', event.page.y);
		menu.setStyle('left', event.page.x);
		menu.setStyle('display', 'block');
		new Event(event).stop();
	}
	
	function hideI18NContextMenu()
	{
		$('i18n_contextMenu').setStyle('display', 'none');
	}
	
	function showQuickConfig(event)
	{
		var panel = $('i18n_quick_config');
		
		// perform Ajax request
		var updater = new Ajax(config_panel_url, 
			{data : {
				referrer: document.location + "",
				baseName: popupElement.getProperty('baseName'),
				locale: popupElement.getProperty('locale'),
				key: popupElement.getProperty('key')
				}, 
			evalScripts : 'true', update: panel, onComplete: internalShowQuickConfig}).request();
		panel.style.top = event.clientY;
		panel.style.left = (document.body.offsetWidth * 0.1 / 2) + 'px';
	}

	function internalShowQuickConfig()
	{
		var panel = $('i18n_quick_config');
		panel.setStyle('display', 'block');
		panel.addEvent('click', function(event){new Event(event).stop();});
		$(document).addEvent('click', hideQuickConfig);
	}
	
	function hideQuickConfig()
	{
		$('i18n_quick_config').setStyle('display', 'none');
		$(document).removeEvent('click', hideQuickConfig);
	}
	
	function parseMessageString(string)
	{
		var result;
		string = "result = {" + string.slice(2, string.length - 1) + "}";
		eval(string);
		return result;
	}
	
	/**
	 * Constructor, parameters are:
	 * visitor: the visitor implementation, it must be a class with a visit(element) method.
	 * scanElementsOnly: a flag telling whether to scan html elements only or all html nodes.
	 */
	function DocumentScanner(visitor, scanElementsOnly)
	{
		this.visitor = visitor;
		this.scanElementsOnly = scanElementsOnly;
	
		/**
		 * Scans the element
		 */
		this.scan = function(element)
		{
			var i;
			var increment = this.visitor(element);
			if(increment >= 0)
			{
				// visit child elements
				var children = element.childNodes;
				for(i = 0; i < children.length; i++)
				{
					if(!this.scanElementsOnly || children[i].nodeType == 1)
					{
						var newIncrement = this.scan(children[i]);
						if(newIncrement > 0)
							i += newIncrement;
					}
				}
			}
			return increment;
		}	
	}
	
	var regex = /#\[.+?\]/;
	
	function visit(node)
	{
		var increment = 0;
		switch(node.nodeType)
		{
			case 1:
				// an element
				
				if(node.tagName == 'IMG')
				{
					var match = regex.exec(node.alt);
					if(match)
					{
						var parsed = parseMessageString(match[0]);
						node.alt = parsed.value;
						var n = $(node);
						n.addClass(parsed.className);
						n.setAttribute('baseName', parsed.baseName);
						n.setAttribute('locale', parsed.locale);
						n.setAttribute('key', parsed.key);
						n.addEvent('contextmenu', showI18NContextMenu);
					}
					if(node.src)
					{
						var src = unescape(node.src);
						match = regex.exec(src);
						if(match)
						{
							var parsed = parseMessageString(match[0]);
							node.src = src.substring(0, match.index) + parsed.value + src.substring(match.index + match[0].length);
						}
					}
				}
				if(node.tagName == 'SCRIPT' || Element.hasClass(node, 'no_i18n'))
					return -1;
				break;
			case 3:
				// a text
				var text = node.data;
				var match = regex.exec(text);
				var currentIndex = 0;
				while(match)
				{
					var parsed = parseMessageString(match[0]);
					var element = document.createElement("span");
					element.className = parsed.className;
					element.setAttribute('baseName', parsed.baseName);
					element.setAttribute('locale', parsed.locale);
					element.setAttribute('key', parsed.key);
					element.appendChild(document.createTextNode(parsed.value));
					
					if(currentIndex < match.index)
					{
						node.parentNode.insertBefore(document.createTextNode(text.substring(currentIndex, match.index)), node);
						increment++;
					}
					
					node.parentNode.insertBefore(element, node);
					$(element).addEvent('contextmenu', showI18NContextMenu);
					increment++;
					
					currentIndex = match.index + match[0].length;
					match = regex.exec(text.substring(currentIndex));
				}

				if(currentIndex > 0)
				{				
					// append the remaining text
					node.parentNode.insertBefore(document.createTextNode(text.substring(currentIndex)), node);
					
					// remove the original text
					node.parentNode.removeChild(node);
				}
				break;
		}
		
		return increment;
	}
	
	function apply()
	{
		new DocumentScanner(visit, false).scan(document.body);
		$(document).addEvent('click', hideI18NContextMenu);
		$('i18n_showQuickConfMenuItem').addEvent('click', showQuickConfig);
		$('i18n_quick_config').makeDraggable();
	}