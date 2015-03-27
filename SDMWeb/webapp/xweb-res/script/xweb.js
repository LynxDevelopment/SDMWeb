/*
 * Parte riguardante identificazione della presenza di un plug in
 */

var ns4 = (document.layers)? true:false;
var ie4 = (document.all)? true:false;

var agt = navigator.userAgent.toLowerCase();
var ie  = (agt.indexOf("msie") != -1);
var ns  = (navigator.appName.indexOf("Netscape") != -1);
var win = ((agt.indexOf("win")!=-1) || (agt.indexOf("32bit")!=-1));
var mac = (agt.indexOf("mac")!=-1);



/* start parte riguardante identificazione della presenza di un plug in */
if (ie && win)
{
	pluginlist = detectIE("Adobe.SVGCtl","SVG Viewer") +
	             detectIE("SWCtl.SWCtl.1","Shockwave Director") +
	             detectIE("ShockwaveFlash.ShockwaveFlash.1","Shockwave Flash") +
	             detectIE("rmocx.RealPlayer G2 Control.1","RealPlayer") +
	             detectIE("QuickTimeCheckObject.QuickTimeCheck.1","QuickTime") +
	             detectIE("MediaPlayer.MediaPlayer.1","Windows Media Player") +
	             detectIE("PDF.PdfCtrl.5","Acrobat Reader");
}
if (ns || !win)
{
		nse = "";
		for ( var i=0; i < navigator.mimeTypes.length; i++)
			nse += navigator.mimeTypes[i].type.toLowerCase();

		pluginlist = detectNS("image/svg-xml","SVG Viewer") +
					 detectNS("application/x-director","Shockwave Director") +
					 detectNS("application/x-shockwave-flash","Shockwave Flash") +
					 detectNS("audio/x-pn-realaudio-plugin","RealPlayer") +
					 detectNS("video/quicktime","QuickTime") +
					 detectNS("application/x-mplayer2","Windows Media Player") +
					 detectNS("application/pdf","Acrobat Reader");
}

function detectIE(ClassID, name)
{
	result = false;

	document.write('<SCRIPT LANGUAGE = "VBScript">\n on error resume next \n result = IsObject(CreateObject("' + ClassID + '"))<\/SCRIPT>\n');

	if (result)
		return name + ',';
	else
		return '';
}

function detectNS(ClassID, name)
{
	n = "";
	if (nse.indexOf(ClassID) != -1)
		if (navigator.mimeTypes[ClassID].enabledPlugin != null)
			n = name + ",";
	return n;
}

pluginlist += navigator.javaEnabled() ? "Java," : "";
if (pluginlist.length > 0) pluginlist = pluginlist.substring(0,pluginlist.length-1);

//SAMPLE USAGE- detect "Flash"
//if (pluginlist.indexOf("Flash")!=-1)
//document.write("You have flash installed")

/* end  parte riguardante identificazione della presenza di un plug in */


//*************************************************
//******* SCRIPT PER L'APERTURA DELL'HELP **********

function closeResize()
{
	if (!opener.closed)
	{
		sw = screen.width;
		sh = screen.height;
		sah = screen.availHeight;
		opener.moveTo(0,0);
		opener.resizeTo(sw,sh);
	}
}


function openResize()
{
	var app = navigator.appName.toLowerCase();
	//alert(app);
	sw = screen.width;
	sh = screen.height;
	W = 230;
	opener.moveTo(0,0);
	if (app.indexOf('netscape') >= 0)
		opener.resizeTo(sw-W-24,sh-185);
	else
		opener.resizeTo(sw-W-12,sh-27);
}

function show()
{
	sw = screen.width;
	sh = screen.height;
	W = 230;
	H = sh-58;
	stringTool="toolbar=0,status=0,menubar=0,width="+W+",height="+H+",left="+(sw-W-12)+",top=0,resizable=1";
	window.open(baseUrl + 'xweb-res/help.xwb','new',stringTool);
}

function navBar( tableCellRef, hoverFlag)
{
	if ( hoverFlag )
	{
		tableCellRef.style.backgroundColor = '#EAEBED';
	}
	else
	{
		tableCellRef.style.backgroundColor = '';
	}
}
//*************************************************
//******* SCRIPT PER HELP *************************

function closeResize()
{
	if (!opener.closed)
	{
		sw = screen.width;
		sh = screen.height;
		sah = screen.availHeight;
		opener.moveTo(0,0);
		opener.resizeTo(sw,sh);
	}
}


function openResize()
{
	var app = navigator.appName.toLowerCase();
	//alert(app);
	sw = screen.width;
	sh = screen.height;
	W = 230;
	opener.moveTo(0,0);
	if (app.indexOf('netscape') >= 0)
		opener.resizeTo(sw-W-24,sh-185);
	else
		opener.resizeTo(sw-W-12,sh-27);
}

//********************************************************************


//************ SCRIPT GENERALI ***************************************

function ltrim(str)
{
	alert(str.charCodeAt(0));
	while(str.charCodeAt(0) == 32)
	{
		alert("'" + str + "'");
		str = str.substring(1);
	}
	return str;
	//return str.replace(/^\s*|\s*$/g,"");
}

function checkAllCheckbox(form)
{
	var sa = false;
	if(form.CheckAll.checked)
		sa=true;

	for (var i=0;i<form.elements.length;i++)
	{
		var e = form.elements[i];
		if( !e.disabled)
		{
			if(sa)
				e.checked=true;
			else
				e.checked=false;
		}
	}
}

