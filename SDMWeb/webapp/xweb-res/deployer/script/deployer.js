var substRegExp = /\x5c/g;

var sourceform;
var destcomponent;

function setSourceForm( form )
{ 
	sourceForm = form ;
}

function setDestComponent( component )
{
	destcomponent = component;
}

function updateEntry( selectedItem )
{
	if ( selectedItem.checked == true )
	{
		var opt = new Option( selectedItem.value, selectedItem.value, false, false)
		destcomponent.options[ destcomponent.options.length ] =  opt ;
	}
	else
	{
		var options = destcomponent.options;
		for ( var i=0; i<options.length; i++ )
		{
			var escapeRegExp = selectedItem.value.replace( substRegExp , '\\\\' )
			
			var re = new RegExp( escapeRegExp + '\\\\.*' );

			if ( options[i].value == selectedItem.value || options[i].value.match( re ) ) 
			{
				destcomponent.remove( i );i--
			}
		}
	}
}


function updateCheckBoxes( )
{

	var options = destcomponent.options
	var checkboxList =	sourceForm.elements['index']
	
	for ( var i=0; i<checkboxList.length; i++ )
	{
		var checkboxListRegExp = checkboxList[i].value.replace( substRegExp , '\\\\' );
		checkboxListRegExp = new RegExp( checkboxListRegExp + '\\.*' );
		
		for( var j=0; j<options.length; j++)
		{
			
			var optionsRegExp = options[j].value.replace( substRegExp , '\\\\' );
			optionsRegExp = new RegExp( optionsRegExp + '\\.*' );
			
			var sameAbsolutePath = options[j].value == checkboxList[i].value;
			
			if ( sameAbsolutePath ) checkboxList[i].checked = true;
			
			if ( checkboxList[i].checked == false && checkboxList[i].value.match( optionsRegExp ) )
			{
				checkboxList[i].checked = true;
				checkboxList[i].disabled = true;
			}
			
			if ( options[j].value.match( checkboxListRegExp ) )
			{
				checkboxList[i].checked = true;
			}

		}
	}
}

function selectAll( checkboxList, toggle )
{
	for ( var i=0; i<checkboxList.length; i++ )
	{
		
		checkboxList[i].click();
		if ( checkboxList[i].checked != toggle.checked ) checkboxList[i].click();
	}
}


function openFilePopup(url)
{
	var props = "resizable=1,scrollbars=1,width=500, height=500";
	window.open(url, null, props);
}

function pubblica( form )
{
	var options = form.file.options
	for ( var i=0; i<options.length; i++ )
	{
		options[i].selected = true;
	}
	form.submit();
}