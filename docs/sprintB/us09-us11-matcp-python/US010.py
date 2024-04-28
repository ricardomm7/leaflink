import pandas as pd
import matplotlib.pyplot as plt

# Carregar os dados do arquivo EquipmentUsed.csv
caminho_arquivo = "EquipmentUsed.csv"
dados = pd.read_csv(caminho_arquivo, skiprows=1, header=None, names=["Equipment"]) #ignora a primeira linha do ficheiro

# Calcular o número de vezes que cada equipamento foi usado
contagem_equipamentos = dados["Equipment"].value_counts()

# Calcular a percentagem de uso de cada equipamento
percentagens = (contagem_equipamentos / len(dados))*100

# Criar o gráfico de pizza
plt.figure(dpi=300)
plt.pie(percentagens, labels=percentagens.index, autopct='%1.1f%%', startangle=160)
plt.title('Gráfico percentual do uso de equipamento no parque', pad=25, size=15)
plt.axis('equal')  # Faz com que o gráfico seja circular
plt.tight_layout()  # Adiciona espaçamento
plt.show()
