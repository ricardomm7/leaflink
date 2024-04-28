import pandas as pd
import matplotlib.pyplot as plt
from tabulate import tabulate

# Carregar o arquivo CSV
df = pd.read_csv('Inquiry.csv', delimiter=';')

# Adicionar uma coluna 'Age' com base no valor de 'Escalao'
df['Age'] = df['Escalao'].apply(lambda x: 'Criança' if x == 1 else ('Adulto' if x == 2 else 'Sênior'))

# Definir o tipo de variável para cada coluna
variable_types = {
    ' - Escalão': 'Variável Quanlitativa Ordinal',
    ' - Y/N': 'Variável Qualitativa Nominal',
    ' - Visits': 'Variável Quantitativa Discreta'
}
print("Tipo de variável para cada uma das três questões:")
for column, variable_type in variable_types.items():
    print(f"{column}: {variable_type}")

# Calcular o número total de recomendações e recomendações por faixa etária
total_recommendations = df['Y/N'].eq('Y').sum()
recommendations_by_age = df.groupby('Age')['Y/N'].apply(lambda x: (x == 'Y').sum())

# Calcular a proporção de recomendações para cada faixa etária
proportion_recommendations = round(recommendations_by_age / total_recommendations,2)

# Imprimir as proporções em uma tabela formatada
print("\nProporção de utilizadores em cada grupo etário que recomendam o parque:")
table_data = []
for age, proportion in proportion_recommendations.items():
    table_data.append([age, proportion])
print(tabulate(table_data, headers=['Idade', 'Proporção'], tablefmt='grid'))

# Criar boxplot para a frequência mensal de uso do parque por grupo de idade, incluindo outliers
df.boxplot(column='Visits', by='Age', grid=True, showfliers=True)  # showfliers=True indica outliers
plt.xlabel("Idade")  # Legenda do eixo x
plt.ylabel("Visitas")  # Legenda do eixo y
plt.title("Frequência mensal de uso do parque por grupo de idade")  # Título
plt.suptitle("") #Retira subtítulo
plt.tight_layout()  # Adiciona espaço

# Adicionar conclusões revisadas
conclusions = """
Conclusões:
- Os Idosos tendem a ter uma mediana mais alta na frequência de uso do parque, sugerindo que visitam o parque com mais 
  frequência relativamente aos adultos e crianças.
- As crianças têm uma pequena variabilidade na frequência de uso, como indicado pela variância dos dados e pela amplitude 
  interquartil reduzida.
- Os idosos têm uma distribuição mais variada na frequência de uso e maior variação no intervalo interquartis, indicando
  uma inconsistência na frequência de visitas.
- Em geral, parece haver uma tendência de aumento na frequência de uso do parque com a idade, com idosos a visitar o 
  parque com mais frequência em média, seguidos pelos adultos e depois pelas crianças.
- A discrepância de dados entre os idosos e os restantes utilizadores pode ser explicada pelo  facto de existir um maior
  registo de dados dos idosos, isto pode ser esperado uma vez que estes possuem uma frequencia de visita maior
  comparativmente ás crianças e aos adultos.
"""
print(conclusions)

plt.show()

# Calcular número de registros por grupo etário
count_per_age_group = df['Age'].value_counts()

# Imprimir o número de registros por grupo etário
print("\nNúmero de registros por grupo etário:")
print(count_per_age_group)
