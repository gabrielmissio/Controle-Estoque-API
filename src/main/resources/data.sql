INSERT INTO CLIENTE(nome, cpf, endereco, cidade) VALUES('Carlos', '123.456.789-12', 'Rua planalto, Bariro LIMOEIRO', 'Monte Castelo');
INSERT INTO CLIENTE(nome, cpf, endereco, cidade) VALUES('Bernardo', '123.456.789-12', 'Rua brasil, Bariro Centro', 'Monte Castelo');
INSERT INTO CLIENTE(nome, cpf, endereco, cidade) VALUES('Alfredo', '123.456.789-12', 'Rua das bergamotas, Bariro das bergabotas', 'Itapuca');
		
INSERT INTO CATEGORIA(codigo, descricao, nome) VALUES('codigo eletrodomestico', 'descricao eletrodomestico', 'Eletrodomestico');
INSERT INTO CATEGORIA(codigo, descricao, nome) VALUES('Codigo alimento', 'descricao alimento', 'Alimento');

INSERT INTO LOJA(cidade, cnpj, endereco, nome) VALUES('São Paulo', '99.999.999/9999-99', 'RUA SANTOS D.', 'ELETROBOM LTDA');
INSERT INTO LOJA(cidade, cnpj, endereco, nome) VALUES('São Paulo', '99.999.999/2992-92', 'RUA Da conceição', 'BOM ALIMENTO LTDA');

INSERT INTO VENDEDOR(comicao, nome, salario, loja_id) VALUES('10.0', 'João Cleber Nascimento', '3000.0', '1');
INSERT INTO VENDEDOR(comicao, nome, salario, loja_id) VALUES('15.0', 'Jorel Camargo Santana', '5000.0', '1');
INSERT INTO VENDEDOR(comicao, nome, salario, loja_id) VALUES('20.0', 'Maria Ines Pererira', '2000.0', '2');
