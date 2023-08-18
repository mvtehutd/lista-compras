grammar Lc;

// Palavras chave da gramática, representam palavras que possuem um sentindo/executam uma função na gramática.
PALAVRA_CHAVE: 'LOJAS' | 'NOME' | 'PRODUTO' | 'MEDIDA' | 'QUANTIDADE' | 'PREÇO' | 'LISTA' | 'PROPRIEDADE' | 'FIM';

UNIDADE: 'UN' | 'KG' | 'L';

PROPRIEDADE: 'Economia' | 'Economia Exata' | 'Caro' | 'Caro Exato';

DELIM: ':';

// Identificadores para números inteiros e reais
NUM_INT: ('0'..'9')+;

NUM_REAL: ('0'..'9')+ (',' ('0'..'9')+)?;

// Ignora espaços em branco
WS: ( ' ' | '\t' | '\r' | '\n') {skip();};

// Reconhece os identificadores (variáveis), que são qualquer sequência de caracteres iniciada por uma letra ou _ (underline)
IDENT: ('a'..'z'|'A'..'Z')+ (( ' ' )? ('a'..'z'|'A'..'Z'|'0'..'9')+)*;

// Qualquer outro símbolo não identificado é reconhecido aqui
ERRO: .;

programa: 'LOJAS' loja+ 'LISTA' lista 'FIM';

loja: 'NOME' DELIM IDENT produto_loja+;

produto_loja: 'PRODUTO' DELIM IDENT 'MEDIDA' DELIM quant = numero UNIDADE 'PREÇO' DELIM valor = numero;

numero: NUM_INT | NUM_REAL;

lista: 'NOME' DELIM IDENT 'PROPRIEDADE' DELIM PROPRIEDADE produto_lista+;

produto_lista: 'PRODUTO' DELIM IDENT 'QUANTIDADE' DELIM quant = numero UNIDADE;