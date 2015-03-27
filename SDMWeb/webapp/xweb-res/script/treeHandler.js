
function checkSelection(sourceEvent)
{
	if(sourceEvent.target)
		var element = sourceEvent.target;
	else
		var element = event.srcElement;	
		
	if(element.tagName != 'SPAN')
	{
		menuTree.clearSelection();
		menuHandler.clearGroupTree();
		document.getElementById(menuHandler.itemDetailElement).innerHTML = '';
	}
}

/** handler constructor **/
function treeHandler( treeSource, context, rootId, associatedGroupTree, availableGroupTree )
{
	this.tree 	= treeSource;
	this.context= context;
	this.rootId	= rootId;
	this.xmlResponseParser = null;
	this.associatedGroupTree = associatedGroupTree;
	this.availableGroupTree  = availableGroupTree;
	this.itemDetailElement = null;
	
	/*@cc_on @*/
	/*@if (@_jscript_version >= 5)
	// JScript gives us Conditional compilation, we can cope with old IE versions.
	// and security blocked creation of the objects.
	 try {
	  this.httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
	  } catch (e) {
	  try {
	   this.httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
	  } catch (E) {
	   this.httpRequest = false;
	  }
	 }
	@end @*/
	
	if (!this.httpRequest && typeof XMLHttpRequest!='undefined') 
	{
	  this.httpRequest = new XMLHttpRequest();
	} //End Function

	
 	return this; 	
}

treeHandler.prototype.getHttpRequest=function( )
{
	  return (window.ActiveXObject)? new ActiveXObject("Microsoft.XMLHTTP"): new XMLHttpRequest();
	
}


/** 
	Function inovoked before opening a tree branch.
	The implementation clears the tree 'OnLoadingEnd' handler
**/

treeHandler.prototype.openHandler=function( itemId, currentState )
{ 
	this.tree.setOnLoadingEnd( new Function('') );
	return true;
}

/** Restores opened tree items **/
treeHandler.prototype.restoreOpenState=function( treeSource ) { treeSource.loadOpenStates(); }

/** 
	Returns the complete url ( url + parameters ) to execute insert action on database 
	Default behaviour returns null which means no request will be sent to the server by calling 
	treeHandler.insertItem()
**/
treeHandler.prototype.getInsertItemAction=function( treeSource ) { return null;  }

/** 
	Returns the complete url ( url + parameters ) to execute update action on database 
	Default behaviour returns null which means no request will be sent to the server by calling 
	treeHandler.updateItem()
**/
treeHandler.prototype.getUpdateItemAction=function( treeSource, form ) { return null;  }

/** 
	Returns the complete url ( url + parameters ) to execute delete action on database 
	Default behaviour returns null which means no request will be sent to the server by calling 
	treeHandler.deleteItem()
**/
treeHandler.prototype.getDeleteItemAction=function( treeSource ) { return null; }


/**
	Return the default message to confirm delete.
	'multi' parameter specify if the user wants to delete checked items. (true value)
	'multi' = false specify if the user wants to delete the selected item. 
**/
treeHandler.prototype.getConfirmDeleteMessage=function( treeSource, multi ) 
{ 
	if ( multi ) return 'Cancellare tutti gli elementi selezionati ?' 
	else
	{
		 var selectedItem = treeSource.getSelectedItemId();
		 
		 var message = "Cancellare l'elemento " + treeSource.getItemText( selectedItem );
		 
		 if ( treeSource.hasChildren( selectedItem ) > 0  ) 
		 	return message + ' e tutti i suoi figli ? '
		 else 
		 	return message;
	}
}

/** 
	Returns the complete url ( url + parameters ) to clone an item. 
	Default behaviour returns null which means no request will be sent to the server by calling 
	treeHandler.cloneItem()
**/
treeHandler.prototype.getCloneItemAction=function( treeSource ) { return null;  }

