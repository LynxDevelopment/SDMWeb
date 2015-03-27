function xmlResponseParser( xmlResponse )
{
 this.response  = xmlResponse.documentElement
 this.items		= this.response.getElementsByTagName("item");
 return this; 	
}


xmlResponseParser.prototype.getMessage=function()
{
	return this.response.getElementsByTagName("message")[0].firstChild.nodeValue;
}


xmlResponseParser.prototype.isDone=function()
{
	return this.response.getAttribute("status") == 'OK';
}

xmlResponseParser.prototype.getItemId=function()
{
	return this.getItems()[0].getElementsByTagName("id")[0].firstChild.nodeValue;
}

xmlResponseParser.prototype.getParentId=function()
{
	return this.getItems()[0].getElementsByTagName("parent")[0].firstChild.nodeValue;
}

xmlResponseParser.prototype.getItemText=function()
{
	return this.getItems()[0].getElementsByTagName("text")[0].firstChild.nodeValue;
}

xmlResponseParser.prototype.getResponse=function()
{
	return this.response;
}

xmlResponseParser.prototype.getItems=function()
{
	return this.items;
}
