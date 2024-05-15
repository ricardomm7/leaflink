import pandas as pd
import matplotlib.pyplot as plt
from tabulate import tabulate

# Importar o arquivo CSV
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

# Calcular o número total de Inqueridos em cada faixa etária
total_users_by_age = df.groupby('Age')['Y/N'].count()

# Calcular o número de Inqueridos em cada faixa etária que responderam "Sim"
users_recommending_by_age = df[df['Y/N'] == 'Y'].groupby('Age')['Y/N'].count()

# Calcular a proporção de Inqueridos em cada faixa etária que responderam "Sim"
proportion_recommendations_by_age = round(users_recommending_by_age / total_users_by_age,2)

# Imprimir as proporções em uma tabela formatada
print("\nProporção de utilizadores em cada grupo etário que recomendam o parque:")
table_data = []
for age, proportion in proportion_recommendations_by_age.items():
    table_data.append([age, proportion])
print(tabulate(table_data, headers=['Idade', 'Proporção'], tablefmt='grid'))

# plt.figure(dpi=300)

# Criar boxplot para a frequência mensal de uso do parque por grupo de idade, incluindo outliers
df.boxplot(column='Visits', by='Age', grid=True, showfliers=True)  # showfliers=True indica outliers
plt.xlabel("Idade")  # Legenda do eixo x
plt.ylabel("Visitas")  # Legenda do eixo y
plt.title("Frequência mensal de uso do parque por grupo de idade", pad=25, size=15)  # Título
plt.suptitle("") #Retira subtítulo
plt.tight_layout()  # Adiciona espaço

# Conclusões do boxplot
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