/** 
	Returns the complete url ( url + parameters ) to show item detail page. 
	Default behaviour returns null which means no request will be sent to the server by calling 
	treeHandler.selectItem()
**/
treeHandler.prototype.getItemDetailAction=function( treeSource ) { return null; }

/** 
	Returns the complete url ( url + parameters ) to show the empty detail page. 
	Default behaviour returns null which means no request will be sent to the server by calling 
	treeHandler.insertItemPage()
**/
treeHandler.prototype.getEmptyItemDetailAction=function(treeSource, itemId ) { return null; }

/** 
	Returns the complete url ( url + parameters ) to move an item with dnd.  
	Default behaviour returns null which means no request will be sent to the server by calling 
	treeHandler.dropItem()
**/
treeHandler.prototype.getMoveItemAction=function( dragItemId, newParentId, targetBeforeId, treeSource, treeTarget ) { return null; }

/** 
	Returns the complete url ( url + parameters ) to enable item.  
	Default behaviour returns null which means no request will be sent to the server by calling 
	treeHandler.enableItem()
**/
treeHandler.prototype.getEnableItemAction=function( treeSource, selectedItemId ){ return null; }

/** 
	Returns the complete url ( url + parameters ) to disable item.  
	Default behaviour returns null which means no request will be sent to the server by calling 
	treeHandler.disableItem()
**/
treeHandler.prototype.getDisableItemAction=function( treeSource, selectedItemId ){ return null; }

/** 
	Returns the complete url ( url + parameters ) to mark an item.  
	Default behaviour returns null which means no request will be sent to the server by calling 
	treeHandler.markItem()
**/
treeHandler.prototype.getMarkItemAction=function( treeSource, selectedItemId ){ return null; }

/** 
	Returns the complete url ( url + parameters ) to unmark an item.  
	Default behaviour returns null which means no request will be sent to the server by calling 
	treeHandler.unMarkItem()
**/
treeHandler.prototype.getUnMarkItemAction=function( treeSource, selectedItemId ){ return null; }

/**  
	Returns the default message to confirm item dropping as sibling node. 
	Must be overwritten to show different message.
**/
treeHandler.prototype.getConfirmDropAsSiblingMessage=function( dragItemId, targetItemId, targetBeforeId, treeSource, treeTarget )
{
	return "Spostare l'item " + treeSource.getItemText( dragItemId ) + " prima di " + treeTarget.getItemText( targetBeforeId ) + " ?";
}

/**  
	Returns the default message to confirm item dropping as child node. 
	Must be overwritten to show different message.
**/
treeHandler.prototype.getConfirmDropAsChildMessage=function( dragItemId, targetItemId, targetBeforeId, treeSource, treeTarget )
{
	return "Spostare l'item " + treeSource.getItemText( dragItemId ) + " come figlio di " + treeTarget.getItemText( targetItemId ) + " ?";
}

/**  
	Returns the default message to show when no item has been selected and user wants to do some action.
	For example delete, activate, deactivate, mark or unmark checked items 
	Must be overwritten to show different message.
**/
treeHandler.prototype.getSelectSomeItemMessage=function( treeSource ){ return 'Selezionare almeno un elemento' }


/**
	Send an http request to the server.
	url is the complete url (url + parameters).
	This method splits url and parameter and send a POST request.
	Parameters values must be escaped. 
**/
treeHandler.prototype.sendRequest=function( url )
{
	
	if ( url == null || url == '' ) return;
	
	var paramindex = url.indexOf( '?' );
	
	var action = paramindex == -1 ? url  : url.substring( 0, paramindex );
	var params = paramindex == -1 ? null : url.substring( paramindex + 1 );
	
	if ( action.charAt(0) != '/' ) action = '/'+ action;
	
   showStatusFrame()
   
   this.httpRequest.open("POST", this.context + action, true );
   this.httpRequest.setRequestHeader('Accept','message/x-formresult');
   this.httpRequest.setRequestHeader( 'Content-Type','application/x-www-form-urlencoded' )
   this.httpRequest.send( params );
   
   
}