//seleziona tutti i checkbox
function ToggleCheckAll()
{
	var sa = false;
	if(document.formDelete.CheckAll.checked)
		sa=true;

	for (var i=0;i<document.formDelete.elements.length;i++)
	{
		var e = document.formDelete.elements[i];
		if( !e.disabled)
		{
			if(sa)
				e.checked=true;
			else
				e.checked=false;
		}
	}
}

//seleziona tutti i checkbox
function ToggleCheckAll(checks, checkAll)
{
	// no checks at all, return
	if(!checks)
		return;

	var sa = false;
	if(checkAll.checked)
		sa=true;

	// more than one checkbox
	if(checks.length)
	{
		for (var i=0; i < checks.length;i++)
		{
			var e = checks[i];
			if( !e.disabled)
			{
				if(sa)
					e.checked=true;
				else
					e.checked=false;
			}
		}
	}
	else	// only one checkbox
	{
		if(!checks.disabled)
		{
			if(sa)
				checks.checked=true;
			else
				checks.checked=false;
		}
	}
}

//seleziona tutti i checkbox
function ToggleCacheCheckAll()
{
	var sa = false;
	if(document.formDelete.CheckAll.checked)
		sa=true;

	if(document.formDelete["cache"])
	{
		var lunghezzaCheck;
		if (document.formDelete["cache"].length)
		{
			lunghezzaCheck = document.formDelete["cache"].length;

			var arrayCheck = document.formDelete["cache"];

			for (var i=0;i<lunghezzaCheck;i++)
			{
				var e = arrayCheck[i];

				if( !e.disabled)
				{
					if(sa)
						e.checked=true;
					else
						e.checked=false;
				}
			}
		}
		else
		{
			if(sa)
				document.formDelete.cache.checked=true;
			else
				document.formDelete.cache.checked=false;
		}
	}
}

//seleziona tutti i checkbox
function ToggleCheckAllEnabled()
{
	var sa = false;
	if(document.formDelete.checkEnabled.checked)
		sa=true;

	if(document.formDelete["cacheEnable"])
	{
		var lunghezzaCheck;
		if (document.formDelete["cacheEnable"].length)
		{
			lunghezzaCheck = document.formDelete["cacheEnable"].length;

			var arrayCheck = document.formDelete["cacheEnable"];

			for (var i=0;i<lunghezzaCheck;i++)
			{
				var e = arrayCheck[i];

				if( !e.disabled)
				{
					if(sa)
						e.checked=true;
					else
						e.checked=false;
				}
			}
		}
		else
		{
			if(sa)
				document.formDelete.cacheEnable.checked=true;
			else
				document.formDelete.cacheEnable.checked=false;
		}
	}
}

// aggiunge il parametro all'action filter user
function createFilterAndSubmit(form, href)
{
	if (document.all['STATUS'].value != "" && document.all['TITLE'].value != "")
	{
		form.action = href +"?"+document.all['STATUS'].value + "=" + document.all['TITLE'].value;
		//alert(form.action);
		form.submit();
	}
}

function filterAndSubmitSubjectOrStatus(form, href)
{
    var actionString = href;

	if (document.all['D.SUBJECT'].value != "")
	{
		actionString = actionString + "&D.SUBJECT=" + document.all['D.SUBJECT'].value;
	}

	if (document.all['WFS.WF_STATUS_ID'].value != "%")
	{
		actionString = actionString + "&WFS.WF_STATUS_ID=" + document.all['WFS.WF_STATUS_ID'].value;
	}

	form.action = actionString;
	form.submit();
}

function CheckAndSubmit(form, href)
{
	form.action = href;
	form.submit();
}

//Usata per l'aggiornamento della cache
function updateControlCheck(form,href,checksname)
{
	var i=0;
	var check = false;
	if (document.formDelete[checksname]) //controllo se c'e' la lista di checkbox
	{
		if (!document.formDelete[checksname].length)
		{
			if (document.formDelete[checksname].checked)
			{
				check = true;
			}
		}
		else
		{
			while (i < document.formDelete[checksname].length)
			{
				if (document.formDelete[checksname][i].checked)
					check = true;

				i++;
			}
		}

		if (check == true)
		{
			form.action = href;
			form.submit();
		}
		else
			alert("Seleziona almeno un elemento");
	}
}



function deleteControlCheck(form,href,checksname)
{
	var i=0;
	var check = false;
	if (document.formDelete[checksname]) //controllo se c'? la lista di checkbox
	{
		if (!document.formDelete[checksname].length)
		{
			if (document.formDelete[checksname].checked)
			{
				check = true;
			}
		}
		else
		{
			while (i < document.formDelete[checksname].length)
			{
				if (document.formDelete[checksname][i].checked)
					check = true;

				i++;
			}
		}

		if (check == true)
			deleteConfirmation(form,href);
		else
			alert("Seleziona almeno un elemento");
	}
}

function deleteUserMessagesCheck(form,href,checksname)
{
	var i=0;
	var check = false;
	if (document.formUserMessageList[checksname]) //controllo se c'? la lista di checkbox
	{
		if (!document.formUserMessageList[checksname].length)
		{
			if (document.formUserMessageList[checksname].checked)
			{
				check = true;
			}
		}
		else
		{
			while (i < document.formUserMessageList[checksname].length)
			{
				if (document.formUserMessageList[checksname][i].checked)
					check = true;

				i++;
			}
		}

		if (check == true)
			deleteConfirmation(form,href);
		else
			alert("Seleziona almeno un elemento");
	}
}

