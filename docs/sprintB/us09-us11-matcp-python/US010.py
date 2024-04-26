import pandas as pd
import matplotlib.pyplot as plt

# Carregar os dados do arquivo EquipmentUsed.csv
caminho_arquivo = "EquipmentUsed.csv"
dados = pd.read_csv(caminho_arquivo, skiprows=1, header=None, names=["Equipment"]) #ignora a primeira linha do ficheiro

# Calcular o número de vezes que cada equipamento foi usado
contagem_equipamentos = dados["Equipment"].value_counts()

# Calcular a porcentagem de uso de cada equipamento
porcentagens = (contagem_equipamentos / len(dados)) * 100

# Criar o gráfico de pizza
plt.figure(figsize=(12, 12))
plt.pie(porcentagens, labels=porcentagens.index, autopct='%1.1f%%', startangle=160)
plt.title('Uso de Equipamentos no Parque')
plt.axis('equal')  # Faz com que o gráfico seja circular
plt.show()