/**
	Insert a new item to the database.
	Do an httRequest to the url returned by getInsertItemAction(tree, form)
**/
treeHandler.prototype.insertItem=function( form )
{
		var httpRequest 			= this.httpRequest;
		var afterActionHandler 		= this.performAfterInsert
		var tree 					= this.tree;
		var selectAfterInsert		= this.selectAfterInsert;
		var updateGroupElements		= this.updateGroupElements;
		
		var selectedItem = tree.getSelectedItemId() == '' ? '/' : tree.getSelectedItemId();
		
    	this.sendRequest ( this.getInsertItemAction( this.tree, form ) )
		httpRequest.onreadystatechange = function( ) 
	    {
	    	if ( httpRequest.readyState == 4)
		   	{		
			   	hideStatusFrame();
		   		xmlResponse = new xmlResponseParser( httpRequest.responseXML );

				if ( !xmlResponse.isDone() ) 
		   			alert( xmlResponse.getMessage() )
		   		else
		   		{ 
		   				selectingItem = xmlResponse.getItemId();
						tree.saveOpenStates();
						tree.setOnLoadingEnd( new Function( 'tree', 'tree.loadOpenStates();tree.selectItem( selectingItem, true )' ) )
						tree.refreshItem( xmlResponse.getParentId() )
		   		}
		   	}
    	}
    		

}

/**
	Update the item detail to the database.
	Do an httRequest to the url returned by getUpdateItemAction( tree, form )
**/
treeHandler.prototype.updateItem=function( itemDetailForm )
{		
		if ( itemDetailForm == null || typeof itemDetailForm == 'undefined' ) return;
		
		var httpRequest 		= this.httpRequest;
		var afterActionHandler 	= this.performAfterUpdate;
		var tree 				= this.tree;
				
    	this.sendRequest ( this.getUpdateItemAction( tree, itemDetailForm ) )
		httpRequest.onreadystatechange = function() 
	    {
	    	if (httpRequest.readyState == 4)
		   	{
		   		hideStatusFrame();
		   		var xmlResponse = new xmlResponseParser( httpRequest.responseXML );
		   		if ( !xmlResponse.isDone() ) alert( xmlResponse.getMessage() )
		   		else
		   		{	 
					tree.setItemText( xmlResponse.getItemId(), xmlResponse.getItemText(), xmlResponse.getItemId() )
		   		}
		   	}
    	}
    		
}


/**
	Deletes an item.
	Do an httRequest to the url returned by getDeleteItemAction( tree, checkedItems )
	'multi' parameter specifies if the user wants to delete checked items.
	'multi' = false specifies the user wants to delete the selected item only. 	
**/
treeHandler.prototype.deleteItem=function( multi )
{	

		var checkedItems = !multi ? new Array( this.tree.getSelectedItemId() ) : this.tree.getAllChecked().split(',')
		
		if ( checkedItems == '' ) { alert( this.getSelectSomeItemMessage() ); return; }
		
		var answer = true;

		var message = this.getConfirmDeleteMessage( this.tree, multi );		
		
		if ( message != '' && message != null ) answer = confirm( message )
		
		if ( !answer ) return;
		
		var httpRequest = this.httpRequest;
		var tree = this.tree;		
		var handler = this
		var element = this.itemDetailElement;
		var clearGroups = this.clearGroupTree;
		
	    this.sendRequest ( this.getDeleteItemAction( tree, checkedItems ) )
		httpRequest.onreadystatechange = function() 
	    {
	    	if (httpRequest.readyState == 4)
		   	{
		   		hideStatusFrame();
		   		var xmlResponse = new xmlResponseParser( httpRequest.responseXML );
		   		if ( !xmlResponse.isDone() ) alert( xmlResponse.getMessage() )
		   		else
		   		{ 
					var items = xmlResponse.getItems();
					for ( var i=0; i<items.length; i++ )
					{
						tree.deleteItem( items[i].getElementsByTagName( 'id' )[0].firstChild.nodeValue, false )
					}
					document.getElementById( element ).innerHTML = ''
					clearGroups();
		   		}
		   	}
    	}
    		
	    	
}