function deletePopupCheck(form,href,checksname)
{
	var i=0;
	var check = false;
	if (document.formListPopup[checksname]) //controllo se c'? la lista di checkbox
	{
		if (!document.formListPopup[checksname].length)
		{
			if (document.formListPopup[checksname].checked)
			{
				check = true;
			}
		}
		else
		{
			while (i < document.formListPopup[checksname].length)
			{
				if (document.formListPopup[checksname][i].checked)
					check = true;

				i++;
			}
		}

		if (check == true)
			deleteConfirmation(form,href);
		else
			alert("Seleziona almeno un elemento");
	}
}

function deleteConfirmation(form,href)
{
	if (confirm("Vuoi eliminare l'elemento selezionato?")==true)
		CheckAndSubmit(form,href);
}


function addCheckAndSubmit(form, href, checksname)
{
	var i=0;
	var check = false;

	if (document.formAdd[checksname]) //controllo se c'? la lista di checkbox
	{
		if (!document.formAdd[checksname].length)
		{
			if (document.formAdd[checksname].checked)
			{
				check = true;
			}
		}
		else
		{
			while (i < document.formAdd[checksname].length)
			{
				if (document.formAdd[checksname][i].checked)
					check = true;

				i++;
			}
		}

		if (check == true)
		{
			form.action = href;
			form.submit();
		}
		else
			alert("Seleziona almeno un elemento");
	}
}



/**
 * Restituisce un oggetto Javascript Date corrispondente alla stringa passata come
 * argomento. Attualmente le date devono essere stringhe nel formato dd/MM/YYYY HH:mm.
*/

function parseDate(data, formato)
{
	// Estrae giorno, mese, anno dalla data
	var giorno = data.substring(0, 2);
	var mese = data.substring(3, 5);
	var anno = data.substring(6, 10);
	var ore = data.substring(11,13);
	var minuti = data.substring(14,16);



	// Controlla se ci sono problemi

	if ( isNaN(giorno) || isNaN(mese) || isNaN(anno) || isNaN(ore) || isNaN(minuti))
		alert("La data " + data + " ha un formato errato!" );

	// Restituisce la data
	return new Date(anno, mese - 1, giorno,ore,minuti,00);
}


/**
 * Confronta due date JavaScript.
 * Restituisce la differenza in giorni tra la data <data1> e
 * la data <data2>.
*/

function dateDiff(data1, data2)
{

	var SECOND = 1000; 			// N. di millisecondi in un secondo
	var MINUTE = SECOND * 60; 	// N. di millisecondi in un minuto
	var HOUR = MINUTE * 60; 	// N. di millisecondi in un ora
	var DAY = HOUR * 24; 		// N. di millisecondi in un giorno
	var WEEK = DAY * 7; 		// N. di millisecondi in una settimana

	// Ottiene la differenza in millisecondi

	var difference = dateCompare(data1, data2);

	// Restituisce il numero di giorni

	return Math.round(difference / DAY);
}

// ritorna data+(days,months,years)
function evaluateOffsetDate(data,days,months,years,hours,minutes) {

	var dataScadenza = data;
	
	dataScadenza.setMinutes(dataScadenza.getMinutes()+minutes);
	dataScadenza.setHours(dataScadenza.getHours()+hours);
	dataScadenza.setDate(dataScadenza.getDate()+days);
	dataScadenza.setMonth(dataScadenza.getMonth()+months);
	dataScadenza.setFullYear(dataScadenza.getFullYear()+years);

	return dataScadenza;
}

/**
 * Confronta due date JavaScript solo controllando giorno mese anno.
 * Restituisce la differenza in millisecondi tra la data <data1>
 * e la data <data2>.
 * Quindi la funzione restituisce un numero minore, uguale o maggiore di
 * zero a seconda che <data1> sia minore, uguale o maggiore di <data2>.
*/

function dateCompare(data1, data2)
{
	// Controlla se ci sono errori

	if ( isNaN(data1) || isNaN(data2) )
		alert("Problemi durante la conversione delle date");

	// Estrae i millisecondi dalle date
	var time1 = Date.UTC(data1.getYear(), data1.getMonth(), data1.getDate());
	var time2 = Date.UTC(data2.getYear(), data2.getMonth(), data2.getDate());
	// Calcola la differenza e restituisce il risultato

	var difference = time1 - time2;

	return difference;
}

/**
 * Confronta due date JavaScript solo controllando giorno mese anno ore e minuti.
 * Restituisce la differenza in millisecondi tra la data <data1>
 * e la data <data2>.
 * Quindi la funzione restituisce un numero minore, uguale o maggiore di
 * zero a seconda che <data1> sia minore, uguale o maggiore di <data2>.
*/

function dateCompareMillis(data1, data2)
{
	// Controlla se ci sono errori

	if ( isNaN(data1) || isNaN(data2) )
		alert("Problemi durante la conversione delle date");

	// Estrae i millisecondi dalle date
	var time1 = Date.UTC(data1.getYear(), data1.getMonth(), data1.getDate(), data1.getHours(), data1.getMinutes());
	var time2 = Date.UTC(data2.getYear(), data2.getMonth(), data2.getDate(), data2.getHours(), data2.getMinutes());
	// Calcola la differenza e restituisce il risultato

	var difference = time1 - time2;

	return difference;
}


