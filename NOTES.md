# Notes for Robocode
#### Coordenadas do Mapa
- Mapa é uma matriz de tamanho `[mapa.height][mapa.width]` com começo na coordenada (0,0) que fica no canto inferior
esquerdo.
- Bússula: 
  - **0º** (Norte): aponta para cima;
  - **90º** (Este): aponta para a direita;
  - **180º** (Sul): aponta para baixo;
  - **270º** (Oeste): aponta para a esquerda.
#### Tempo:
- Unidade: ticks (t);
#### Distância:
- Unidade: Pixels (px);
#### Física:
- **aceleração (a)**: `1 px/t`
- **desaceleração**: `2 px/t`
- **velocidade (v)**: `v = a * t, (v <= 8 px/t)`
- **Distancia (d)**: `d = v * t`
#### Rotação:
- **Rotação da base**: `(10 - 0.75 * abs(v)) graus/t` (Nota: quanto mais rápido fores mais devagar rodas)
- **Rotação da arma**: `20 graus/t`
- **Rotação do radar**: `45 graus/t`
#### Balas
- **Dano**: `4 * poder de fogo` (se poder de fogo > 1, dano adicional: `2 * (poder - 1)`)
- **Velocidade**: `20 - 3 * poder de fogo`
- **Aquecimento da arma a disparar**: `1 + poder de fogo / 5` (se `aquecimento > 0` a arma está sobreaquecida e não pode
disparar. Ela começa sobreaquecida no início da ronda.)
- **Energia retornada quando acerta em alguém**: `3 * poder de fogo`
#### Colisões:
- **Contra um robô**: Cada robô leva 0.6 de dano
- **Contra uma parede**: robôs avançados levam `abs(v) * 0.5 - 1 (nunca < 0)`
