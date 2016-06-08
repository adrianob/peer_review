Mudan�as feitas no projeto:

- Database:
	- M�todos para adicionar no banco foram movidos para add(), usando overload
	- Usando Mapas no lugar de listas // TODO: Elaborar

- Article:
	- Adicionado m�todo toString()
	- Adicionado m�todo toStringSimple(), que retorna informa��es simplificadas sobre o Article
	- Adicionado m�todo getGradeAverage(), usado para decidir se o artigo ser� aceito ou n�o em uma confer�ncia, de acordo com a m�dia de suas avalia��es.
	- Removido reviewers, pode ser calculado a partir de reviews
	
- Researcher:
	- Adicionado m�todo toString()
	- Adicionado m�todo toStringSimple(), que retorna informa��es simplificadas sobre o Researcher
	
- Conference:
	- Adicionado m�todo toString()
	- Adicionado m�todo toStringSimple(), que retorna informa��es simplificadas sobre a Conference
	- Removido atributo coordinator
	- Removido articlesAllocated, pode ser calculado a partir de reviews
	
- University:
	- Adicionado m�todo toString()
	
- ResearchTopic:
	- Adicionado m�todo toString()
	
	
- Command:
	- Adicionado m�todo abstrato getName(), que deve retornar um nome leg�vel do comando
	
	