/**
 * La funzione parseIntDec funziona come la funzione JavaScript parseInt.
 * La funzione parseIntDec considera la stringa passata come un numero
 * intero comunque in formato decimale. La funzione parseInt, invece,
 * considera in formato ottale i numeri che iniziano con uno 0.
*/


function parseIntDec(string)
{
	while (string.substring(0,1) == "0")
		string = string.substring(1);

	return parseInt(string);
}

/**
 * La funzione setScreenInfo controlla attraverso JavaScript la risoluzione
 * video usata dall'utente. Se la risoluzione e' stabilita correttamente,
 * viene inserito in sessione un Cookie contenente il valore della risoluzione
 * nel formato 1024x768, 800x600, etc. Il Cookie scade al termine della sessione.
*/

function setScreenInfo()
{
	var screenSize;

	var width = screen.width;
	var height = screen.height;

	if ( !isNaN(width) && !isNaN(height) )
	{
		screenSize = width + "x" + height;
		document.cookie = "screensize=" + escape(screenSize);
	}
}

/**
 * Controlla quanto caratteri sono digitati in un box di testo.
 * Prende come parametri la textbox da controllare, il numero massimo di caratteri
 * disponobili e un textbox in cui visualizzare il conteggio dei caratteri disponibili.
 * Il textbox per il conteggio puo' non essere usato specificando null come parametro
 * della funzione.
 * La funzione restituisce true se il numero di caratteri rientra nel massimo specificato.
 * Se il numero dei caratteri digitati eccede il numero massimo specificato, la funzione
 * rimuove i caratteri in eccesso dalla textbox e restiruisce false;
*/
function countChars(textbox, max)
{
	var commento = textbox.value;
	var count = commento.length;

	if (count > max)
	{
		alert("Il testo del commento deve essere al massimo di "+max+" caratteri.");
		textbox.value = commento.substring(0,max);
		return false;
	}
	else
	{
		return true;
	}

}

function openCenteredWindow(url,title,width,height,features)
{
	if ( width == null ) width = 700;;
	if ( height == null ) height = 400;
	if ( features == null ) features = "scrollbars=yes,resizable=yes";

	var left = (screen.availWidth-width)/2;
	var top = (screen.availHeight-height)/2;

	window.open(url, title, features+",width="+width+",height="+height+",left="+left+",top="+top);

}


//******************************************************************
//************ SCRIPT GROUPS ***************************************

function saveGroup()
{
	if (document.frmNew.GROUP_ID.value == "")
	{
		alert("I campi ID GRUPPO e TIPO sono obbligatori");
		return false;
	}

	if ((document.frmNew.USERS_FILE.value != "") &&
		 (!checkExtension(document.frmNew.USERS_FILE.value, new Array("csv","txt"))) )
	{
		return false;
	}

	document.frmNew.submit();
}

function saveDynamicGroup()
{
	if (document.frmNew.GROUP_ID.value == "")
	{
		alert("Il campo ID GRUPPO ? obbligatorio");
		return false;
	}

	if (document.frmNew.RULE.value == "")
	{
		alert("Il campo REGOLA ? obbligatorio");
		return false;
	}

	document.frmNew.submit();
}

function ToggleCheckAllFunctions()
{
	var sa = false;
	if(document.formAdd.CheckAll.checked)
		sa=true;

	for (var i=0;i<document.formAdd.elements.length;i++)
	{
		var e = document.formAdd.elements[i];
		if( !e.disabled)
		{
			if(sa)
				e.checked=true;
			else
				e.checked=false;
		}
	}
}


//usata in dynamicgroupupdate
function updateDynamicGroup()
{
	if (document.frmNew.RULE.value == "")
	{
		alert(unescape("Il campo RULE e' obbligatorio"));
		return false;
	}

	document.frmNew.submit();
}

//******************************************************************
//************ SCRIPT MENU ***************************************


function saveMenuItem()
{
	if ((document.frmNew.MENU_ITEM_TITLE.value == ""))
	{
		alert("Il campo TITOLO ? obbligatorio");
		return false;
	}
	else
	{
		document.frmNew.submit();
	}
}

function saveUpdateMenu()
{
	if (document.updateMenu.MENU_TITLE.value == "")
	{
		alert("Il campo TITOLO ? obbligatorio");
		return false;
	}
	else
	{
		document.updateMenu.submit();
	}
}

function saveUpdateMenuItem()
{
	if (document.updateMenuItem.MENU_ITEM_TITLE.value == "")
	{
		alert("Il campo TITOLO ? obbligatorio");
		return false;
	}
	else
	{
		document.updateMenuItem.submit();
	}
}

function saveMenu()
{
	if (document.frmNew.MENU_TITLE.value == "")
	{
		alert("Il campo TITOLO ? obbligatorio");
		return false;
	}
	else
	{
		document.frmNew.submit();
	}
}

//******************************************************************
//************ SCRIPT DIARY ****************************************
function saveDiaryMsg()
{
	if ((document.frmNew.SUBJECT.value == "") ||
		(document.frmNew.NOTE_DATE.value == "") ||
		(document.frmNew.PUBLISH_DATE.value == "") ||
		(document.frmNew.EXPIRATION_DATE.value == ""))
	{
		alert("I campi SOGGETTO, CONTENUTO, DATA MESSAGGIO, DATA DI PUBBLICAZIONE e DATA DI SCADENZA sono obbligatori");
		return false;
	}

	var currentDate = new Date();
	var publishDate = parseDate(document.frmNew.PUBLISH_DATE.value);
	var expirationDate = parseDate(document.frmNew.EXPIRATION_DATE.value);

	var diffCP = dateCompare(currentDate,publishDate);
	var diffPE = dateCompareMillis(publishDate,expirationDate);

	if ( diffCP > 0 )
	{
		alert("Data di pubblicazione non valida!");
		return false;
	}

	if ( diffPE >= 0 )
	{
		alert("Data di scadenza non valida!");
		return false;
	}

	document.frmNew.submit();
}

