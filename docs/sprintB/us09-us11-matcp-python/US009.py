import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
from scipy import stats
from tabulate import tabulate

####################################################################
####################################################################
#######################     PARTE 1    ############################
####################################################################
####################################################################

# Função para calcular o custo da água para um determinado consumo
def calcular_custo_agua(consumo):
    taxa = 0.15
    limite_consumo = 1000
    price = 0.7

    if consumo <= limite_consumo:
        return consumo * price
    else:
        custo_ate_limite = limite_consumo * price
        consumo_excedente = consumo - limite_consumo
        custo_excedente = consumo_excedente * (price + (price * taxa))
    return custo_ate_limite + custo_excedente


# Função para imprimir o gráfico de barras representando o consumo mensal de água
def imprimir_consumo_mensal_agua(dados, ano, mes_inicio, mes_fim, identificacao_parque):
    # Filtra os dados com base no ano, período de tempo e identificação do parque
    dados_filtrados = dados[(dados['Year'] == ano) &
                            (dados['Month'] >= mes_inicio) &
                            (dados['Month'] <= mes_fim) &
                            (dados['Park'] == identificacao_parque)]

    consumo_mensal = {}

    # Agrupa os dados pelo mês e calcula a soma do consumo para cada mês
    for mes, grupo in dados_filtrados.groupby('Month'):
        consumo_total_mes = grupo['Consumption'].sum()
        consumo_mensal[mes] = consumo_total_mes

    # Ordena os meses
    meses_ordenados = sorted(consumo_mensal.keys())

    # Cria listas separadas para os meses e os consumos
    meses = [str(mes) for mes in meses_ordenados]
    consumos = [consumo_mensal[mes] for mes in meses_ordenados]

    # Imprime o gráfico de barras
    plt.figure(dpi=300)
    plt.bar(meses, consumos, color='blue')
    plt.xlabel('Mês')
    plt.ylabel('Consumo de Água (m3)')
    plt.title(f'Consumo Mensal de Água para o Parque {identificacao_parque} - Ano {ano}')
    plt.show()


# Define o caminho do arquivo CSV
caminho_arquivo = "water_consumption.csv"

# Lê o arquivo CSV em um DataFrame, substituindo vírgulas por pontos nos valores da coluna 'Consumption'
dados = pd.read_csv(caminho_arquivo, sep=';', decimal=',')

# Verificar quantos parques tem no ficheiro
# Crie um conjunto vazio para armazenar os nomes únicos dos parques
parques_unicos = set()
# Percorra todas as linhas do DataFrame
for nome_park in dados['Park']:
    # Adicione o nome do parque ao conjunto de parques únicos
    parques_unicos.add(nome_park)
# Agora, o tamanho do conjunto parques_unicos representa o número de parques únicos
numero_de_parques = len(parques_unicos)
print("No ficheiro dado, existem", numero_de_parques, "parques.")

# Verificar os anos que existem no ficheiro
# Crie um conjunto vazio para armazenar os anos
anos_unicos = set()
# Percorra todas as linhas do DataFrame
for anos in dados['Year']:
    # Adicione ano ao conjunto
    anos_unicos.add(anos)

# Input das especificações (Período de tempo e nome do Parque)
ano = int(input("Digite o ano a analisar (2023-2024): "))
# Verificação dos anos
while ano not in anos_unicos:
    print('\nO ano inserido não está no ficheiro dado.\n')
    ano = int(input("Digite o ano a analisar (2023-2024): "))

mes_inicio = int(input("Digite o mês inicial (1-12): "))
mes_fim = int(input("Digite o mês final (1-12): "))
# Verificação dos meses
while mes_inicio > mes_fim or mes_inicio <= 0 or mes_fim <= 0 or mes_inicio > 12 or mes_fim > 12:
    print('\nOs dados relativos aos meses de análise estão inválidos, por favor insira de novo. \n')
    mes_inicio = int(input("Digite o mês inicial (1-12): "))
    mes_fim = int(input("Digite o mês final (1-12): "))

identificacao_parque = input("Digite a identificação do parque para o gráfico de consumo de água: ")
while identificacao_parque not in parques_unicos:
    print('\nO parque fornecido não se encontra na lista de parques do ficheiro csv dado.\n')
    identificacao_parque = input("Digite a identificação do parque para o gráfico de consumo de água: ")

imprimir_consumo_mensal_agua(dados, ano, mes_inicio, mes_fim, identificacao_parque)


