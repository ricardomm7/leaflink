import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from tabulate import tabulate

# Função para calcular o custo da água para um determinado consumo
def calcular_custo_agua(consumo):
    taxa = 0.15
    limite_consumo = 1000
    preco_base = 0.7

    if consumo <= limite_consumo:
        return consumo * preco_base
    else:
        custo_ate_limite = limite_consumo * preco_base
        consumo_excedente = consumo - limite_consumo
        preco_com_taxa = preco_base + (preco_base * taxa)
        custo_excedente = consumo_excedente * preco_com_taxa
        return custo_ate_limite + custo_excedente

# Lê o arquivo CSV em um DataFrame
caminho_arquivo = "water_consumption_updated.csv"
dados = pd.read_csv(caminho_arquivo, sep=';', decimal=',')

# Agrupar os dados pelo parque, ano e mês e calcular o consumo mensal para cada parque
consumo_mensal_por_parque = {}
for (parque, ano, mes), grupo in dados.groupby(['Park', 'Year', 'Month']):
    consumo_total_mes = grupo['Consumption'].sum()
    if parque in consumo_mensal_por_parque:
        if (ano, mes) in consumo_mensal_por_parque[parque]:
            consumo_mensal_por_parque[parque][(ano, mes)] += consumo_total_mes
        else:
            consumo_mensal_por_parque[parque][(ano, mes)] = consumo_total_mes
    else:
        consumo_mensal_por_parque[parque] = {(ano, mes): consumo_total_mes}

# Lista para armazenar os custos médios mensais por parque
custos_mensais = []

# Calcula o custo médio mensal para cada parque
for parque, consumos_mensais in consumo_mensal_por_parque.items():
    consumo_mensal_total = sum(consumos_mensais.values())
    custo_medio_mensal = calcular_custo_agua(consumo_mensal_total)
    area = dados[dados['Park name'] == parque]['Area'].iloc[0]  # Obter área do parque
    custos_mensais.append({'Park name': parque, 'Area': area, 'Custo Medio Mensal': round(custo_medio_mensal, 2)})

# Converte a lista de custos mensais em um DataFrame
custos_medios_mensais = pd.DataFrame(custos_mensais)

# Verifica se há áreas ausentes e remove esses registros
custos_medios_mensais = custos_medios_mensais.dropna(subset=['Area'])

# Função para imprimir a tabela formatada
def imprimir_tabela_formatada(tabela):
    print(tabulate(tabela, headers='keys', tablefmt='grid', showindex=False))

# Imprime o custo médio mensal para cada parque de forma formatada
print("Custo médio mensal para cada parque:")
imprimir_tabela_formatada(custos_medios_mensais)

# Definir as variáveis independentes (X) e dependentes (y)
X = custos_medios_mensais['Area'].values
y = custos_medios_mensais['Custo Medio Mensal'].values

# Calcular a regressão linear manualmente
def regressao_linear_manual(X, y):
    n = len(X)
    xy = X * y
    xx = X * X

    # Coeficientes da regressão linear
    a = (n * np.sum(xy) - np.sum(X) * np.sum(y)) / (n * np.sum(xx) - np.sum(X) * np.sum(X))
    b = (np.sum(y) - a * np.sum(X)) / n

    return a, b

a, b = regressao_linear_manual(X, y)

# Imprimir na consola a equação da regressão linear
print(f"\nEquação da regressão linear: Custo = {b:.2f} + {a:.2f} * Área")

# Prever o custo médio mensal para um parque de 55 hectares
area_novo_parque = 55
custo_previsto = a * area_novo_parque + b

print(f"\nO custo médio mensal previsto para um parque com 55 hectares é: {custo_previsto:.2f}€")

# Gráfico da regressão linear
plt.figure(dpi= 70, figsize=(16,16))
plt.scatter(X, y, color='blue', label='Dados reais')
plt.plot(X, a * X + b, color='red', label='Linha de regressão')
plt.scatter([area_novo_parque], [custo_previsto], color='green', label=f'Previsão para 55 ha')
plt.xlabel('Área (hectares)')
plt.ylabel('Custo Médio Mensal (€)')
plt.title('Gráfico Regressão Linear: Custo Médio Mensal por Área')
plt.legend()

# Adiciona o nome dos parques aos pontos no gráfico
for i, parque in enumerate(custos_medios_mensais['Park name']):
    plt.annotate(parque, (X[i], y[i]), textcoords="offset points", xytext=(0,7), ha='center')

plt.show()
