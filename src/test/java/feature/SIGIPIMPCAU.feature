#language: pt
#encoding: UTF-8

@CriarDeclaracaoImportacao
Funcionalidade: Criar Declaracao de Importacao
  Designada a criar uma declaração de importação usando o sistema backoffice

  Fundo:
    Dado que o utilizador acede ao backoffice usando "User Sigip" and "Password Sigip"

  Esquema do Cenario: [Modulo] - "<cenario>"
#    Dado Selecionar o menu Administração com a opção de "Pesquisa de Importação"
#    E Clicar no botão criar
#    E Criar uma declaracao de importacao usando o "<arquivo>"
#    Quando Clicar no botão submeter
#    Então validar o resultado da resposta no respectivo ecrã

    Exemplos:
      | cenario      | arquivo   |
      | Declaracao 1 | auto00001 |