/**
	Copy an item.
	Do an httRequest to the url returned by getCloneItemAction( tree )
**/
treeHandler.prototype.cloneItem=function()
{
	if ( this.tree.getSelectedItemId() == this.rootId ) return;
	
	var httpRequest = this.httpRequest;
	var tree = this.tree;
	
	this.sendRequest ( this.getCloneItemAction( tree ) )
	httpRequest.onreadystatechange = function() 
    {
    	if (httpRequest.readyState == 4)
	   	{
	   		hideStatusFrame();
	   		var xmlResponse = new xmlResponseParser( httpRequest.responseXML );
		   	if ( !xmlResponse.isDone() ) alert( xmlResponse.getMessage() )
		   	else
		   	{ 
				tree.refreshItem( xmlResponse.getParentId() );
		   	}
	   	}
	}
		
}

/**
	Invoked when the user drops an item in a different position in the tree.
	Do an httRequest to the url returned by getMoveItemAction( dragItemId, targetItemId, targetBeforeId, treeSource, treeTarget )
**/
treeHandler.prototype.dropItem=function( dragItemId, targetItemId, targetBeforeId, treeSource, treeTarget )
{
	var answer = true;
	
	var message = ''
	
	if( targetBeforeId != null && targetBeforeId != 'null' )
		message = this.getConfirmDropAsSiblingMessage( dragItemId, targetItemId, targetBeforeId, treeSource, treeTarget )
	else
		message = this.getConfirmDropAsChildMessage( dragItemId, targetItemId, targetBeforeId, treeSource, treeTarget )
	
	if ( message != null && message != '' ) answer = confirm( message )
	
	if ( !answer ) return false;
	
	var httpRequest = this.httpRequest;
	var restoreFunction = this.restoreOpenState;
	var clearGroups = this.clearGroupTree;
	var element = this.itemDetailElement;
	var avgroupTree = this.availableGroupTree;
	var assgroupTree = this.associatedGroupTree;
	
    var url = this.getMoveItemAction( dragItemId, targetItemId, targetBeforeId, treeSource, treeTarget )
    
    if ( url == null ) { return true; }
    
    this.sendRequest( url )
    httpRequest.onreadystatechange = function() {
    	if (httpRequest.readyState == 4)
    	{
    	   hideStatusFrame();
    	   var xmlResponse = new xmlResponseParser( httpRequest.responseXML );
		   
		   if ( !xmlResponse.isDone() ) 
		   	alert( xmlResponse.getMessage() )
		   else 
		   {
		   		treeSource.deleteItem( dragItemId, false );
		   		treeTarget.saveOpenStates();
		   		treeTarget.setOnLoadingEnd( restoreFunction )
		   		if ( avgroupTree != null ) avgroupTree.deleteChildItems( avgroupTree.getRootId() ); 
		   		if ( assgroupTree != null ) assgroupTree.deleteChildItems( assgroupTree.getRootId() ); 
		   		if ( element != null ) document.getElementById( element ).innerHTML = '';
		   		treeTarget.refreshItem( targetItemId );
		   }
    		
    	}
    }
   
    
	return false;
	
}


