import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from tabulate import tabulate
from scipy import stats

# Função para calcular o custo da água para um determinado consumo
def calcular_custo_agua(consumo):
    TAXA = 0.15
    LIMITE_CONSUMO = 1000
    PRECO_BASE = 0.7
    
    if consumo < 0:
        raise ValueError("Consumo must be a positive number.")

    if consumo <= LIMITE_CONSUMO:
        return consumo * PRECO_BASE
    else:
        return consumo * PRECO_BASE + (consumo - LIMITE_CONSUMO) * (PRECO_BASE*TAXA)

# Lê o arquivo CSV com os dados de consumo de água
caminho_arquivo_consumo = "water_consumption_updated.csv"
dados_consumo = pd.read_csv(caminho_arquivo_consumo, sep=';', decimal=',')

# Lê o arquivo CSV com as áreas dos parques
caminho_arquivo_areas = "Area.csv"
dados_areas = pd.read_csv(caminho_arquivo_areas, sep=';', decimal=',')

# Converte a lista de áreas em um dicionário para fácil acesso
areas_dict = pd.Series(dados_areas.Area.values, index=dados_areas.Park).to_dict()

# Função para calcular a média mensal de consumo analiticamente
def calcular_media_mensal_analitica(dados_consumo):
    # Agrupar por parque, ano e mês e somar os consumos
    consumo_mensal = dados_consumo.groupby(['Park', 'Year', 'Month'])['Consumption'].sum().reset_index()

    # Calcular a soma total e o número de meses para cada parque
    consumo_total_por_parque = {}
    meses_por_parque = {}

    for _, row in consumo_mensal.iterrows():
        parque = row['Park']
        consumo = row['Consumption']
        
        if parque not in consumo_total_por_parque:
            consumo_total_por_parque[parque] = 0
            meses_por_parque[parque] = 0

        consumo_total_por_parque[parque] += consumo
        meses_por_parque[parque] += 1

    # Calcular a média mensal manualmente
    media_mensal_consumo = [{'Park': parque, 'Consumption': consumo_total_por_parque[parque] / meses_por_parque[parque]}
                            for parque in consumo_total_por_parque]
    return pd.DataFrame(media_mensal_consumo)

# Calcular a média mensal de consumo para cada parque
media_mensal_consumo = calcular_media_mensal_analitica(dados_consumo)

# Lista para armazenar os custos médios mensais por parque
custos_mensais = []

# Calcula o custo médio mensal para cada parque
for _, row in media_mensal_consumo.iterrows():
    parque = row['Park']
    consumo_medio_mensal = row['Consumption']
    custo_medio_mensal = calcular_custo_agua(consumo_medio_mensal)
    area = areas_dict.get(parque)  # Obter área do parque
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
    X = np.array(X)
    y = np.array(y)
    n = X.size
    xy = X * y
    xx = np.square(X)
    sum_X = np.sum(X)
    sum_y = np.sum(y)
    sum_xy = np.sum(xy)
    sum_xx = np.sum(xx)

    a = (n * sum_xy - sum_X * sum_y) / (n * sum_xx - sum_X * sum_X)
    b = (sum_y - a * sum_X) / n

    return a, b

a, b = regressao_linear_manual(X, y)

# Imprimir na consola a equação da regressão linear
print(f"\nEquação da regressão linear: Custo = {b:.2f} + {a:.2f} * Área")

# Calcular o coeficiente de correlação
correlacao = np.corrcoef(X, y)[0, 1]
print(f"\nCoeficiente de correlação: {correlacao:.2f}")

# Caracterizar a correlação
if correlacao == 1:
    caracterizacao = "perfeita positiva"
elif 0.8 <= correlacao < 1:
    caracterizacao = "forte positiva"
elif 0.5 <= correlacao < 0.8:
    caracterizacao = "moderada positiva"
elif 0.1 <= correlacao < 0.5:
    caracterizacao = "fraca positiva"
elif 0 < correlacao < 0.1:
    caracterizacao = "ínfima positiva"
elif correlacao == 0:
    caracterizacao = "nula"
elif -0.1 < correlacao < 0:
    caracterizacao = "ínfima negativa"
elif -0.5 <= correlacao <= -0.1:
    caracterizacao = "fraca negativa"
elif -0.8 <= correlacao <= -0.5:
    caracterizacao = "moderada negativa"
elif -1 < correlacao <= -0.8:
    caracterizacao = "forte negativa"
elif correlacao == -1:
    caracterizacao = "perfeita negativa"

print(f"\nCaracterização da correlação: {caracterizacao}")

# Prever o custo médio mensal para um parque de 55 hectares
area_novo_parque = 55
custo_previsto = a * area_novo_parque + b

# Calcular o intervalo de confiança de 95%
# Erro padrão da estimativa
y_pred = a * X + b
residuals = y - y_pred
s_err = np.sqrt(np.sum(residuals**2) / (len(y) - 2))
mean_x = np.mean(X)
t_value = stats.t.ppf(1 - 0.025, df=len(y) - 2)  # valor t para um intervalo de confiança de 95%
conf_interval = t_value * s_err * np.sqrt(1/len(X) + (area_novo_parque - mean_x)**2 / np.sum((X - mean_x)**2))

valor_min = custo_previsto - conf_interval
valor_max = custo_previsto + conf_interval
print(f"\nCom um grau de confiança de 95%, o custo médio mensal previsto para um parque com 55 hectares é esperado estar no intervalo: [{valor_min:.2f}€, {valor_max:.2f}€].")

# Gráfico da regressão linear
plt.figure(dpi=300, figsize=(10,7))
plt.scatter(X, y, color='blue', label='Dados reais')
plt.plot(X, a * X + b, color='red', label='Linha de regressão')
plt.scatter([area_novo_parque], [custo_previsto], color='green', label=f'Previsão para 55 ha')
plt.errorbar(area_novo_parque, custo_previsto, yerr=conf_interval, fmt='o', color='green', capsize=5)
plt.xlabel('Área (hectares)')
plt.ylabel('Custo Médio Mensal (€)')
plt.title('Gráfico Regressão Linear: Custo Médio Mensal por Área')
plt.legend()

# Adiciona o nome dos parques aos pontos no gráfico
for i, parque in enumerate(custos_medios_mensais['Park name']):
    plt.annotate(parque, (X[i], y[i]), textcoords="offset points", xytext=(0,7), ha='center')

plt.tight_layout()
plt.show()
