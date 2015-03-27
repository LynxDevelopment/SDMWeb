var /*MooTreeControl */ tree;

var /*Integer*/ index = 0 ;

var /*Integer*/ nodo = -1 ;

var /*String*/ nodeToFind = new String();//file_browser         newboxlet

window.onload = function() { 
	url = parent.location.href;
	url = url.substring(url.indexOf("url") + 4,url.length); 
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
	
	var a = new Array();
	
	var list = $('menuHelp').getElements('li');
	list.each(function(el){
		if (el.getProperty('class')) {
			node = tree.insert({text: el.getProperty('class'), id: index++});
			el.getElements('li').each(function(el){
				str = el.getProperty('id');
				a = str.split(".");
				internalNode = node.insert({text: el.getText(), id: str});
				internalNode.onClick = function Callopen_url() {
                                     open_url('./fileHelp/' + a[0] + '.html#' + a[1]); 
                                    };
				internalNode.onSelect = function Callopen_url() { 
																open_url('./fileHelp/' + a[0] + '.html#' + a[1]);
																 };
			});
		}
	});

	tree.expand();
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