/**
	Show the item detail.
	Do an httRequest to the url returned by this.getItemDetailAction( tree )
	and writes the html results to this.itemDetailElement
**/
treeHandler.prototype.selectItem=function( itemId )
{	    
	   isUpdating = true;
	   var httpRequest 	= this.httpRequest;
		var detailElement = this.itemDetailElement;
		var availableGroupTree = this.availableGroupTree
		var associateGroupTree = this.associatedGroupTree

    	this.sendRequest ( this.getItemDetailAction( this.tree ) )
	   httpRequest.onreadystatechange = function() 
	    {
	    	if (httpRequest.readyState == 4)
		   	{
		   	    hideStatusFrame();
		   		if ( httpRequest.status != 200 ) alert( 'Server error: ' + httpRequest.status );
		   		
		   		var element = detailElement
		   		
		   		if( element != null )
		   		{
			   		if ( element.innerHTML ) 
			   			element.innerHTML = httpRequest.responseText 
			   		else 
			   			document.getElementById( element ).innerHTML = httpRequest.responseText;
		   		}
		   		
		   		if ( typeof availableGroupTree != 'undefined'  ) 
		   			availableGroupTree.refreshItem( availableGroupTree.getRootId() )
		   		if ( typeof associatedGroupTree != 'undefined'  ) 
		   			associatedGroupTree.refreshItem( associateGroupTree.getRootId() )
		   	}
    	}
    		
}

/**
	Shows the empty item detail.
	Do an httRequest to the url returned by getEmptyItemDetailAction( this.tree )
	and writes the html results to this.itemDetailElement
**/
treeHandler.prototype.insertItemPage=function()
{	    
	 isUpdating = false;
	 
	 var httpRequest 	= this.httpRequest;
	 var detailElement  = this.itemDetailElement;
	 var clearGroups	= this.clearGroupTree
	 
     var selectedItemId = this.tree.getSelectedItemId() == '' ? this.getRootId() : this.tree.getSelectedItemId()
     
     this.sendRequest ( this.getEmptyItemDetailAction( this.tree, selectedItemId ) )
	   httpRequest.onreadystatechange = function() 
	    {
	    	if (httpRequest.readyState == 4)
		   	{
		   		hideStatusFrame();
		   		
		   		if ( httpRequest.status != 200 ) alert( 'Server error: ' + httpRequest.status );
		   		
		   		var element = detailElement
		   		if ( element.innerHTML ) 
		   			element.innerHTML = httpRequest.responseText 
		   		else 
		   			document.getElementById( element ).innerHTML = httpRequest.responseText;

		   		clearGroups();
		   	}
		}
		
}


/**
	Enable selected items.
	Do an httRequest to the url returned by getEnableItemAction( this.tree, selectedItems )
**/
treeHandler.prototype.enableItem=function( multi )
{ 
	 this.changeFlag( this.getEnableItemAction, multi )
}

/**
	disable selected items.
	Do an httRequest to the url returned by getDisableItemAction( this.tree, selectedItems )
**/
treeHandler.prototype.disableItem=function( multi )
{ 
	this.changeFlag( this.getDisableItemAction, multi )
}

/**
	marks selected items.
	Do an httRequest to the url returned by getMarkItemAction( this.tree, selectedItems )
**/
treeHandler.prototype.markItem=function( multi )
{ 
		this.changeFlag( this.getMarkItemAction, multi );
}

/**
	unmarks selected items.
	Do an httRequest to the url returned by getUnMarkItemAction( this.tree, selectedItems )
**/
treeHandler.prototype.unMarkItem=function( multi )
{ 
		this.changeFlag( this.getUnMarkItemAction, multi );
}

/**
	Changes item flag.
	Called by enable, disable, mark and unmark functions.
**/
treeHandler.prototype.changeFlag=function( action, multi )
{
	 var httpRequest 	= this.httpRequest;
	 var tree 			= this.tree;
	 var changeIcon 	= this.changeIcon;
	
	var checkedItems = !multi ? new Array( this.tree.getSelectedItemId() ) : this.tree.getAllChecked().split(',')
	
	if ( checkedItems == this.getRootId() ) return;
	
	if ( checkedItems == '' ) { alert( this.getSelectSomeItemMessage() ); return; }
   
   
	this.sendRequest ( action( this.tree, checkedItems ) )
	   httpRequest.onreadystatechange = function() 
	    {
	    	if (httpRequest.readyState == 4)
		   	{	
	    	   hideStatusFrame();
	    	   var xmlResponse = new xmlResponseParser( httpRequest.responseXML );
			   
			   if ( !xmlResponse.isDone() ) 
			   	alert( xmlResponse.getMessage() )
			   else 
			   {
					changeIcon( tree, xmlResponse )
			   }
		   	}
		}
   		
}


