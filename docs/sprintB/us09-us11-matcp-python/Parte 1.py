import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import numpy as np
from scipy import stats

####################################################################
####################################################################
#######################     PARTE 1    ############################
####################################################################
####################################################################

# Função para calcular o custo da água para um determinado consumo


def calcular_custo_agua(consumo):
    taxa = 0.15
    limite_consumo = 50
    preço = 0.7

    if consumo <= limite_consumo:
        return consumo * preço
    else:
        return (consumo * preço) + (consumo * taxa)


# Função para imprimir o gráfico de barras representando o consumo mensal de água
def imprimir_consumo_mensal_agua(dados, mes_inicio, mes_fim, identificacao_parque):
    # Filtra os dados com base no período de tempo e na identificação do parque
    dados_filtrados = dados[(dados['Month'] >= mes_inicio) & (dados['Month'] <= mes_fim) & (
        dados['Park'] == identificacao_parque)]

    # Agrupa os dados filtrados por mês e calcula o consumo total para cada mês
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
    plt.bar(meses, consumos, color='blue')
    plt.xlabel('Mês')
    plt.ylabel('Consumo de Água (m3)')
    plt.title('Consumo Mensal de Água para o Parque {}'.format(
        identificacao_parque))
    plt.show()


# Define o caminho do arquivo CSV
caminho_arquivo = "water_consumption.csv"

# Lê o arquivo CSV em um DataFrame, substituindo vírgulas por pontos nos valores da coluna 'Consumption'
dados = pd.read_csv(caminho_arquivo, sep=';', decimal=',')

# Input das especificações (Período de tempo e nome do Parque)
mes_inicio = int(input("Digite o mês inicial (1-12): "))
mes_fim = int(input("Digite o mês final (1-12): "))
identificacao_parque = input("Digite a identificação do parque: ")

imprimir_consumo_mensal_agua(dados, mes_inicio, mes_fim, identificacao_parque)


####################################################################
####################################################################
#######################     PARTE 2     ############################
####################################################################
####################################################################

# Função para calcular os custos médios mensais relacionados ao consumo de água
def calcular_custos_medios_mensais(dados, num_parques):
    custos_medios_parques = {}

    for _ in range(num_parques):
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

custos_medios_parques = calcular_custos_medios_mensais(dados, num_parques)
for parque, custo_medio in custos_medios_parques.items():
    print(f"Custo Médio Mensal para {parque}: {custo_medio:.2f}€")


####################################################################
####################################################################
#######################     PARTE 3     ############################
####################################################################
####################################################################

# Função para calcular as estatísticas
def calcular_estatisticas(dados):
    mean = np.mean(dados['Consumption'])
    median = np.median(dados['Consumption'])
    std_dev = np.std(dados['Consumption'])
    skewness = stats.skew(dados['Consumption'])

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
    intervalos = pd.cut(dados, bins=num_classes, include_lowest=True)
    frequencia_absoluta = intervalos.value_counts().sort_index()
    frequencia_relativa = (frequencia_absoluta / len(dados)
                           * 100).round(2).astype(str) + '%'
    consumo = [
        f'[{intervalo.left:.1f}, {intervalo.right:.1f}]' for intervalo in frequencia_absoluta.index]
    tabela_frequencia = pd.DataFrame(
        {'Intervalos de Consumo': consumo, 'Freq. abs.': frequencia_absoluta, 'Freq. rel.': frequencia_relativa})
    return tabela_frequencia


# Função para construir tabela com informações sobre os parques
def construir_tabelas_informacoes(dados_parque):
    media, mediana, desvio_padrao, assimetria = calcular_estatisticas(
        dados_parque)
    existe_outliers = verificar_outliers(dados_parque)

    if existe_outliers.empty:
        outliers = "Não"
    else:
        outliers = "Sim"

    tabela_info = pd.DataFrame({'Média': [media], 'Mediana': [mediana], 'Desvio Padrão': [desvio_padrao],
                                'Assimetria': [assimetria], 'Existência de Outliers': [outliers]})
    return tabela_info


tabela_info_menor_consumo = construir_tabelas_informacoes(
    dados_parque_menor_consumo)
tabela_info_maior_consumo = construir_tabelas_informacoes(
    dados_parque_maior_consumo)

print(f"Tabela de dados - Parque com Maior Consumo: {parque_maior_consumo}")
print(tabela_info_maior_consumo[[
    'Média', 'Mediana', 'Desvio Padrão', 'Assimetria', 'Existência de Outliers']])
print(verificar_outliers(dados_parque_maior_consumo))


print(f"\nTabela de dados - Parque com Menor Consumo: {parque_menor_consumo}")
print(tabela_info_menor_consumo[[
    'Média', 'Mediana', 'Desvio Padrão', 'Assimetria', 'Existência de Outliers']])
print(verificar_outliers(dados_parque_menor_consumo))


# Construir tabelas de frequências para os parques com maior e menor consumo
tabela_frequencia_maior = construir_tabela_frequencia(dados_parque_maior_consumo['Consumption'],
                                                      num_classes=5).reset_index(drop=True)
tabela_frequencia_menor = construir_tabela_frequencia(dados_parque_menor_consumo['Consumption'],
                                                      num_classes=5).reset_index(drop=True)

print(
    f"\nTabela de Frequência - Parque com Maior Consumo: {parque_maior_consumo}")
print(tabela_frequencia_maior[[
    'Intervalos de Consumo', 'Freq. abs.', 'Freq. rel.']])


print(
    f"\nTabela de Frequência - Parque com Menor Consumo: {parque_menor_consumo}")
print(tabela_frequencia_menor[[
    'Intervalos de Consumo', 'Freq. abs.', 'Freq. rel.']])

# Histograma do parque com maior consumo
plt.figure(figsize=(10, 5))
plt.subplot(1, 2, 1)
plt.hist(dados_parque_maior_consumo['Consumption'],
         bins=10, color='blue', alpha=0.7)
plt.title(f'Histograma do parque com maior consumo - {parque_maior_consumo}')
plt.xlabel('Consumo de Água (m3)')
plt.ylabel('Frequência (Número de Registos)')
plt.subplot(1, 2, 2)

# Histograma do parque com menor consumo
plt.hist(dados_parque_menor_consumo['Consumption'],
         bins=10, color='red', alpha=0.7)
plt.title(f'Histograma do parque com menor consumo- {parque_menor_consumo}')
plt.xlabel('Consumo de Água (m3)')
plt.ylabel('Frequência (Número de Registos)')
plt.tight_layout()
plt.show()
