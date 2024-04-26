import pandas as pd
import matplotlib.pyplot as plt

# Carregar o arquivo CSV
df = pd.read_csv('Inquiry.csv', delimiter=';')

# Adicionar uma coluna 'Age' com base no valor de 'Escalao'
df['Age'] = df['Escalao'].apply(lambda x: 'Child' if x == 1 else ('Adult' if x == 2 else 'Senior'))

# Calcular a proporção de usuários de cada grupo de idade que recomendam o parque
proportion_recommendations = df.groupby('Age')['Y/N'].apply(lambda x: round((x == 'Y').mean(), 2))

# Definir o tipo de variável para cada coluna
variable_types = {
    'Escalão': 'Qualitativa Ordinal',
    'Y/N': 'Qualitativa Nominal',
    'Visits': 'Quantitativa Discreta'
}
print("Tipo de variável para cada uma das três perguntas:")
for column, variable_type in variable_types.items():
    print(f"{column}: {variable_type}")

# Reformatar a impressão das proporções em uma tabela
print("\nProporção de usuários de cada grupo de idade que recomendam o parque:")
print("Escalão |||| Proporção")
print("-" * 20)
for age, proportion in proportion_recommendations.items():
    print(f"{age} |||| {proportion}")
print("-" * 20)

# Criar boxplot para a frequência mensal de uso do parque por grupo de idade
df.boxplot(column='Visits', by='Age', grid=False)
plt.show()

