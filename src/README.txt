Mudanças feitas no projeto:

- Database:
	- Métodos para adicionar no banco foram movidos para add(), usando overload
	- Usando Mapas no lugar de listas // TODO: Elaborar

- Article:
	- Adicionado método toString()
	- Adicionado método toStringSimple(), que retorna informações simplificadas sobre o Article
	- Adicionado método getGradeAverage(), usado para decidir se o artigo será aceito ou não em uma conferência, de acordo com a média de suas avaliações.
	
- Researcher:
	- Adicionado método toString()
	- Adicionado método toStringSimple(), que retorna informações simplificadas sobre o Researcher
	
- Conference:
	- Adicionado método toString()
	- Adicionado método toStringSimple(), que retorna informações simplificadas sobre a Conference
	
- University:
	- Adicionado método toString()
	
- ResearchTopic:
	- Adicionado método toString()
	
	
- Command:
	- Adicionado método abstrato getName(), que deve retornar um nome legível do comando
	
	