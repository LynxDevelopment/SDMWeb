editAreaLoader.load_syntax["cpp"] = {
	'COMMENT_SINGLE' : {1 : '//'}
	,'COMMENT_MULTI' : {'/*' : '*/'}
	,'QUOTEMARKS' : {1: "'", 2: '"'}
	,'KEYWORD_CASE_SENSITIVE' : true
	,'KEYWORDS' : {
		'constants' : [
			'null', 'false','true','System'
		]
		,'types' : [
			'abstract', 'class', 'boolean', 'byte', 'char', 'float',
			'double', 'enum', 'extends', 'final', 'implements',
			'int', 'interface', 'long', 'package', 'import',
			'private', 'protected', 'public', 'short', 'static',
			'transient', 'void', 'volatile','synchronized'
		]
		,'statements' : [
			'assert','break', 'case', 'catch', 'continue', 'default', 'do', 'else',
			'finally', 'for', 'if', 'new', 'return', 'super', 'switch','this','throw',
			'throws','try','while'
		]
 		,'keywords' : [
			'instanceof'
		]
	}
	,'OPERATORS' :[
		'+', '-', '/', '*', '=', '<', '>', '%', '!', '?', ':', '&'
	]
	,'DELIMITERS' :[
		'(', ')', '[', ']', '{', '}'
	]
	,'REGEXPS' : {
/*		'precompiler' : {
			'search' : '()(#[^\r\n]*)()'
			,'class' : 'precompiler'
			,'modifiers' : 'g'
			,'execute' : 'before'
		}
		,'precompilerstring' : {
			'search' : '(#[\t ]*include[\t ]*)([^\r\n]*)([^\r\n]*[\r\n])'
			,'class' : 'precompilerstring'
			,'modifiers' : 'g'
			,'execute' : 'before'
		}*/
	}
	,'STYLES' : {
		'COMMENTS': 'color: #AAAAAA;'
		,'QUOTESMARKS': 'color: #6381F8;'
		,'KEYWORDS' : {
			'constants' : 'color: #EE0000;'
			,'types' : 'color: #0000EE;'
			,'statements' : 'color: #60CA00;'
			,'keywords' : 'color: #48BDDF;'
		}
		,'OPERATORS' : 'color: #FF00FF;'
		,'DELIMITERS' : 'color: #0038E1;'
		,'REGEXPS' : {
			'precompiler' : 'color: #009900;'
			,'precompilerstring' : 'color: #994400;'
		}
	}
};
