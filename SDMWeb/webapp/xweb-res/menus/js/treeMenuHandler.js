/** Handle Menu tree **/
 

function dropMenu( itemId, newParent, itemBefore, treeSource, treeTarget )
{
	if ( treeSource.getRootId() != '/' ) return 
	menuHandler.dropItem( itemId, newParent, itemBefore, treeSource, treeTarget  );
}


function selectMenuItem( itemId )
{
	menuHandler.selectItem( itemId )
}


function addGroup( itemId, newParent, itemBefore, treeSource, treeTarget )
{
	if ( treeSource.getRootId() != 'availableGroupTree' ) return 
	addGroupHandler.dropItem( itemId, newParent, itemBefore, treeSource, treeTarget )
}

function removeGroup( itemId, newParent, itemBefore, treeSource, treeTarget )
{
	if ( treeSource.getRootId() != 'associatedGroupTree' ) return 
	removeGroupHandler.dropItem( itemId, newParent, itemBefore, treeSource, treeTarget )
}

function menuInsertPage()
{
	menuHandler.insertItemPage()
}

function getMenuItemElement()
{
	return 'menuItemTree'
}

function menuOnOpenHandler( itemId, currentStatus )
{
	menuTree.setOnLoadingEnd( new Function('') );
	return true;
}

function getMenuDetailAction( treeSource )
{
	var url =  '/xweb-res/menus/editmenuitem.xwb';
	url += '?MENU_ITEM_ID=' + escape( treeSource.getSelectedItemId() );
	url += '&no_template=true';
	return url;
}


function getEmptyMenuDetailAction( treeSource, selectedItemId )
{
	var url =  '/xweb-res/menus/newmenuitem.xwb'
	url += '?PARENT=' + escape( selectedItemId );
	url += '&no_template=true';
	return url
}

function getMoveMenuAction( itemId, newParent, itemBefore, treeSource, treeTarget )
{
	var url=  '/xweb-res/menus/moveMenuItem.do';
	url += '?MENU_ITEM_ID=' + escape( itemId );
	url += '&NEW_PARENT=' + escape( newParent );
	if ( itemBefore != '' && itemBefore != null ) url += '&MENU_ITEM_BEFORE=' + escape( itemBefore );
	return url;

}

function getUpdateMenuAction( treeSource, form )
{
	var url= '/xweb-res/menus/updateMenuItem.do'
	url += '?MENU_ITEM_TITLE=' + escape( form.MENU_ITEM_TITLE.value );
	url += '&MENU_ITEM_ID=' +  escape( form.MENU_ITEM_ID.value );
	url += '&URL=' +  escape( form.URL.value );
	url += '&ICON1=' +  escape( form.ICON1.value );
	url += '&ICON2=' +  escape( form.ICON2.value );
	return url;
}

function getInsertMenuAction( treeSource, form )
{
	var url=  '/xweb-res/menus/createMenuItem.do';
	url += '?PARENT=' + escape( form.PARENT.value );
	url += '&MENU_ITEM_ID=' + escape( form.MENU_ITEM_ID.value );
	url += '&MENU_ITEM_TITLE=' + escape( form.MENU_ITEM_TITLE.value );
	url += '&URL=' + escape( form.URL.value );
	url += '&ENABLED=T';
	url += '&MARKED=F';
	url += '&ICON1=' + escape( form.ICON1.value );
	url += '&ICON2=' + escape( form.ICON2.value );
	url += '&LANGUAGE_ID=it'
	return url;
}

function getRadioValue( radioList )
{

	for ( i=0; i<radioList.length; i++ )
	{
		if ( radioList[i].checked )
		{
			return radioList[i].value;
		}
	}
}

function getDeleteMenuAction( treeSource, selectedItems )
{
	var url=  '/xweb-res/menus/deleteMenuItem.do?';
	
	for ( var i=0; i<selectedItems.length; i++ )
	{
		url += 'menuitemchecked=' + escape( selectedItems[i] );
		if ( i != selectedItems.length - 1 ) url += '&';
	}
	
	return url;
}

function getEnableMenuAction( treeSource, selectedItems )
{
	
	return getChangeStatusAction( selectedItems, 'T', null )
}