/**
	Changes the an item icon after one of its flags has been changed.
	items to change icons are returned by the server into the xmlResponseParser param.
**/
treeHandler.prototype.changeIcon=function( tree, xmlResponseParser )
{
	var items = xmlResponseParser.getItems();
	
	
	for ( var i=0; i<items.length; i++ )
	{
		var id   = items[i].getElementsByTagName( 'id' )[0].firstChild.nodeValue;
		var icon = items[i].getElementsByTagName( 'icon' )[0].firstChild.nodeValue;

		tree.setItemImage2( id, icon, icon, icon );
		tree.disableCheckbox( id, 0 )
	
		if(items[i].getElementsByTagName( 'enabled' )[0].firstChild)
		{
			var enabled = items[i].getElementsByTagName( 'enabled' )[0].firstChild.nodeValue;
			tree.setUserData(id, 'enabled', enabled);
		}
		

		if(items[i].getElementsByTagName( 'marked' )[0].firstChild)
		{
			var marked = items[i].getElementsByTagName( 'marked' )[0].firstChild.nodeValue;
			tree.setUserData(id, 'marked', marked);
		}
		
			
	}
}

/**
	Refreshes an item by reloading children form database.
**/
treeHandler.prototype.refreshItem=function( itemId )
{ 
	this.tree.refreshItem( itemId );
}

/** Returns the tree root id **/
treeHandler.prototype.getRootId=function(){ return this.rootId; }

/** Returns the tree control associated whit this handler **/
treeHandler.prototype.getTree=function() { return this.tree; }

/** Sets associated group tree control **/
treeHandler.prototype.setAssociatedGroupTree=function( tree ) { this.associatedGroupTree = tree }
/** Sets availablegroup tree control **/
treeHandler.prototype.setAvailableGroupTree=function( tree ) { this.availableGroupTree = tree }

/** Clears group trees ( typically invoked when no item is selected on a tree **/
treeHandler.prototype.clearGroupTree=function()
{
	if ( typeof this.associatedGroupTree != 'undefined' && this.associatedGroupTree != null ) 
		this.associatedGroupTree.deleteChildItems( this.associatedGroupTree.getRootId() )
	if ( typeof this.availableGroupTree != 'undefined' && this.availableGroupTree != null ) 
		this.availableGroupTree.deleteChildItems( this.availableGroupTree.getRootId() )
}

/** Common function **/

var isUpdating = true;
var selectingItem = ''

/** 
	Function to call when saving an item detail 
	@param handler the tree handler
	@param updateForm the form which holds item attribute to update.
	@param insertForm the form which holds item attribute to insert.
**/
function saveItem( handler, updateForm, insertForm )
{
	if ( !updateForm && !insertForm ) return;
	
	if ( isUpdating ) 
		handler.updateItem( updateForm )
	else
		handler.insertItem( insertForm )
}

/** Shows the status frame.Invoked before every server request **/
function showStatusFrame()
{
	var status = document.getElementById( 'requeststatus' );
	
	if ( status ) 
	{
		status.style.top  = '200px'
		status.style.left = ( window.screen.width  - status.style.width  ) / 2 + 'px'
		status.style.visibility = 'visible'
	}
}

/** Hides the status frame.Invoked after every server request **/
function hideStatusFrame()
{
	var status = document.getElementById( 'requeststatus' );
	if ( status ) status.style.visibility ='hidden'
}