//******************************************************************
//************ SCRIPT MESSAGE ****************************************
function saveMessage()
{
	if ((document.checkedGroups.SUBJECT.value == "") ||
		(document.checkedGroups.NOTE.value == "") ||
		(document.checkedGroups.NOTE_DATE.value == "") ||
		(document.checkedGroups.PUBLISH_DATE.value == "") ||
		(document.checkedGroups.EXPIRATION_DATE.value == ""))
	{
				alert("I campi SOGGETTO, CONTENUTO, DATA MESSAGGIO, DATA DI PUBBLICAZIONE, DATA DI SCADENZA sono obbligatori");
				return false;
	}
	else
	{
		lunghezzaRadio = document.checkedGroups["DEST_TYPE_ID"].length;

		var arrayRadio = document.checkedGroups["DEST_TYPE_ID"];
		var destTypeId, checked = false;
		for (var i=0;i<lunghezzaRadio;i++)
		{
			var e = arrayRadio[i];

			if (arrayRadio[i].checked)
			{
				  destTypeId = arrayRadio[i].value;
				  checked = true;
			}
		}

		if (checked == false)
		{
			alert("Nessun DESTINATARIO inserito.");
			return false;
		}
	}

	var currentDate = new Date();
	var publishDate = parseDate(document.checkedGroups.PUBLISH_DATE.value);
	var expirationDate = parseDate(document.checkedGroups.EXPIRATION_DATE.value);

	var diffCP = dateCompare(currentDate,publishDate);
	var diffPE = dateCompareMillis(publishDate,expirationDate);

	if ( diffCP > 0 )
	{
		alert("Data di pubblicazione non valida!");
		return false;
	}

	if ( diffPE >= 0 )
	{
		alert("Data di scadenza non valida!");
		return false;
	}

	if ((destTypeId == "USERS_LIST") && (document.checkedGroups.USERS_LIST.value == ""))
	{
		alert("Lista utenti non compilata");
		return false;
	}

	if ((destTypeId == "USERS_FILE") && (document.checkedGroups.USERS_FILE.value == ""))
	{
		alert("File destinatari mancante");
		return false;
	}

	if (destTypeId == "USERS_GROUPS")
	{
	    var check = false;
	    var i=0;

		if (document.checkedGroups['groupchecked']) //controllo se c'? la lista di checkbox
	    {
		    if (document.checkedGroups['groupchecked'].length <= 0)
		    {
			    if (document.checkedGroups['groupchecked'].checked)
			    {
				    check = true;
			    }
		    }
		    else
		    {
			    while (i < document.checkedGroups['groupchecked'].length)
			    {

				    if (document.checkedGroups['groupchecked'][i].checked)
					    check = true;

				    i++;
			    }
		    }

        	if (check == false)
        	{
        		alert("Seleziona almeno un elemento");
        		return false;
        	}
        }
        else
        {
            alert("Lista gruppi mancante");
            return false;
        }
	}

	//if ( confirm("Save message?") )
		document.checkedGroups.submit();
	//else
	//	return false;
}

function updateMessage()
{
	if ((document.frmNew.SUBJECT.value == "") ||
		(document.frmNew.NOTE.value == "") ||
		(document.frmNew.NOTE_DATE.value == "") ||
		(document.frmNew.PUBLISH_DATE.value == "") ||
		(document.frmNew.EXPIRATION_DATE.value == ""))
	{
				alert("I campi SOGGETTO, CONTENUTO, DATA MESSAGGIO, DATA DI PUBBLICAZIONE, DATA DI SCADENZA sono obbligatori");
				return false;
	}


	var currentDate = new Date();
	var publishDate = parseDate(document.frmNew.PUBLISH_DATE.value);
	var expirationDate = parseDate(document.frmNew.EXPIRATION_DATE.value);

	var diffCP = dateCompare(currentDate,publishDate);
	var diffPE = dateCompareMillis(publishDate,expirationDate);

	if ( diffCP > 0 )
	{
		alert("Data di pubblicazione non valida!");
		return false;
	}

	if ( diffPE >= 0 )
	{
		alert("Data di scadenza non valida!");
		return false;
	}

	//if ( confirm("Save message?") )
		document.frmNew.submit();
	//else
	//	return false;
}

//******************************************************************
//************ SCRIPT POLL *****************************************

function savePoll()
{
	if ((document.frmNewPoll.DESCRIPTION.value == "") ||
		(document.frmNewPoll.QUESTION.value == "") ||
		(document.frmNewPoll.PUBLISH_DATE.value == "") ||
		(document.frmNewPoll.EXPIRATION_DATE.value == ""))
	{
		alert("I campi DESCRIZIONE,DOMANDA,DATA DI PUBBLICAZIONE e DATA DI SCADENZA sono obbligatori.");
		return false;
	}

	var currentDate = new Date();
	var publishDate = parseDate(document.frmNewPoll.PUBLISH_DATE.value);
	var expirationDate = parseDate(document.frmNewPoll.EXPIRATION_DATE.value);

	if ( dateCompare(currentDate,publishDate) > 0 )
	{
		alert("Data di pubblicazione non valida!");
		return false;
	}

	if ( dateCompareMillis(publishDate,expirationDate) >= 0 )
	{
		alert("Data di scadenza non valida!");
		return false;
	}

	document.frmNewPoll.submit();
}