function getDisableMenuAction( treeSource, selectedItems )
{	
	return getChangeStatusAction( selectedItems, 'F', null )
}


function getMarkMenuAction( treeSource, selectedItems )
{	
	return getChangeStatusAction( selectedItems, null, 'T' )
}

function getUnmarkMenuAction( treeSource, selectedItems )
{	
	return getChangeStatusAction( selectedItems, null, 'F' )
}

function getChangeStatusAction( items, enabledStatus, markedStatus )
{
	var url=  '/xweb-res/menus/updateMenuItemsFlags.do?';
	
	for ( var i=0; i<items.length; i++ )
	{
		url += 'menuitemchecked=' + escape( items[i] );
		if ( i != items.length - 1 ) url += '&';
	}
	
	if( enabledStatus != null ) url += '&enabled=' + enabledStatus;
	if( markedStatus  != null ) url += '&marked=' + markedStatus;
	
	return url
}


function getCloneMenuAction( treeSource )
{
	var newId = prompt( 'Nuovo id menu' );
	
	if( newId == null ) return null;
	
	var url=  '/xweb-res/menus/cloneMenuItem.do';
	url += '?MENU_ITEM_ID=' + escape( treeSource.getSelectedItemId() );
	url += '&NEW_MENU_ITEM=' + escape( newId );
	
	return url;
}

function getConfirmDeleteMenuMessage( treeSource, multi )
{
	if ( multi ) return 'Cancellare tutti i menu selezionati ?' 
	else
	{
		 var selectedItem = treeSource.getSelectedItemId();
		 
		 var message = "Cancellare il menu " + treeSource.getItemText( selectedItem );
		 
		 if ( treeSource.hasChildren( selectedItem ) > 0  ) 
		 	return message + ' e tutti i suoi sottomenu ? '
		 else 
		 	return message;
	}
}

function getConfirmDropAsSiblingMessage( dragItemId, targetItemId, targetBeforeId, treeSource, treeTarget )
{
	return 'Spostare il menu ' + treeSource.getItemText( dragItemId ) + ' prima di ' + treeTarget.getItemText( targetBeforeId ) + ' ?'
}

function getConfirmDropAsChildMessage( dragItemId, targetItemId, targetBeforeId, treeSource, treeTarget ) 
{
	var text = targetItemId == MENU_ROOT_ITEM ? MENU_ROOT_ITEM : treeTarget.getItemText( targetItemId );
	
	return 'Spostare il menu ' + treeSource.getItemText( dragItemId ) + ' come sottomenu di ' + text + ' ?'
}


function getAddMenuGroupAction( dragItemId, targetItemId, targetBeforeId, treeSource, treeTarget )
{
	var url = '/xweb-res/menus/addMenuItemToGroup.do'
	url += '?MENU_ITEM_ID=' + escape( menuTree.getSelectedItemId() )
	url += '&group_id=' + escape( dragItemId )
	return url;
}

function getRemoveMenuGroupAction( dragItemId, targetItemId, targetBeforeId, treeSource, treeTarget )
{
	var url = '/xweb-res/menus/removeMenuItemFromGroup.do'
	url += '?MENU_ITEM_ID=' + escape( menuTree.getSelectedItemId() )
	url += '&group_id=' + escape( dragItemId )
	return url;
}

function onCtxMenuClick( ctxMenuId, treeItemId )
{
	if ( ctxMenuId == 'deleteItem' ) menuHandler.deleteItem();
	else if ( ctxMenuId == 'enableItem' ) menuHandler.enableItem();
	else if ( ctxMenuId == 'disableItem' ) menuHandler.disableItem();
	else if ( ctxMenuId == 'refreshItem' ) menuHandler.refreshItem( treeItemId );
	else if ( ctxMenuId == 'insertItem' ) menuHandler.insertItemPage();
	else if ( ctxMenuId == 'markItem' ) menuHandler.markItem();
	else if ( ctxMenuId == 'unmarkItem' ) menuHandler.unMarkItem();
	else if ( ctxMenuId == 'copyItem' ) { menuHandler.cloneItem(); }
}

function rightClickHandler(itemId)
{
	menuTree.selectItem(itemId, true);
}