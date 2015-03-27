var /*MooTreeControl */ tree;

var /*Integer*/ index = 0 ;

var /*Integer*/ nodo = -1 ;

var /*String*/ nodeToFind = new String();//file_browser         newboxlet

window.onload = function() { 
	url = parent.location.href;
	url = url.substring(url.indexOf("url") + 4,url.length); 
	alert(url);
	populateMooTree(url); 
};

function populateMooTree(/*String*/ element) {
	
	// --- ordinary MooTreeControl example:
	
	tree = new MooTreeControl({
		div: 'mytree',
		mode: 'files',
		grid: true,
	},{
		text: 'Help',
		open: true
	});
	
	tree.disable(); // this stops visual updates while we're building the tree...
	
	var node1 = tree.insert({text:'Sicurezza', id:'1'});
	
	var node1_1 = node1.insert({text:'Aggiornamento risorsa', id:'update_resource_collection'});
		node1_1.onClick = function Callopen_url(){ open_url('authorization.htm'); };
	
	var node1_2 = node1.insert({text:'Risorse', id:'resource_collections'});
		node1_2.onClick = function Callopen_url() { open_url('boxlet.htm#newboxletconfiguration'); };
	var node1_3 = node1.insert({text:'Nuova risorsa', id:'new_resource_collection'});
		node1_3.onClick = function Callopen_url(){ open_url('boxlet.htm#upload');};
	var node1_4 = node1.insert({text:'Lista file inclusi', id:'file_browser_included'});
	var node1_5 = node1.insert({text:'Lista file', id:'file_browser'});
	
	
	var node2 = tree.insert({text:'Boxlet', id:'2'});
	
	var node2_1 = node2.insert({text:'Upload contenuto boxlet', id:'upload'});
	var node2_2 = node2.insert({text:'Nuova configurazione boxlet', id:'newboxletconfiguration'});
	var node2_3 = node2.insert({text:'Dettaglio boxlet', id:'boxletdetail'});
		node2_3.onSelect = function nodeSelected() { open_url('boxlet.htm#upload'); };
	var node2_4 = node2.insert({text:'Edit veloce della boxlet', id:'edit_quick_boxlet'});
	var node2_5 = node2.insert({text:'Edit boxlet', id:'editboxlet'});
	var node2_6 = node2.insert({text:'Configurazione boxlet', id:'boxlet_quick_config'});
	var node2_7 = node2.insert({text:'Lista boxlet', id:'boxlet.boxletlist'});
				  node2_7.onSelect = function nodeSelected() { open_url('./fileHelp/boxlet.html'); };
				  node2_7.onClick = function nodeSelected() { open_url('./fileHelp/boxlet.html'); };
	var node2_8 = node2.insert({text:'Nuova boxlet', id:'newboxlet'});
				  node2_8.onSelect = function nodeSelected() { open_url('./fileHelp/boxlet.html#newboxlet'); };
				  node2_8.onClick = function nodeSelected() { open_url('./fileHelp/boxlet.html#newboxlet'); };
	var node2_9 = node2.insert({text:'Configurazione dettaglio boxlet', id:'boxletconfigdetail'});
				  node2_9.onSelect = function nodeSelected() { open_url('./fileHelp/boxlet.html#editboxlet'); };
				  node2_9.onClick = function nodeSelected() { open_url('./fileHelp/boxlet.html#editboxlet'); };
	
	var node3 = tree.insert({text:'Users', id:'3'});
	
	var node3_1 = node3.insert({text:'Lista utenti', id:'users.userlist'});
				  node3_1.onSelect = function nodeSelected() { open_url('./fileHelp/users.html#userlist'); };
				  node3_1.onClick  = function nodeSelected() { open_url('./fileHelp/users.html#userlist'); };
	var node3_2 = node3.insert({text:'Crea nuovo utente', id:'users.newuser'});
				  node3_2.onSelect = function nodeSelected() { open_url('./fileHelp/users.html#newuser'); };
				  node3_2.onClick  = function nodeSelected() { open_url('./fileHelp/users.html#newuser'); };

	//tree.expand();
	toogleElement(element);
	//node2.toggle(false, true);
	//tree.select(node2_6);
	
	tree.enable(); // this turns visual updates on again.
	//toogleElement(element);
}

function toogleElement(/*String*/ element) {
	nodeToFind = element;
	search(tree.root);
}

function search(item){
	if(item.nodes.length)
	{
		nodo++;
		item.nodes.each(function(elem){
			search(elem);
		});
	}
	else {
			if (nodo > 0) {
			
			//alert(tree.get(nodo).nodes[index].text + "    NODO = " + nodo + "   INDEX = " + index + " LUNGHEZZA NODO   " + tree.get(nodo).nodes.length);
			if (tree.get(nodo).nodes[index].id == nodeToFind) {
					//alert(tree.get(nodo).nodes[index].text + "--------"   + nodeToFind);
					//break;
					tree.get(nodo).toggle(false, true);
					tree.select(tree.get(nodo).nodes[index]);
			}
			
			if (index == tree.get(nodo).nodes.length - 1) {
				index = 0;
			}else{
				index++;
			}
			
			}
		}	
	}

	
function open_url(url) {
	parent.menu.location.href = url;
}

function find_node() {
	var node = tree.get( $('nodeid_input').value );
	window.alert( node ? 'found: ' + node.text : 'not found...' );
}