####################################################################
####################################################################
#######################     PARTE 2     ############################
####################################################################
####################################################################

# Função para calcular os custos médios mensais relacionados ao consumo de água
def calcular_custos_medios_mensais(dados, num_parques, parq_unic):
    custos_medios_parques = {}

    for _ in range(num_parques):
        identificacao_parque = input("Digite a identificação do parque: ")
        while identificacao_parque not in parq_unic:
            print('\nO parque fornecido não se encontra na lista de parques do ficheiro csv dado.\n')
            identificacao_parque = input("Digite a identificação do parque: ")

        # Filtra os dados com base na identificação do parque especificada
        dados_filtrados = dados[dados['Park']
                                == identificacao_parque]

        # Agrupa os dados filtrados por mês e calcula o consumo total para cada mês
        consumo_mensal = dados_filtrados.groupby('Month')['Consumption'].sum()

        # Calcula o custo total para cada mês
        custos_mensais = consumo_mensal.apply(calcular_custo_agua)

        # Calcula o custo médio para o período total de meses
        custo_medio_mensal = custos_mensais.sum() / len(custos_mensais)

        custos_medios_parques[identificacao_parque] = custo_medio_mensal

    return custos_medios_parques


# Input do número de parques a serem analisados
num_parques = int(input("\nDigite o número de parques a serem analisados: "))
# Verificação número de parques inseridos
while num_parques > numero_de_parques or num_parques < 0:
    num_parques = int(input("\nO valor inserido é inválido.\n\n Digite o número de parques a serem analisados: "))

custos_medios_parques = calcular_custos_medios_mensais(dados, num_parques, parques_unicos)
for parque, custo_medio in custos_medios_parques.items():
    print(f"\nCusto Médio Mensal para {parque}: {custo_medio:.2f}€")


####################################################################
####################################################################
#######################     PARTE 3     ############################
####################################################################
####################################################################

# Função para calcular as estatísticas
def calcular_estatisticas(dados):
    mean = round(np.mean(dados['Consumption']), 2)
    median = round(np.median(dados['Consumption']), 2)
    std_dev = round(np.std(dados['Consumption']), 2)
    skewness = round(stats.skew(dados['Consumption']), 2)

    return mean, median, std_dev, skewness


# Verifica outliers
def verificar_outliers(dados):
    Q1 = dados['Consumption'].quantile(0.25)
    Q3 = dados['Consumption'].quantile(0.75)
    IQR = Q3 - Q1
    median = np.median(dados['Consumption'])
    outliers = dados[(dados['Consumption'] < (median - 1.5 * IQR))
                     | (dados['Consumption'] > (median + 1.5 * IQR))]
    return outliers


# Encontra o parque com o maior e o menor consumo de água diário
parque_maior_consumo = dados.groupby('Park')[
    'Consumption'].sum().idxmax()
parque_menor_consumo = dados.groupby('Park')[
    'Consumption'].sum().idxmin()

# Filtra os dados para o parque com o maior consumo e menor consumo
dados_parque_maior_consumo = dados[dados['Park']
                                   == parque_maior_consumo]
dados_parque_menor_consumo = dados[dados['Park']
                                   == parque_menor_consumo]

# Calcula as estatísticas para o parque com o maior consumo e menor consumo
mean_maior, median_maior, std_dev_maior, skewness_maior = calcular_estatisticas(
    dados_parque_maior_consumo)
mean_menor, median_menor, std_dev_menor, skewness_menor = calcular_estatisticas(
    dados_parque_menor_consumo)

# Verifica outliers para o parque com maior consumo e menor consumo
outliers_maior = verificar_outliers(dados_parque_maior_consumo)
outliers_menor = verificar_outliers(dados_parque_menor_consumo)


# Função para construir tabela de frequência
def construir_tabela_frequencia(dados, num_classes):
    intervalos = pd.cut(dados, bins=num_classes, include_lowest=True, right=False)
    frequencia_absoluta = intervalos.value_counts().sort_index()
    frequencia_relativa = (frequencia_absoluta / len(dados)
                           * 100).round(2).astype(str) + '%'
    consumo = [
        f'[{intervalo.left:.2f}, {intervalo.right:.2f}]' for intervalo in frequencia_absoluta.index]
    tabela_frequencia = pd.DataFrame(
        {'Intervalos de Consumo': consumo, 'Freq. abs.': frequencia_absoluta, 'Freq. rel.': frequencia_relativa})
    return tabela_frequencia


