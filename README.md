#API Rest para leitura e manutenção de uma	lista de cidades

#Arquitetura de microserviço em Spring boot

#Utilizado MongoDB para manutenção dos dados
Base Mongo Download
https://www.mongodb.com/download-center#community


IDE Robomongo para visualização dos dados da base
https://robomongo.org/download

#para execução do projeto...

#para executar os testes...

As alteracões das configurações da base, porta e local do arquivo csv, devem ser realizadas no arquivo application.properties

#porta:8082

#curl
Obtém todas as capitais
GET - cities/capitais

retorno
{
    "totalCidades": 1,
    "success": [
        {
            "ibgeId": 1100205,
            "uf": "RO",
            "name": "Porto Velho",
            "capital": true,
            "latitude": -8.76889179,
            "longitude": -63.8314456544,
            "noAccents": "Porto Velho",
            "alternativeNames": "",
            "microregion": "Porto Velho",
            "mesoregion": "Madeira-Guapor"
        }
    ],
    "hasError": false
}

#obtém a lista de cidades pesquisada por um atributo, nome, uf etc.
GET - cities/cityByAttribute?name=maria

@RequestParam -> qualquer atributo referente a cidade

retorno
{
    "totalCidades": 1,
    "success": [
        {
            "ibgeId": 3156403,
            "uf": "MG",
            "name": "Romaria",
            "capital": false,
            "latitude": -18.8834800349,
            "longitude": -47.5817549005,
            "noAccents": "Romaria",
            "alternativeNames": "",
            "microregion": "Patrocnio",
            "mesoregion": "Tringulo Mineiro/Alto Paranaba"
        }
    ],
    "hasError": false
}

#obtém o estado com o maior numero de cidades e o estado com o menor numero de cidades e a quantidade

GET - cities/maiorMenorPorEstado

retorno

{
    "totalCidades": 0,
    "success": {
        "_id": null,
        "minUf": "DF",
        "minUfTotal": 1,
        "maxUf": "MG",
        "maxUfTotal": 853
    },
    "hasError": false
}

#obtém uma pagina das cidades

@RequestParam:
page -> o numero da pagina
size -> o total de registro da pagina

GET - cities/byPage?page=1&size=2

{
    "totalCidades": 2,
    "success": [
        {
            "ibgeId": 2100055,
            "uf": "MA",
            "name": "Aailndia",
            "capital": false,
            "latitude": -4.9513770002,
            "longitude": -47.5066645931,
            "noAccents": "Acailandia",
            "alternativeNames": "",
            "microregion": "Imperatriz",
            "mesoregion": "Oeste Maranhense"
        },
        {
            "ibgeId": 5200050,
            "uf": "GO",
            "name": "Abadia de Gois",
            "capital": false,
            "latitude": -16.7588118948,
            "longitude": -49.4405478327,
            "noAccents": "Abadia de Goias",
            "alternativeNames": "",
            "microregion": "Goinia",
            "mesoregion": "Centro Goiano"
        }
    ],
    "hasError": false
}


#deletar uma cidades

PUT - cities/deletar/{ibgeId}

retorno
{
    "totalCidades": 0,
    "hasError": false
}