//******************************************************************
//************ SCRIPT NEWSLETTER ***************************************

function saveNewsletter()
{
	if ((document.frmNew.groupscheck.value == "") ||
		(document.frmNew.DESCRIPTION.value == "") ||
		( (document.frmNew.USERS_FILE.value == "") && (document.frmNew.USERS_LIST.value == "")) )
	{
		alert("I campi DESCRIZIONE, IMPORT FILE DESTINATARI o DESTINATARI sono obbligatori");
		return false;
	}
	else if ((document.frmNew.USERS_FILE.value != "") &&
			 (!checkExtension(document.frmNew.USERS_FILE.value, new Array("csv","txt"))) )
		{
			return false;
		}

	else
	{
		document.frmNew.submit();
	}
}

function saveNewsletterMessage()
{
	if ((document.frmNew.SUBJECT.value == "") ||
		(document.frmNew.CONTENT.value == "") ||
		(document.frmNew.CONTENT.value == " ") ||
		(document.frmNew.PUBLISH_DATE.value == "") ||
		(document.frmNew.EXPIRATION_DATE.value == ""))
	{
		alert("I campi SOGGETTO, CONTENUTO, DATA di PUBBLICAZIONE e DATA di SCADENZA sono obbligatori");
		return false;
	}

	var currentDate = new Date();
	var publishDate = parseDate(document.frmNew.PUBLISH_DATE.value);
	var expirationDate = parseDate(document.frmNew.EXPIRATION_DATE.value);

	if ( dateCompare(currentDate,publishDate) > 0 )
	{
		alert("Data di pubblicazione non valida!");
		return false;
	}

	if ( dateCompareMillis(publishDate,expirationDate) >= 0 )
	{
		alert("Data di scadenza non valida!");
		return false;
	}

	//if ( confirm("Save message?") )
		document.frmNew.submit();
	//else
	//	return false;
}

function sendControlCheck(form,href,checksname)
{

	var i=0;
	var check = false;
	if (document.formDelete[checksname]) //controllo se c'? la lista di checkbox
	{
		if (!document.formDelete[checksname].length)
		{
			if (document.formDelete[checksname].checked)
			{
				check = true;
			}
		}
		else
		{
			while (i < document.formDelete[checksname].length)
			{
				if (document.formDelete[checksname][i].checked)
					check = true;

				i++;
			}
		}

		if (check == true)
			CheckAndSubmit(form,href);
		else
			alert("Seleziona almeno un elemento");
	}
}

function checkExtension(fileName,allowedExtArray) {

  	var ext = fileName.substring(fileName.length-3,fileName.length);
  	ext = ext.toLowerCase();

	for (i=0; i<allowedExtArray.length; i++)
	{
		if ( ext == allowedExtArray[i] )
			return true;
	}

	var extList = "";

	for (i=0; i<allowedExtArray.length; i++)
	{
		extList = extList + "." + allowedExtArray[i] + " ";
	}

    //alert("You selected a \'." + fileName + "\' file; the only valid files are: " + extList);
    alert("You selected the file \'" + fileName + "\'; the only valid files are: " + extList);
    return false;

}

//******************************************************************
//************ SCRIPT BOXLET ***************************************

// Inizializza un data picker in base ad un offset(days,months,years) dalla data odierna
function initializeDateTime(formName,elementDate1,elementDate2,days,months,years,hours,minutes)
{
	var elDate1 = document.getElementById(formName+'_components_'+elementDate1);
	var elDate2 = document.getElementById(formName+'_components_'+elementDate2);

	var today = new Date();
	pubDate = new Date(Date.parse(today));

	annoP= pubDate.getFullYear();
	meseP = pubDate.getMonth()+1;
	if (meseP < 10)
		meseP = "0"+meseP;
	giornoP = pubDate.getDate();
	if (giornoP < 10)
		giornoP = "0"+giornoP;
	oreP = pubDate.getHours();
	if (oreP < 10)
		oreP = "0"+oreP;
	minutiP = pubDate.getMinutes();
	if (minutiP < 10)
		minutiP = "0"+minutiP;

	dataOraPub = giornoP + "/" + meseP + "/" + annoP + " " + oreP + ":" + minutiP;
	if (elDate1!=null)
		elDate1.value = dataOraPub;

	expDate = evaluateOffsetDate(today,days,months,years,hours,minutes);
 	annoE= expDate.getFullYear();
 	meseE = expDate.getMonth()+1;
	if (meseE < 10)
		meseE = "0"+meseE;
	giornoE = expDate.getDate();
	if (giornoE < 10)
		giornoE = "0"+giornoE;
	oreE = expDate.getHours();
	if (oreE < 10)
		oreE = "0"+oreE;
	minutiE = expDate.getMinutes();
	if (minutiE < 10)
		minutiE = "0"+minutiE;

	dataOraExp = giornoE + "/" + meseE + "/" + annoE + " " + oreE + ":" + minutiE;
	if (elDate2!=null)
		elDate2.value = dataOraExp;
}


//******************************************************************
//************ SCRIPT USERS ***************************************

