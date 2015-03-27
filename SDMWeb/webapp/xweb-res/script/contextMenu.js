
function openPopupMenu(clickEvent, menuElementId)
{	
	var menuElement = document.getElementById(menuElementId);
	menuElement.style.position = 'absolute';
	menuElement.style.top = clickEvent.clientY + 'px';
	menuElement.style.left = clickEvent.clientX + 'px';
	menuElement.style.display = 'block';
	
	document.onclick = new Function('menuElementId', "clearContextMenu('" + menuElementId + "')");
}

function clearContextMenu(menuElementId)
{
	var menuElement = document.getElementById(menuElementId);
	menuElement.style.display = 'none';
}