# Função para construir tabela com informações sobre os parques
def construir_tabela_informacoes(parque_menor, dados_parque_menor, parque_maior, dados_parque_maior):
    media_menor, mediana_menor, desvio_padrao_menor, assimetria_menor = calcular_estatisticas(dados_parque_menor)
    media_maior, mediana_maior, desvio_padrao_maior, assimetria_maior = calcular_estatisticas(dados_parque_maior)

    outliers_menor = verificar_outliers(dados_parque_menor)
    outliers_maior = verificar_outliers(dados_parque_maior)

    if outliers_menor.empty:
        outliers_menor = "Não"
    else:
        outliers_menor = "Sim"

    if outliers_maior.empty:
        outliers_maior = "Não"
    else:
        outliers_maior = "Sim"

    tabela_info = pd.DataFrame({
        'Parque': [parque_menor, parque_maior],
        'Descrição': ['Menor Consumo', 'Maior Consumo'],
        'Média': [media_menor, media_maior],
        'Mediana': [mediana_menor, mediana_maior],
        'Desvio Padrão': [desvio_padrao_menor, desvio_padrao_maior],
        'Coeficiente de Assimetria': [assimetria_menor, assimetria_maior],
        'Existência de Outliers': [outliers_menor, outliers_maior]
    })

    return tabela_info


def imprimir_tabela_formatada(tabela):
    print(tabulate(tabela, headers='keys', tablefmt='grid', showindex= False))


# Construir tabela de dados para os parques com maior e menor consumo
tabela_info = construir_tabela_informacoes(parque_menor_consumo, dados_parque_menor_consumo, parque_maior_consumo,
                                           dados_parque_maior_consumo)

# Construir tabelas de frequências para os parques com maior e menor consumo
tabela_frequencia_maior = construir_tabela_frequencia(dados_parque_maior_consumo['Consumption'],
                                                      num_classes=5).reset_index(drop=True)
tabela_frequencia_menor = construir_tabela_frequencia(dados_parque_menor_consumo['Consumption'],
                                                      num_classes=5).reset_index(drop=True)

# Exibir a tabela de informações
print("\nTabela de Dados dos Parques:")
imprimir_tabela_formatada(tabela_info)

# Exibir as tabelas de frequencias
print(
    f"\nTabela de Frequência - Parque com Maior Consumo: {parque_maior_consumo}")
imprimir_tabela_formatada(tabela_frequencia_maior)

print(
    f"\nTabela de Frequência - Parque com Menor Consumo: {parque_menor_consumo}")
imprimir_tabela_formatada(tabela_frequencia_menor)

# Histograma do parque com maior consumo com 10 classes e do parque com menor consumo com 10 classes
plt.figure(figsize=(14, 7),dpi=300)

plt.subplot(2, 2, 1)
plt.hist(dados_parque_maior_consumo['Consumption'], bins=10, color='blue', alpha=0.7)
plt.title(f'Histograma do parque com maior consumo (10 classes) - {parque_maior_consumo}')
plt.xlabel('Consumo de Água (m3)')
plt.ylabel('Frequência (Número de Registos)')

plt.subplot(2, 2, 2)
plt.hist(dados_parque_menor_consumo['Consumption'], bins=10, color='red', alpha=0.7)
plt.title(f'Histograma do parque com menor consumo (10 classes)- {parque_menor_consumo}')
plt.xlabel('Consumo de Água (m3)')
plt.ylabel('Frequência (Número de Registos)')

# Histograma do parque com maior consumo com 100 classes e do parque com menor consumo com 100 classes
plt.subplot(2, 2, 3)
plt.hist(dados_parque_maior_consumo['Consumption'], bins=100, color='blue', alpha=0.7)
plt.title(f'Histograma do parque com maior consumo (100 classes) - {parque_maior_consumo}')
plt.xlabel('Consumo de Água (m3)')
plt.ylabel('Frequência (Número de Registos)')

plt.subplot(2, 2, 4)
plt.hist(dados_parque_menor_consumo['Consumption'], bins=100, color='red', alpha=0.7)
plt.title(f'Histograma do parque com menor consumo (100 classes)- {parque_menor_consumo}')
plt.xlabel('Consumo de Água (m3)')
plt.ylabel('Frequência  (Número de Registos)')

plt.tight_layout()
plt.show()