function ToggleCheckAllGroups()
{
	var sa = false;
	if(document.checkedGroups.CheckAll.checked)
		sa=true;

	for (var i=0;i<document.checkedGroups.elements.length;i++)
	{
		var e = document.checkedGroups.elements[i];
		if( !e.disabled)
		{
			if(sa)
				e.checked=true;
			else
				e.checked=false;
		}
	}
}// end function


function ToggleCheckAllUserGroups()
{
	var sa = false;
	if(document.checkedUserGroups.CheckAll.checked)
		sa=true;

	for (var i=0;i<document.checkedUserGroups.elements.length;i++)
	{
		var e = document.checkedUserGroups.elements[i];
		if( !e.disabled)
		{
			if(sa)
				e.checked=true;
			else
				e.checked=false;
		}
	}
}// end function


function controlUserGroupsCheck(href)
{
	var i=0;
	var check = false;

	if (document.checkedUserGroups['usergroupschecked']) //controllo se c'? la lista di checkbox
	{
		if (!document.checkedUserGroups['usergroupschecked'].length)
		{
			if (document.checkedUserGroups['usergroupschecked'].checked)
			{
				check = true;
			}
		}
		else
		{
			while (i < document.checkedUserGroups['usergroupschecked'].length)
			{

				if (document.checkedUserGroups['usergroupschecked'][i].checked)
					check = true;

				i++;
			}
		}


	if (check == true)
		removeFromGroupsConfirmation(href);
	else
		alert("Seleziona almeno un elemento");

	}
}//end function


function controlGroupsCheck(href)
{
	var i=0;
	var check = false;


	if (document.checkedGroups['groupchecked']) //controllo se c'? la lista di checkbox
	{
		if (!document.checkedGroups['groupchecked'].length)
		{
			if (document.checkedGroups['groupchecked'].checked)
			{
				check = true;
			}
		}
		else
		{
			while (i < document.checkedGroups['groupchecked'].length)
			{

				if (document.checkedGroups['groupchecked'][i].checked)
					check = true;

				i++;
			}
		}


	if (check == true)
		addToGroupsConfirmation(href);
	else
		alert("Seleziona almeno un elemento");

	}
}//end function


function addToGroupsConfirmation(href)
{
	//if (confirm("Vuoi aggiungere i gruppi selezionati?")==true)
		CheckAndSubmit(checkedGroups,href);
}

function removeFromGroupsConfirmation(href)
{
	if (confirm("Vuoi eliminare i gruppi selezionati?")==true)
		CheckAndSubmit(checkedUserGroups,href);
}

function openDatePicker(dateCtrl)
{
	var dialogArguments = new Array();
	dialogArguments = [parent, dateCtrl];
	strFeatures = "dialogWidth=225px; dialogHeight=260px; scroll=no; center=yes; border=thin; help=no; status=no;"
	finestra = window.showModalDialog(baseUrl + "xweb-res/common/datepicker.xwb", dialogArguments ,  strFeatures);
}

function openDateTimePicker(dateCtrl)
{
	var dialogArguments = new Array();
	dialogArguments = [parent, dateCtrl];
	strFeatures = "dialogWidth=225px; dialogHeight=260px; scroll=no; center=yes; border=thin; help=no; status=no;"
	finestra = window.showModalDialog(baseUrl + "xweb-res/common/datetimepicker.xwb", dialogArguments ,  strFeatures);
}


function saveUser()
{
	document.frmNew.submit();
}

function gestioneTabColori(newColor)
{
	who = document.getElementById("palette").foreground;
	if (who == true)
	{
		var obj = document.getElementById("foreButton");
		showMyState(obj);
		viewHideColor(obj.active);
		if (isIE5_5OrGreater())
		{
			divSelection.select();
		}
		setForeColor(newColor);
	}
	else
	{
		var obj = document.getElementById("backButton");
		showMyState(obj);
		viewHideColor(obj.active);
		if (isIE5_5OrGreater())
		{
			divSelection.select();
		}
		setBackColor(newColor);
	}
}

function preview()
{
	sw = screen.width;
	sh = screen.height;
	W = 230;
	H = sh-58;
	stringTool="toolbar=0,status=0,menubar=0,width="+W+",height="+H+",left="+(sw-W-12)+",top=0,resizable=1";
	window.open('','new',stringTool);
}

//usata in cloneuser
function validate() {
	if (document.frmNew.USER_ID.value == "")
	{
		alert(unescape("Il campo ID UTENTE e' obbligatorio"));
		return false;
	}
	if(document.frmNew.CLONED_USER_ID.value == document.frmNew.USER_ID.value) {
		alert(unescape("Esiste gia' un utente con questo id!"));
		return false;
	}
	saveUser();
}

/**
* La funzione parseStrictInt converte una stringa in intero.
* La stringa deve rappresentare un numero strettamente intero,
* cioe' non sono accettati valori come "123ws".
*/

function parseStrictInt(number)
{
	if (number==null) return NaN;
	if (isNaN(number)) return NaN;
	var digits = "1234567890";
	for(i=0; i<number.length; i++)
	{
		if(digits.indexOf(number.charAt(i)) < 0) return NaN;
	}
	return parseInt(Math.round(number));
}


/**
 * Modifica l'attributo class dei tr presenti nel tbody delle tabella con class = applyToClass
 * settandolo a evenClass o oddClass a seconda che sia una riga pari o dispari.
 */
