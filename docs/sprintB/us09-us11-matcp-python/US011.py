import pandas as pd
import matplotlib.pyplot as plt
from tabulate import tabulate


# Carregar o arquivo CSV
df = pd.read_csv('Inquiry.csv', delimiter=';')

# Adicionar uma coluna 'Age' com base no valor de 'Escalao'
df['Age'] = df['Escalao'].apply(lambda x: 'Child' if x == 1 else ('Adult' if x == 2 else 'Senior'))

# Calcular a proporção de usuários de cada grupo de idade que recomendam o parque
proportion_recommendations = df.groupby('Age')['Y/N'].apply(lambda x: round((x == 'Y').mean(), 2))

# Definir o tipo de variável para cada coluna
variable_types = {
    ' - Child, Adult, Senior': 'Qualitative Ordinal',
    ' - Y/N': 'Qualitative Nominal',
    ' - Visits': 'Quantitative Discrete'
}
print("Type of variable for each of the three questions:")
for column, variable_type in variable_types.items():
    print(f"{column}: {variable_type}")

# Imprimir as proporções em uma tabela formatada
print("\nProportion of users in each age group who recommend the park:")
table_data = []
for age, proportion in proportion_recommendations.items():
    table_data.append([age, proportion])
print(tabulate(table_data, headers=['Age', 'Proportion'], tablefmt='grid'))

# Criar boxplot para a frequência mensal de uso do parque por grupo de idade
df.boxplot(column='Visits', by='Age', grid=True)
plt.tight_layout()  # Adiciona espaçamento
plt.show()