function tableStripe(applyToClass, evenClass, oddClass)
{
	var tables = document.getElementsByTagName("table");

	if(tables == null)
		return;

	for(var table = 0; table < tables.length; table++)
	{
		if(tables[table].className == applyToClass)
		{
			var tableBody = tables[table].getElementsByTagName('tbody')[0];
			var rows = tableBody.getElementsByTagName('TR');
			if(rows)
	  		{
	  			for(var i = 0; i < rows.length; i++)
	  			{
	  				var rowClass;
	  				if(i % 2 == 1)
	  					rowClass = evenClass;
	  				else
	  					rowClass = oddClass;
	  				$(rows.item(i)).removeClass(oddClass);
	  				$(rows.item(i)).removeClass(evenClass);
	  				$(rows.item(i)).addClass(rowClass);
	  			}
	  		}
  		}
	}
}

function getMouseX(event)
{
	var posx = 0;
	if (!e) var e = window.event;
	if (e.pageX)
	{
		posx = e.pageX;
	}
	else if (e.clientX)
	{
		posx = e.clientX + document.body.scrollLeft;
	}
	return posx;
}

function getMouseY(event)
{
	var posy = 0;
	if (!e) var e = window.event;
	if (e.pageY)
	{
		posy = e.pageY;
	}
	else if (e.clientY)
	{
		posy = e.clientY + document.body.scrollTop;
	}

	return posy;
}

function addClass(element, className)
{
	if(element.className == "")
		element.className = className;
	else if(element.className.indexOf(className) < 0)
		element.className = element.className + " " + className;
}

function removeClass(element, className)
{
	var oldClass = element.className;
	if(oldClass.indexOf(className) >= 0)
	{
		var left = oldClass.indexOf(className);
		var right = left + className.length;
		var result = oldClass.substring(0, left);
		if(right < oldClass.length)
			result += oldClass.substring(right, oldClass.length);
		if(result.charAt(0) == " ")
			result = result.substring(1);
		element.className = result;
	}
}

function toggleVisibility(elementId)
{
	var element = document.getElementById(elementId);
	if(element.style.display == 'none')
		element.style.display = '';
	else
		element.style.display = 'none';
}


/**************************************
 **************   AJAX   **************
 **************************************/
var http_request = false;

/**
 * Nota: la linea sovrastante http_request.overrideMimeType('text/xml'); causer? errori di JavaScript Console in Firefox 1.5b 
 * come documentato in https://bugzilla.mozilla.org/show bug.cgi?id=311724 se la pagina chiamata da XMLHttpRequest non ? XML valido 
 * (per esempio se ? testo semplice).
 */
function ajaxRequest(url, elementId) 
{
    http_request = false;

    if (window.XMLHttpRequest) 
    { // Mozilla, Safari,...
        http_request = new XMLHttpRequest();
        if (http_request.overrideMimeType) 
        {
            http_request.overrideMimeType('text/xml');
        }
    } 
    else if (window.ActiveXObject) 
    { // IE
        try 
        {
            http_request = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) 
        {
            try 
            {
                http_request = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {}
        }
    }

    if (!http_request) 
    {
        alert('Giving up :( Cannot create an XMLHTTP instance');
        return false;
    }
    http_request.onreadystatechange = function(){
	    getAjaxResponseText(elementId);
	};
    http_request.open('GET', url, true);
    http_request.send(null);
}

function getAjaxResponseText(elementId) 
{
    if (http_request.readyState == 4) 
    {
        if (http_request.status == 200) 
  		{
        	document.getElementById(elementId).innerHTML = http_request.responseText;      
        }
        else 
        {
        	alert('There was a problem with the request.');
        }	
    }
}

/**
 * This function retrieves the actual value of a style property even if it is set via css.
 */
function getStyle(node, styleProp)
{
	// if not an element
	if( node.nodeType != 1)
		return;
	
	var value;
	if (node.style[styleProp]) {
		// inline style property
		value = node.style[styleProp];
	}
	if (node.currentStyle)
	{
		// ie case
		styleProp = replaceDashWithCamelNotation(styleProp);
		value = node.currentStyle[styleProp];
	}
	else if (window.getComputedStyle)
	{
		// mozilla case
		value = document.defaultView.getComputedStyle(node, null).getPropertyValue(styleProp);
	}
	
	return value;
}

function replaceDashWithCamelNotation(value)
{
	var pos = value.indexOf('-');
	while(pos > 0 && value.length > pos + 1)
	{
		value = value.substring(0, pos) + value.substring(pos + 1, pos + 2).toUpperCase() + value.substring(pos + 2);
		pos = value.indexOf('-');
	}
	return value;
}

/*
function getStyle(element, prop) {
    if (typeof element == 'string') element = document.getElementById(element);
	
	if (element.style[prop]) {
		// inline style property
		return element.style[prop];
	} else if (element.currentStyle) {
		// external stylesheet for Explorer
		return element.currentStyle[prop];
	} else if (document.defaultView && document.defaultView.getComputedStyle) {
		// external stylesheet for Mozilla and Safari 1.3+
		prop = prop.replace('/([A-Z])/g', '\"-$1\"');
		prop = prop.toLowerCase();
		return document.defaultView.getComputedStyle(element,'\"\"').getPropertyValue(prop);
	} else {
		return null;
	}
}*/

function disableSelection(target){
	if (typeof target.onselectstart!="undefined") //IE route
		target.onselectstart=function(){return false}
	else if (typeof target.style.MozUserSelect!="undefined") //Firefox route
		target.style.MozUserSelect="none"
	else //All other route (ie: Opera)
		target.onmousedown=function(){return false}
	target.style.cursor = "default"
}